package com.se300.ledger.assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.se300.ledger.Account;
import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;

public class AssertionsTest {

    //TODO: Demonstrate all assertions, including exception and timeout testing
    
    private static Ledger ledger;
    private static Account mary;
    private static Account sergey;
    //private static Account master;

    @BeforeAll
    @SuppressWarnings("unused")
    static void setUp() throws LedgerException{
        ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");
        ledger.createAccount("mary");
        ledger.createAccount("sergey");
        //ledger.createAccount("master");

        mary = ledger.getUncommittedBlock().getAccount("mary");
        sergey = ledger.getUncommittedBlock().getAccount("sergey");
        //master = ledger.getUncommittedBlock().getAccount("master");
    }

    // Asserts that accounts are not null
    @Test
    public void testAccountCreation() {
        assertNotNull(mary);
        assertNotNull(sergey);
        //assertNotNull(master);
    }

    // Test the ability to get and set the balance of an account
    @Test
    public void testAccount_Balance() {
        // Arrange
        Account account = new Account("test", 0);

        // Act
        Integer initialBalance = account.getBalance();
        account.setBalance(initialBalance + 100);

        // Assert
        assertEquals(0, initialBalance);
        assertEquals(100, account.getBalance());
    }

    // tests a transaction
    @Test
    public void testTransaction() {

        assumingThat(ledger.getTransaction("11") == null,
        () -> {
            mary.setBalance(100);
            sergey.setBalance(0);
            Integer initialMaryBalance = mary.getBalance();
            Integer initialSergeyBalance = sergey.getBalance();

            Transaction firstTransaction = new Transaction("11", 60, 10, "simple test", mary, sergey);
            try {
                ledger.processTransaction(firstTransaction);
            } catch (LedgerException ex) {
            }

            // Assert
            assertEquals(initialMaryBalance - 70, mary.getBalance());
            assertEquals(initialSergeyBalance + 60, sergey.getBalance());
        }
        );
    }

    /* 
    @Test
    public void testException() throws LedgerException {
        // Arrange
        ledger = Ledger.getInstance("Ledger", "Description", "Seed");

        // Act
        Account payer = ledger.createAccount("Payer");
        payer.setBalance(-100);

        Account receiver = new Account("Receiver", 0);

        assertThrows(LedgerException.class,
        () -> {
            ledger.processTransaction(new Transaction("Transaction", -100, 10, "Test", payer, receiver));
        }
        );
    }
    */
}
