package com.example.graphqlcrud.dto;

public record ProductUpdateInput(
        String name,
        String description,
        Double price,
        Integer quantity
) {}
