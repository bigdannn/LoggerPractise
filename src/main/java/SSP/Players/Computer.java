package SSP.Players;

public class Computer {
    private String name = "Computer";
    private int wins = 0;
    private int loses = 0;
    private int draws = 0;

    @Override
    public String toString() {
        return "Computer{" +
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
}
