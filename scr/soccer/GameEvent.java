package soccer;

public abstract class GameEvent {
    public Team theTeam;
    public Player thePlayer;
    public double theTime;

    public Team getTheTeam() {
        return this.thePlayer.getPlayerTeam();
    }

    public void setTheTeam(Team theTeam) {
        this.theTeam = theTeam;
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " for " +theTeam.teamName +
                " by " + thePlayer.toString() +
                ", at " + theTime + "min";
    }
}
