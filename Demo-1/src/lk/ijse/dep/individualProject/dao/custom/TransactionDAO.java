package lk.ijse.dep.individualProject.dao.custom;

import lk.ijse.dep.individualProject.dao.CrudDAO;
import lk.ijse.dep.individualProject.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionDAO extends CrudDAO<Transaction,Integer> {

    int transactionCountByAccount(int accountID) throws Exception;

    boolean deleteTransactionsByAccount(int accountID) throws Exception;

    Optional<List<Transaction>> getTransactionBySubCategoryID(int subCategoryId) throws Exception;

}
