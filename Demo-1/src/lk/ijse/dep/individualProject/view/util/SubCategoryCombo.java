package lk.ijse.dep.individualProject.view.util;

public class SubCategoryCombo {

    private int scId;
    private String scName;

    public SubCategoryCombo() {
    }

    public SubCategoryCombo(int scId, String scName) {
        this.scId = scId;
        this.scName = scName;
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

    @Override
    public String toString() {
        return getScName();
    }
}
