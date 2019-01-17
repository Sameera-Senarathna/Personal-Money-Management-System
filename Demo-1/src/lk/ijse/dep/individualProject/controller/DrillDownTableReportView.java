package lk.ijse.dep.individualProject.controller;

        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Label;
        import javafx.scene.control.TableView;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;
        import lk.ijse.dep.individualProject.business.BOFactory;
        import lk.ijse.dep.individualProject.business.custom.ReportBO;
        import lk.ijse.dep.individualProject.dto.TableReportDrillDTO;
        import lk.ijse.dep.individualProject.view.util.TableReportDrillTM;

        import java.io.IOException;
        import java.util.List;
        import java.util.logging.Level;
        import java.util.logging.Logger;

public class DrillDownTableReportView {


    @FXML
    private TableView<TableReportDrillTM> tblDrillDown;

    @FXML
    private Label lblTableHeaderDrillDown;

    ReportBO reportBO = BOFactory.getInstance().getBO(BOFactory.BOType.REPORT);

    public void initialize(){

        tblDrillDown.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("subCategoryName"));
        tblDrillDown.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("subCategoryTotal"));
        tblDrillDown.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("percentage"));

        int categoryID = TableReportViewController.categoryID;
        int year = TableReportViewController.localDate.getYear();
        int month = TableReportViewController.localDate.getMonthValue();
        String categoryName = TableReportViewController.categoryName;
        String type = TableReportViewController.expenseType;

        List<TableReportDrillDTO> subCategoryTotal = null;
        try {
            subCategoryTotal = reportBO.getSubCategoryTotal(categoryID, year, month);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        int totalCalegoryBalance = 0;

        for (TableReportDrillDTO a : subCategoryTotal) {
            totalCalegoryBalance = totalCalegoryBalance + a.getSubCategoryMonthTotal();
        }

        for (TableReportDrillDTO a : subCategoryTotal) {

            tblDrillDown.getItems().add(new TableReportDrillTM(a.getSubCategoryName(),a.getSubCategoryMonthTotal(),totalCalegoryBalance));

        }

        // -------------------------------- change Label ------------------------------//
        String lable = year+" - "+TableReportViewController.localDate.getMonth()+" - "+categoryName+" ("+type+") "+"Category Break Down";
        lblTableHeaderDrillDown.setText(lable);

    }


    @FXML
    void clickHome(MouseEvent event){
        Stage primaryStage = (Stage) lblTableHeaderDrillDown.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/TableReportView.fxml"));
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
