package se.maha.exercises.transaction.refactor;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class TransactionProcessorTest {

    @Test
    void testProcessTransaction_withSufficientFunds() {
        TransactionProcessor processor = new TransactionProcessor(new PercentageFeeCalculator(new BigDecimal("1.5")));
        processor.processTransaction(new AccountId("Alice"), new AccountId("Bob"), new BigDecimal("200.0"));

        assertEquals(new BigDecimal("797.00"), processor.getBalance(new AccountId("Alice")));
        assertEquals(new BigDecimal("700.00"), processor.getBalance(new AccountId("Bob")));
    }

    @Test
    void testProcessTransaction_insufficientFunds() {
        TransactionProcessor processor = new TransactionProcessor(new PercentageFeeCalculator(new BigDecimal("1.5")));

        Exception exception = assertThrows(InsufficientFundsException.class, () -> 
            processor.processTransaction(new AccountId("Alice"), new AccountId("Bob"), new BigDecimal("2000.0"))
        );

        assertEquals("Insufficient funds for account: Alice", exception.getMessage());
    }

    @Test
    void testProcessTransaction_concurrentTransactions() {
        TransactionProcessor processor = new TransactionProcessor(new PercentageFeeCalculator(new BigDecimal("1.5")));

        Runnable task = () -> processor.processTransaction(new AccountId("Alice"), new AccountId("Bob"), new BigDecimal("100.0"));

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<?> future1 = executor.submit(task);
            Future<?> future2 = executor.submit(task);

            future1.get();
            future2.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals(new BigDecimal("797.00"), processor.getBalance(new AccountId("Alice")));
        assertEquals(new BigDecimal("700.00"), processor.getBalance(new AccountId("Bob")));
    }
}