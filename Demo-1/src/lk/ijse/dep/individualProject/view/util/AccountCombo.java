package lk.ijse.dep.individualProject.view.util;

public class AccountCombo {

    private int accountId;
    private String accountName;

    public AccountCombo() {
    }

    public AccountCombo(int accountId, String accountName) {
        this.accountId = accountId;
        this.accountName = accountName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return getAccountName();
    }
}
