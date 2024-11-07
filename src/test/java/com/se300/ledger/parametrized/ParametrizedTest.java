package com.se300.ledger.parametrized;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.se300.ledger.Account;
import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;

public class ParametrizedTest {

    //TODO: Demonstrate parametrization

    private static Ledger ledger;

    @BeforeAll
    public static void setUp() throws LedgerException {
        ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");

        Account mary = ledger.createAccount("mary");
        Account master = ledger.getUncommittedBlock().getAccount("master");
    }

    @ParameterizedTest
    @ValueSource(strings = { "jill", "bob" })
    void testDuplicateAccountCreation(String name) throws LedgerException {

        ledger.createAccount(name);
        assertThrows(LedgerException.class, () -> ledger.createAccount(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalidAccount"})
    void testTransaction_invalidAccount(String invalidName) {
        Account invalidAccount = new Account(null, null);
        Transaction invalidTransaction = new Transaction("13", 50, 5, "invalid test", invalidAccount, invalidAccount);
        assertThrows(LedgerException.class, () -> ledger.processTransaction(invalidTransaction));
    }
}
