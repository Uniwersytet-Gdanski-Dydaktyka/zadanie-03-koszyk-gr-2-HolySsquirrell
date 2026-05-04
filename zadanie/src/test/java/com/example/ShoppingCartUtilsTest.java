package com.example;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ShoppingCartUtilsTest {

    @Test
    void shouldFindCheapestProduct() {
        List<Product> products = List.of(
                new Product("1", "A", 100),
                new Product("2", "B", 50),
                new Product("3", "C", 200)
        );

        Product cheapest = ShoppingCart.getCheapest(products);

        assertNotNull(cheapest);
        assertEquals("2", cheapest.getCode());
    }

    @Test
    void shouldReturnNullForEmptyList() {
        Product cheapest = ShoppingCart.getCheapest(List.of());

        assertNull(cheapest);
    }

    @Test
    void shouldReturnNCheapestProducts() {
        List<Product> products = List.of(
                new Product("1", "A", 100),
                new Product("2", "B", 50),
                new Product("3", "C", 200)
        );

        List<Product> result = ShoppingCart.getNCheapest(products, 2);

        assertEquals(2, result.size());
        assertEquals("2", result.get(0).getCode());
        assertEquals("1", result.get(1).getCode());
    }

    @Test
    void shouldHandleNGreaterThanSize() {
        List<Product> products = List.of(
                new Product("1", "A", 100)
        );

        List<Product> result = ShoppingCart.getNCheapest(products, 5);

        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmptyListForEmptyInput() {
        List<Product> result = ShoppingCart.getNCheapest(List.of(), 3);

        assertTrue(result.isEmpty());
    }
}