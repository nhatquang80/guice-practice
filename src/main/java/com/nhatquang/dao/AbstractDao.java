package com.nhatquang.dao;

import com.google.inject.Inject;
import org.jongo.MongoCollection;

/**
 * @author Quang Nguyen
 */
public abstract class AbstractDao {

    @Inject
    private MongoCollection btcCollection;

    public MongoCollection getBtcCollection() {
        // indexing
        btcCollection.ensureIndex("{clientIdentifier: 1}");
        btcCollection.ensureIndex("{btcAddress: 1}");
        btcCollection.ensureIndex("{item: 'text'}");
        btcCollection.ensureIndex("{avatar: 1}");
        btcCollection.ensureIndex("{date: 1}");
        return btcCollection;
    }
}
