package lk.ijse.dep.individualProject.dto;

public class TableReportDrillDTO implements SuperDTO{

    private String subCategoryName;
    private int subCategoryMonthTotal;

    public TableReportDrillDTO() {
    }



    public TableReportDrillDTO(String subCategoryName, int subCategoryMonthTotal) {
        this.subCategoryName = subCategoryName;
        this.subCategoryMonthTotal = subCategoryMonthTotal;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getSubCategoryMonthTotal() {
        return subCategoryMonthTotal;
    }

    public void setSubCategoryMonthTotal(int subCategoryMonthTotal) {
        this.subCategoryMonthTotal = subCategoryMonthTotal;
    }
}
