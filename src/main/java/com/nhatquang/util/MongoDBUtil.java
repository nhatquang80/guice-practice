package com.nhatquang.util;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.IOException;

/**
 * @author Quang Nguyen
 */
public class MongoDBUtil {

    public static final String localhost = "localhost";
    public static final int port = 12345;

    private static final MongodStarter starter = MongodStarter.getDefaultInstance();
    private static MongodExecutable mongodExecutable;

    /**
     * Startup embedded MongoDB
     *
     * @param host
     * @param port
     * @return
     * @throws IOException
     */
    public static MongodProcess startMongoDB(String host, int port) throws IOException {

        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(host, port, Network.localhostIsIPv6()))
                .build();

        mongodExecutable = starter.prepare(mongodConfig);
        return mongodExecutable.start();
    }

    /**
     * Shutdown Mongo DB
     *
     */
    public static void stopMongoDB() {
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }
}
