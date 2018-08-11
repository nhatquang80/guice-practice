package com.nhatquang.config;

import com.mongodb.MongoClient;
import com.nhatquang.util.MongoDBUtil;

/**
 * @author Quang Nguyen
 */
public class MongoConfig {

    public static final String BTC_DATABASE_NAME = "btc";
    public static final String BTC_COLLECTION_NAME = "btc_transaction";

    private static final MongoClient mongoClient = new MongoClient(MongoDBUtil.localhost, MongoDBUtil.port);

    public static MongoClient getMongoClient() {
        return mongoClient;
    }
}
