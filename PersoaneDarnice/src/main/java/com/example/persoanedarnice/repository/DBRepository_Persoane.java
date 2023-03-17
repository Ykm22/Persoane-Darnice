package com.example.persoanedarnice.repository;

import com.example.persoanedarnice.domain.Oras;
import com.example.persoanedarnice.domain.Persoana;

import java.sql.*;
import java.util.ArrayList;

public class DBRepository_Persoane implements Repository<Persoana>{
    @Override
    public void update(Persoana nevoie) {

    }

    private String db_url;
    private String db_username;
    private String db_password;

    public DBRepository_Persoane(String db_url, String db_username, String db_password) {
        this.db_url = db_url;
        this.db_username = db_username;
        this.db_password = db_password;
    }
    @Override
    public void save(Persoana persoana) {
        String sql = "INSERT INTO persoane (serialVersionUID, id, nume, prenume, username, parola, oras, strada, numarStrada, telefon) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, persoana.getSerialVersionUID());
            preparedStatement.setInt(2, persoana.getId());
            preparedStatement.setString(3, persoana.getNume());
            preparedStatement.setString(4, persoana.getPrenume());
            preparedStatement.setString(5, persoana.getUsername());
            preparedStatement.setString(6, persoana.getParola());
            preparedStatement.setString(7, persoana.getOras().toString());
            preparedStatement.setString(8, persoana.getStrada());
            preparedStatement.setString(9, persoana.getNumarStrada());
            preparedStatement.setString(10, persoana.getTelefon());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Persoana delete(int id) {
        return null;
    }

    @Override
    public Persoana getOne(int id) {
        return null;
    }

    @Override
    public ArrayList<Persoana> getAll() {
        ArrayList<Persoana> persoane = new ArrayList<>();
        int row = 1;
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persoane");
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                long serialVersionUID = resultSet.getLong("serialversionuid");
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String username = resultSet.getString("username");
                String parola = resultSet.getString("parola");
                String oras = resultSet.getString("oras");
                String strada = resultSet.getString("strada");
                String numarStrada = resultSet.getString("numarstrada");
                String telefon = resultSet.getString("telefon");
                Oras oras_enum = null;
                if(oras.equals("BUCURESTI")){
                    oras_enum = Oras.BUCURESTI;
                }
                if(oras.equals("CLUJ")){
                    oras_enum = Oras.CLUJ;
                }
                if(oras.equals("HAMPSHIRE")){
                    oras_enum = Oras.HAMPSHIRE;
                }
                if(oras.equals("TIMISOARA")){
                    oras_enum = Oras.TIMISOARA;
                }
                if(oras.equals("NEW_YORK")){
                    oras_enum = Oras.NEW_YORK;
                }
                Persoana persoana = new Persoana(serialVersionUID, id, nume, prenume, username, parola, oras_enum, strada, numarStrada, telefon);
                persoane.add(persoana);
                row++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return persoane;
    }

    @Override
    public ArrayList<Persoana> filter(String first_name) {
        return null;
    }

    @Override
    public Persoana find(int id) {
        return null;
    }
}
