package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.promotions.Buy2Get1Promotion;
import com.example.promotions.CouponPromotion;
import com.example.promotions.TotalDiscountPromotion;

public class PromotionsTest {
    
    @Test
    void shouldHandlePromotionOnEmptyCart() {
        ShoppingCart cart = new ShoppingCart();

        cart.addPromotion(new TotalDiscountPromotion());
        cart.applyPromotions();

        assertEquals(0, cart.getTotalPrice());
    }

    @Test
    void shouldApply5PercentDiscountOver300() {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("1", "Laptop", 400));

        cart.addPromotion(new TotalDiscountPromotion());
        cart.applyPromotions();

        double total = cart.getTotalPrice();

        assertEquals(380, total, 0.01);
    }

    @Test
    void shouldNotApplyDiscountBelow300() {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("1", "Mouse", 100));

        cart.addPromotion(new TotalDiscountPromotion());
        cart.applyPromotions();

        assertEquals(100, cart.getTotalPrice());
    }

    @Test
    void shouldApplyBuy2Get1Free() {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("1", "A", 100));
        cart.addProduct(new Product("2", "B", 50));
        cart.addProduct(new Product("3", "C", 30));

        cart.addPromotion(new Buy2Get1Promotion());
        cart.applyPromotions();

        double total = cart.getTotalPrice();

        assertEquals(150, total); // 100 + 50 + 0
    }

    @Test
    void shouldApplyCouponToSpecificProduct() {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("1", "Laptop", 1000));
        cart.addProduct(new Product("2", "Mouse", 100));

        cart.addPromotion(new CouponPromotion("1"));
        cart.applyPromotions();

        double total = cart.getTotalPrice();

        assertEquals(800, total, 0.01); // 700 + 100
    }
    
}
