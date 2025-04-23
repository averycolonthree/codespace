public class Player {

    private int health;
    private String move;

    public int getHealth() {
        return health;
    }

    public String getMove() {
        return move;
    }

    public Player(int newHealth, String newMove) {
    this.health = newHealth;
    this.move = newMove;
    }
}
