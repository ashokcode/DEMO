package com.jpa.csv.parse;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "TransactionFailed.findAll", query = "select o from TransactionFailed o") })
@Table(name = "\"transaction_failed\"")
public class TransactionFailed implements Serializable {
    private static final long serialVersionUID = -2027736154017195819L;
    @Column(name = "FAILED_ROW", nullable = false)
    private String failedRow;
    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;
    @Column(nullable = false)
    private String remarks;
    @Id
    @Column(name = "uid", nullable = false)
    private long uid;

    public TransactionFailed() {
    }

    public TransactionFailed(String failedRow, String fileName, String remarks, long uid) {
        this.failedRow = failedRow;
        this.fileName = fileName;
        this.remarks = remarks;
        this.uid = uid;
    }

    public String getFailedRow() {
        return failedRow;
    }

    public void setFailedRow(String failedRow) {
        this.failedRow = failedRow;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
