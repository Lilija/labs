package soccer;

import util.GameUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Game {
    private Team homeTeam;
    private Team awayTeam;
    public GameEvent[] gameEvents;
    private LocalDateTime theDateTime;
    public int homeTeamGoals = 0;
    public int awayTeamGoals = 0;

    public LocalDateTime getTheDateTime() {
        return theDateTime;
    }

    public void setTheDateTime(LocalDateTime theDateTime) {
        this.theDateTime = theDateTime;
    }


    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    //Constructor
    public Game(Team pHomeTeam, Team pAwayTeam) {
        this.homeTeam = pHomeTeam;
        this.awayTeam = pAwayTeam;
        this.homeTeamGoals = 0;
        this.awayTeamGoals = 0;
        this.setTheDateTime(LocalDateTime.now());
    }

    public void playGame(int maxNumGoals) {
        this.homeTeamGoals = 0;
        this.awayTeamGoals = 0;
        GameUtils.addGameEvent(this, maxNumGoals);

    }

    public void playGame() {
        GameUtils.addGameEvent(this, 9);

    }
/*
    public String getForTeam(GameEvent theGameEvent) {
        String forTeam;

        forTeam = (this.homeTeam == theGameEvent.theTeam) ? "hometeam" : "away team";
        return forTeam;
    }
*/
    public int getNumOfGoals(Team team) {
        int forTeam;
        forTeam = (team == this.homeTeam) ? homeTeamGoals : awayTeamGoals;
        return forTeam;
    }

    public void getDescription() {
        StringBuilder desc = new StringBuilder();
        for (GameEvent item : this.gameEvents) {
            desc = desc.delete(0, desc.length());
            desc = desc.append(item.toString());
            System.out.println(desc.toString());
        }
    }


    public String toString() {
        String whoWon = "Datum: "+this.getTheDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))+" "
                +this.homeTeam.teamName +" vs "+this.awayTeam.teamName+", ukupno golova: "+(homeTeamGoals +awayTeamGoals);

        if( getNumOfGoals(this.homeTeam) >getNumOfGoals(this.awayTeam))

        {
            whoWon = whoWon  +this.homeTeam.teamName + " sa " + getNumOfGoals(this.homeTeam) + "-" + getNumOfGoals(this.awayTeam);
            this.homeTeam.setTeamPoints(2);
        }
        else if(getNumOfGoals(this.homeTeam) <getNumOfGoals(this.awayTeam))

        {
            whoWon = whoWon+  this.awayTeam.teamName + " sa " + getNumOfGoals(this.awayTeam) + "-" + getNumOfGoals(this.homeTeam);
            this.awayTeam.setTeamPoints(2);
        }
        else
        {
            whoWon = whoWon + "/n"+"It's a tye";
            this.homeTeam.setTeamPoints(1);
            this.awayTeam.setTeamPoints(1);
        }

        return whoWon;

    }

}
