package com.example.promotions;

import java.util.List;

import com.example.Product;

public interface Promotion {
    void apply(List<Product> products);
}
