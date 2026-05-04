package com.example;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.promotions.CouponPromotion;
import com.example.promotions.FreeMugPromotion;
import com.example.promotions.StaticValuePromotion;
import com.example.promotions.TotalDiscountPromotion;

public class BestPromotionTest {

    @Test
    void shouldChooseBestPromotionOrder() {
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("1", "Laptop", 200));
        cart.addProduct(new Product("2", "Mouse", 110));
        // suma = 310

        cart.addPromotion(new StaticValuePromotion("1")); // -20
        cart.addPromotion(new FreeMugPromotion());
        cart.addPromotion(new TotalDiscountPromotion()); // -5% >300
        cart.addPromotion(new CouponPromotion("1"));     // -30% 

        double bestPrice = cart.getBestPrice();

        // Best case
        // 310 -> TotalDiscountPromotion -> 294.5(190+104.5) -> FreeMugPromotion ->
        // 294.5(190+104.5+0) -> CouponPromotion -> 237.5(133+104.5+0) -> StaticValuePromotion ->
        // 217.5(113+104.5+0)

        assertEquals(217.5, bestPrice, 0.01);
    }
}