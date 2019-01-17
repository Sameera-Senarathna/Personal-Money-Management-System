package lk.ijse.dep.individualProject.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeViewController {

    public AnchorPane root;
    @FXML
    private ImageView imgCategory;

    public void initialize(){
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    void clickAccount(MouseEvent event){
        Stage primaryStage = (Stage) imgCategory.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/AccountVIew.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void clickCategory(MouseEvent event){

        Stage primaryStage = (Stage) imgCategory.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/CategoryView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    void clickReports(MouseEvent event) {
        Stage primaryStage = (Stage) imgCategory.getScene().getWindow();
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
    void clickSubCategory(MouseEvent event) {
        Stage primaryStage = (Stage) imgCategory.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/SubCategoryView.fxml"));
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void clickTransaction(MouseEvent event){
        Stage primaryStage = (Stage) imgCategory.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/TransactionView.fxml"));
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}