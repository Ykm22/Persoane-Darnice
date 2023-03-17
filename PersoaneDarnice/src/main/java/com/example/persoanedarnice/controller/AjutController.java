package com.example.persoanedarnice.controller;

import com.example.persoanedarnice.business.Business;
import com.example.persoanedarnice.domain.Nevoie;
import com.example.persoanedarnice.domain.NevoieDTO;
import com.example.persoanedarnice.domain.Persoana;
import com.example.persoanedarnice.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AjutController implements Observer {
    public TableView<NevoieDTO> tableView_FapteBune;
    public TableColumn<NevoieDTO, String> tableColumn_TitluBun;
    public TableColumn<NevoieDTO, String> tableColumn_DescriereBun;
    public TableColumn<NevoieDTO, LocalDateTime> tableColumn_DeadlineBun;
    public TableColumn<NevoieDTO, String> tableColumn_OmInNevoieBun;
    public TableColumn<NevoieDTO, String> tableColumn_OmSalvatorBun;
    public TableColumn<NevoieDTO, String> tableColumn_StatusBun;

    @Override
    public void update() {
        initModel();
    }

    public TableView<NevoieDTO> tableView_Nevoi;
    public TableColumn<NevoieDTO, String> tableColumn_Titlu;
    public TableColumn<NevoieDTO, String> tableColumn_Descriere;
    public TableColumn<NevoieDTO, LocalDateTime> tableColumn_Deadline;
    public TableColumn<NevoieDTO, String> tableColumn_OmInNevoie;
    public TableColumn<NevoieDTO, String> tableColumn_OmSalvator;
    public TableColumn<NevoieDTO, String> tableColumn_Status;

    Business service;
    Persoana loggedPersoana;
    ObservableList<NevoieDTO> model = FXCollections.observableArrayList();
    ObservableList<NevoieDTO> modelBun = FXCollections.observableArrayList();

    public void setService(Business service){
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    private void initModel() {
        ArrayList<Nevoie> nevoi = service.getAllNevoi();
        ArrayList<NevoieDTO> nevoiDTO = new ArrayList<>();
        ArrayList<NevoieDTO> nevoiDTOBun = new ArrayList<>();
        for(Nevoie nevoie : nevoi){
            long idOmInNevoie = nevoie.getOmInNevoie();
            Persoana omInNevoie = service.getPersoana(idOmInNevoie);
            long idOmSalvator = nevoie.getOmSalvator();
            Persoana omSalvator = service.getPersoana(idOmSalvator);

            if(omInNevoie.getOras() == loggedPersoana.getOras() && loggedPersoana.getId() != omInNevoie.getId() && nevoie.getStatus().equals("Caut erou!")){
                NevoieDTO nevoieDTO = new NevoieDTO(nevoie.getId(), nevoie.getTitlu(), nevoie.getDescriere(), nevoie.getDeadline(), omInNevoie.getNume(), "", nevoie.getStatus());
                nevoiDTO.add(nevoieDTO);
            }
            if(omSalvator != null && omInNevoie.getOras() == loggedPersoana.getOras() && loggedPersoana.getId() == omSalvator.getId() && loggedPersoana.getId() != omInNevoie.getId() && nevoie.getStatus().equals("Erou gasit!")){
                NevoieDTO nevoieDTO = new NevoieDTO(nevoie.getId(), nevoie.getTitlu(), nevoie.getDescriere(), nevoie.getDeadline(), omInNevoie.getNume(), loggedPersoana.getNume(), nevoie.getStatus());
                nevoiDTOBun.add(nevoieDTO);
            }
        }
        model.setAll(nevoiDTO);
        modelBun.setAll(nevoiDTOBun);


    }

    public void setLoggedPersoana(Persoana loggedPersoana){
        this.loggedPersoana = loggedPersoana;
    }

    public void initialize(){
        tableView_Nevoi.setItems(model);
        tableColumn_Titlu.setCellValueFactory(new PropertyValueFactory<NevoieDTO, String>("titlu"));
        tableColumn_Descriere.setCellValueFactory(new PropertyValueFactory<NevoieDTO, String>("descriere"));
        tableColumn_Deadline.setCellValueFactory(new PropertyValueFactory<NevoieDTO, LocalDateTime>("deadline"));
        tableColumn_OmInNevoie.setCellValueFactory(new PropertyValueFactory<NevoieDTO, String>("numeInNevoie"));
        tableColumn_OmSalvator.setCellValueFactory(new PropertyValueFactory<NevoieDTO, String>("numeSalvator"));
        tableColumn_Status.setCellValueFactory(new PropertyValueFactory<NevoieDTO, String>("status"));

        tableView_FapteBune.setItems(modelBun);
        tableColumn_TitluBun.setCellValueFactory(new PropertyValueFactory<NevoieDTO, String>("titlu"));
        tableColumn_DescriereBun.setCellValueFactory(new PropertyValueFactory<NevoieDTO, String>("descriere"));
        tableColumn_DeadlineBun.setCellValueFactory(new PropertyValueFactory<NevoieDTO, LocalDateTime>("deadline"));
        tableColumn_OmInNevoieBun.setCellValueFactory(new PropertyValueFactory<NevoieDTO, String>("numeInNevoie"));
        tableColumn_OmSalvatorBun.setCellValueFactory(new PropertyValueFactory<NevoieDTO, String>("numeSalvator"));
        tableColumn_StatusBun.setCellValueFactory(new PropertyValueFactory<NevoieDTO, String>("status"));
    }

    public void handleSelectedItem(MouseEvent mouseEvent) {
        NevoieDTO nevoieDTO = tableView_Nevoi.getSelectionModel().getSelectedItem();
        AlertMessage.showMessage(null, Alert.AlertType.INFORMATION, "INFO", "Selectat nevoie cu succes");
        Nevoie nevoie = service.getNevoie(nevoieDTO.getId());
        service.updateNevoie(nevoie.getId(), loggedPersoana.getId());
    }
}
