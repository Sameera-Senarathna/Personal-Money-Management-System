package lk.ijse.dep.individualProject.dao.custom.impl;

import lk.ijse.dep.individualProject.dao.CrudUtil;
import lk.ijse.dep.individualProject.dao.custom.AccountDAO;
import lk.ijse.dep.individualProject.dto.AccountTableDTO;
import lk.ijse.dep.individualProject.entity.Account;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public Optional<Account> find(Integer key) throws Exception {

        ResultSet rst = CrudUtil.execute("SELECT *  FROM Account WHERE aId=?",key);
        Account account = null;
        if(rst.next()){
            account = new Account(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3));
        }
        return Optional.ofNullable(account);
    }

    @Override
    public Optional<List<Account>> findAll() throws Exception {

        List<Account> allAccount = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT *  FROM Account");
        while (rst.next()){
            allAccount.add( new Account(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3)));
        }

        return Optional.ofNullable(allAccount);
    }

    @Override
    public boolean save(Account entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Account (aName,aDescription) VALUES (?,?)",entity.getaName(),entity.getaDescription())>0;
    }

    @Override
    public boolean update(Account entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Account SET aName=? , aDescription=? WHERE aId=?",entity.getaName(),entity.getaDescription(),entity.getaId())>0;
    }

    @Override
    public boolean delete(Integer key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Account WHERE aID=?",key)>0;
    }

    @Override
    public int getAccountIDByName(String accountName) throws Exception {
         ResultSet rst = CrudUtil.execute("SELECT aId FROM Account WHERE aName = ?",accountName);
         rst.next();
         return rst.getInt(1);
    }


}
