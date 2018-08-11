package com.nhatquang.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Flatten object represents data of a client transaction
 *
 * @author Quang Nguyen
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BTCClientTransaction implements Serializable {

    private ObjectId id;
    private String clientIdentifier;
    private String btcAddress;
    private String countryCode;
    private String avatar;
    private String item;
    private BigDecimal payment;
    private Date date;

    public BTCClientTransaction() {

    }

    public BTCClientTransaction(ObjectId id, String clientIdentifier, String btcAddress, String countryCode, String avatar) {
        this.id = id;
        this.clientIdentifier = clientIdentifier;
        this.btcAddress = btcAddress;
        this.countryCode = countryCode;
        this.avatar = avatar;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }

    public void setClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }

    public String getBtcAddress() {
        return btcAddress;
    }

    public void setBtcAddress(String btcAddress) {
        this.btcAddress = btcAddress;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BTCClientTransaction that = (BTCClientTransaction) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(clientIdentifier, that.clientIdentifier) &&
                Objects.equal(btcAddress, that.btcAddress) &&
                Objects.equal(countryCode, that.countryCode) &&
                Objects.equal(avatar, that.avatar) &&
                Objects.equal(item, that.item) &&
                Objects.equal(payment, that.payment) &&
                Objects.equal(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, clientIdentifier, btcAddress, countryCode, avatar, item, payment, date);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("clientIdentifier", clientIdentifier)
                .add("btcAddress", btcAddress)
                .add("countryCode", countryCode)
                .add("avatar", avatar)
                .add("item", item)
                .add("payment", payment)
                .add("date", date)
                .toString();
    }
}
