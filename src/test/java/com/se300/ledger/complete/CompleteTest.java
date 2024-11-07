package com.se300.ledger.complete;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mockStatic;

import com.se300.ledger.Account;
import com.se300.ledger.Block;
import com.se300.ledger.Ledger;
import com.se300.ledger.MerkleTrees;
import com.se300.ledger.Transaction;

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
    public void testAccount_getBalance() {
        // Arrange
        Account account = new Account("address", 0);

        // Act
        account.setBalance(100);

        assertEquals(100, account.getBalance());
        
    } 
    
    @Test
    public void testAccount_getAddress() {
        // Arrange
        Account account = new Account("address", 0);

        // Act
        account.setAddress("newAddress");

        // Assert
        assertEquals("newAddress", account.getAddress());
    }

    @Test
    public void testAccount_clone() {
        Account originalAccount = new Account("address", 100);

        Account cloneAccount = (Account) originalAccount.clone();

        assertEquals(originalAccount.getAddress(), cloneAccount.getAddress());
        assertEquals(originalAccount.getBalance(), cloneAccount.getBalance());
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

    @Test
    public void testBlock_setHash() {
        Block block = new Block(1, "previousHash");

        block.setHash("hash");

        assertEquals("hash", block.getHash());
    }

    @Test
    public void testBlock_Account() {
        Account mary = new Account("mary", 100);

        Block block = new Block(1, "previousHash");

        block.addAccount("mary", mary);

        assertEquals(mary, block.getAccount("mary"));
    }

    @Test
    public void testBlock_previousBlock() {
        Block block = new Block(2, "previousHash");
        Block previousBlock = new Block(1, "newPreviousHash");

        block.setPreviousBlock(previousBlock);

        assertEquals(previousBlock, block.getPreviousBlock());
    }


    // Test for MerkleTree.java:
    @Test
    public void testGetSHA2HexValueException() throws NoSuchAlgorithmException {
        try (MockedStatic<MessageDigest> mdMock = mockStatic(MessageDigest.class)) {
            // Mock the getInstance method to throw an exception
            mdMock.when(() -> MessageDigest.getInstance("SHA-256")).thenThrow(new NoSuchAlgorithmException());

            MerkleTrees merkleTrees = new MerkleTrees(new ArrayList<>());

            String result = merkleTrees.getSHA2HexValue("test");

            // Assert
            assertEquals("", result);
        }
    }

    /*
     * Transaction.java
     */

    @Test
    public void testTransaction_setValues() {
        // Arrange
        Account payer = new Account("payer", 100);
        Account receiver = new Account("receiver", 100);
        Transaction transaction = new Transaction("ID", (Integer) 10, (Integer) 5,"note", null, null);

        // Act
        transaction.setTransactionId("1");
        transaction.setAmount(60);
        transaction.setFee(10);
        transaction.setNote("simple test");
        transaction.setPayer(payer);
        transaction.setReceiver(receiver);

        // Assert
        assertEquals("1", transaction.getTransactionId());
        assertEquals(60, transaction.getAmount());
        assertEquals(10, transaction.getFee());
        assertEquals("simple test", transaction.getNote());
        assertEquals(payer, transaction.getPayer());
        assertEquals(receiver, transaction.getReceiver());
    }

    @Test
    public void testTransaction_toString() {
        // Arrange
        Account mary = new Account("mary", 100);
        Account sergey = new Account("sergey", 100);
        Transaction transaction = new Transaction("1", 60, 10, "simple test", mary, sergey);

        String transactionId = "1";
        Integer amount = 60;
        Integer fee = 10;
        String note = "simple test";

        String transactionInfo = "Transaction Id: " + transactionId +
                ", Amount: " + amount +
                ", Fee: " + fee +
                ", Note: " + note +
                ", Payer: " + mary.getAddress() +
                ", Receiver: " + sergey.getAddress();

        // Assert
        assertEquals(transactionInfo, transaction.toString());
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

    /* 
    @Test
    public void testLedger_getDescription() {
        // Assert
        ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");
        assertEquals("test ledger 2023", ledger.getDescription());
    }
    */

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
