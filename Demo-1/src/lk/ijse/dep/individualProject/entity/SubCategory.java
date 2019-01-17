package lk.ijse.dep.individualProject.entity;

public class SubCategory extends SuperEntity{

    private int scId;
    private String scName;
    private String scDescription;
    private int cId;

    public SubCategory() {
    }

    public SubCategory(int scId, String scName, String scDescription, int cId) {
        this.scId = scId;
        this.scName = scName;
        this.scDescription = scDescription;
        this.cId = cId;
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

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }
}
