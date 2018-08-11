package com.nhatquang;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.nhatquang.config.ApplicationModule;
import com.nhatquang.model.BTCClient;
import com.nhatquang.service.ClientTransactionService;
import com.nhatquang.service.JsonProcessingService;
import com.nhatquang.util.MongoDBUtil;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Trial extends Application {

    private Injector injector;
    private JsonProcessingService processingService;
    private ClientTransactionService clientTransactionService;

    @Override
    public void init() throws Exception {
        super.init();

        URL resourceURL = Resources.getResource("data/btc_transactions_20170401_20170630.json");
        File dataFile = new File(resourceURL.getFile());
        try (InputStream inputStream = Files.asByteSource(dataFile).openStream()) {
            // start mongo db
            MongoDBUtil.startMongoDB(MongoDBUtil.localhost, MongoDBUtil.port);
            // injector
            injector = Guice.createInjector(new ApplicationModule());
            processingService = injector.getInstance(JsonProcessingService.class);
            clientTransactionService = injector.getInstance(ClientTransactionService.class);

            // read json file
            List<BTCClient> clientList = processingService.readJsonFile(inputStream);
            // store to mongo db
            processingService.writeDataToMongoDB(clientList);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = injector.getInstance(FXMLLoader.class);
        try (InputStream fxmlInputStream = ClassLoader.getSystemResourceAsStream("ui/main.fxml")) {
            Parent parent = fxmlLoader.load(fxmlInputStream);
            primaryStage.setScene(new Scene(parent));
            primaryStage.setTitle("BTC Transactions");
            primaryStage.show();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        // shutdown mongo db
        MongoDBUtil.stopMongoDB();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
