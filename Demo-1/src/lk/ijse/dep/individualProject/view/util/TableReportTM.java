package lk.ijse.dep.individualProject.view.util;

public class TableReportTM {
    private int categoryID;
    private String categoryName;
    private int categoryTotal;
    private String percentage;

    public TableReportTM(int categoryID, String categoryName, int categoryTotal, String percentage) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryTotal = categoryTotal;
        this.percentage = percentage;
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

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
