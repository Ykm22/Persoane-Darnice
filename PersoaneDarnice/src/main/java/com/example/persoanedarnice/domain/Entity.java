package com.example.persoanedarnice.domain;

public class Entity {
    private long serialVersionUID;
    private int id;

    public Entity(long serialVersionUID, int id) {
        this.serialVersionUID = serialVersionUID;
        this.id = id;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(long serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "serialVersionUID=" + serialVersionUID +
                ", id=" + id +
                '}';
    }
}