package com.nhatquang.config;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.mongodb.DB;
import org.jongo.Jongo;
import org.jongo.Mapper;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.JacksonMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Quang Nguyen
 */
public class JongoConfig {

    private static final DB database = new DB(MongoConfig.getMongoClient(), MongoConfig.BTC_DATABASE_NAME);
    private static final Jongo jongo = new Jongo(database, mapperBuilder());

    public static Jongo getJongo() {
        return jongo;
    }

    public static MongoCollection getBtcCollection() {
        MongoCollection collection = jongo.getCollection(MongoConfig.BTC_COLLECTION_NAME);
        return collection;
    }

    /**
     * Add serializer and deserializer of LocalDateTime object
     *
     * @return
     */
    private static Mapper mapperBuilder() {
        return new JacksonMapper.Builder()
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer())
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer())
                .build();
    }

    public static class LocalDateTimeSerializer extends StdScalarSerializer<LocalDateTime> {

        public LocalDateTimeSerializer() {
            super(LocalDateTime.class);
        }

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeObject(Date.from(value.atZone(ZoneId.systemDefault()).toInstant()));
        }
    }

    public static class LocalDateTimeDeserializer extends StdScalarDeserializer<LocalDateTime> {

        public LocalDateTimeDeserializer() {
            super(LocalDateTime.class);
        }

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            Date date = (Date) p.getEmbeddedObject();
            LocalDateTime localDateTime = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            return localDateTime;
        }

    }
}
