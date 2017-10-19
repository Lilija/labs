package soccer;

public class Team implements  Comparable{
    public String teamName;
    public Player[] playerArray;
    private int numOfPoints;


    public int getNumOfPoints() {
        return numOfPoints;
    }

    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = numOfPoints;
    }




    public Team (String pName){
        this.teamName = pName;

    }
    public Team (String pName, Player[] pPlayers){
        this(pName);
        this.playerArray = pPlayers;
        this.numOfPoints = 0;
    }


    public Team (){ }

    protected  void setTeamPoints (int numOfPoints){
        this.numOfPoints +=numOfPoints;
    }

    public void setPlayerArray(Player[] playerArray) {
        this.playerArray = playerArray;
        for (Player item:playerArray)  {
            item.setTeam(this);


        }

    }

    public void getPlayernamelike (String matchingString){
        for (Player item : this.playerArray){
            if (item.getPlayerName().matches(matchingString)){
                System.out.println("Igraci koji imaju ime "+ matchingString + " je "+ item.toString());
            }
            else
                System.out.println("Igrac koji nema takvo ime je "+ item.getPlayerName().split(" ")[1]);
        }
    }

    @Override
    public int compareTo(Object o) {
        return (int) ( (this.numOfPoints<=((Team)o).numOfPoints)? 1:-1);

    }

    public String toString() {
        return this.teamName + ", points "+ this.numOfPoints;
    }
}
