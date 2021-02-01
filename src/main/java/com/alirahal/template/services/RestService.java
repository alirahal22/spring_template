package com.alirahal.template.services;

import com.alirahal.template.config.MapperFactory;
import com.alirahal.template.database.BaseEntity;
import com.alirahal.template.error.exceptions.NotFoundException;
import com.alirahal.template.model.Product;
import com.alirahal.template.utils.HasLogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RestService<Model extends BaseEntity, Repository extends JpaRepository<Model, UUID>> implements HasLogger {

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

    public List<Model> getByPage(int pageNumber, int size, List<String> sortingParams) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(getOrdersList(sortingParams)));
        Page<Model> page = repository.findAll(pageable);
        return page.getContent();
    }

    public List<Model> getSorted(List<String> sortingParams) {
        List<Model> data = repository.findAll(Sort.by(getOrdersList(sortingParams)));
        return data;
    }

    private List<Sort.Order> getOrdersList(List<String> sortingParams) {
        Sort.Order dateOrder = new Sort.Order(Sort.Direction.ASC, "createDateTime");
        List<Sort.Order> ordersList = new ArrayList<>();
        for (String sortingFilter : sortingParams) {
            System.out.println(sortingFilter);
            if (sortingFilter.toLowerCase().contains(".asc") || sortingFilter.toLowerCase().contains(".desc")) {
                getLogger().info("Adding sorting filter to list: " + sortingFilter);
                String property = sortingFilter.split("\\.")[0];
                String direction = sortingFilter.split("\\.")[1];

                ordersList.add(new Sort.Order(Sort.Direction.fromString(direction), property));
            }
        }
        ordersList.add(dateOrder);
        return ordersList;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }


}
