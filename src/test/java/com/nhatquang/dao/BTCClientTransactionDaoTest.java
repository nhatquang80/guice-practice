package com.nhatquang.dao;

import com.mongodb.WriteResult;
import com.nhatquang.model.BTCClientTransaction;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * @author Quang Nguyen
 */
@RunWith(MockitoJUnitRunner.class)
public class BTCClientTransactionDaoTest {

    @InjectMocks
    private BTCClientTransactionDao btcClientTransactionDao;
    @Mock
    private MongoCollection mongoCollection;

    private BTCClientTransaction clientTransaction = new BTCClientTransaction();
    private List<BTCClientTransaction> clientTransactions = new ArrayList<>();
    private WriteResult writeResult = new WriteResult(0, false, null);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        clientTransaction.setId(new ObjectId("5979affafc13ae1105000561"));
        clientTransaction.setClientIdentifier("123123");
        clientTransaction.setCountryCode("VN");

        clientTransactions.add(clientTransaction);
    }

    @Test
    public void givenBTCClientTransaction_WhenSave_ThenReturnCount() {
        when(mongoCollection.save(any())).thenReturn(writeResult);
        int result = btcClientTransactionDao.save(clientTransaction);
        assertTrue(result > 0);
    }

    @Test
    public void givenBTCClientTransactions_WhenSave_ThenReturnCount() {
        when(mongoCollection.save(any())).thenReturn(writeResult);
        int result = btcClientTransactionDao.save(clientTransactions);
        assertTrue(result > 0);
    }
}
