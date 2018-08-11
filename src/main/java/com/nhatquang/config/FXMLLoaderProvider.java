package com.nhatquang.config;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import javafx.fxml.FXMLLoader;


/**
 * @author Quang Nguyen
 */
public class FXMLLoaderProvider implements Provider<FXMLLoader> {

    @Inject
    Injector injector;

    @Override
    public FXMLLoader get() {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(injector::getInstance);
        return loader;
    }
}
