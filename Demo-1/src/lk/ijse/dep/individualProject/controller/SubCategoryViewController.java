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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dep.individualProject.business.BOFactory;
import lk.ijse.dep.individualProject.business.custom.CategoryBO;
import lk.ijse.dep.individualProject.business.custom.SubCategoryBO;
import lk.ijse.dep.individualProject.business.custom.TransactionBO;
import lk.ijse.dep.individualProject.dto.CategoryDTO;
import lk.ijse.dep.individualProject.dto.SubCategoryDTO;
import lk.ijse.dep.individualProject.dto.SubCategoryTableDTO;
import lk.ijse.dep.individualProject.dto.TransactionDTO;
import lk.ijse.dep.individualProject.entity.Transaction;
import lk.ijse.dep.individualProject.view.util.CategoryCombo;
import lk.ijse.dep.individualProject.view.util.SubCategoryTM;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubCategoryViewController {

    @FXML
    private ImageView imgHome;

    @FXML
    private JFXTextField txtSubCategoryName;

    @FXML
    private JFXComboBox<CategoryCombo> comboCategory;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelelte;

    private boolean isEdit = false;

    @FXML
    private TableView<SubCategoryTM> tblSubCategory;

    CategoryBO categoryBO = BOFactory.getInstance().getBO(BOFactory.BOType.CATEGORYBO);
    SubCategoryBO subCategoryBO = BOFactory.getInstance().getBO(BOFactory.BOType.SUBCATEGORYBO);
    TransactionBO transactionBO = BOFactory.getInstance().getBO(BOFactory.BOType.TRANSACTION);

    public void initialize() {

        btnDelelte.setDisable(true);

    //---------------Load values to Category Combo Box---------------------------//

        List<CategoryDTO> allCategory = null;
        try {
            allCategory = categoryBO.getAllCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (CategoryDTO c : allCategory) {
            if (c.getcType().equals("Transfer")){
                continue;
            }
            comboCategory.getItems().add(new CategoryCombo(c.getcId(),c.getcName(),c.getcType()));
        }

    //---------------- Fill Table ------------------------------------//

        tblSubCategory.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("scName"));
        tblSubCategory.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cName"));
        tblSubCategory.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("cType"));
        tblSubCategory.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("scDescription"));

        refreshTable();

    //--------------- Set Listener to Table -------------------------//

        tblSubCategory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SubCategoryTM>() {
            @Override
            public void changed(ObservableValue<? extends SubCategoryTM> observable, SubCategoryTM oldValue, SubCategoryTM newValue) {
                if(newValue == null){
                    return;
                }
                isEdit=true;
                btnDelelte.setDisable(false);
                btnSave.setText("Edit");

                txtSubCategoryName.setText(newValue.getScName());
                txtDescription.setText(newValue.getScDescription());

                ObservableList<CategoryCombo> items = comboCategory.getItems();

                for (CategoryCombo c : comboCategory.getItems()) {
                    if(c.getcName().equals(newValue.getCName())){
                        comboCategory.getSelectionModel().select(c);
                        break;
                    }
                }



            }
        });


    }

    @FXML
    void clickDeleteBtn(ActionEvent event) {
        if(isEdit){
            boolean result = false;
            try {

                List<TransactionDTO> transactionBySubCategoryID = transactionBO.getTransactionBySubCategoryID(tblSubCategory.getSelectionModel().getSelectedItem().getScId());

                if (transactionBySubCategoryID.size()>0){
                    new Alert(Alert.AlertType.ERROR,"Sub-Category Deletion Operation Failed\nSub Category Has Transactions", ButtonType.OK).showAndWait();
                    return;
                }


                result = subCategoryBO.deletesubCategory(tblSubCategory.getSelectionModel().getSelectedItem().getScId());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Sub-Category Successfully deleted", ButtonType.OK).showAndWait();
                refreshTable();
            }else {
                new Alert(Alert.AlertType.ERROR,"Sub-Category Deletion Operation failed", ButtonType.OK).showAndWait();
                return;
            }

            clear();
        }

    }

    @FXML
    void clickHome(MouseEvent event) {
        Stage primaryStage = (Stage) txtDescription.getScene().getWindow();
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
    void clickSaveBtn(ActionEvent event){
        if(txtSubCategoryName.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please enter Sub-Category Name", ButtonType.OK).showAndWait();
            txtSubCategoryName.requestFocus();
            return;
        }else if(txtDescription.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please enter Sub-Category Description", ButtonType.OK).showAndWait();
            txtDescription.requestFocus();
            return;
        }else if(comboCategory.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Please Select Sub-Category's Category", ButtonType.OK).showAndWait();
            comboCategory.requestFocus();
            return;
        }

        if(!isEdit){

            SubCategoryDTO subCategoryDTO = new SubCategoryDTO(0,txtSubCategoryName.getText().trim(),txtDescription.getText().trim(),comboCategory.getValue().getcId());
            boolean result = false;
            try {
                result = subCategoryBO.addSubCategory(subCategoryDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Sub-Category Successfully Added", ButtonType.OK).showAndWait();
                refreshTable();
            }else {
                new Alert(Alert.AlertType.ERROR,"Sub-Category Save Operation failed", ButtonType.OK).showAndWait();
                return;
            }
        }else {
            int selectedscId = tblSubCategory.getSelectionModel().getSelectedItem().getScId();
            SubCategoryDTO subCategoryDTO = new SubCategoryDTO(selectedscId,txtSubCategoryName.getText().trim(),txtDescription.getText().trim(),comboCategory.getValue().getcId());

            boolean result = false;
            try {
                result = subCategoryBO.updateCategory(subCategoryDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Sub-Category Successfully updated", ButtonType.OK).showAndWait();
                refreshTable();
            }else {
                new Alert(Alert.AlertType.ERROR,"Sub-Category Save Operation updated", ButtonType.OK).showAndWait();
                return;
            }

        }
        clear();
    }

    private void clear(){
        txtSubCategoryName.clear();
        txtDescription.clear();
        comboCategory.getSelectionModel().clearSelection();
        isEdit=false;
        btnDelelte.setDisable(true);
        btnSave.setText("Save");
        tblSubCategory.getSelectionModel().clearSelection();
    }

    private void refreshTable(){

        List<SubCategoryTableDTO> allSubCategory = null;
        try {
            allSubCategory = subCategoryBO.getAllSubCategory();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        tblSubCategory.getItems().clear();

        for (SubCategoryTableDTO sc : allSubCategory ) {

            if (sc.getcType().equals("Transfer")){
                continue;
            }

            SubCategoryTM subCategoryTM = new SubCategoryTM(sc.getScId(), sc.getScName(), sc.getcName(), sc.getcType(), sc.getScDescription());
            tblSubCategory.getItems().add(subCategoryTM);
        }

    }


}
