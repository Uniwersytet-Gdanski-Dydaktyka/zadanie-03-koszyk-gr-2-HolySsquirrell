package com.example.promotions;
import java.util.Comparator;
import java.util.List;

import com.example.Product;

public class Buy2Get1Promotion implements Promotion {
    
    @Override
    public void apply(List<Product> products) {
        if (products.size() < 3) return;

        Product cheapest = products.stream()
                .min(Comparator.comparing(Product::getDiscountPrice))
                .orElse(null);

        if (cheapest != null) {
            cheapest.setDiscountPrice(0);
        }
    }
}
