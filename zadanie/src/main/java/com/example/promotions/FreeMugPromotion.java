package com.example.promotions;

import java.util.List;

import com.example.Product;

public class FreeMugPromotion implements Promotion{
    
    private static final String MUG_CODE = "MUG";

    @Override
    public void apply(List<Product> products) {
        double sum = products.stream()
                .mapToDouble(Product::getDiscountPrice)
                .sum();
        
        boolean mugAlreadyExists = products.stream()
                .anyMatch(p -> p.getCode().equals(MUG_CODE));

        if (sum > 200 && !mugAlreadyExists) {
            products.add(new Product(MUG_CODE, "Mug", 0));
        }
    }
}
