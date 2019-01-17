package lk.ijse.dep.individualProject.view.util;

public class CategoryCombo {

    private int cId;
    private String cName;
    private String cType;

    public CategoryCombo(int cId, String cName, String cType) {
        this.cId = cId;
        this.cName = cName;
        this.cType = cType;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
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

    @Override
    public String toString() {
        return cName+" - "+cType;
    }
}