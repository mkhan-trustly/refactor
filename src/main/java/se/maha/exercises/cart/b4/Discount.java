package se.maha.exercises.cart.b4;

import java.math.BigDecimal;

public class Discount {
    public String name;
    public BigDecimal amount;
    public BigDecimal percentage;

    public Discount(BigDecimal amount, BigDecimal percentage) {
        this(null, amount, percentage);
    }

    public Discount(String name, BigDecimal amount, BigDecimal percentage) {
        this.name = name;
        this.amount = amount;
        this.percentage = percentage;

        if (amount == null) {
            if (percentage == null) {
                throw new IllegalArgumentException("Can't have percentage and money discount at the same time.");
            }
        } else {
            if (percentage != null) {
                throw new IllegalArgumentException("Can't have percentage and money discount at the same time.");
            }
        }
    }
}

