package se.maha.exercises.cart.refactor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageDiscount implements Discount {

    private final String name;
    private final Integer discount;

    public PercentageDiscount(String name, Integer discount) {
        this.name = name;
        this.discount = discount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal apply(BigDecimal price) {
        BigDecimal discountPercent = BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal discountAmount = price.multiply(discountPercent);

        return price.subtract(discountAmount).max(BigDecimal.ZERO);
    }
}
