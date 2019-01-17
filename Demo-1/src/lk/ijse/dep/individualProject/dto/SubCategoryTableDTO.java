package lk.ijse.dep.individualProject.dto;

public class SubCategoryTableDTO  implements SuperDTO{
    private int scId ;
    private String scName;
    private String scDescription;

    private String cName;
    private String cType;

    public SubCategoryTableDTO(int scId, String scName, String scDescription, String cName, String cType) {
        this.scId = scId;
        this.scName = scName;
        this.scDescription = scDescription;
        this.cName = cName;
        this.cType = cType;
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

    @Override
    public String toString() {
        return "SubCategoryTableDTO{" +
                "scId=" + scId +
                ", scName='" + scName + '\'' +
                ", scDescription='" + scDescription + '\'' +
                ", cName='" + cName + '\'' +
                ", cType='" + cType + '\'' +
                '}';
    }
}
