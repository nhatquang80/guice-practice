package com.nhatquang.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Quang Nguyen
 */
public class BTCTransaction implements Serializable {

    private static final long serialVersionUID = -4415279459780082174L;

    private String item;
    private BigDecimal payment;
    private Date date;

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
        BTCTransaction that = (BTCTransaction) o;
        return Objects.equal(item, that.item) &&
                Objects.equal(payment, that.payment) &&
                Objects.equal(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(item, payment, date);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("item", item)
                .add("payment", payment)
                .add("date", date)
                .toString();
    }
}
