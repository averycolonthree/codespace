public class Enemy {
    
    private int health;
    private int move;

    public int getHealth() {
        return health;
    }

    public int getMove() {
        return move;
    }

    public Enemy(int newHealth, int newMove) {
        this.health = newHealth;
        this.move = newMove;
    }
}
