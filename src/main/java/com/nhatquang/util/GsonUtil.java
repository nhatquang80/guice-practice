package com.nhatquang.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * @author Quang Nguyen
 */
public class GsonUtil {

    private static final GsonBuilder gsonBuilder = new GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .registerTypeAdapter(ObjectId.class,
                    (JsonSerializer<ObjectId>) (src, typeOfSrc, context) -> {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("$oid", src.toString());
                        return jsonObject;
                    })
            .registerTypeAdapter(ObjectId.class,
                    (JsonDeserializer<ObjectId>) (json, typeOfT, context) ->
                            new ObjectId(json.getAsJsonObject().get("$oid").getAsString()))
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                            ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime());


    /**
     * Create gson object
     *
     * @return
     */
    public static Gson getGson() {
        return gsonBuilder.create();
    }
}
