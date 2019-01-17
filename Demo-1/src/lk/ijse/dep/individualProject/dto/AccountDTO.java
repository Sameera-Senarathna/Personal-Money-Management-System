package lk.ijse.dep.individualProject.dto;

public class AccountDTO implements SuperDTO {

    private int aId;
    private String accountName;
    private String accountDescription;

    public AccountDTO(int aId, String accountName, String accountDescription) {
        this.aId = aId;
        this.accountName = accountName;
        this.accountDescription = accountDescription;
    }

    public int getAId() {
        return aId;
    }

    public void setAId(int aId) {
        this.aId = aId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

}
