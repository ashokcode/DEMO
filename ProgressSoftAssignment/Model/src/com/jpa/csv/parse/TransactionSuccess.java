package com.jpa.csv.parse;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name="transaction_success")
@NamedQueries({ @NamedQuery(name = "TransactionSuccess.findAll", query = "select o from transaction_success o") })
@Table(name = "\"transaction_success\"")
public class TransactionSuccess implements Serializable {
    private static final long serialVersionUID = 522063054518823578L;
    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private String filename;
    @Column(nullable = false)
    private String orderingcurrency;
    @Column(nullable = false)
    private String tocurrency;
    @Column(nullable = false)
    private Timestamp transactiontime;
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String uid;

    public TransactionSuccess() {
    }

    public TransactionSuccess(Long amount, String filename, String orderingcurrency, String tocurrency,
                              Timestamp transactiontime, String uid) {
        this.amount = amount;
        this.filename = filename;
        this.orderingcurrency = orderingcurrency;
        this.tocurrency = tocurrency;
        this.transactiontime = transactiontime;
        this.uid = uid;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOrderingcurrency() {
        return orderingcurrency;
    }

    public void setOrderingcurrency(String orderingcurrency) {
        this.orderingcurrency = orderingcurrency;
    }

    public String getTocurrency() {
        return tocurrency;
    }

    public void setTocurrency(String tocurrency) {
        this.tocurrency = tocurrency;
    }

    public Timestamp getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Timestamp transactiontime) {
        this.transactiontime = transactiontime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
