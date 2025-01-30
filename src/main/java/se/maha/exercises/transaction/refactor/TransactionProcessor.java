package se.maha.exercises.transaction.refactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionProcessor {
    private static final Logger logger = LoggerFactory.getLogger(TransactionProcessor.class);

    private final Map<AccountId, BigDecimal> accountBalances = new ConcurrentHashMap<>();
    private final FeeCalculator feeCalculator;
    private final List<TransactionRecord> transactionLog = new ArrayList<>();

    public TransactionProcessor(FeeCalculator feeCalculator) {
        this.feeCalculator = feeCalculator;

        accountBalances.put(new AccountId("Alice"), new BigDecimal("1000.0"));
        accountBalances.put(new AccountId("Bob"), new BigDecimal("500.0"));
    }

    public void processTransaction(AccountId sender, AccountId receiver, BigDecimal amount) {
        validateAmount(amount);
        BigDecimal fee;

        synchronized (accountBalances) {
            verifyAccountExists(sender);
            verifyAccountExists(receiver);
            verifySufficientFunds(sender, amount);

            fee = feeCalculator.applyFee(amount);

            accountBalances.put(sender, accountBalances.get(sender).subtract(amount).subtract(fee));
            accountBalances.put(receiver, accountBalances.get(receiver).add(amount));

            logTransaction(sender, receiver, amount, fee);
        }

        logger.info("Transaction completed: Sender={}, Receiver={}, Amount={}, Fee={}", 
            sender.id(), receiver.id(), amount, fee);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }

    private void verifyAccountExists(AccountId accountId) {
        if (!accountBalances.containsKey(accountId)) {
            throw new IllegalArgumentException("Account does not exist: " + accountId.id());
        }
    }

    private void verifySufficientFunds(AccountId sender, BigDecimal amount) {
        BigDecimal currentBalance = accountBalances.get(sender);
        BigDecimal fee = feeCalculator.applyFee(amount);

        if (currentBalance.compareTo(amount.add(fee)) < 0) {
            throw new InsufficientFundsException("Insufficient funds for account: " + sender.id());
        }
    }

    private void logTransaction(AccountId sender, AccountId receiver, BigDecimal amount, BigDecimal fee) {
        transactionLog.add(new TransactionRecord(sender, receiver, amount, fee));
    }

    public BigDecimal getBalance(AccountId accountId) {
        return accountBalances.get(accountId).setScale(2, RoundingMode.HALF_UP);
    }

    public List<TransactionRecord> getTransactionLog() {
        return new ArrayList<>(transactionLog);
    }
}