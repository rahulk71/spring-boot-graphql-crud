package com.example.graphqlcrud.dto;

public record ProductInput(
        String name,
        String description,
        Double price,
        Integer quantity
) {}
