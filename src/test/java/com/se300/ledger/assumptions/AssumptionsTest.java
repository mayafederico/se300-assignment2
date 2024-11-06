package com.se300.ledger.assumptions;

import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.se300.ledger.Account;
import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;

public class AssumptionsTest {

    //TODO: Demonstrate assumptions

    private static Ledger ledger;
    private static Account mary;
    private static Account sergey;

    @BeforeAll
    public static void setUpClass() throws LedgerException {
        ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");

        mary = ledger.createAccount("mary");
        sergey = ledger.createAccount("sergey");

        //Transaction transaction = new Transaction("1", 60, 10, "simple test", mary, sergey);
    }

    @Test
    public void AssumeBalance_NotNegative() {
        // Balance should not be negative
        assumeTrue(mary.getBalance() >= 0);
    }

    @Test
    public void AssumeAddress_NotNull() {
        // Address should not be null
        assumeFalse(sergey.getAddress() == null);
    }

    @Test
    public void testTransaction() {
        assumingThat(ledger.getTransaction("11") != null,
        () -> {
            Transaction transaction = new Transaction("11", (Integer) 60, (Integer) 10, "simple test", mary, sergey);
            ledger.processTransaction(transaction);
        }
        );
    }

}
