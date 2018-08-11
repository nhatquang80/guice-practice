package com.nhatquang.dto;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Object is to bind data to TreeViewTable
 *
 * @author Quang Nguyen
 */
public class BTCClientTransDto {

    private SimpleStringProperty id;
    private SimpleStringProperty clientIdentifier;
    private SimpleStringProperty btcAddress;
    private SimpleStringProperty countryCode;
    private SimpleStringProperty avatar;
    private SimpleStringProperty item;
    private SimpleDoubleProperty payment;
    private SimpleStringProperty date;

    public BTCClientTransDto() {

    }

    public BTCClientTransDto(ObjectId id,
                             String clientIdentifier,
                             String btcAddress,
                             String countryCode,
                             String avatar,
                             String item,
                             BigDecimal payment,
                             Date date) {
        this.id = id != null ? new SimpleStringProperty(id.toHexString()) : null;
        this.clientIdentifier = clientIdentifier != null ? new SimpleStringProperty(clientIdentifier) : null;
        this.btcAddress = btcAddress != null ? new SimpleStringProperty(btcAddress) : null;
        this.countryCode = countryCode != null ? new SimpleStringProperty(countryCode) : null;
        this.avatar = avatar != null ? new SimpleStringProperty(avatar) : null;
        this.item = item != null ? new SimpleStringProperty(item) : null;
        this.payment = payment != null ? new SimpleDoubleProperty(payment.doubleValue()) : null;
        this.date = date != null ? new SimpleStringProperty(DateFormatUtils.ISO_DATETIME_FORMAT.format(date)) : null;
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getClientIdentifier() {
        return clientIdentifier.get();
    }

    public SimpleStringProperty clientIdentifierProperty() {
        return clientIdentifier;
    }

    public String getBtcAddress() {
        return btcAddress.get();
    }

    public SimpleStringProperty btcAddressProperty() {
        return btcAddress;
    }

    public String getCountryCode() {
        return countryCode.get();
    }

    public SimpleStringProperty countryCodeProperty() {
        return countryCode;
    }

    public String getAvatar() {
        return avatar.get();
    }

    public SimpleStringProperty avatarProperty() {
        return avatar;
    }

    public String getItem() {
        return item.get();
    }

    public SimpleStringProperty itemProperty() {
        return item;
    }

    public double getPayment() {
        return payment.get();
    }

    public SimpleDoubleProperty paymentProperty() {
        return payment;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }
}
