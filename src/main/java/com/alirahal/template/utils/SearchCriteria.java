package com.alirahal.template.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {

    private static HashMap<String, FilterOperation> operationsMap;

    static {
        operationsMap = new HashMap<>();

        operationsMap.put("eq", FilterOperation.EQUAL);
        operationsMap.put("ne", FilterOperation.NOT_EQUAL);

        operationsMap.put("lt", FilterOperation.LESS_THAN);
        operationsMap.put("lte", FilterOperation.LESS_THAN_OR_EQUAL);

        operationsMap.put("gt", FilterOperation.GREATER_THAN);
        operationsMap.put("gte", FilterOperation.GREATER_THAN_OR_EQUAL);

        operationsMap.put("like", FilterOperation.LIKE);
    }

    private String key;
    private FilterOperation operation;
    private String value;

    public void setOperation(String operation) {
        this.operation = operationsMap.get(operation);
    }
}