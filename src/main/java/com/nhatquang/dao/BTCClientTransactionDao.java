package com.nhatquang.dao;

import com.google.common.collect.Lists;
import com.mongodb.WriteResult;
import com.nhatquang.model.BTCClientTransaction;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Quang Nguyen
 */
public class BTCClientTransactionDao extends AbstractDao implements BasicDao<BTCClientTransaction> {

    @Override
    public int save(BTCClientTransaction btcClientTransaction) {
        WriteResult result = getBtcCollection().save(btcClientTransaction);
        if (result != null && result.wasAcknowledged()) {
            return 1;
        }
        return 0;
    }

    @Override
    public int save(List<BTCClientTransaction> list) {
        int result = 0;
        if (list != null && list.size() > 0) {
            for (BTCClientTransaction trans : list) {
                WriteResult writeResult = getBtcCollection().save(trans);
                if (writeResult != null && writeResult.wasAcknowledged()) {
                    result++;
                }
            }
        }
        return result;
    }

    @Override
    public BTCClientTransaction findOne(String query) {
        return getBtcCollection()
                .findOne(query)
                .as(BTCClientTransaction.class);
    }

    @Override
    public List<BTCClientTransaction> findAll() {
        return find("{}");
    }

    @Override
    public List<BTCClientTransaction> find(String query) {
        Iterator<BTCClientTransaction> iterator = getBtcCollection()
                .find(query)
                .as(BTCClientTransaction.class)
                .iterator();
        return Lists.newArrayList(iterator);
    }

    @Override
    public List<BTCClientTransaction> search(String text) {
        Iterator<BTCClientTransaction> iterator = getBtcCollection()
                .find("{ item: { $regex: #, $options: 'im' } }",
                        Pattern.compile(String.format(".*%s.*", text)).pattern())
                .as(BTCClientTransaction.class)
                .iterator();
        return Lists.newArrayList(iterator);
    }
}
