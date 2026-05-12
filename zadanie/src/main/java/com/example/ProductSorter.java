package com.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSorter {

    private final Comparator<Product> comparator;

    private ProductSorter(Comparator<Product> comparator) {
        this.comparator = comparator;
    }

    public static ProductSorter byPriceAsc() {
        return new ProductSorter(Comparator.comparing(Product::getPrice));
    }

    public static ProductSorter byPriceDesc() {
        return new ProductSorter(Comparator.comparing(Product::getPrice).reversed());
    }

    public static ProductSorter byNameAsc() {
        return new ProductSorter(Comparator.comparing(Product::getName));
    }

    public static ProductSorter byNameDesc() {
        return new ProductSorter(Comparator.comparing(Product::getName).reversed());
    }

    public static Comparator<Product> byPriceDescThenNameAsc() {
        return Comparator.comparing(Product::getPrice).reversed()
                .thenComparing(Product::getName);
    }

    public static List<Product> sortDefault(List<Product> products) {
        return products.stream()
                .sorted(byPriceDescThenNameAsc())
                .collect(Collectors.toList());
    }

    public static ProductSorter by(Comparator<Product> comparator) {
        return new ProductSorter(comparator);
    }

    public List<Product> sort(List<Product> products) {
        return products.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<Product> topN(List<Product> products, int n) {
        return products.stream()
                .sorted(comparator)
                .limit(n)
                .collect(Collectors.toList());
    }
}