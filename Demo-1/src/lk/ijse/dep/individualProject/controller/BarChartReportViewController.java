package lk.ijse.dep.individualProject.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dep.individualProject.business.BOFactory;
import lk.ijse.dep.individualProject.business.custom.ReportBO;
import lk.ijse.dep.individualProject.dto.TableReportDTO;
import lk.ijse.dep.individualProject.view.util.TableReportTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BarChartReportViewController {

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private JFXRadioButton radioExpense;

    @FXML
    private JFXRadioButton radioIncome;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ImageView imgPrevious;

    @FXML
    private ImageView imgNext;

    @FXML
    private Label lblTableHeader;

    @FXML
    private Label lblTotal;

    static LocalDate localDate ;

    ToggleGroup toggleGroup = new ToggleGroup();


    ReportBO reportBO = BOFactory.getInstance().getBO(BOFactory.BOType.REPORT);

    public void initialize(){

        barChart.setLegendVisible(false);


        if(localDate==null){
            datePicker.setValue(LocalDate.now());
            localDate = LocalDate.now();
        }else {
            datePicker.setValue(localDate);
        }

        radioExpense.setSelected(true);

        radioExpense.setToggleGroup(toggleGroup);
        radioIncome.setToggleGroup(toggleGroup);

        datePicker.setVisible(false);

        refresh();


        //----------------- Set Listener to Invisible Date Picker -------------------------------------//

        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if(newValue==null){
                    return;
                }

                try {
                    refresh();
                } catch (Exception e) {
                    Logger.getLogger("").log(Level.SEVERE,null,e);
                }
            }
        });

        //---------------- Set Listeners to Radio Buttons --------------------------------------------- //

        radioExpense.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                try {
                    if(newValue){
                        refresh();
                    }
                } catch (Exception e) {
                    Logger.getLogger("").log(Level.SEVERE,null,e);
                }
            }
        });

        radioIncome.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                try {
                    if(newValue){
                        refresh();
                    }
                } catch (Exception e) {
                    Logger.getLogger("").log(Level.SEVERE,null,e);
                }
            }
        });


    }

    @FXML
    void clickHome(MouseEvent event){
        Stage primaryStage = (Stage) lblTableHeader.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/ReportView.fxml"));
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void clickNextImage(MouseEvent event) {
        datePicker.setValue(datePicker.getValue().plusMonths(1));
    }

    @FXML
    void clickPreviousImage(MouseEvent event) {
        datePicker.setValue(datePicker.getValue().plusMonths(-1));
    }

    private void refresh(){

        String type = null;
        if(radioExpense.isSelected()){
            type = "Expense";
        }else if(radioIncome.isSelected()){
            type = "Income";
        }

        List<TableReportDTO> categoryTotal = null;
        try {
            categoryTotal = reportBO.getCategoryTotal(type, datePicker.getValue().getYear(),datePicker.getValue().getMonthValue());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        int total = 0 ;             // Get total To Calculate Percentage

        for (TableReportDTO t : categoryTotal) {
            total = total + t.getCategoryTotal();
        }

        setTotalLabel(total);
        setHeaderLabel();

        XYChart.Series dataSeries = new XYChart.Series();


        for (TableReportDTO t : categoryTotal) {

            int cTotal = 0;                 // Make Expenses Positive Value
            if(t.getCategoryTotal()>0){
                cTotal = t.getCategoryTotal();
            }else {
                cTotal = (-1)*(t.getCategoryTotal());
            }

            dataSeries.getData().add(new XYChart.Data(t.getCategoryName(),cTotal));

        }
        barChart.getData().clear();
        barChart.getData().add(dataSeries);

    }

    private void setTotalLabel(int total){
        if(total<0){
            total=total*(-1);
        }
        String showLabel = "Total Value : Rs. "+total;
        lblTotal.setText(showLabel);
    }

    private void setHeaderLabel(){
        String header = datePicker.getValue().getYear()+" - "+datePicker.getValue().getMonth()+" Report";
        lblTableHeader.setText(header);
    }

}
