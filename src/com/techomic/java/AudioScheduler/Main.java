package com.techomic.java.AudioScheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initLayout();
    }

    private void initLayout() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(MainUiController.class.getResource("MainUi.fxml"));
            rootPane = loader.load();
        }  catch (IOException e) {
            e.printStackTrace();
            rootPane = new BorderPane();
            Label label = new Label();
            label.setText("Could not initialize the application");
            rootPane.setCenter(label);
        } finally {
            Scene scene = new Scene(rootPane);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle(AppConstants.APP_TITLE);
            ((MainUiController) loader.getController()).initUi();
            primaryStage.show();
        }
    }
}
