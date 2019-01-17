package lk.ijse.dep.individualProject.dao.custom;

import lk.ijse.dep.individualProject.dao.SuperDAO;
import lk.ijse.dep.individualProject.dto.SubCategoryTableDTO;
import lk.ijse.dep.individualProject.entity.CustomerEntity;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface QueryDAO extends SuperDAO {

    Optional<List<CustomerEntity>> getAllSubCategoriesWithCategoryNames() throws Exception;

    Optional<List<CustomerEntity>> getAllTransactionByDate(Date date) throws Exception;

    Optional<List<CustomerEntity>> getAllAccountsWithBalance() throws Exception;

    List<CustomerEntity> getAllTransfers() throws Exception;

    Optional<List<CustomerEntity>> getCategoryTotal(String type , int year , int month) throws Exception;

    Optional<List<CustomerEntity>> getSubCategoryTotal(int cId, int year , int month) throws Exception;

}
