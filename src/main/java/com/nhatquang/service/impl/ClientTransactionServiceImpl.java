package com.nhatquang.service.impl;

import com.google.inject.Inject;
import com.nhatquang.dao.BTCClientTransactionDao;
import com.nhatquang.form.BTCCriteria;
import com.nhatquang.model.BTCClient;
import com.nhatquang.model.BTCClientTransaction;
import com.nhatquang.util.ListUtil;
import com.nhatquang.builder.QueryBuilder;
import com.nhatquang.service.ClientTransactionService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Quang Nguyen
 */
public class ClientTransactionServiceImpl implements ClientTransactionService {

    @Inject
    private Logger logger;
    @Inject
    private BTCClientTransactionDao btcClientTransactionDao;

    @Override
    public List<BTCClient> getByCriteria(BTCCriteria criteria) {
        String query = QueryBuilder.build(criteria);
        logger.log(Level.INFO, "Execute query: {0}", query);
        List<BTCClientTransaction> results = btcClientTransactionDao.find(query);
        return ListUtil.transform(results);
    }
}
