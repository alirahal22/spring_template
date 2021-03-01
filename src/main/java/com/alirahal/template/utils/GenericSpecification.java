package com.alirahal.template.utils;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class GenericSpecification<Model> implements Specification<Model> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation() == null)
            throw new IllegalArgumentException("Invalid filter operator");
        switch (criteria.getOperation()) {
            case EQUAL:
                return criteriaBuilder.equal(
                        root.get(criteria.getKey()), criteria.getValue());
            case NOT_EQUAL:
                return criteriaBuilder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return criteriaBuilder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN_OR_EQUAL:
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue());
            case LESS_THAN:
                return criteriaBuilder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue());
            case LESS_THAN_OR_EQUAL:
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue());
            case LIKE:
                return criteriaBuilder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            default:
                throw new IllegalArgumentException("Invalid filter operator");
        }
    }
}