package se.maha.exercises.cart.b4;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public String currency;
    public List<Item> items;
    public Discount discount;

    public Cart(String currency) {
        this(currency, null);
    }

    public Cart(String currency, List<Item> items) {
        this.currency = currency;
        this.items = items != null ? items : new ArrayList<>();

        for (Item item : this.items) {
            if (!item.currency.equals(currency)) {
                throw new IllegalArgumentException();
            }
        }
    }
}