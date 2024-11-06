package com.se300.ledger.mocks;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.se300.ledger.Account;
import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;

public class MockTest {

    //TODO: Demonstrate mocks

    @Test
    public void testPayerBalanceCheck() throws LedgerException {

        Ledger ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");

        Account mary = mock(Account.class);
        Account sergey = mock(Account.class);

        Transaction transaction = new Transaction("1", 1000, 10, "simple test", mary, sergey);

        assertThrows(LedgerException.class, () -> ledger.processTransaction(transaction));

        verify(mary, times(1)).getBalance();
    }

    @Test
    public void testBalance() throws LedgerException {
        Ledger ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");
        Account mary = mock(Account.class);
        Account sergey = mock(Account.class);

        Transaction transaction = new Transaction("1", 1000, 10, "simple test", mary, sergey);

        assertThrows(LedgerException.class, () -> ledger.processTransaction(transaction));

        verify(mary, times(2)).getBalance();
    };
}
