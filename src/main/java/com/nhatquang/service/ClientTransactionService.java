package com.nhatquang.service;

import com.nhatquang.form.BTCCriteria;
import com.nhatquang.model.BTCClient;

import java.util.List;

/**
 * @author Quang Nguyen
 */
public interface ClientTransactionService {

    /**
     *
     * @param criteria
     * @return
     */
    List<BTCClient> getByCriteria(BTCCriteria criteria);
}
