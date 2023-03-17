package com.example.persoanedarnice;

import com.example.persoanedarnice.business.Business;
import com.example.persoanedarnice.controller.RegisterController;
import com.example.persoanedarnice.domain.*;
import com.example.persoanedarnice.repository.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Business service;
    Repository<Persoana> repository_persoane;
    Repository<Nevoie> repository_nevoi;

    private void initView(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/RegisterView.fxml"));
        AnchorPane Layout = fxmlLoader.load();
        primaryStage.setScene(new Scene(Layout));
        RegisterController registerController = fxmlLoader.getController();
        registerController.setService(service);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/practic";
        repository_persoane = new DBRepository_Persoane(url, "postgres", "postgres");
        repository_nevoi = new DBRepository_Nevoi(url, "postgres", "postgres");
        service = new Business(repository_persoane, repository_nevoi);
        initView(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
