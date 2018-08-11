package com.nhatquang.util;

import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Quang Nguyen
 */
@RunWith(MockitoJUnitRunner.class)
public class MongoDBUtilTest {

    private MongodProcess mongodProcess;
    private String host;
    private int port;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        host = "localhost";
        port = Network.getFreeServerPort();
        mongodProcess = MongoDBUtil.startMongoDB(host, port);
    }

    @Test
    public void whenStartMongoDB_ThenReturnMongodProcess() throws IOException {
        assertNotNull(mongodProcess);
        assertTrue(mongodProcess.isProcessRunning());
    }

    @Test
    public void whenStopMongoDB_ThenMongodProcessIsStopped() {
        assertNotNull(mongodProcess);
        MongoDBUtil.stopMongoDB();
        assertFalse(mongodProcess.isProcessRunning());
    }

    @After
    public void tearDown() throws Exception {
        if (mongodProcess != null) {
            mongodProcess.stop();
        }
    }
}