package lk.ijse.dep.individualProject.entity;

public class Account extends SuperEntity {

    private int aId;
    private String aName;
    private String aDescription;

    public Account() {
    }

    public Account(int aId, String aName, String aDescription) {
        this.aId = aId;
        this.aName = aName;
        this.aDescription = aDescription;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
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
}
