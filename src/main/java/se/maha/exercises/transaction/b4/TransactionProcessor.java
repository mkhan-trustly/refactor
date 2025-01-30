package se.maha.exercises.transaction.b4;

import java.util.HashMap;
import java.util.Map;

public class TransactionProcessor {

    private Map<String, Double> accountBalances = new HashMap<>();
    private double transactionFeePercentage = 1.5; // in percentage

    public TransactionProcessor() {
        // Initialize with some dummy balances
        accountBalances.put("Alice", 1000.0);
        accountBalances.put("Bob", 500.0);
    }

    public void processTransaction(String sender, String receiver, double amount) {
        System.out.println("Processing transaction...");
        
        // Check if accounts exist
        if (!accountBalances.containsKey(sender) || !accountBalances.containsKey(receiver)) {
            System.out.println("One of the accounts does not exist. Transaction aborted.");
            return;
        }

        // Deduct amount from sender
        double senderBalance = accountBalances.get(sender);
        if (senderBalance < amount) {
            System.out.println("Insufficient funds. Transaction aborted.");
            return;
        }

        // Calculate transaction fee
        double fee = amount * transactionFeePercentage / 100;

        // Update balances
        accountBalances.put(sender, senderBalance - amount - fee); // Deduct fee and amount
        accountBalances.put(receiver, accountBalances.get(receiver) + amount);

        System.out.println("Transaction completed.");
        System.out.println("Fee charged: " + fee);
        System.out.println("Updated balances: " + accountBalances);
    }

    public static void main(String[] args) {
        TransactionProcessor processor = new TransactionProcessor();

        // Simulate a transaction
        processor.processTransaction("Alice", "Bob", 200.0);
    }
}