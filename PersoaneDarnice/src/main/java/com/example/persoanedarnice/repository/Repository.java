package com.example.persoanedarnice.repository;

import java.util.ArrayList;

public interface Repository<T> {
    void save(T user) ;

    T delete(int id);

    T getOne(int id);

    ArrayList<T> getAll();

    ArrayList<T> filter(String first_name);

    T find(int id);

    void update(T nevoie);

}