package com.nhatquang.service.impl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.inject.Inject;
import com.nhatquang.dao.BTCClientTransactionDao;
import com.nhatquang.model.BTCClient;
import com.nhatquang.model.BTCClientTransaction;
import com.nhatquang.service.JsonProcessingService;
import com.nhatquang.util.GsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * @author Quang Nguyen
 */
public class JsonProcessingServiceImpl implements JsonProcessingService {

    @Inject
    private Logger logger;
    @Inject
    private BTCClientTransactionDao btcClientTransactionDao;

    @Override
    public List<BTCClient> readJsonFile(InputStream jsonInputStream) throws IOException {

        logger.info("Reading json from input stream");

        checkNotNull(jsonInputStream);

        List<BTCClient> clients = new ArrayList<>();
        Gson gson = GsonUtil.getGson();
        try (InputStreamReader inputReader = new InputStreamReader(jsonInputStream, "UTF-8");
                JsonReader reader = new JsonReader(inputReader)) {

            reader.beginObject();
            while (reader.hasNext()) {
                String dataName = reader.nextName();
                if ("data".equalsIgnoreCase(dataName)) {
                    // it's an array.
                    reader.beginArray();
                    while (reader.hasNext()) {
                        BTCClient client = gson.fromJson(reader, BTCClient.class);
                        clients.add(client);
                    }
                    reader.endArray();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
        }
        return clients;
    }

    @Override
    public void writeDataToMongoDB(List<BTCClient> clients) {
        List<BTCClientTransaction> clientTransactions = new ArrayList<>();
        if (clients != null && clients.size() > 0) {
            clients.forEach(client -> {
                clientTransactions.addAll(client.flatten());
            });
            btcClientTransactionDao.save(clientTransactions);
        }
    }
}
