package com.se300.ledger.stubs;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;

public class StubTest {

    //TODO: Demonstrate stubs

    @Test
    public void testTransaction_NullAccount() throws LedgerException {
        Ledger ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");
        Transaction transaction = mock(Transaction.class);
        when(transaction.getPayer() == null);

        assertThrows(org.mockito.exceptions.misusing.UnfinishedStubbingException.class, () -> ledger.processTransaction(transaction));
    }

}
