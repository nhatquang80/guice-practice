package com.nhatquang.util;

import com.google.gson.Gson;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * @author Quang Nguyen
 */
@RunWith(MockitoJUnitRunner.class)
public class GsonUtilTest {

    private final String hexString = "5979aff3fc13ae110500017c";
    private final String jsonObjectIdStr = "{\"$oid\":\""+ hexString + "\"}";
    private final ObjectId objectId = new ObjectId(hexString);
    private final String dateStr = "2017-04-09T22:08:48Z";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetGson_ThenReturnGsonObject() {
        Gson gson = GsonUtil.getGson();
        Assert.assertNotNull(gson);
    }

    @Test
    public void givenJsonStr_WhenFromJson_ThenReturnObject() {
        Gson gson = GsonUtil.getGson();
        ObjectId id = gson.fromJson(jsonObjectIdStr, ObjectId.class);
        Assert.assertEquals(objectId, id);
    }

    @Test
    public void givenNull_WhenFromJson_ThenReturnNull() {
        Gson gson = GsonUtil.getGson();
        String str = null;
        ObjectId id = gson.fromJson(str, ObjectId.class);
        Assert.assertNull(id);
    }

    @Test
    public void givenObjectId_WhenToJson_ThenReturnString() {
        Gson gson = GsonUtil.getGson();
        String idStr = gson.toJson(objectId);
        Assert.assertEquals(jsonObjectIdStr, idStr);
    }

    @Test
    public void givenNull_WhenToJson_ThenReturnNullString() {
        Gson gson = GsonUtil.getGson();
        String idStr = gson.toJson(null);
        Assert.assertEquals("null", idStr);
    }

    @Test
    public void givenDateStr_WhenFromJson_ThenReturnLocalDateTime() {
        Gson gson = GsonUtil.getGson();
        LocalDateTime expectedDate = ZonedDateTime.parse(dateStr).toLocalDateTime();
        LocalDateTime result = gson.fromJson("\"" + dateStr + "\"", LocalDateTime.class);
        Assert.assertEquals(expectedDate, result);
    }

}
