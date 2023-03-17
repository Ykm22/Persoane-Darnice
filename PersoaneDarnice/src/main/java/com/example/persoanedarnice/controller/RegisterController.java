package com.example.persoanedarnice.controller;

import com.example.persoanedarnice.Main;
import com.example.persoanedarnice.business.Business;
import com.example.persoanedarnice.domain.Oras;
import com.example.persoanedarnice.domain.Persoana;
import com.example.persoanedarnice.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RegisterController implements Observer {
    @Override
    public void update() {
        initModel();
    }

    public TextField textField_Nume;
    public TextField textField_Prenume;
    public TextField textField_Username;
    public TextField textField_Strada;
    public TextField textField_Parola;
    public TextField textField_NumarStrada;
    public TextField textField_Telefon;
    public ComboBox<Oras> comboBox_Oras;
    public Button button_Register;
    public TableView<Persoana> tableView_Persoane;
    public TableColumn<Persoana, String> tableColumn_Username;
    Business service;
    ObservableList<Oras> orase = FXCollections.observableArrayList();
    ObservableList<Persoana> model = FXCollections.observableArrayList();
    public void setService(Business service){
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    private void initModel() {
        orase.setAll(service.getAllOrase());
        model.setAll(service.getAllUsers());
    }

    public void initialize(){
        comboBox_Oras.setItems(orase);
        tableView_Persoane.setItems(model);
        tableColumn_Username.setCellValueFactory(new PropertyValueFactory<Persoana, String>("username"));
    }

    public void handleRegister(ActionEvent actionEvent) {
        String nume = textField_Nume.getText();
        String prenume = textField_Prenume.getText();
        String username = textField_Username.getText();
        String strada = textField_Strada.getText();
        String parola = textField_Parola.getText();
        String numarStrada = textField_NumarStrada.getText();
        String telefon = textField_Telefon.getText();
        Oras oras = comboBox_Oras.getValue();
        service.save(nume, prenume, username, parola, oras, strada, numarStrada, telefon);
    }

    public void handleSelectedItem(MouseEvent mouseEvent) throws IOException {
        Persoana persoana = tableView_Persoane.getSelectionModel().getSelectedItem();
        TabPane tabPane = new TabPane();

        FXMLLoader loaderAjut = new FXMLLoader(Main.class.getResource("views/AjutView.fxml"));
        AnchorPane layoutAjut = loaderAjut.load();
        AjutController ajutController = loaderAjut.getController();
        ajutController.setLoggedPersoana(persoana);
        ajutController.setService(service);


        FXMLLoader loaderAjutat = new FXMLLoader(Main.class.getResource("views/AjutatView.fxml"));
        AnchorPane layoutAjutat = loaderAjutat.load();
        AjutatController ajutatController = loaderAjutat.getController();
        ajutatController.setLoggedPersoana(persoana);
        ajutatController.setService(service);


        Tab ajutTab = new Tab("Doresc sa ajut!", layoutAjut);
        Tab ajutatTab = new Tab("Doresc sa fiu ajutat!", layoutAjutat);
        tabPane.getTabs().add(ajutTab);
        tabPane.getTabs().add(ajutatTab);
        VBox vBox = new VBox(tabPane);
        Scene tabScene = new Scene(vBox);
        Stage tabStage = new Stage();
        tabStage.setScene(tabScene);
        tabStage.initStyle(StageStyle.DECORATED);
        tabStage.show();
    }
}
