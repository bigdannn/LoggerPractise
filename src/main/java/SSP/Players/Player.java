package SSP.Players;

public class Player {
    private String name;
    private int wins = 0;
    private int loses = 0;
    private int draws = 0;

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", wins=" + wins +
                ", loses=" + loses +
                ", draws=" + draws +
                '}';
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public Player(String name) {
        this.name = name;
    }
}
