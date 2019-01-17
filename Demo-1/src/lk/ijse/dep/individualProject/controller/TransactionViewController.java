package lk.ijse.dep.individualProject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dep.individualProject.business.BOFactory;
import lk.ijse.dep.individualProject.business.custom.AccountBO;
import lk.ijse.dep.individualProject.business.custom.CategoryBO;
import lk.ijse.dep.individualProject.business.custom.SubCategoryBO;
import lk.ijse.dep.individualProject.business.custom.TransactionBO;
import lk.ijse.dep.individualProject.dto.*;
import lk.ijse.dep.individualProject.view.util.AccountCombo;
import lk.ijse.dep.individualProject.view.util.CategoryCombo;
import lk.ijse.dep.individualProject.view.util.SubCategoryCombo;
import lk.ijse.dep.individualProject.view.util.TransactionTM;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionViewController {

    @FXML
    private JFXComboBox<AccountCombo> comboAccount;

    @FXML
    private JFXComboBox<CategoryCombo> comboCategory;

    @FXML
    private JFXComboBox<SubCategoryCombo> comboSubCategory;

    @FXML
    private JFXTextField txtTransactionValue;

    @FXML
    private JFXRadioButton radioIncome;

    @FXML
    private JFXRadioButton radioExpense;

    @FXML
    private JFXDatePicker dateTransactionDate;

    @FXML
    private TableView<TransactionTM> tblTransaction;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelelte;

    @FXML
    private JFXDatePicker dateShowTransaction;

    @FXML
    private JFXTextField txtSpecialNote;

    private ToggleGroup type = new ToggleGroup();

    private boolean isEdit = false;

    CategoryBO categoryBO = BOFactory.getInstance().getBO(BOFactory.BOType.CATEGORYBO);
    SubCategoryBO subCategoryBO = BOFactory.getInstance().getBO(BOFactory.BOType.SUBCATEGORYBO);
    AccountBO accountBO = BOFactory.getInstance().getBO(BOFactory.BOType.AccountBO);
    TransactionBO transactionBO = BOFactory.getInstance().getBO(BOFactory.BOType.TRANSACTION);

    @FXML
    void clickDeleteBtn(ActionEvent event) {
        if(isEdit){
            boolean result = false;
            try {
                result = transactionBO.deleteTransaction(tblTransaction.getSelectionModel().getSelectedItem().getTid());
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Transaction Successfully Deleted", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Transaction Deletion Operation failed", ButtonType.OK).showAndWait();
                return;
            }

            refreshTable(tblTransaction.getSelectionModel().getSelectedItem().getDate());
            clear();
        }
    }


    public void initialize() {

        btnDelelte.setDisable(true);
        dateShowTransaction.setValue(LocalDate.now());

        //-------------------- Set Table View ------------------------//

        tblTransaction.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        tblTransaction.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("scName"));
        tblTransaction.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblTransaction.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("value"));

        refreshTable(Date.valueOf(dateShowTransaction.getValue()));

        // ----------------- add Lister to Date Picker ---------------//

        dateShowTransaction.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue == null){
                    return;
                }

                Date date = Date.valueOf(newValue);
                try {
                    refreshTable(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //------------Radio Button Groups-----------------------------//

        radioExpense.setToggleGroup(type);
        radioIncome.setToggleGroup(type);

        //------------- Add data to Account Combo --------------------//

        List<AccountDTO> accounts = null;
        try {
            accounts = accountBO.getAccounts();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        for (AccountDTO accountDTO : accounts) {
            comboAccount.getItems().add(new AccountCombo(accountDTO.getAId(),accountDTO.getAccountName()));
        }

        //------------- Load Date to Category Combo based on Radio Button Selection -----------------//

        radioExpense.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    comboCategory.getItems().clear();
                    try {
                        List<CategoryDTO> allCategory =  allCategory = categoryBO.getAllCategory();
                        for (CategoryDTO c : allCategory) {
                            if(c.getcType().equals("Expense")){
                                comboCategory.getItems().add(new CategoryCombo(c.getcId(),c.getcName(),c.getcType()));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    comboCategory.requestFocus();
                }
            }
        });

        radioIncome.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    comboCategory.getItems().clear();
                    try {
                        List<CategoryDTO> allCategory =  allCategory = categoryBO.getAllCategory();
                        for (CategoryDTO c : allCategory) {
                            if(c.getcType().equals("Income")){
                                comboCategory.getItems().add(new CategoryCombo(c.getcId(),c.getcName(),c.getcType()));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    comboCategory.requestFocus();
                }
            }
        });



        // ------------ Set Listener to Category-----------------------------------//

        comboCategory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CategoryCombo>() {
            @Override
            public void changed(ObservableValue<? extends CategoryCombo> observable, CategoryCombo oldValue, CategoryCombo newValue) {
                if(newValue == null){
                    comboSubCategory.getItems().clear();
                    return;
                }
                comboSubCategory.getItems().clear();

                try {
                    List<SubCategoryDTO> all = subCategoryBO.getSubCategoryByCategoryID(newValue.getcId());
                    for (SubCategoryDTO s : all) {
                        comboSubCategory.getItems().add(new SubCategoryCombo(s.getScId(),s.getScName()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //------------------------- Add Listener to Table -----------------------------------------//

        tblTransaction.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TransactionTM>() {
            @Override
            public void changed(ObservableValue<? extends TransactionTM> observable, TransactionTM oldValue, TransactionTM newValue) {

                if(newValue==null){
                    return;
                }

                btnSave.setText("Edit");
                btnDelelte.setDisable(false);
                isEdit = true ;

                ObservableList<AccountCombo> items = comboAccount.getItems();
                for (AccountCombo a : items) {
                    if(newValue.getAid()==a.getAccountId()){
                        comboAccount.setValue(a);
                        break;
                    }
                }

                if(newValue.getType().equals("Expense")){
                    radioExpense.setSelected(true);
                }else if(newValue.getType().equals("Income")){
                    radioIncome.setSelected(true);
                }

                dateTransactionDate.setValue(newValue.getDate().toLocalDate());

                ObservableList<CategoryCombo> categories = comboCategory.getItems();
                for (CategoryCombo c : categories) {
                    if(newValue.getCid()== c.getcId()){
                        comboCategory.setValue(c);
                        break;
                    }
                }

                ObservableList<SubCategoryCombo> subCategories = comboSubCategory.getItems();
                for (SubCategoryCombo s : subCategories) {
                    if(newValue.getScid()==s.getScId()){
                        comboSubCategory.setValue(s);
                        break;
                    }
                }

                String[] split = newValue.getValue().split("[.]");
                txtTransactionValue.setText(split[1].trim());

                txtSpecialNote.setText(newValue.getNote());

            }
        });

    }

    @FXML
    void clickHome(MouseEvent event) {
        Stage primaryStage = (Stage) txtSpecialNote.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/HomeView.fxml"));
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void clickNext(MouseEvent event) {
            LocalDate localDate = dateShowTransaction.getValue().plusDays(1);
            dateShowTransaction.setValue(localDate);
    }

    @FXML
    void clickPrevious(MouseEvent event) {
            LocalDate localDate = dateShowTransaction.getValue().plusDays(-1);
            dateShowTransaction.setValue(localDate);
    }

    @FXML
    void clickSaveBtn(ActionEvent event) {

        if(comboAccount.getSelectionModel().getSelectedItem()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select Account for the Transaction", ButtonType.OK).showAndWait();
            comboAccount.requestFocus();
            return;
        }else if(!radioExpense.isSelected()&&!radioIncome.isSelected()){
            new Alert(Alert.AlertType.ERROR,"Please Select Transaction Type", ButtonType.OK).showAndWait();
            return;
        }else if(dateTransactionDate.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select Transaction Date", ButtonType.OK).showAndWait();
            dateTransactionDate.requestFocus();
        }else if(comboCategory.getSelectionModel().getSelectedItem()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select Transaction Category", ButtonType.OK).showAndWait();
            comboCategory.requestFocus();
        }else if(comboSubCategory.getSelectionModel().getSelectedItem()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select Transaction Sub Categoty", ButtonType.OK).showAndWait();
            comboSubCategory.requestFocus();
        }else if(txtTransactionValue.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Transaction Value", ButtonType.OK).showAndWait();
            txtTransactionValue.requestFocus();
        }else if(!isInt(txtTransactionValue.getText().trim())){
            new Alert(Alert.AlertType.ERROR,"Transaction Amount should be Numeric", ButtonType.OK).showAndWait();
            txtTransactionValue.requestFocus();
        }

        int account = comboAccount.getSelectionModel().getSelectedItem().getAccountId();
        int type = 0;
        if(radioExpense.isSelected()){
            type= (-1);
        }else if(radioIncome.isSelected()){
            type= (+1);
        }
        Date transactionDate = Date.valueOf(dateTransactionDate.getValue());
        int scId = comboSubCategory.getValue().getScId();
        int amount = (Integer.parseInt(txtTransactionValue.getText().trim())*type);
        String note = txtSpecialNote.getText();

        if(!isEdit){
            TransactionDTO transactionDTO = new TransactionDTO(0,transactionDate,note,amount,account,scId);
            boolean result = false;
            try {
                result = transactionBO.addTransaction(transactionDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Transaction Successfully Added", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Transaction Save Operation failed", ButtonType.OK).showAndWait();
                return;
            }
        }else {
            TransactionDTO transactionDTO = new TransactionDTO(tblTransaction.getSelectionModel().getSelectedItem().getTid(),transactionDate,note,amount,account,scId);
            boolean result = false;
            try {
                result = transactionBO.updateTransaction(transactionDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Transaction Successfully Updated", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Transaction Update Operation Failed", ButtonType.OK).showAndWait();
                return;
            }

        }
        refreshTable(transactionDate);
        clear();

    }

    private void clear(){

        comboAccount.getSelectionModel().clearSelection();
        radioIncome.setSelected(false);
        radioExpense.setSelected(false);
        dateTransactionDate.setValue(null);
        comboCategory.getSelectionModel().clearSelection();
        comboSubCategory.getSelectionModel().clearSelection();
        txtTransactionValue.clear();
        txtSpecialNote.clear();

        tblTransaction.getSelectionModel().clearSelection();

        isEdit = false;
        btnSave.setText("Save");
        btnDelelte.setDisable(true);
    }

    private boolean isInt(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void refreshTable(Date date){

        List<TransactionViewDTO> all = null;
        try {
            all = transactionBO.getAllTransactionByDate(date);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        dateShowTransaction.setValue(date.toLocalDate());

        tblTransaction.getItems().clear();

        for (TransactionViewDTO t : all) {

            if(t.getcType().equals("Transfer")){        // Transfer Category not show up in the transaction Table
                continue;
            }
            tblTransaction.getItems().add(new TransactionTM(t.gettId(),t.gettDate(),t.gettNote(),t.getTvalue(),t.getaId(),t.getScId(),t.getcId(),t.getcType(),t.getCategoryName(),t.getScName()));

        }
    }

}