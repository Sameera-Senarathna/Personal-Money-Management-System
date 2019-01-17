package lk.ijse.dep.individualProject.entity;

import java.sql.Date;

public class Transaction extends SuperEntity{

    private int tId;
    private Date tDate;
    private String tNote;
    private int tValue;
    private int aId;
    private int scId;

    public Transaction() {
    }

    public Transaction(int tId, Date tDate, String tNote, int tValue, int aId, int scId) {
        this.tId = tId;
        this.tDate = tDate;
        this.tNote = tNote;
        this.tValue = tValue;
        this.aId = aId;
        this.scId = scId;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(Date tDate) {
        this.tDate = tDate;
    }

    public String gettNote() {
        return tNote;
    }

    public void settNote(String tNote) {
        this.tNote = tNote;
    }

    public int gettValue() {
        return tValue;
    }

    public void settValue(int tValue) {
        this.tValue = tValue;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }
}
