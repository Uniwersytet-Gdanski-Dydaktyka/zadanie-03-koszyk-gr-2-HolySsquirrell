package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.promotions.Promotion;

public class ShoppingCart {

    private List<Product> products = new ArrayList<>();
    private List<Promotion> promotions = new ArrayList<>();

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
            products = ProductSorter.sortDefault(products);
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

    public static Product getCheapest(List<Product> products) {
        return products.stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    public static Product getMostExpensive(List<Product> products) {
        return products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    public List<Product> getNMostExpensive(int n) {
    return ProductSorter.byPriceDesc().topN(products, n);
    }

    public List<Product> getNCheapest(int n) {
        return ProductSorter.byPriceAsc().topN(products, n);
    }

    public double getBestPrice() {
        if (promotions.isEmpty()) {
            return getTotalPrice();
        }

        List<List<Promotion>> permutations = generatePermutations(promotions);

        double bestPrice = Double.MAX_VALUE;

        for (List<Promotion> order : permutations) {

            List<Product> copiedProducts = products.stream()
                    .map(Product::copy)
                    .collect(java.util.stream.Collectors.toList());

            for (Product p : copiedProducts) {
                p.setDiscountPrice(p.getPrice());
            }

            for (Promotion promo : order) {
                promo.apply(copiedProducts);
            }

            double total = copiedProducts.stream()
                    .mapToDouble(Product::getDiscountPrice)
                    .sum();

            if (total < bestPrice) {
                bestPrice = total;
            }
        }

        return bestPrice;
    }
    private List<List<Promotion>> generatePermutations(List<Promotion> list) {
        List<List<Promotion>> result = new ArrayList<>();
        permute(list, 0, result);
        return result;
    }

    private void permute(List<Promotion> list, int start, List<List<Promotion>> result) {
        if (start == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            swap(list, start, i);
            permute(list, start + 1, result);
            swap(list, start, i);
        }
    }

    private void swap(List<Promotion> list, int i, int j) {
        Promotion temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
