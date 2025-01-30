package se.maha.exercises.transaction.refactor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRecord(AccountId sender, AccountId receiver, BigDecimal amount, BigDecimal fee, LocalDateTime timestamp) {

    public TransactionRecord(AccountId sender, AccountId receiver, BigDecimal amount, BigDecimal fee) {
        this(sender, receiver, amount, fee, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return String.format("Sender: %s, Receiver: %s, Amount: %s, Fee: %s, Timestamp: %s",
                sender.id(), receiver.id(), amount, fee, timestamp);
    }
}
