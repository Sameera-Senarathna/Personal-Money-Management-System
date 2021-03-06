package lk.ijse.dep.individualProject.dto;

public class AccountTableDTO implements SuperDTO{

    private int accountId;
    private String accountName;
    private String accountDescription;
    private int balance;

    public AccountTableDTO(int accountId, String accountName, String accountDescription, int balance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountDescription = accountDescription;
        this.balance = balance;
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

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
