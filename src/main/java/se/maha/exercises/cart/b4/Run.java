package se.maha.exercises.cart.b4;

import java.io.IOException;
import java.math.BigDecimal;

public class Run {
    public static void main(String[] args) throws IOException {
        // Create a cart.
        Cart cart = new Cart("GBP");

        // Create an item and add it to the cart.
        // This card reader costs £59 before a £25 discount.
        BigDecimal price = new BigDecimal("59.00");
        Discount discount = new Discount(new BigDecimal("25.00"), null);
        cart.items.add(new Item("Shampoo", price, "GBP", discount));

        // Add a 10% discount to the cart.
        cart.discount = new Discount("10% off!", null, new BigDecimal("0.1"));

        // Compute the cart's total.
        BigDecimal total = Util.calculateAmountToPayForCart(cart);

        System.out.println(total);
    }
}
