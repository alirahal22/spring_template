package com.alirahal.template.services;

import com.alirahal.template.database.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

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

    public Model get(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public Model create(Model model) {
        Model newModel = repository.save(model);
        return newModel;
    }

    public Model update(UUID id, Model updateData) {
        updateData.setId(id);
        repository.save(updateData);
        Model updatedObject = repository.findById(id).orElseThrow();
        return updatedObject;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }


}
