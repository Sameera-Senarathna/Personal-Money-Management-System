package lk.ijse.dep.individualProject.dto;

public class TransferDTO implements SuperDTO {

    private int fromAccountID;
    private int toAccountID;
    private String fromAccountName;
    private String toAccountName;
    private int fromTransactionID;
    private int toTransactionID;
    private int transferValue;

    public TransferDTO(int fromAccountID, int toAccountID, String fromAccountName, String toAccountName, int fromTransactionID, int toTransactionID, int transferValue) {
        this.fromAccountID = fromAccountID;
        this.toAccountID = toAccountID;
        this.fromAccountName = fromAccountName;
        this.toAccountName = toAccountName;
        this.fromTransactionID = fromTransactionID;
        this.toTransactionID = toTransactionID;
        this.transferValue = transferValue;
    }

    public int getFromAccountID() {
        return fromAccountID;
    }

    public void setFromAccountID(int fromAccountID) {
        this.fromAccountID = fromAccountID;
    }

    public int getToAccountID() {
        return toAccountID;
    }

    public void setToAccountID(int toAccountID) {
        this.toAccountID = toAccountID;
    }

    public String getFromAccountName() {
        return fromAccountName;
    }

    public void setFromAccountName(String fromAccountName) {
        this.fromAccountName = fromAccountName;
    }

    public String getToAccountName() {
        return toAccountName;
    }

    public void setToAccountName(String toAccountName) {
        this.toAccountName = toAccountName;
    }

    public int getFromTransactionID() {
        return fromTransactionID;
    }

    public void setFromTransactionID(int fromTransactionID) {
        this.fromTransactionID = fromTransactionID;
    }

    public int getToTransactionID() {
        return toTransactionID;
    }

    public void setToTransactionID(int toTransactionID) {
        this.toTransactionID = toTransactionID;
    }

    public int getTransferValue() {
        return transferValue;
    }

    public void setTransferValue(int transferValue) {
        this.transferValue = transferValue;
    }

    @Override
    public String toString() {
        return "TransferDTO{" +
                "fromAccountID=" + fromAccountID +
                ", toAccountID=" + toAccountID +
                ", fromAccountName='" + fromAccountName + '\'' +
                ", toAccountName='" + toAccountName + '\'' +
                ", fromTransactionID=" + fromTransactionID +
                ", toTransactionID=" + toTransactionID +
                ", transferValue=" + transferValue +
                '}';
    }
}
