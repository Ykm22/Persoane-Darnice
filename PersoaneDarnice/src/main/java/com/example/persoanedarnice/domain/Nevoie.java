package com.example.persoanedarnice.domain;

import java.time.LocalDateTime;

public class Nevoie extends Entity{
    private String titlu;
    private String descriere;
    private LocalDateTime deadline;
    private long omInNevoie;
    private long omSalvator;
    private String status;

    public Nevoie(long serialVersionUID, int id, String titlu, String descriere, LocalDateTime deadline, long omInNevoie) {
        super(serialVersionUID, id);
        this.titlu = titlu;
        this.descriere = descriere;
        this.deadline = deadline;
        this.omInNevoie = omInNevoie;
        this.omSalvator = 0;
        this.status = "Caut erou!";
    }
    public Nevoie(long serialVersionUID, int id, String titlu, String descriere, LocalDateTime deadline, long omInNevoie, long omSalvator, String status) {
        super(serialVersionUID, id);
        this.titlu = titlu;
        this.descriere = descriere;
        this.deadline = deadline;
        this.omInNevoie = omInNevoie;
        this.omSalvator = omSalvator;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Nevoie{" +
                "titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                ", deadline=" + deadline +
                ", omInNevoie=" + omInNevoie +
                ", omSalvator=" + omSalvator +
                ", status='" + status + '\'' +
                '}';
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

    public long getOmInNevoie() {
        return omInNevoie;
    }

    public void setOmInNevoie(long omInNevoie) {
        this.omInNevoie = omInNevoie;
    }

    public long getOmSalvator() {
        return omSalvator;
    }

    public void setOmSalvator(long omSalvator) {
        this.omSalvator = omSalvator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
