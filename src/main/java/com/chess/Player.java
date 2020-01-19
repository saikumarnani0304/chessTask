package com.chess;

public class Player {
    private int playerID;
    private String name;
    private String email;
    private String password;

    public Player(){

    }

    public Player(int playerID, String name, String email, String password) {
        this.playerID = playerID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
