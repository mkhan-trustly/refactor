package se.maha.exercises.transaction.refactor;

import java.math.BigDecimal;

public interface FeeCalculator {

    BigDecimal applyFee(BigDecimal amount);
}