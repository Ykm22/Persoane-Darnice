package com.example.persoanedarnice.repository;

import com.example.persoanedarnice.domain.Nevoie;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DBRepository_Nevoi implements Repository<Nevoie>{
    private String db_url;
    private String db_username;
    private String db_password;

    public DBRepository_Nevoi(String db_url, String db_username, String db_password) {
        this.db_url = db_url;
        this.db_username = db_username;
        this.db_password = db_password;
    }
    @Override
    public void save(Nevoie nevoie) {
        String sql = "insert into nevoi (serialversionuid, id, titlu, descriere, ominnevoie, omsalvator, status, deadline) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password);
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, nevoie.getSerialVersionUID());
            ps.setInt(2, nevoie.getId());
            ps.setString(3, nevoie.getTitlu());
            ps.setString(4, nevoie.getDescriere());
            ps.setLong(5, nevoie.getOmInNevoie());
            ps.setLong(6, 0);
            ps.setString(7,nevoie.getStatus());
            ps.setDate(8, Date.valueOf(nevoie.getDeadline().toLocalDate()));
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Nevoie delete(int id) {
        return null;
    }

    @Override
    public Nevoie getOne(int id) {
        return null;
    }

    @Override
    public ArrayList<Nevoie> getAll() {
        ArrayList<Nevoie> nevoi = new ArrayList<>();
        int row = 1;
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM nevoi");
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                long serialVersionUID = resultSet.getLong("serialversionuid");
                int id = resultSet.getInt("id");
                String titlu = resultSet.getString("titlu");
                String descriere = resultSet.getString("descriere");
                LocalDateTime deadline = resultSet.getDate("deadline").toLocalDate().atStartOfDay();
                long omInNevoie = resultSet.getLong("ominnevoie");
                long omSalvator = resultSet.getLong("omsalvator");
                String status = resultSet.getString("status");
                Nevoie nevoie = new Nevoie(serialVersionUID, id, titlu, descriere, deadline, omInNevoie, omSalvator, status);
                nevoi.add(nevoie);
                row++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return nevoi;
    }

    @Override
    public ArrayList<Nevoie> filter(String first_name) {
        return null;
    }

    @Override
    public Nevoie find(int id) {
        return null;
    }

    @Override
    public void update(Nevoie nevoie) {
        String sql = "UPDATE nevoi SET omsalvator = (?), status = (?) WHERE id = (?)";
        try(Connection connection = DriverManager.getConnection(db_url, db_username, db_password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, nevoie.getOmSalvator());

            preparedStatement.setString(2, "Erou gasit!");
            preparedStatement.setLong(3, nevoie.getId());

            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
