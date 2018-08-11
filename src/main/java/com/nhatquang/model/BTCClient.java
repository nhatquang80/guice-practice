package com.nhatquang.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Quang Nguyen
 *
 */
public class BTCClient implements Serializable {

    private static final long serialVersionUID = -4415279469780082174L;

    private ObjectId _id;
    private String clientIdentifier;
    private String btcAddress;
    private String countryCode;
    private String avatar;
    private List<BTCTransaction> transactions;

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
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

    public List<BTCTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BTCTransaction> transactions) {
        this.transactions = transactions;
    }

    public List<BTCClientTransaction> flatten() {
        List<BTCClientTransaction> results = new ArrayList<>();
        if (transactions == null || transactions.size() == 0) {
            BTCClientTransaction clientTransaction = new BTCClientTransaction(_id, clientIdentifier, btcAddress, countryCode, avatar);
            results.add(clientTransaction);
        } else {
            transactions.forEach(trans -> {
                BTCClientTransaction ct = new BTCClientTransaction(_id, clientIdentifier, btcAddress, countryCode, avatar);
                ct.setItem(trans.getItem());
                ct.setPayment(trans.getPayment());
                ct.setDate(trans.getDate());
                results.add(ct);
            });

        }
        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BTCClient btcClient = (BTCClient) o;
        return Objects.equal(_id, btcClient._id) &&
                Objects.equal(clientIdentifier, btcClient.clientIdentifier) &&
                Objects.equal(btcAddress, btcClient.btcAddress) &&
                Objects.equal(countryCode, btcClient.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_id, clientIdentifier, btcAddress, countryCode);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", _id)
                .add("clientIdentifier", clientIdentifier)
                .add("btcAddress", btcAddress)
                .add("countryCode", countryCode)
                .toString();
    }
}
