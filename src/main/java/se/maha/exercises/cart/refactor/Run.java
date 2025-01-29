package se.maha.exercises.cart.refactor;

import java.math.BigDecimal;

public class Run {

    public static void main(String[] args) {

        Discount cartDiscount = new PercentageDiscount("10% off!", 10);
        Cart cart = new Cart(cartDiscount);

        BigDecimal price = new BigDecimal("59.00");
        CartItem firstItem = new CartItem("Shampoo", price, new AmountDiscount("25% discount", 25));
        cart.addToCart(firstItem);

        BigDecimal total = cart.calculateTotalAmount();
        System.out.println(total);
    }
}
