package lk.ijse.dep.individualProject.business.custom;

import lk.ijse.dep.individualProject.business.SuperBO;
import lk.ijse.dep.individualProject.dto.AccountDTO;
import lk.ijse.dep.individualProject.dto.TransactionDTO;
import lk.ijse.dep.individualProject.dto.TransactionViewDTO;
import lk.ijse.dep.individualProject.dto.TransferDTO;

import java.sql.Date;
import java.util.List;

public interface TransactionBO extends SuperBO{

    boolean addTransaction(TransactionDTO transactionDTO) throws Exception;

    List<TransactionViewDTO> getAllTransactionByDate(Date date) throws Exception;

    boolean updateTransaction(TransactionDTO transactionDTO) throws Exception;

    boolean deleteTransaction(int transactionId) throws Exception;

    boolean addTransfer(TransferDTO transferDTO) throws Exception;

    List<TransferDTO> getAllTransfer() throws Exception;

    boolean updateTransfer(TransferDTO transferDTO) throws Exception;

    List<TransactionDTO> getTransactionBySubCategoryID(int subCategoryId) throws Exception;

}
