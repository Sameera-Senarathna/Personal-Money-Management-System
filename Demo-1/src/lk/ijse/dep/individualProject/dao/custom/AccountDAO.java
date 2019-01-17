package lk.ijse.dep.individualProject.dao.custom;

import lk.ijse.dep.individualProject.dao.CrudDAO;
import lk.ijse.dep.individualProject.dto.AccountTableDTO;
import lk.ijse.dep.individualProject.entity.Account;

import java.util.List;

public interface AccountDAO extends CrudDAO<Account,Integer> {

    int getAccountIDByName(String accountName) throws Exception;



}
