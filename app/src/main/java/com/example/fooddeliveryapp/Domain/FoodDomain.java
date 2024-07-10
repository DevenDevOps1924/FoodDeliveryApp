package com.example.fooddeliveryapp.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private  String title;
    private String pic;
    private  String description;
    private  String fee;
    private int numberInCart;
    FoodDomain foodDomain = ; // assume you have a FoodDomain object

    ViewholderItem viewholderItem = new ViewholderItem(
            foodDomain.getItemId(),
            foodDomain.getTitle(),
            foodDomain.getPic(),
            foodDomain.getFee()
    );

    public FoodDomain(String title, String pic, String description, String fee) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
    }

    public FoodDomain(String title, String pic, String description, String fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFee() {
            return (String) fee;

    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

}
