package lk.ijse.dep.individualProject.view.util;

public class CategoryTM {

    private int cId;
    private String cName;
    private String cType;
    private String cDescription;

    public CategoryTM(int cId, String cName, String cType, String cDescription) {
        this.cId = cId;
        this.cName = cName;
        this.cType = cType;
        this.cDescription = cDescription;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getCType() {
        return cType;
    }

    public void setCType(String cType) {
        this.cType = cType;
    }

    public String getCDescription() {
        return cDescription;
    }

    public void setCDescription(String cDescription) {
        this.cDescription = cDescription;
    }
}
