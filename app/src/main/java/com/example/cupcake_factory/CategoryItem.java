package com.example.cupcake_factory;

public class CategoryItem {

    String categoryId;
    String categoryType;
    String CupCake;
    String Cost;

    public CategoryItem() {
    }

    public CategoryItem(String categoryId, String categoryType, String cupCake, String cost) {
        this.categoryId = categoryId;
        this.categoryType = categoryType;
        CupCake = cupCake;
        Cost = cost;
    }

    public CategoryItem(String id) {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCupCake() {
        return CupCake;
    }

    public void setCupCake(String cupCake) {
        CupCake = cupCake;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }
}
