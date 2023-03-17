package com.example.persoanedarnice.controller;

import com.example.persoanedarnice.business.Business;
import com.example.persoanedarnice.domain.Nevoie;
import com.example.persoanedarnice.domain.Persoana;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class AjutatController {
    public TextField textField_Titlu;
    public TextField textField_Descriere;
    public DatePicker datePicker_Deadline;
    public Button button_Ajutor;

    private long lastSerialVersionUID = 555;
    private int lastId = 555;

    Business service;
    Persoana loggedPersoana;

    public void setService(Business service){
        this.service = service;
    }
    public void setLoggedPersoana(Persoana loggedPersoana){
        this.loggedPersoana = loggedPersoana;
    }

    public void initialize(){
        ;
    }

    public void handleAjutor(ActionEvent actionEvent) {
        String titlu = textField_Titlu.getText();
        String descriere = textField_Descriere.getText();
        LocalDateTime deadline = datePicker_Deadline.getValue().atStartOfDay();
        long serialVersionId = lastSerialVersionUID + 1;
        int id = lastId + 1;
        lastSerialVersionUID = serialVersionId;
        lastId = id;
        Nevoie nevoie = new Nevoie(serialVersionId, id, titlu, descriere, deadline, loggedPersoana.getId());
        service.addNevoie(nevoie);
    }
}
