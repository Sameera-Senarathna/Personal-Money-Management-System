package lk.ijse.dep.individualProject.view.util;

import java.text.DecimalFormat;

public class TableReportDrillTM {

    private String subCategoryName;
    private int subCategoryTotal;
    private String percentage;
    private int categoryTotol;

    public TableReportDrillTM() {
    }

    public TableReportDrillTM(String subCategoryName, int subCategoryTotal, int categoryTotol) {
        this.subCategoryName = subCategoryName;
        this.subCategoryTotal = subCategoryTotal;
        this.categoryTotol = categoryTotol;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getSubCategoryTotal() {
        if(subCategoryTotal<0){
            return subCategoryTotal*(-1);
        }
        return subCategoryTotal;
    }

    public void setSubCategoryTotal(int subCategoryTotal) {
        this.subCategoryTotal = subCategoryTotal;
    }

    public String getPercentage() {

        DecimalFormat format = new DecimalFormat("0.00");

        double doublePercentage = (subCategoryTotal/(double)categoryTotol)*100.0;
        percentage = doublePercentage + "";
        return format.format(doublePercentage) + " %";
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public int getCategoryTotol() {
        return categoryTotol;
    }

    public void setCategoryTotol(int categoryTotol) {
        this.categoryTotol = categoryTotol;
    }
}
