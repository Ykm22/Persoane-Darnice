package com.example.persoanedarnice.business;

import com.example.persoanedarnice.domain.*;
import com.example.persoanedarnice.observer.Observable;
import com.example.persoanedarnice.observer.Observer;
import com.example.persoanedarnice.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Business implements Observable {
    private final Repository<Persoana> repository_persoane;
    private final Repository<Nevoie> repository_nevoi;
    private int lastId;
    private long lastSerialVersionUID;

    private final List<Observer> observers = new ArrayList<>();

    public Business(Repository<Persoana> repository_persoane, Repository<Nevoie> repository_nevoi) {
        lastId = 55;
        lastSerialVersionUID = 55;
        this.repository_persoane = repository_persoane;
        this.repository_nevoi = repository_nevoi;
    }


    public void save(String nume, String prenume, String username, String parola, Oras oras, String strada, String numarStrada, String telefon){
        int id = lastId + 1;
        long serialVersionUID = lastSerialVersionUID + 1;
        lastId = id;
        lastSerialVersionUID = serialVersionUID;
        Persoana persoana = new Persoana(serialVersionUID, id, nume, prenume, username, parola, oras, strada, numarStrada, telefon);
        repository_persoane.save(persoana);
        notifyObservers();
    }


    @Override
    public void addObserver(Observer e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(x -> x.update());
    }

    public ArrayList<Oras> getAllOrase() {
        ArrayList<Oras> orase = new ArrayList<>();
        orase.add(Oras.CLUJ);
        orase.add(Oras.BUCURESTI);
        orase.add(Oras.HAMPSHIRE);
        orase.add(Oras.NEW_YORK);
        orase.add(Oras.TIMISOARA);
        return orase;
    }

    public ArrayList<Persoana> getAllUsers() {
        return repository_persoane.getAll();
    }

    public ArrayList<Nevoie> getAllNevoi() {
        return repository_nevoi.getAll();
    }

    public Persoana getPersoana(long idOmInNevoie) {
        ArrayList<Persoana> persoane = repository_persoane.getAll();
        for(Persoana persoana : persoane){
            if(persoana.getId() == idOmInNevoie)
                return persoana;
        }
        return null;
    }

    public Nevoie getNevoie(int id) {
        ArrayList<Nevoie> nevoi = repository_nevoi.getAll();
        for(Nevoie nevoie : nevoi){
            if(nevoie.getId() == id)
                return nevoie;
        }
        return null;
    }

    public void updateNevoie(int id, int id1) {
        Nevoie nevoie = getNevoie(id);
        nevoie.setOmSalvator(id1);
        nevoie.setStatus("Erou gasit!");

        repository_nevoi.update(nevoie);

        notifyObservers();
    }

    public void addNevoie(Nevoie nevoie) {
        repository_nevoi.save(nevoie);
        notifyObservers();
    }
}
