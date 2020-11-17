package com.alirahal.template.config;

import com.alirahal.template.database.TimestampsMixIn;
import com.alirahal.template.model.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Define all custom ObjectMapper instances here.
 */
@Getter
@AllArgsConstructor
public enum MapperFactory {

    SIMPLE_MAPPER(ObjectMapper::new),

    // This is a dummy mapper to show how the configuration works
    IGNORE_TIMESTAMPS_MAPPER(
            new ObjectMapperProvider() {
                @Override
                public ObjectMapper provide() {
                    return SIMPLE_MAPPER.provide();
                }

                @Override
                public ObjectMapper provide(Class clazz) {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.addMixIn(clazz, TimestampsMixIn.class);
                    return mapper;
                }
            }
    ),
    OFFSET_DATE_TIME_MAPPER(
            () -> {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                SimpleModule simpleModule = new SimpleModule();
                simpleModule.addSerializer(OffsetDateTime.class, new JsonSerializer<>() {
                    @Override
                    public void serialize(OffsetDateTime offsetDateTime, JsonGenerator jsonGenerator,
                                          SerializerProvider serializerProvider) throws IOException {
                        jsonGenerator.writeString(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(offsetDateTime));
                    }
                });
                mapper.registerModule(simpleModule);

                return mapper;
            }
    );

    private final ObjectMapperProvider provider;


    public ObjectMapper provide() {
        return provider.provide();
    }

    public ObjectMapper provide(Class clazz) {
        return provider.provide(clazz);
    }

    interface ObjectMapperProvider {
        ObjectMapper provide();

        default ObjectMapper provide(Class clazz) {
            return null;
        }

    }

}