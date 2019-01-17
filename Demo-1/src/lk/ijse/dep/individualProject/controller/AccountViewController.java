package lk.ijse.dep.individualProject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountViewController {

    @FXML
    private ImageView imgCategory;

    @FXML
    void clickAccountManagement(MouseEvent event){
        Stage primaryStage = (Stage) imgCategory.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/AccountManagement.fxml"));
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void clickFundTransfer(MouseEvent event){
        Stage primaryStage = (Stage) imgCategory.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/FundTransaferView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void clickHome(MouseEvent event) {

        Stage primaryStage = (Stage) imgCategory.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/individualProject/view/HomeView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}