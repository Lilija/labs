package soccer;
import util.CrTeamsException;
import util.ProjectSettings;
import util.GameUtils;
import util.PlayerDatabase;

import java.sql.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class League {

private LocalDate fromDate;
private LocalDate toDate;

public void getBestTeams (Team[] teams) {
    Arrays.sort(teams);
    for (Team item:teams) {
        System.out.println(item);
    }

}

    public void getBestPlayers (Team[] teams) {
        ArrayList<Player> thePlayers = new ArrayList();
        for (Team item:teams) {
            thePlayers.addAll(Arrays.asList(item.playerArray));

        }
        Collections.sort(thePlayers,
                (p1,p2)->Double.valueOf(((Player)p2).getNumOfPoints()).compareTo(Double.valueOf(((Player)p1).getNumOfPoints())));

        for (Player item: thePlayers) {
            System.out.println(item.toString());
        }

    }

    public League(LocalDate fromDate, LocalDate toDate){
    this.fromDate = fromDate;
    this.toDate = toDate;
}

public void getAnounsment() {
    System.out.println("Leage will be played from "+
                        this.fromDate.format(ProjectSettings.LeagueFormatter)+ " to "+ this.toDate.format(ProjectSettings.LeagueFormatter));
}

    public  Team[]createTeams (String names, int numOfPlayers) {
        PlayerDatabase leaguePlayers = new PlayerDatabase();
        //getTeam(String name, int numOfPlayers)
        ArrayList<Team> theTeams = new ArrayList();
        for (String item : names.split(",")){
           try{ theTeams.add(leaguePlayers.getTeam(item, numOfPlayers));}
           catch (CrTeamsException e){
               e.printStackTrace();
           }
        }
            return theTeams.toArray(new Team[theTeams.size()]);

    }

    public Game[]createGames (Team[] teams){
        ArrayList<Game> games = new ArrayList();
        for (Team homeTeam: teams){
            for (Team awayTeam:teams){
                if (homeTeam!=awayTeam){
                    games.add(new Game(homeTeam, awayTeam));
                }
            }
        }

        return games.toArray(new Game[games.size()]);
    }




    public static void main (String[] args) {

        League theLeague = new League(LocalDate.now(),  LocalDate.now().plusMonths(2).plusDays(5));
        theLeague.getAnounsment();
        String names = "Prvi tim, Drugi tim, Treci tim";
        Team[] gameTeams  = theLeague.createTeams(names, 5);
        Game[] leagueGames = theLeague.createGames(gameTeams);
        for (Game item : leagueGames) {
            item.playGame();
            System.out.println(item.toString());
            //item.getDescription();
        }
        theLeague.getBestTeams(gameTeams);
        theLeague.getBestPlayers (gameTeams);

    }
}
