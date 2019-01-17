package lk.ijse.dep.individualProject.dto;

public class SubCategoryDTO implements SuperDTO {

    private int scId;
    private String scName;
    private String scDescription;
    private int cId;

    public SubCategoryDTO(int scId, String scName, String scDescription, int cId) {

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
