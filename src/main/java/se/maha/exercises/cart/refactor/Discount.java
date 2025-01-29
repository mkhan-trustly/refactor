package se.maha.exercises.cart.refactor;

import java.math.BigDecimal;

public interface Discount {

    String getName();
    BigDecimal apply(BigDecimal price);
}
