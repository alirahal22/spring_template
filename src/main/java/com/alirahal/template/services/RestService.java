package com.alirahal.template.services;

import com.alirahal.template.config.MapperFactory;
import com.alirahal.template.database.BaseEntity;
import com.alirahal.template.error.exceptions.NotFoundException;
import com.alirahal.template.model.Product;
import com.alirahal.template.utils.FilterOperation;
import com.alirahal.template.utils.GenericSpecification;
import com.alirahal.template.utils.HasLogger;
import com.alirahal.template.utils.SearchCriteria;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.server.MethodNotAllowedException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RestService<Model extends BaseEntity, Repository extends JpaRepository<Model, UUID>> implements HasLogger {

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    @Autowired
    Repository repository;

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

    private List<Model> getByPage(int pageNumber, int size, List<Sort.Order> sortingParams) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sortingParams));
        Page<Model> page = repository.findAll(pageable);
        return page.getContent();
    }

    private List<Model> getSorted(List<Sort.Order> sortingParams) {
        List<Model> data = repository.findAll(Sort.by(sortingParams));
        return data;
    }

    private List<Sort.Order> getOrdersList(List<String> sortingParams) {
        Sort.Order dateOrder = new Sort.Order(Sort.Direction.ASC, "createDateTime");
        List<Sort.Order> ordersList = new ArrayList<>();
        for (String sortingFilter : sortingParams) {
            getLogger().info(sortingFilter);
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

    /**
     * @noinspection unchecked
     */
    private List<Model> getByFilters(Specification filterSpecifications, List<Sort.Order> sortingParams, Integer page,
                                     Integer pageSize)
            throws NotFoundException {
        if (filterSpecifications == null)
            return getByPage(page, pageSize, sortingParams);
        if (repository instanceof JpaSpecificationExecutor) {
            getLogger().info("You can use filters");
            return ((JpaSpecificationExecutor<Model>) repository).findAll(filterSpecifications);
        }
        getLogger().info("You can't use filters");
        throw new NotFoundException();
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<Model> getWithFilters(Map<String, String> queryParams, List<String> sortingParams) throws
            NotFoundException {
        Integer page = null, pageSize = DEFAULT_PAGE_SIZE;
        if (queryParams == null || (queryParams.isEmpty() && sortingParams == null)) {
            getLogger().info("Getting all");
            Iterable<Model> iterable = repository.findAll();
            return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        }
        if (queryParams.containsKey("page")) {
            getLogger().info("Setting page params");
            try {
                page = Integer.parseInt(queryParams.get("page"));
                pageSize = queryParams.containsKey("page_size") ?
                        Integer.parseInt(queryParams.get("page_size")) : DEFAULT_PAGE_SIZE;
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        if (queryParams.keySet().size() == 1 && sortingParams != null) {
            getLogger().info("Getting sorted");
            return getSorted(getOrdersList(sortingParams));
        }
        return getByFilters(buildFilters(queryParams), getOrdersList(sortingParams), page, pageSize);
    }

    private Specification buildFilters(Map<String, String> queryParams) {
        Specification<Model> specification = null;
        for (String property : queryParams.keySet()) {
            if (property.equals("page") || property.equals("page_size") || property.equals("sort"))
                continue;
            getLogger().info(property);
            String filterString = queryParams.get(property);
            if (!filterString.contains(":"))
                throw new IllegalArgumentException("Invalid filter string format");

            SearchCriteria criteria = new SearchCriteria();
            criteria.setKey(property);
            criteria.setOperation(filterString.split(":")[0]);
            criteria.setValue(filterString.split(":")[1]);
            getLogger().info("Adding filter: " + property + " " + criteria.getOperation() + " " + filterString.split
                    (":")[1]);
            if (specification == null) {
                getLogger().info("Creating the criteria");
                specification = new GenericSpecification<>(criteria);
            } else {
                getLogger().info("Adding to existing criteria");
                specification = specification.and(new GenericSpecification<>(criteria));
            }
        }

        return specification;
    }
}