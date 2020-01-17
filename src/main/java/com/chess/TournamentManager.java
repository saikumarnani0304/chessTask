package com.chess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentManager {

    private Connection connection;
    public TournamentManager(){

    }
    public void establishConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chess", "admin", "admin");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addTournament(String name,String dateRange,String locationInput,int rounds,String duration,int win,int loss,int bye,int draw){
        establishConnection();
        String query="insert into Tournament(name,daterange,location,rounds,duration,win,loss,bye,draw) values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,dateRange);
            statement.setString(3,locationInput);
            statement.setInt(4,rounds);
            statement.setString(5,duration);
            statement.setInt(6,win);
            statement.setInt(7,loss);
            statement.setInt(8,bye);
            statement.setInt(9,draw);


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }

    public List<Tournament> getTournamentDetails(){
        establishConnection();
        List<Tournament> allTournaments=new ArrayList<>();
        String query="select *from Tournament";


        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Tournament tournament=createTournament(rs);
                allTournaments.add(tournament);
            }
            return allTournaments;

        } catch (SQLException e) {
            e.printStackTrace();
            return allTournaments;
        }finally {
            closeConnection();
        }
    }


    public int joinTournament(int playerID,int tournamentID){
        establishConnection();
        String query="insert into PlayersIn values(?,?)";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,playerID);
            statement.setInt(2,tournamentID);
            int rows=statement.executeUpdate();
            return rows;
        } catch (SQLException e) {

            e.printStackTrace();
            return 0;
        }
        finally {

            closeConnection();
        }
    }

    public Tournament createTournament(ResultSet resultSet) {
        Tournament tournament = new Tournament();
        try {
            tournament.setTournamentId(resultSet.getInt(1));
            tournament.setName(resultSet.getString(2));
            tournament.setDateRange(resultSet.getString(3));
            tournament.setLocationInput(resultSet.getString(4));
            tournament.setRounds(resultSet.getInt(5));
            tournament.setDuration(resultSet.getString(6));
            tournament.setWin(resultSet.getInt(7));
            tournament.setLoss(resultSet.getInt(8));
            tournament.setBye(resultSet.getInt(9));
            tournament.setDraw(resultSet.getInt(10));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tournament;
    }



}
