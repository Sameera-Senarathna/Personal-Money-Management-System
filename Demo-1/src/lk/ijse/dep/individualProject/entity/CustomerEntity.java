package lk.ijse.dep.individualProject.entity;

import java.sql.Date;

public class CustomerEntity {

    // sub category fields

    private int scId ;
    private String scName;
    private String scDescription;

    // category Fields

    private String cName;
    private String cType;
    private int cId;

    // account fields

    private int aId;
    private String aName;
    private String aDescription;

    // transaction fields

    private int tId;
    private Date tDate;
    private String tNote;
    private int tvalue;

    // function variabls

    private int balance;
    private int categotyTotal;
    private int subCategoryTotal;

    public CustomerEntity(String scName, int subCategoryTotal) {
        this.scName = scName;
        this.subCategoryTotal = subCategoryTotal;
    }

    public CustomerEntity(int cId, String cName, int categotyTotal) {
        this.cName = cName;
        this.cId = cId;
        this.categotyTotal = categotyTotal;
    }

    public CustomerEntity(int scId, String cType, int cId, int aId, int tId, Date tDate, String tNote, int tvalue, String scName, String cName) {
        this.scId = scId;
        this.cType = cType;
        this.cId = cId;
        this.aId = aId;
        this.tId = tId;
        this.tDate = tDate;
        this.tNote = tNote;
        this.tvalue = tvalue;
        this.scName = scName;
        this.cName = cName;
    }

    public CustomerEntity(int tId ,int aId, String aName,  int tvalue) {
        this.aId = aId;
        this.aName = aName;
        this.tId = tId;
        this.tvalue = tvalue;
    }

    public CustomerEntity(int scId, String scName, String scDescription, String cName, String cType) {
        this.scId = scId;
        this.scName = scName;
        this.scDescription = scDescription;
        this.cName = cName;
        this.cType = cType;
    }

    public CustomerEntity(int aId, String aName, String aDescription, int balance) {
        this.aId = aId;
        this.aName = aName;
        this.aDescription = aDescription;
        this.balance = balance;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaDescription() {
        return aDescription;
    }

    public void setaDescription(String aDescription) {
        this.aDescription = aDescription;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public String getScDescription() {
        return scDescription;
    }

    public void setScDescription(String scDescription) {
        this.scDescription = scDescription;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
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

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getCategotyTotal() {
        return categotyTotal;
    }

    public void setCategotyTotal(int categotyTotal) {
        this.categotyTotal = categotyTotal;
    }

    public int getSubCategoryTotal() {
        return subCategoryTotal;
    }

    public void setSubCategoryTotal(int subCategoryTotal) {
        this.subCategoryTotal = subCategoryTotal;
    }
}
