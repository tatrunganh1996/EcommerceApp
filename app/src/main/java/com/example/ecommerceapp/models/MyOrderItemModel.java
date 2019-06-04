package com.example.ecommerceapp.models;

public class MyOrderItemModel {
    private int id;
    private int productImage;
    private int rating;
    private String productTitle;
    private String deliveryStatus;
    private String method;

    public MyOrderItemModel(int id, int productImage, int rating, String productTitle, String deliveryStatus, String method) {
        this.id = id;
        this.productImage = productImage;
        this.rating = rating;
        this.productTitle = productTitle;
        this.deliveryStatus = deliveryStatus;
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
