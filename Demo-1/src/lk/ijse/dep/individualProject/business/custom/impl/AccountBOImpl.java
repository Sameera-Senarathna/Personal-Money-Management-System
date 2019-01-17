package lk.ijse.dep.individualProject.business.custom.impl;

import lk.ijse.dep.individualProject.business.Converter;
import lk.ijse.dep.individualProject.business.custom.AccountBO;
import lk.ijse.dep.individualProject.dao.DAOFactory;
import lk.ijse.dep.individualProject.dao.custom.AccountDAO;
import lk.ijse.dep.individualProject.dao.custom.QueryDAO;
import lk.ijse.dep.individualProject.dao.custom.TransactionDAO;
import lk.ijse.dep.individualProject.dto.AccountDTO;
import lk.ijse.dep.individualProject.dto.AccountTableDTO;
import lk.ijse.dep.individualProject.dto.TransactionDTO;
import lk.ijse.dep.individualProject.entity.Account;
import lk.ijse.dep.individualProject.entity.CustomerEntity;

import java.util.List;
import java.util.function.Function;

public class AccountBOImpl implements AccountBO {

    AccountDAO accountDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ACCOUNT);
    TransactionDAO transactionDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TRANSACTION);
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public boolean addAccount(AccountDTO accountDTO , TransactionDTO transactionDTO) throws Exception {

        // (1) Account Table Should Update;
        boolean createAccountResults = accountDAO.save(Converter.getEntity(accountDTO));

        //--- get account ID of newly Created Account
        int accountID = accountDAO.getAccountIDByName(accountDTO.getAccountName());

        //--- Add account ID to transactionDTO Object
        transactionDTO.setAccountId(accountID);

        // (2) Transaction Table Should Update;
        boolean addTransactionresult = transactionDAO.save(Converter.getEntity(transactionDTO));

        return addTransactionresult && createAccountResults;
    }

    @Override
    public List<AccountTableDTO> getAllAccountWithBalances() throws Exception {
        return queryDAO.getAllAccountsWithBalance().map(new Function<List<CustomerEntity>, List<AccountTableDTO>>() {
            @Override
            public List<AccountTableDTO> apply(List<CustomerEntity> customerEntities) {
                return Converter.getDTOList(customerEntities,AccountTableDTO.class);
            }
        }).get();
    }

    @Override
    public boolean updateAccount(AccountDTO accountDTO) throws Exception {
        return accountDAO.update(Converter.getEntity(accountDTO));
    }

    @Override
    public boolean deleteAccount(int accountID) throws Exception {

        int noOfTransaction = transactionDAO.transactionCountByAccount(accountID);
        if(noOfTransaction>1){
            return false;
        }else {
            boolean transactionDelete = transactionDAO.deleteTransactionsByAccount(accountID); // Transaction Needed
            boolean accountdelete = accountDAO.delete(accountID);                              // Transaction Needed
            return true;
        }
    }

    @Override
    public List<AccountDTO> getAccounts() throws Exception {
        return accountDAO.findAll().map(new Function<List<Account>, List<AccountDTO>>() {
            @Override
            public List<AccountDTO> apply(List<Account> accounts) {
                return Converter.getDTOList(accounts);
            }
        }).get();
    }



}
