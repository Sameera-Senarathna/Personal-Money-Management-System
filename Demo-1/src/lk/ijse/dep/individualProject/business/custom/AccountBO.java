package lk.ijse.dep.individualProject.business.custom;

import lk.ijse.dep.individualProject.business.SuperBO;
import lk.ijse.dep.individualProject.dto.AccountDTO;
import lk.ijse.dep.individualProject.dto.AccountTableDTO;
import lk.ijse.dep.individualProject.dto.TransactionDTO;

import java.util.List;

public interface AccountBO extends SuperBO {

    boolean addAccount(AccountDTO accountDTO,TransactionDTO transactionDTO) throws Exception;

    List<AccountDTO> getAccounts() throws Exception;

    List<AccountTableDTO> getAllAccountWithBalances() throws Exception;

    boolean updateAccount(AccountDTO accountDTO) throws Exception;

    boolean deleteAccount(int accountID) throws Exception;



}
