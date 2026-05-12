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
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("1", "A", 100));
        cart.addProduct(new Product("2", "B", 50));
        cart.addProduct(new Product("3", "C", 200));

        List<Product> result = cart.getNCheapest(2);

        assertEquals(2, result.size());
        assertEquals("2", result.get(0).getCode());
        assertEquals("1", result.get(1).getCode());
    }

    @Test
    void shouldHandleNGreaterThanSize() {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("1", "A", 100));
        List<Product> result = cart.getNCheapest(5);

        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmptyListForEmptyInput() {
        ShoppingCart cart = new ShoppingCart();
        List<Product> result = cart.getNCheapest(3);

        assertTrue(result.isEmpty());
    }
}