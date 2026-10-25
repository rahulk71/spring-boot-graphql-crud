package com.example.graphqlcrud.controller;

import com.example.graphqlcrud.dto.ProductInput;
import com.example.graphqlcrud.dto.ProductUpdateInput;
import com.example.graphqlcrud.entity.Product;
import com.example.graphqlcrud.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    // ==================== GET (Query) ====================

    @QueryMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @QueryMapping
    public Product getProductById(@Argument Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // ==================== CREATE ====================

    @MutationMapping
    public Product createProduct(@Argument ProductInput input) {
        Product product = Product.builder()
                .name(input.name())
                .description(input.description())
                .price(input.price())
                .quantity(input.quantity())
                .build();
        return productRepository.save(product);
    }

    // ==================== UPDATE ====================

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductUpdateInput input) {
        return productRepository.findById(id)
                .map(existing -> {
                    if (input.name() != null) {
                        existing.setName(input.name());
                    }
                    if (input.description() != null) {
                        existing.setDescription(input.description());
                    }
                    if (input.price() != null) {
                        existing.setPrice(input.price());
                    }
                    if (input.quantity() != null) {
                        existing.setQuantity(input.quantity());
                    }
                    return productRepository.save(existing);
                })
                .orElse(null);
    }

    // ==================== DELETE ====================

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
