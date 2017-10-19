/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import soccer.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author ksomervi
 */
public class GameUtils {

    public static void addGameEvent(Game currGame, int maxNumGoals) {
        if (currGame.gameEvents == null) {
            currGame.gameEvents = new GameEvent[(int) (Math.random() * (maxNumGoals+1))];   // If goals not initialized max will be 9
        }
        //System.out.println(currGame.goals.length);
        int i = 0;
        for (GameEvent currGameEvent : currGame.gameEvents) {
            currGameEvent = (Math.random()>0.5)? new Goal() : new Possesion();
            //currGameEvent = new Goal();
            currGameEvent.theTeam = Math.random() > 0.5 ? getHomeTeam(currGame, "home") : getHomeTeam(currGame, "away");
            currGameEvent.thePlayer = currGameEvent.theTeam.playerArray[(int) (Math.random() * currGameEvent.theTeam.playerArray.length)];
            currGameEvent.theTime = (int) (Math.random() * 90);
            currGame.gameEvents[i] = currGameEvent;
            if (currGameEvent.getClass().getSimpleName().equals("Goal") ) {
                currGameEvent.thePlayer.incPlayerNumOfPoints(1);
                if (currGameEvent.theTeam == getHomeTeam(currGame, "home")) {
                    ++currGame.homeTeamGoals;
                } else ++currGame.awayTeamGoals;
            }
            i++;
        }



    }

    public static void addGameEvent(Game currGame) {

        addGameEvent( currGame,9) ;

    }

    // Uses reflection so works with getter method or public field
    private static Team getHomeTeam(Game currGame, String homeOrAway) {
        Team theTeam = null;
        Method m;
        Field f;
        try {
            m = Game.class.getMethod("get" + Character.toUpperCase(homeOrAway.charAt(0)) + homeOrAway.substring(1) + "Team");
            theTeam = (Team)m.invoke(currGame);
        } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException em) {
            try {
                f = Game.class.getField(homeOrAway + "Team");
                theTeam = (Team)f.get(currGame);
            } catch (NoSuchFieldException|IllegalAccessException ef) { 
                System.out.println("The addGoals() utility requires the Goal class to contain either:\n" +
                        "public String fields called homeTeam and awayTeam, OR,\n" +
                        "public accessor methods called getHomeTeam() and getAwayTeam().");
            }
        }
        return theTeam;
    }
}
