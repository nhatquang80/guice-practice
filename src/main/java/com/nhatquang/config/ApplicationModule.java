package com.nhatquang.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.nhatquang.dao.BTCClientTransactionDao;
import com.nhatquang.service.JsonProcessingService;
import com.nhatquang.service.impl.ClientTransactionServiceImpl;
import com.nhatquang.service.impl.JsonProcessingServiceImpl;
import javafx.fxml.FXMLLoader;
import com.nhatquang.service.ClientTransactionService;
import org.jongo.MongoCollection;

/**
 * @author Quang Nguyen
 */
public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MongoCollection.class).toProvider(BTCCollectionProvider.class);
        bind(BTCClientTransactionDao.class).toInstance(new BTCClientTransactionDao());
        bind(JsonProcessingService.class).to(JsonProcessingServiceImpl.class).in(Scopes.SINGLETON);
        bind(ClientTransactionService.class).to(ClientTransactionServiceImpl.class).in(Scopes.SINGLETON);
        bind(FXMLLoader.class).toProvider(FXMLLoaderProvider.class);
    }
}
