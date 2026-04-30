package com.example;



import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

 
public class ShoppingCartTest {

    @Test
    void shouldHandleEmptyCart() {
        ShoppingCart cart = new ShoppingCart();

        assertEquals(0, cart.getTotalPrice());
    }

    @Test
    void shouldCalculateTotalPriceWithoutPromotions() {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("1", "Laptop", 1000));
        cart.addProduct(new Product("2", "Mouse", 100));

        double total = cart.getTotalPrice();

        assertEquals(1100, total);
    }
    
    @Test
    void shouldIgnoreNullProduct() {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(null);

        assertEquals(0, cart.getTotalPrice());
    }

}