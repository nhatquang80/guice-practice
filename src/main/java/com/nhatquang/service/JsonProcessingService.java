package com.nhatquang.service;

import com.nhatquang.model.BTCClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Quang Nguyen
 */
public interface JsonProcessingService {

    /**
     * Read json file and parse the data to List<BTCClient>
     * @param jsonInputStream
     */
    List<BTCClient> readJsonFile(InputStream jsonInputStream) throws IOException;

    /**
     * Write data list to mongo db
     *
     * @param clients
     */
    void writeDataToMongoDB(List<BTCClient> clients);
}
