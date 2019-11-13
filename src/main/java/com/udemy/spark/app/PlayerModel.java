package com.udemy.spark.app;

import java.io.Serializable;

public class PlayerModel implements Serializable {
    //roundID,matchID,Team,coachName,Line-up,shirtNumber,playerName,Position,Event
    String roundID;
    String matchID;
    String team;
    String coachName;
    String lineup;
    String shirtNumber;
    String playerName;
    String position;
    String event;

    public PlayerModel(String roundID, String matchID, String team, String coachName, String lineup, String shirtNumber, String playerName, String position, String event) {
        this.roundID = roundID;
        this.matchID = matchID;
        this.team = team;
        this.coachName = coachName;
        this.lineup = lineup;
        this.shirtNumber = shirtNumber;
        this.playerName = playerName;
        this.position = position;
        this.event = event;
    }

    public String getRoundID() {
        return roundID;
    }

    public void setRoundID(String roundID) {
        this.roundID = roundID;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getLineup() {
        return lineup;
    }

    public void setLineup(String lineup) {
        this.lineup = lineup;
    }

    public String getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(String shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
