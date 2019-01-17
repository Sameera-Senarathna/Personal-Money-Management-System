package lk.ijse.dep.individualProject.view.util;

public class SubCategoryTM {

    private int scId;
    private String scName;
    private String cName;
    private String cType;
    private String scDescription;

    public SubCategoryTM(int scId, String scName, String cName, String cType, String scDescription) {
        this.scId = scId;
        this.scName = scName;
        this.cName = cName;
        this.cType = cType;
        this.scDescription = scDescription;
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

    public String getScDescription() {
        return scDescription;
    }

    public void setScDescription(String scDescription) {
        this.scDescription = scDescription;
    }
}
