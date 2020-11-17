package com.alirahal.template.services;

import com.alirahal.template.config.MapperFactory;
import com.alirahal.template.database.BaseEntity;
import com.alirahal.template.error.exceptions.NotFoundException;
import com.alirahal.template.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RestService<Model extends BaseEntity, Repository extends CrudRepository<Model, UUID>> {

    @Autowired
    Repository repository;

    public List<Model> getAll() {
        Iterable<Model> iterable = repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    public Model get(UUID id) throws NotFoundException {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Model create(Model model) {
        Model newModel = repository.save(model);
        return newModel;
    }

    public Model update(UUID id, JsonNode updateData) throws NotFoundException, IOException {
        Model toUpdate = repository.findById(id).orElseThrow(NotFoundException::new);
        ObjectMapper mapper = MapperFactory.IGNORE_TIMESTAMPS_MAPPER.provide(Product.class);
        mapper.readerForUpdating(toUpdate).readValue(updateData);
        repository.save(toUpdate);
        return toUpdate;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }


}
