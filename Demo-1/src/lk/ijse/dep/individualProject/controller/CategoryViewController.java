package lk.ijse.dep.individualProject.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dep.individualProject.business.BOFactory;
import lk.ijse.dep.individualProject.business.custom.CategoryBO;
import lk.ijse.dep.individualProject.business.custom.SubCategoryBO;
import lk.ijse.dep.individualProject.dto.CategoryDTO;
import lk.ijse.dep.individualProject.dto.SubCategoryDTO;
import lk.ijse.dep.individualProject.view.util.CategoryTM;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryViewController {

    @FXML
    private ImageView clickHome;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXRadioButton radioIncome;

    @FXML
    private JFXRadioButton radioExpense;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelelte;

    @FXML
    private TableView<CategoryTM> tblCategory;

    private ToggleGroup type = new ToggleGroup();

    private boolean isEdit = false;

    ObservableList<CategoryTM> tableItems = FXCollections.observableArrayList();

    CategoryBO categoryBO = BOFactory.getInstance().getBO(BOFactory.BOType.CATEGORYBO);
    SubCategoryBO subCategoryBO = BOFactory.getInstance().getBO(BOFactory.BOType.SUBCATEGORYBO);

    public void initialize(){

        btnDelelte.setDisable(true);

    //------------Radio Button Groups-----------------------------//

        radioExpense.setToggleGroup(type);
        radioIncome.setToggleGroup(type);

    //------------Table Fill-------------------------------------//

        tblCategory.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cName"));
        tblCategory.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cType"));
        tblCategory.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("cDescription"));

        tableRefresh();

     //----------Set Listener to table--------------------------//

     tblCategory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CategoryTM>() {
         @Override
         public void changed(ObservableValue<? extends CategoryTM> observable, CategoryTM oldValue, CategoryTM newValue) {
             if(newValue==null){
                 return;
             }
             txtName.setText(newValue.getCName());
             txtDescription.setText(newValue.getCDescription());
             if(newValue.getCType().equals("Expense")){
                 radioExpense.setSelected(true);
             }else if(newValue.getCType().equals("Income")){
                 radioIncome.setSelected(true);
             }

             isEdit = true;
             btnDelelte.setDisable(false);
             btnSave.setText("Edit");
         }
     });

    }

    @FXML
    void clickDeleteBtn(ActionEvent event){

        boolean result = false;
        try {

        List<SubCategoryDTO> subCategoryByCategoryID = subCategoryBO.getSubCategoryByCategoryID(tblCategory.getSelectionModel().getSelectedItem().getcId());

        if (subCategoryByCategoryID.size()>0){
            new Alert(Alert.AlertType.ERROR,"Delete Operation Failed.\nThis Category Has Sub Categories", ButtonType.OK).showAndWait();
            return;
        }


            result = categoryBO.deleteCategory(tblCategory.getSelectionModel().getSelectedItem().getcId());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }


        if(result){
            new Alert(Alert.AlertType.INFORMATION,"Category Successfully Deleted", ButtonType.OK).showAndWait();
        }else {
            new Alert(Alert.AlertType.ERROR,"Category Delete Operation failed", ButtonType.OK).showAndWait();
            return;
        }

        clear();
    }

    @FXML
    void clickSaveBtn(ActionEvent event){

        String cName = txtName.getText().trim();
        String cDescription = txtDescription.getText().trim();
        String type = null;
        if(radioExpense.isSelected()){
            type = "Expense";
        }else if(radioIncome.isSelected()){
            type = "Income";
        }

        if(cName.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please enter Category Name", ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        }else if(type==null){
            new Alert(Alert.AlertType.ERROR,"Please Select Category Type", ButtonType.OK).showAndWait();
            return;
        }else if(cDescription.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please enter Category Description", ButtonType.OK).showAndWait();
            txtDescription.requestFocus();
            return;
        }



        if(!isEdit){

            CategoryDTO categoryDTO = new CategoryDTO(0,cName, cDescription, type);
            boolean result = false;
            try {
                result = categoryBO.addCategory(categoryDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Category Successfully Added", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Category Save Operation failed", ButtonType.OK).showAndWait();
                return;
            }
        }else {

            int cId = tblCategory.getSelectionModel().getSelectedItem().getcId();
            CategoryDTO categoryDTO = new CategoryDTO(cId,cName, cDescription, type);

            boolean result = false;
            try {
                result = categoryBO.updateCategory(categoryDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Category Successfully Updated", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Category Updating Operation failed", ButtonType.OK).showAndWait();
                return;
            }
        }
        clear();
    }

    public void clickHome(MouseEvent mouseEvent){
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

    private void clear(){
        txtName.clear();
        txtDescription.clear();
        radioExpense.setSelected(false);
        radioIncome.setSelected(false);
        isEdit=false;
        tableRefresh();
        btnDelelte.setDisable(true);
        tblCategory.getSelectionModel().clearSelection();
        btnSave.setText("Save");
    }

    private void tableRefresh(){

        List<CategoryDTO> allCategory = null;
        try {
            allCategory = categoryBO.getAllCategory();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        tblCategory.getItems().clear();

        for (CategoryDTO c : allCategory) {
            if(c.getcType().equals("Transfer")){
                continue;
            }
            tblCategory.getItems().add(new CategoryTM(c.getcId(),c.getcName(),c.getcType(),c.getcDescription()));
        }
    }
}