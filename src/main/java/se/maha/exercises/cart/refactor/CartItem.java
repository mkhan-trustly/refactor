package se.maha.exercises.cart.refactor;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CartItem {

    private final String name;
    private final BigDecimal price;
    private final Discount discount;

    public final BigDecimal getDiscountedPrice() {
        return discount.apply(price);
    }
}
