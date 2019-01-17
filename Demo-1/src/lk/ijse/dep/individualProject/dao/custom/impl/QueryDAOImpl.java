package lk.ijse.dep.individualProject.dao.custom.impl;

import lk.ijse.dep.individualProject.dao.CrudUtil;
import lk.ijse.dep.individualProject.dao.custom.QueryDAO;
import lk.ijse.dep.individualProject.entity.CustomerEntity;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public Optional<List<CustomerEntity>> getAllSubCategoriesWithCategoryNames() throws Exception {

        ResultSet rst = CrudUtil.execute("SELECT scId,scName,cName,cType,scDescription FROM SubCategory INNER JOIN Category ON SubCategory.cId = Category.cId");

        List<CustomerEntity>  list = new ArrayList<>();

        while (rst.next()){
            list.add(new CustomerEntity(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(5),
                    rst.getString(3),
                    rst.getString(4))
            );
        }
        return Optional.ofNullable(list);
    }

    @Override
    public Optional<List<CustomerEntity>> getAllTransactionByDate(Date date) throws Exception {

        List<CustomerEntity>  list = new ArrayList<>();

        ResultSet rst = CrudUtil.execute("SELECT tId,tDate,tNote,tvalue,aId,SubCategory.scId,Category.cId,cType,cName,scName FROM Transaction\n" +
                "  INNER JOIN SubCategory ON Transaction.scId = SubCategory.scId\n" +
                "  INNER JOIN Category ON SubCategory.cId = Category.cId WHERE tDate = ?",date);

        while (rst.next()){
            list.add(new CustomerEntity(rst.getInt(6),rst.getString(8),
                    rst.getInt(7),rst.getInt(5),
                    rst.getInt(1), rst.getDate(2),
                    rst.getString(3),rst.getInt(4),rst.getString(10),rst.getString(9)));
        }

        return Optional.ofNullable(list);
    }

    @Override
    public Optional<List<CustomerEntity>> getAllAccountsWithBalance() throws Exception {

        List<CustomerEntity>  list = new ArrayList<>();

        ResultSet rst = CrudUtil.execute("SELECT Account.aId,aName,aDescription,sum(tvalue) AS total FROM Account INNER JOIN Transaction ON Account.aId = Transaction.aId GROUP BY aId;");

        while (rst.next()){
            list.add(new CustomerEntity(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4)));
        }
        return Optional.ofNullable(list);
    }

    @Override
    public List<CustomerEntity> getAllTransfers() throws Exception {
        List<CustomerEntity>  list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT Transaction.tId ,Transaction.aId , Account.aName , Transaction.tvalue FROM Transaction INNER JOIN Account ON Transaction.aId = Account.aId WHERE scId = 2");
        while (rst.next()){
            list.add(new CustomerEntity(rst.getInt(1),rst.getInt(2),rst.getString(3),rst.getInt(4)));
        }

        return list;
    }

    @Override
    public Optional<List<CustomerEntity>> getCategoryTotal(String type, int year, int month) throws Exception {
        List<CustomerEntity>  list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT Category.cId , Category.cName , SUM(Transaction.tvalue) AS Total FROM Category\n" +
                "  INNER JOIN SubCategory ON Category.cId = SubCategory.cId\n" +
                "  INNER JOIN Transaction ON SubCategory.scId = Transaction.scId\n" +
                "WHERE cType = ? AND YEAR(Transaction.tDate)=? AND MONTH(Transaction.tDate)=? GROUP BY Category.cId",type,year,month);
        while (rst.next()){
            list.add(new CustomerEntity(rst.getInt(1),rst.getString(2),rst.getInt(3)));
        }

        return Optional.ofNullable(list);
    }

    @Override
    public Optional<List<CustomerEntity>> getSubCategoryTotal(int cId, int year, int month) throws Exception {

        List<CustomerEntity>  list = new ArrayList<>();

        ResultSet rst = CrudUtil.execute("SELECT SubCategory.scName , SUM(Transaction.tvalue) AS Total FROM SubCategory\n" +
                "INNER JOIN Transaction ON SubCategory.scId = Transaction.scId\n" +
                "WHERE SubCategory.cId = ? AND YEAR(Transaction.tDate)=? AND MONTH(Transaction.tDate)=? GROUP BY SubCategory.scId",
                cId,year,month);

        while (rst.next()){
            list.add(new CustomerEntity(rst.getString(1),rst.getInt(2)));
        }

        return Optional.ofNullable(list);
    }
}
