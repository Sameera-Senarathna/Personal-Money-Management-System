package lk.ijse.dep.individualProject.dto;

import java.sql.Date;

public class TransactionViewDTO implements SuperDTO {

    private int tId;
    private Date tDate;
    private String tNote;
    private int tvalue;
    private int aId;
    private int scId;
    private int cId;
    private String cType;
    private String categoryName;
    private String scName;


    public TransactionViewDTO() {
    }

    public TransactionViewDTO(int tId, Date tDate, String tNote, int tvalue, int aId, int scId, int cId, String cType, String categoryName, String scName) {
        this.tId = tId;
        this.tDate = tDate;
        this.tNote = tNote;
        this.tvalue = tvalue;
        this.aId = aId;
        this.scId = scId;
        this.cId = cId;
        this.cType = cType;
        this.categoryName = categoryName;
        this.scName = scName;
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

    public int getTvalue() {
        return tvalue;
    }

    public void setTvalue(int tvalue) {
        this.tvalue = tvalue;
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

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }
}
