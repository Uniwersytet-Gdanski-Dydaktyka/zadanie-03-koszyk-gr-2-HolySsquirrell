package com.example.promotions;
import java.util.List;

import com.example.Product;

public class TotalDiscountPromotion implements Promotion {

    @Override
    public void apply(List<Product> products) {
        double sum = products.stream()
                .mapToDouble(Product::getDiscountPrice)
                .sum();

        if (sum > 300) {
            for (Product p : products) {
                p.setDiscountPrice(p.getDiscountPrice() * 0.95);
            }
        }
    }
}
