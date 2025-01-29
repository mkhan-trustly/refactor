package se.maha.exercises.cart.refactor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<CartItem> cartItems = new ArrayList<>();
    private final Discount discount;

    public Cart(Discount discount) {
        this.discount = discount;
    }

    public void addToCart(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public BigDecimal calculateTotalAmount() {
        BigDecimal amountToPay = BigDecimal.ZERO;

        for (CartItem item : cartItems) {
            amountToPay = amountToPay.add(item.getDiscountedPrice());
        }

        if (discount != null && discount.apply(amountToPay).compareTo(amountToPay) > 0) {
            amountToPay = discount.apply(amountToPay);
        }
        return amountToPay.setScale(2, RoundingMode.HALF_UP);
    }
}
