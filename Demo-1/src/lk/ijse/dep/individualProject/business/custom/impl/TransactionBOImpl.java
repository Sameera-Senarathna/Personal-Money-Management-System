package lk.ijse.dep.individualProject.business.custom.impl;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.dep.individualProject.business.Converter;
import lk.ijse.dep.individualProject.business.custom.TransactionBO;
import lk.ijse.dep.individualProject.dao.DAOFactory;
import lk.ijse.dep.individualProject.dao.custom.QueryDAO;
import lk.ijse.dep.individualProject.dao.custom.TransactionDAO;
import lk.ijse.dep.individualProject.db.DBConnection;
import lk.ijse.dep.individualProject.dto.TransactionDTO;
import lk.ijse.dep.individualProject.dto.TransactionViewDTO;
import lk.ijse.dep.individualProject.dto.TransferDTO;
import lk.ijse.dep.individualProject.entity.CustomerEntity;
import lk.ijse.dep.individualProject.entity.Transaction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionBOImpl implements TransactionBO {

    TransactionDAO transactionDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TRANSACTION);
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public boolean addTransaction(TransactionDTO transactionDTO) throws Exception {
        return transactionDAO.save(Converter.getEntity(transactionDTO));
    }

    @Override
    public List<TransactionViewDTO> getAllTransactionByDate(Date date) throws Exception {
        return queryDAO.getAllTransactionByDate(date).map(new Function<List<CustomerEntity>, List<TransactionViewDTO>>() {
            @Override
            public List<TransactionViewDTO> apply(List<CustomerEntity> customerEntities) {
                return Converter.getDTOList(customerEntities,TransactionViewDTO.class);
            }
        }).get();

    }

    @Override
    public boolean updateTransaction(TransactionDTO transactionDTO) throws Exception {
        return transactionDAO.update(Converter.getEntity(transactionDTO));
    }

    @Override
    public boolean deleteTransaction(int transactionId) throws Exception {
        return transactionDAO.delete(transactionId);
    }

    @Override
    public boolean addTransfer(TransferDTO transferDTO) throws Exception {

        //--- Account DTO convertion to Transaction Convertion Happend Here -------------- //

        TransactionDTO toTransaction = new TransactionDTO(0,Date.valueOf(LocalDate.now()),"Transfer between Two Accounts",
                transferDTO.getTransferValue(),transferDTO.getToAccountID(),2);

        TransactionDTO fromTransaction = new TransactionDTO(0,Date.valueOf(LocalDate.now()),"Transfer between Two Accounts",
                (transferDTO.getTransferValue()*(-1)),transferDTO.getFromAccountID(),2);

        Connection dbconnection = null;

        try{

            dbconnection = DBConnection.getConnection();
            dbconnection.setAutoCommit(false);

            boolean result1 = transactionDAO.save(Converter.getEntity(toTransaction));      // Transaction Should Apply Here

            if (!result1){
                return false;
            }

            boolean result2= transactionDAO.save(Converter.getEntity(fromTransaction));     // Transaction Should Apply Here

            if (!result2){
                dbconnection.rollback();
                return false;
            }

            dbconnection.commit();

        }catch (Exception e){
            try {
                dbconnection.commit();
                dbconnection.setAutoCommit(true);
                return false;
            }catch (SQLException e1){
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

        }finally {
            try {
                dbconnection.setAutoCommit(true);
            }catch (SQLException e1){
                Logger.getLogger("").log(Level.SEVERE,null,e1);
            }
        }

        return true;

    }

    @Override
    public List<TransferDTO> getAllTransfer() throws Exception {
        List<CustomerEntity> a = queryDAO.getAllTransfers();
        List<TransferDTO> allTransfersDTO = new ArrayList<>();

        for(int i = 0 ; i<a.size();i++){
            allTransfersDTO.add(new TransferDTO(a.get(i+1).getaId(),a.get(i).getaId(),a.get(i+1).getaName(),a.get(i).getaName(),a.get(i+1).gettId(),a.get(i).gettId(),a.get(i).getTvalue()));
            i++;
        }

        return allTransfersDTO;
    }

    @Override
    public boolean updateTransfer(TransferDTO transferDTO) throws Exception {

        TransactionDTO toTransaction = new TransactionDTO(transferDTO.getToTransactionID(),Date.valueOf(LocalDate.now()),"Transfer between Two Accounts",
                transferDTO.getTransferValue(),transferDTO.getToAccountID(),2);

        TransactionDTO fromTransaction = new TransactionDTO(transferDTO.getFromTransactionID(),Date.valueOf(LocalDate.now()),"Transfer between Two Accounts",
                (transferDTO.getTransferValue()*(-1)),transferDTO.getFromAccountID(),2);

        boolean result_1 = transactionDAO.update(Converter.getEntity(toTransaction)); // Transaction Should Apply here
        boolean result_2 = transactionDAO.update(Converter.getEntity(fromTransaction)); // Transaction Should Apply here

        return result_1 && result_2;
    }

    @Override
    public List<TransactionDTO> getTransactionBySubCategoryID(int subCategoryId) throws Exception {
        return transactionDAO.getTransactionBySubCategoryID(subCategoryId).map(new Function<List<Transaction>, List<TransactionDTO>>() {
            @Override
            public List<TransactionDTO> apply(List<Transaction> transactions) {
                return Converter.getDTOList(transactions);
            }
        }).get();
    }
}
