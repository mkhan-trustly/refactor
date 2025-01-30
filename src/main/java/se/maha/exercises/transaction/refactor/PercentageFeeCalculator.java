package se.maha.exercises.transaction.refactor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageFeeCalculator implements FeeCalculator {

    private final BigDecimal feePercentage;

    public PercentageFeeCalculator(BigDecimal feePercentage) {
        this.feePercentage = feePercentage;
    }

    @Override
    public BigDecimal applyFee(BigDecimal amount) {
        return amount.multiply(feePercentage)
                .divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
    }
}