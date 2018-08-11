package com.nhatquang.util;

import com.google.common.collect.Lists;
import com.nhatquang.model.BTCClient;
import com.nhatquang.model.BTCClientTransaction;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Quang Nguyen
 */
public class ListUtilTest {

    @Test
    public void givenBTCClientTrasactions_whenTransform_thenReturnBTCClients() {
        List<BTCClientTransaction> clientTransactions = Lists.newArrayList();

        BTCClientTransaction ctran1 = new BTCClientTransaction(new ObjectId("5979aff9fc13ae110500053a"), "1", "123", "VN", null);
        ctran1.setItem("A");
        ctran1.setDate(new Date());
        ctran1.setPayment(BigDecimal.TEN);

        BTCClientTransaction ctran2 = new BTCClientTransaction(new ObjectId("5979aff9fc13ae110500053a"), "1", "123", "VN", null);
        ctran2.setItem("B");
        ctran2.setDate(new Date());
        ctran2.setPayment(BigDecimal.TEN);

        BTCClientTransaction ctran3 = new BTCClientTransaction(new ObjectId("5979aff9fc13ae110500053c"), "3", "789", "VN", null);
        ctran3.setItem("C");
        ctran3.setDate(new Date());
        ctran3.setPayment(BigDecimal.TEN);

        clientTransactions.add(ctran1);
        clientTransactions.add(ctran2);
        clientTransactions.add(ctran3);

        List<BTCClient> clients = ListUtil.transform(clientTransactions);

        assertNotNull(clients);
        assertTrue(clients.size() > 0);
        assertTrue(clients.get(0).getTransactions().size() > 0);
    }
}