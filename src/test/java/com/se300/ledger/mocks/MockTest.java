package com.se300.ledger.mocks;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import com.se300.ledger.Account;
import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;

public class MockTest {

    //TODO: Demonstrate mocks

    @Mock
    private static Ledger ledger;
    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }
    
    @Test
    public void testPayerBalanceCheck() throws LedgerException {

        Account mary = mock(Account.class);
        Account sergey = mock(Account.class);

        Transaction transaction = new Transaction("1", 1000, 10, "simple test", mary, sergey);

        assertThrows(LedgerException.class, () -> ledger.processTransaction(transaction));

        verify(mary, times(1)).getBalance();
    }

    @Test
    public void testBalance() throws LedgerException {
        Account mary = mock(Account.class);
        Account sergey = mock(Account.class);

        Transaction transaction = new Transaction("1", 1000, 10, "simple test", mary, sergey);

        assertThrows(LedgerException.class, () -> ledger.processTransaction(transaction));

        verify(mary, times(1)).getBalance();
    };
}
