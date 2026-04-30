package com.example.promotions;
import java.util.List;

import com.example.Product;

public class CouponPromotion implements Promotion{
    private final String productCode;

    public CouponPromotion(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public void apply(List<Product> products) {
        for (Product p : products) {
            if (p.getCode().equals(productCode)) {
                p.setDiscountPrice(p.getDiscountPrice() * 0.7);
                break;
            }
        }
    }
}
