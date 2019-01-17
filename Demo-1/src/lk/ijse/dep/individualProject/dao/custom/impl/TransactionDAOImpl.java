package lk.ijse.dep.individualProject.dao.custom.impl;

import lk.ijse.dep.individualProject.dao.CrudUtil;
import lk.ijse.dep.individualProject.dao.custom.TransactionDAO;
import lk.ijse.dep.individualProject.entity.Transaction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public Optional<Transaction> find(Integer key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Transaction WHERE tId = ?",key);
        Transaction transaction = null;
        while (rst.next()){
            transaction = new Transaction(rst.getInt(1),
                    rst.getDate(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getInt(6));
        }
        return Optional.ofNullable(transaction);
    }

    @Override
    public Optional<List<Transaction>> findAll() throws Exception {

        List<Transaction> allTransaction = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Transaction");
        while (rst.next()){
            allTransaction.add(new Transaction(rst.getInt(1),
                    rst.getDate(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getInt(6)));
        }
        return Optional.ofNullable(allTransaction);
    }

    @Override
    public boolean save(Transaction entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Transaction (tDate,tNote,tvalue,aId,scId) VALUES (?,?,?,?,?)",entity.gettDate(),entity.gettNote(),entity.gettValue(),entity.getaId(),entity.getScId())>0;
    }

    @Override
    public boolean update(Transaction entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Transaction SET tDate=? , tNote=? , tvalue=? , aId=? , scId=? WHERE tId=?",entity.gettDate(),entity.gettNote(),entity.gettValue(),entity.getaId(),entity.getScId(),entity.gettId())>0;
    }

    @Override
    public boolean delete(Integer key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Transaction WHERE tId=?",key)>0;
    }

    @Override
    public int transactionCountByAccount(int accountID) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT COUNT(aId) FROM Transaction WHERE aID = ?", accountID);
        rst.next();
        return rst.getInt(1);
    }

    @Override
    public boolean deleteTransactionsByAccount(int accountID) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Transaction WHERE aId = ?",accountID)>0;
    }

    @Override
    public Optional<List<Transaction>> getTransactionBySubCategoryID(int subCategoryId) throws Exception {

        ResultSet rst = CrudUtil.execute("SELECT * FROM Transaction WHERE scId = ?", subCategoryId);

        List<Transaction> allTransaction = new ArrayList<>();

        while (rst.next()){
            allTransaction.add(new Transaction(rst.getInt(1),
                    rst.getDate(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getInt(6)));
        }
        return Optional.ofNullable(allTransaction);
    }

}
