package lk.ijse.dep.individualProject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import lk.ijse.dep.individualProject.dao.custom.QueryDAO;
import lk.ijse.dep.individualProject.dto.AccountDTO;
import lk.ijse.dep.individualProject.dto.AccountTableDTO;
import lk.ijse.dep.individualProject.dto.TransactionDTO;
import lk.ijse.dep.individualProject.view.util.AccountTM;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountManagementController {

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtInitialAmount;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelelte;

    @FXML
    private TableView<AccountTM> tblAccount;

    @FXML
    private JFXComboBox<?> comboFromAccount;

    @FXML
    private JFXComboBox<?> comboToAccount;

    @FXML
    private JFXTextField txtTransferAmount;

    @FXML
    private JFXButton btnTransfer;

    boolean isEdit = false;

    AccountBO accountBO = BOFactory.getInstance().getBO(BOFactory.BOType.AccountBO);

    @FXML
    void clickDeleteBtn(ActionEvent event) {

        if(isEdit){

            boolean result = false;

            try {
                result = accountBO.deleteAccount(tblAccount.getSelectionModel().getSelectedItem().getAccountId());
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Account Successfully Deleted", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Account Can Not be Deleted.\nTransactions are available...", ButtonType.OK).showAndWait();
                return;
            }
            clear();
            refreshTable();
        }
    }

    public void initialize(){

        btnDelelte.setDisable(true);

        //------------- Set Table Value---------------------------------//

        tblAccount.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("accountName"));
        tblAccount.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("accountDescription"));
        tblAccount.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("balance"));

        refreshTable();

        // ------------------------ add Listener to Table ------------------------------//

        tblAccount.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AccountTM>() {
            @Override
            public void changed(ObservableValue<? extends AccountTM> observable, AccountTM oldValue, AccountTM newValue) {
                if(newValue == null){
                    return;
                }

                isEdit = true;
                txtInitialAmount.setVisible(false);
                btnSave.setText("Edit");
                btnDelelte.setDisable(false);

                txtName.setText(newValue.getAccountName());
                txtDescription.setText(newValue.getAccountDescription());
                txtInitialAmount.setText("0"); // Dump Value. Just to Avoid Alert When Editing

            }
        });

    }

    @FXML
    void clickHome(MouseEvent event) throws IOException {
        Stage primaryStage = (Stage) txtDescription.getScene().getWindow();
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/AccountVIew.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    @FXML
    void clickSaveBtn(ActionEvent event){
        if(txtName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please enter Account", ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        }else if(txtDescription.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please enter Description", ButtonType.OK).showAndWait();
            txtDescription.requestFocus();
            return;
        }else if(!isEdit && txtInitialAmount.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please enter Initial Amount of the Account", ButtonType.OK).showAndWait();
            txtInitialAmount.requestFocus();
            return;
        }
        String name = txtName.getText().trim();
        String description = txtDescription.getText().trim();

        String initialValue = null;

        initialValue = txtInitialAmount.getText().trim();


        int initialValueInt = 0;

        if(!isInt(initialValue) ){
            new Alert(Alert.AlertType.ERROR,"Initial Value Should be An Numeric", ButtonType.OK).showAndWait();
            txtInitialAmount.requestFocus();
            return;
        }else {
            initialValueInt = Integer.parseInt(initialValue);
        }

        if(!isEdit){

            AccountDTO accountDTO = new AccountDTO(0,name,description); //------- Account DTO
            TransactionDTO transactionDTO = new TransactionDTO(0, Date.valueOf(LocalDate.now()),"Initial Value",initialValueInt,0,1);// --------- Transaction DTO

            boolean result = false;
            try {
                result = accountBO.addAccount(accountDTO,transactionDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Account Successfully Created", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Account Creating Operation Failed", ButtonType.OK).showAndWait();
                return;
            }
        }else {
            AccountDTO accountDTO = new AccountDTO(tblAccount.getSelectionModel().getSelectedItem().getAccountId(),name,description);

            boolean result = false;
            try {
                result = accountBO.updateAccount(accountDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Account Successfully Updated", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Account Updating Operation Failed", ButtonType.OK).showAndWait();
                return;
            }
        }
        refreshTable();
        clear();
    }

    @FXML
    void clickTransferBtn(ActionEvent event) {

    }

    private boolean isInt(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void clear(){
        txtName.clear();
        txtDescription.clear();
        txtInitialAmount.clear();

        tblAccount.getSelectionModel().clearSelection();

        isEdit = false;
        txtInitialAmount.setVisible(true);
        btnSave.setText("Save");
        btnDelelte.setDisable(true);
    }

    private void refreshTable() {

        List<AccountTableDTO> allAccountWithBalances = null;

        try {
            allAccountWithBalances = accountBO.getAllAccountWithBalances();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        tblAccount.getItems().clear();

        for (AccountTableDTO a : allAccountWithBalances) {
            tblAccount.getItems().add(new AccountTM(a.getAccountId(),a.getAccountName(),a.getAccountDescription(),a.getBalance()));
        }
    }

}