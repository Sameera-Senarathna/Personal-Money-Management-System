package lk.ijse.dep.individualProject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dep.individualProject.business.BOFactory;
import lk.ijse.dep.individualProject.business.custom.AccountBO;
import lk.ijse.dep.individualProject.business.custom.TransactionBO;
import lk.ijse.dep.individualProject.db.DBConnection;
import lk.ijse.dep.individualProject.dto.AccountDTO;
import lk.ijse.dep.individualProject.dto.TransferDTO;
import lk.ijse.dep.individualProject.view.util.AccountCombo;
import lk.ijse.dep.individualProject.view.util.TransferTM;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FundTransaferViewController {

    @FXML
    private JFXComboBox<AccountCombo> comboFromAccount;

    @FXML
    private JFXComboBox<AccountCombo> comboToAccount;

    @FXML
    private JFXTextField txtTransferAmount;

    @FXML
    private JFXButton btnTransfer;

    @FXML
    private TableView<TransferTM> tblTransfer;

    private boolean isEdit = false;

    @FXML
    private JFXButton btnDelelte;

    AccountBO accountBO = BOFactory.getInstance().getBO(BOFactory.BOType.AccountBO);
    TransactionBO transactionBO = BOFactory.getInstance().getBO(BOFactory.BOType.TRANSACTION);

    public void initialize(){

        btnDelelte.setDisable(true);

        //-------------------- Fill From Account Combo --------------------------------//

        List<AccountDTO> accounts = null;
        try {
            accounts = accountBO.getAccounts();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        for (AccountDTO accountDTO : accounts) {
            comboFromAccount.getItems().add(new AccountCombo(accountDTO.getAId(),accountDTO.getAccountName()));
        }

        //--------------------  Fill Initial To Account Combo ------------------------//


        for (AccountDTO accountDTO : accounts) {
            comboToAccount.getItems().add(new AccountCombo(accountDTO.getAId(),accountDTO.getAccountName()));
        }

        //------------------- Lister To From Combo ----------------------------------//

        List<AccountDTO> finalAccounts = accounts; // Added by Intelij Idea
        comboFromAccount.valueProperty().addListener(new ChangeListener<AccountCombo>() {
            @Override
            public void changed(ObservableValue<? extends AccountCombo> observable, AccountCombo oldValue, AccountCombo newValue) {
                if(newValue == null){
                    return;
                }

                comboToAccount.getItems().clear();

                for (AccountDTO accountDTO : finalAccounts) {
                    if(accountDTO.getAId()==newValue.getAccountId()){
                        continue;
                    }
                    comboToAccount.getItems().add(new AccountCombo(accountDTO.getAId(),accountDTO.getAccountName()));
                }

            }
        });

        //----------------- Set Table -------------------------------------------------//

        tblTransfer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("fromAccountName"));
        tblTransfer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("toAccountName"));
        tblTransfer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("transferValue"));

        refershTable();

        //---------------- Set Listener to Table -------------------------------------//

        tblTransfer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TransferTM>() {
            @Override
            public void changed(ObservableValue<? extends TransferTM> observable, TransferTM oldValue, TransferTM newValue) {
                if(newValue == null){
                    return;
                }

                    isEdit = true;
                    btnDelelte.setDisable(false);
                    btnTransfer.setText("Edit");


                    ObservableList<AccountCombo> items = comboFromAccount.getItems();
                    for (AccountCombo a : items) {
                        if(a.getAccountId()==newValue.getFromAccountID()){
                            comboFromAccount.setValue(a);
                            break;
                        }
                    }

                    ObservableList<AccountCombo> items1 = comboToAccount.getItems();
                    for (AccountCombo a : items1) {
                        if(a.getAccountId()==newValue.getToAccountID()){
                            comboToAccount.setValue(a);
                            break;
                        }
                    }

                    txtTransferAmount.setText(newValue.getTransferValue()+"");
            }
        });

    }

    @FXML
    void clickDeleteBtn(ActionEvent event) {
        if (isEdit){

            Connection dbconnection = null;   // Transaction Control

            try {

                dbconnection = DBConnection.getConnection();
                dbconnection.setAutoCommit(false);

                boolean result_1 = transactionBO.deleteTransaction(tblTransfer.getSelectionModel().getSelectedItem().getToTransactionID());         // Transaction Should Apply

                if (!result_1){
                    return;
                }

                boolean result_2 = transactionBO.deleteTransaction(tblTransfer.getSelectionModel().getSelectedItem().getFromTransactionID());       // Transaction Should Apply

                if (!result_2){
                    dbconnection.rollback();
                    return;
                }

                dbconnection.commit();

                new Alert(Alert.AlertType.INFORMATION,"Transfer Record Deleted successfully", ButtonType.OK).showAndWait();


            }catch (Exception e){

                try {
                    new Alert(Alert.AlertType.ERROR,"Transfer recode deleting failed", ButtonType.OK).showAndWait();
                    dbconnection.commit();
                    dbconnection.setAutoCommit(true);
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

            refershTable();
            clear();

        }
    }

    @FXML
    void clickHome(MouseEvent event) {
        Stage primaryStage = (Stage) btnDelelte.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/AccountVIew.fxml"));
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void clickTransferBtn(ActionEvent event){

        AccountCombo fromAccount = comboFromAccount.getValue();
        AccountCombo toAccount = comboToAccount.getValue();
        String transferAccount = txtTransferAmount.getText();

        if(fromAccount==null){
            new Alert(Alert.AlertType.ERROR,"Please Select From Account for the Transfer", ButtonType.OK).showAndWait();
            comboFromAccount.requestFocus();
            return;
        }else if(toAccount == null){
            new Alert(Alert.AlertType.ERROR,"Please Select to Account for the Transfer", ButtonType.OK).showAndWait();
            comboToAccount.requestFocus();
            return;
        }else if(transferAccount.trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Transfer Amount", ButtonType.OK).showAndWait();
            txtTransferAmount.requestFocus();
            return;
        }else if(!isInt(transferAccount)){
            new Alert(Alert.AlertType.ERROR,"Transfer Amount Should be Integer", ButtonType.OK).showAndWait();
            txtTransferAmount.requestFocus();
            return;
        }

        if(!isEdit){

            TransferDTO transferDTO = new TransferDTO(fromAccount.getAccountId(), toAccount.getAccountId(), null, null, 0,0,Integer.parseInt(transferAccount));

            boolean result = false;
            try {
                result = transactionBO.addTransfer(transferDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Fund Transfer Successful", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fund Transfer Operation failed", ButtonType.OK).showAndWait();
                return;
            }
        }else {

            TransferDTO transferDTO = new TransferDTO(fromAccount.getAccountId(), toAccount.getAccountId(), null, null, tblTransfer.getSelectionModel().getSelectedItem().getFromTransactionID(),tblTransfer.getSelectionModel().getSelectedItem().getToTransactionID(),Integer.parseInt(transferAccount));

            System.out.println(transferDTO);

            boolean result = false;
            try {
                result = transactionBO.updateTransfer(transferDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Fund Transfer Updated Successful", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fund Transfer Update Operation failed", ButtonType.OK).showAndWait();
                return;
            }

        }

        refershTable();
        clear();


    }

    private void clear(){

        comboFromAccount.getSelectionModel().clearSelection();
        comboToAccount.getSelectionModel().clearSelection();
        txtTransferAmount.clear();

        isEdit = false;
        btnDelelte.setDisable(true);
        btnTransfer.setText("Transfer");

        tblTransfer.getSelectionModel().clearSelection();
    }

    private boolean isInt(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void refershTable() {

        List<TransferDTO> allTransfer = null;
        try {
            allTransfer = transactionBO.getAllTransfer();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        tblTransfer.getItems().clear();

        for (TransferDTO t : allTransfer) {
            tblTransfer.getItems().add(new TransferTM(t.getFromAccountID(),t.getToAccountID(),t.getFromAccountName(),t.getToAccountName(),t.getFromTransactionID(),t.getToTransactionID(),t.getTransferValue()));
        }
    }

}
