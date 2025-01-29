package se.maha.exercises.cart.b4;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {
    static BigDecimal calculateAmountToPayForCart(Cart cart) {
        BigDecimal amountToPay = BigDecimal.ZERO;

        for (Item item : cart.items) {
            amountToPay = amountToPay.add(item.price);
        }

        amountToPay = applyDiscount(cart.discount, amountToPay);

        return amountToPay.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal applyDiscount(Discount discount, BigDecimal amount) {
        BigDecimal discountAmount = BigDecimal.ZERO;

        if (discount.percentage != null) {
            discountAmount = amount.multiply(discount.percentage);
        }

        if (discount.amount != null) {
            discountAmount = amount;
        }

        return amount.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);
    }
}
