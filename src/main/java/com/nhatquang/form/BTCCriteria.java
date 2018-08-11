package com.nhatquang.form;

import java.time.LocalDate;

/**
 * @author Quang Nguyen
 */
public class BTCCriteria {

    private String item;
    private String countryCode;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String clientId;
    private String address;
    private String avatar;

    public BTCCriteria() {
    }

    public BTCCriteria(String item, String countryCode, LocalDate fromDate, LocalDate toDate, String clientId, String address, String avatar) {
        this.item = item;
        this.countryCode = countryCode;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.clientId = clientId;
        this.address = address;
        this.avatar = avatar;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
