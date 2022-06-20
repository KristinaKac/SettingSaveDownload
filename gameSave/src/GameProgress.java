import java.io.Serializable;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;
    public String path;
    public GameProgress(int health, int weapons, int lvl, double distance, String path){
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
        this.path  = path;
    }

    @Override
    public String toString() {
        return "Game Progress {" +
                " health = " + health +
                ", weapons = " + weapons +
                ", lvl = " + lvl +
                ", distance = " + distance + " }";
    }
}
