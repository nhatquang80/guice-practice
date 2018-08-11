package com.nhatquang.service;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.nhatquang.model.BTCClient;
import com.nhatquang.service.impl.JsonProcessingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * @author Quang Nguyen
 */
@RunWith(MockitoJUnitRunner.class)
public class JsonProcessingServiceImplTest {

    @InjectMocks
    private JsonProcessingServiceImpl jsonProcessingServiceImpl;
    @Mock
    private Logger logger;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenInputStream_WhenReadJsonFile_ThenReturnBTCClientList() throws IOException {
        URL resourceURL = Resources.getResource("data/data.json");
        File dataFile = new File(resourceURL.getFile());
        try (InputStream inputStream = Files.asByteSource(dataFile).openStream()) {
            List<BTCClient> clientList = jsonProcessingServiceImpl.readJsonFile(inputStream);
            assertNotNull(clientList);
            assertTrue(clientList.size() > 0);
        }
    }
}
