package lk.ijse.dep.individualProject.view.util;

import java.sql.Date;

public class TransactionTM {

    private int tid;
    private Date date;
    private String note;
    private int value;
    private int aid;
    private int scid;
    private int cid;
    private String type;
    private String categoryName;
    private String scName;

    public TransactionTM(int tid, Date date, String note, int value, int aid, int scid, int cid, String type, String categoryName, String scName) {
        this.tid = tid;
        this.date = date;
        this.note = note;
        this.value = value;
        this.aid = aid;
        this.scid = scid;
        this.cid = cid;
        this.type = type;
        this.categoryName = categoryName;
        this.scName = scName;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
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

    public String getValue() {

        if(value < 0){
            value = value*(-1);
        }
        return "Rs."+value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }
}
