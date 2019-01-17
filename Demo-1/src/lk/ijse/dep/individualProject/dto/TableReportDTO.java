package lk.ijse.dep.individualProject.dto;

public class TableReportDTO implements SuperDTO {

    private int categoryID;
    private String categoryName;
    private int categoryTotal;

    public TableReportDTO(int categoryID, String categoryName, int categoryTotal) {
        this.setCategoryID(categoryID);
        this.setCategoryName(categoryName);
        this.setCategoryTotal(categoryTotal);
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryTotal() {
        return categoryTotal;
    }

    public void setCategoryTotal(int categoryTotal) {
        this.categoryTotal = categoryTotal;
    }
}
