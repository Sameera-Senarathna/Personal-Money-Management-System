package lk.ijse.dep.individualProject.dto;

public class CategoryDTO implements SuperDTO {

    private int cId;
    private String cName;
    private String cDescription;
    private String cType;

    public CategoryDTO(int cId, String cName, String cDescription, String cType) {
        this.cId = cId;
        this.cName = cName;
        this.cDescription = cDescription;
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

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cDescription='" + cDescription + '\'' +
                ", cType='" + cType + '\'' +
                '}';
    }
}
