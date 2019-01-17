package lk.ijse.dep.individualProject.dto;

import java.sql.Date;

public class TransactionDTO implements SuperDTO {

    private int tId;
    private Date date;
    private String note;
    private int value;
    private int accountId;
    private int scId;

    public TransactionDTO() {
    }

    public TransactionDTO(int tId, Date date, String note, int value, int accountId, int scId) {
        this.tId = tId;
        this.date = date;
        this.note = note;
        this.value = value;
        this.accountId = accountId;
        this.scId = scId;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }
}
