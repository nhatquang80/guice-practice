package com.nhatquang.config;

import com.google.inject.Provider;
import org.jongo.MongoCollection;

/**
 * @author Quang Nguyen
 */
public class BTCCollectionProvider implements Provider<MongoCollection> {

    public MongoCollection get() {
        return JongoConfig.getBtcCollection();
    }
}
