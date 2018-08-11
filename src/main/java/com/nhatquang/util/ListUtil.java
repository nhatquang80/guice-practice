package com.nhatquang.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.nhatquang.model.BTCClient;
import com.nhatquang.model.BTCClientTransaction;
import com.nhatquang.model.BTCTransaction;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @author Quang Nguyen
 */
public class ListUtil {

    public static List<BTCClient> transform(List<BTCClientTransaction> clientTransactions) {
        Set<BTCClient> clients = Sets.newHashSet();
        if (clientTransactions != null && clientTransactions.size() > 0) {
            clients = clientTransactions.stream()
                    .map(result -> {
                        BTCClient client = new BTCClient();
                        client.setId(result.getId());
                        client.setClientIdentifier(result.getClientIdentifier());
                        client.setBtcAddress(result.getBtcAddress());
                        client.setAvatar(result.getAvatar());
                        client.setCountryCode(result.getCountryCode());
                        return client;
                    })
                    .collect(toSet());
            for (BTCClient client : clients) {
                List<BTCTransaction> transactions = clientTransactions.stream()
                        .filter(result -> result.getId().equals(client.getId()))
                        .map(result -> {
                            BTCTransaction transaction = new BTCTransaction();
                            transaction.setItem(result.getItem());
                            transaction.setPayment(result.getPayment());
                            transaction.setDate(result.getDate());
                            return transaction;
                        })
                        .collect(toList());
                client.setTransactions(transactions);
            }
        }
        return Lists.newArrayList(clients);
    }
}
