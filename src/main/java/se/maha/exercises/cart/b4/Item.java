package se.maha.exercises.cart.b4;

import java.math.BigDecimal;

public class Item {
    public String name;
    public BigDecimal price;
    public String currency;
    public Discount discount;

    public Item(String name, BigDecimal price, String currency, Discount discount) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.discount = discount;
    }
}