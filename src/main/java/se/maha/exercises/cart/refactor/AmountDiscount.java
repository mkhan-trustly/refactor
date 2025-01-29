package se.maha.exercises.cart.refactor;

import java.math.BigDecimal;

public class AmountDiscount implements Discount {

    private final String name;
    private final Integer discount;

    public AmountDiscount(String name, Integer discount) {
        this.name = name;
        this.discount = discount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal apply(BigDecimal price) {
        return price.subtract(BigDecimal.valueOf(discount));
    }
}
