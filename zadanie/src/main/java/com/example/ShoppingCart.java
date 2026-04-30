package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.promotions.Promotion;

public class ShoppingCart {

    private List<Product> products = new ArrayList<>();
    private List<Promotion> promotions = new ArrayList<>();

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    public void addPromotion(Promotion promotion) {
        promotions.add(promotion);
    }

    public void applyPromotions() {
        for (Promotion promotion : promotions) {
            promotion.apply(products);
        }
    }

    public double getTotalPrice() {
        return products.stream()
                .mapToDouble(Product::getDiscountPrice)
                .sum();
    }
}
