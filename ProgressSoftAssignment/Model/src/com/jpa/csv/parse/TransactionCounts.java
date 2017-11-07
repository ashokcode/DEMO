package com.jpa.csv.parse;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "TransactionCounts.findAll", query = "select o from TransactionCounts o") })
@Table(name = "\"transaction_counts\"")
@SuppressWarnings("oracle.toplink.jpa.entity.id-mapping-unkown")
public class TransactionCounts implements Serializable {
    private static final long serialVersionUID = -4350348921192872595L;
    
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String orderingcurrency;
    @Column(name = "TRANSACTION_COUNT", nullable = false)
    private long transactionCount;

    public TransactionCounts() {
    }

    public TransactionCounts(String orderingcurrency, long transactionCount) {
        this.orderingcurrency = orderingcurrency;
        this.transactionCount = transactionCount;
    }

    public String getOrderingcurrency() {
        return orderingcurrency;
    }

    public void setOrderingcurrency(String orderingcurrency) {
        this.orderingcurrency = orderingcurrency;
    }

    public long getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(long transactionCount) {
        this.transactionCount = transactionCount;
    }
}
