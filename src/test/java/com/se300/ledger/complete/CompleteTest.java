package com.se300.ledger.complete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.se300.ledger.Account;
import com.se300.ledger.Block;
import com.se300.ledger.Ledger;

public class CompleteTest {

    /* TODO: The following
     * 1. Achieve 100% Test Coverage
     * 2. Produce/Print Identical Results to Command Line DriverTest
     * 3. Produce Quality Report
     */

    /*
     * Account.java
     */

    private static Ledger ledger;

     @Test
     public void testAccount_getAddress() {
        // Arrange
        Account account = new Account("address", 0);

        // Act
        account.setAddress("newAddress");

        // Assert
        assertEquals("newAddress", account.getAddress());
     }


     /*
      * Block.java
      */

      @Test
      public void testBlock_setBlockNumber() {
        // Arrange
        Block block = new Block(1, "previousHash");
        
        // Act
        block.setBlockNumber(2);

        // Assert
        assertEquals(2, block.getBlockNumber());
      }

      @Test
      public void testSetPreviousHash() {
        // Arrange
        Block block = new Block(1, "previousHash");

        // Act
        block.setPreviousHash("newPreviousHash");

        // Assert
        assertEquals("newPreviousHash", block.getPreviousHash());
      }

    /*
     * Ledger.java
     */

    @BeforeEach
    public void setUp() {
        ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");
    }

    @Test
    public void testLedger_getName() {
        // Assert
        assertEquals("test", ledger.getName());
    }

    @Test
    public void testLedger_setName() {
        // Act
        ledger.setName("newTest");

        // Assert
        assertEquals("newTest", ledger.getName());
    }

    @Test
    public void testLedger_getDescription() {
        // Assert
        assertEquals("test ledger 2023", ledger.getDescription());
    }

    @Test
    public void testLedger_setDescription() {
        // Act
        ledger.setDescription("new test ledger 2023");

        // Assert
        assertEquals("new test ledger 2023", ledger.getDescription());
    }

    @Test
    public void testLedger_getSeed() {
        // Assert
        assertEquals("chapman", ledger.getSeed());
    }
}
