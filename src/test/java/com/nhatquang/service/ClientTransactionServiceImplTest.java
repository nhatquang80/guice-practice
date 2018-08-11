package com.nhatquang.service;

import com.google.common.collect.Lists;
import com.nhatquang.dao.BTCClientTransactionDao;
import com.nhatquang.form.BTCCriteria;
import com.nhatquang.model.BTCClient;
import com.nhatquang.model.BTCClientTransaction;
import com.nhatquang.service.impl.ClientTransactionServiceImpl;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Quang Nguyen
 */
public class ClientTransactionServiceImplTest {

    @InjectMocks
    private ClientTransactionServiceImpl clientTransactionServiceImpl;
    @Mock
    private BTCClientTransactionDao btcClientTransactionDao;
    @Mock
    private Logger logger;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenCriteria_whenGetByCriteria_thenReturnBTCClientList() {
        LocalDate fromDate = LocalDate.now().minusDays(1);
        LocalDate toDate = LocalDate.now();
        BTCCriteria criteria = new BTCCriteria("lam", "cn", fromDate, toDate, null, null, null);

        List<BTCClientTransaction> btcClientTransactions = Lists.newArrayList();
        BTCClientTransaction clientTransaction = new BTCClientTransaction(
                new ObjectId("5b6a15f0f85c7e5442e51bfa"), "123", "abc", "CN", "");
        clientTransaction.setItem("Item1");
        clientTransaction.setPayment(BigDecimal.TEN);
        clientTransaction.setDate(new Date());
        btcClientTransactions.add(clientTransaction);

        when(btcClientTransactionDao.find(ArgumentMatchers.any(String.class))).thenReturn(btcClientTransactions);
        List<BTCClient> results = clientTransactionServiceImpl.getByCriteria(criteria);

        assertNotNull(results);
        assertTrue(results.size() > 0);
    }
}
