package com.example.persoanedarnice.domain;

import java.time.LocalDateTime;

public class NevoieDTO {
    private String titlu;
    private String descriere;
    private LocalDateTime deadline;
    private String numeInNevoie;
    private String numeSalvator;
    private String status;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getNumeInNevoie() {
        return numeInNevoie;
    }

    public void setNumeInNevoie(String numeInNevoie) {
        this.numeInNevoie = numeInNevoie;
    }

    public String getNumeSalvator() {
        return numeSalvator;
    }

    public void setNumeSalvator(String numeSalvator) {
        this.numeSalvator = numeSalvator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NevoieDTO(int id, String titlu, String descriere, LocalDateTime deadline, String numeInNevoie, String numeSalvator, String status) {
        this.id = id;
        this.titlu = titlu;
        this.descriere = descriere;
        this.deadline = deadline;
        this.numeInNevoie = numeInNevoie;
        this.numeSalvator = numeSalvator;
        this.status = status;
    }

    @Override
    public String toString() {
        return "NevoieDTO{" +
                "titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                ", deadline=" + deadline +
                ", numeInNevoie='" + numeInNevoie + '\'' +
                ", numeSalvator='" + numeSalvator + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
