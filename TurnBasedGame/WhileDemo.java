// Very simple Rock-Paper-Scissors RPG.
// Based on this Twine demo https://twine2.neocities.org/The_Basics_of_TBC
// I thought it was cool but I could not locate the source code...
// rewrote my own version in Java for fun, it works OK.

import java.util.Scanner;
import java.util.Random;

public class WhileDemo {
    public static void main(String[] args) {

        // TODO - Better fix for input bug  - Refactor Enemy and Player to use one Input object
        // Enemy will map to strings by using indexOf("ADF").

        System.out.println("Hark! A skeleton appears!");

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random(1);
        Player player = new Player();
        Enemy enemy = new Enemy();

        Health health = new Health();
        health.setEnemyHealth(5);
        health.setPlayerHealth(5);

        while(health.getPlayerHealth() > 0 && health.getEnemyHealth() > 0) {
            
            enemy.setMove(rand.nextInt(3));
            // 0 = Attack
            // 1 = Feint
            // 2 = Dodge

            // Attacking beats Feinting
            // Feinting beats Dodging
            // Dodging beats Attacking

            // Display text corresponding to next move enemy will use
            System.out.println(enemyPredictMove(enemy.getMove()));

            // Show "scoreboard" (player & enemy health + controls)
            scoreboard(health.getPlayerHealth(), health.getEnemyHealth());

            // Take player input, used in battle method
            player.setMove(scanner.next().toUpperCase());

            health = battle(player.getMove(), enemy.getMove(), health);
            
        }

        if(health.getPlayerHealth() <= 0) {
            System.out.println("Better luck next time!");
        } else {
            System.out.println("You defeated the skeleton!");
        }
    }
    
    // Display basic info about game (Health & controls)
    static void scoreboard(int playerHealth, int enemyHealth) {
        System.out.println("Player HP: " + playerHealth);
        System.out.println("Enemy HP: " + enemyHealth);
        System.out.println("What will you do?");
        System.out.println("A to Attack. F to Feint. D to Dodge.");
    }
    
    static Health battle(String playerMove, int enemyMove, Health health) {

        // Major bug, if player inputs anything else, it rerolls skeleton attacks.
        // Fixed by making Dodging a fallback move, but I do wish there was a better way..
        
        
        if(playerMove.equals("A")) { // player attacking cases
            // Draw - player & enemy attack
            if(enemyMove == 0) {
                System.out.println("You both attack, swords clashing.");
                return health;
            // Win - player attacks, enemy feints
            } else if(enemyMove == 1) {
                System.out.println("You spear the skeleton's bones before it can try to feint!");
                health.setEnemyHealth(health.getEnemyHealth()-1);
                return health;
            // Loss - player attacks, enemy dodges
            } else {
                System.out.println("You try to attack, but the skeleton dodges and counters!");
                health.setPlayerHealth(health.getPlayerHealth()-1);
                return health;
            }
        } else if(playerMove.equals("F")) { // player feinting cases
            // Lose - player feints, enemy attacks
            if(enemyMove == 0) {
                System.out.println("You try to feint, but the skeleton catches you and attacks!");
                health.setPlayerHealth(health.getPlayerHealth()-1);
                return health;
            // Draw - player feints, enemy feints
            } else if(enemyMove == 1) {
                System.out.println("You try to feint eachother and fail terribly.");
                return health;
            // Win - player feints, enemy dodges
            } else {
                System.out.println("You feint and jab the skeleton out before it can dodge!");
                health.setEnemyHealth(health.getEnemyHealth()-1);
                return health;
            }
        } else  { // player dodging cases
            // Win - player dodges, enemy attacks
            if(enemyMove == 0) {
                System.out.println("You dodge before the skeleton can get you, and it trips!");
                health.setEnemyHealth(health.getEnemyHealth()-1);
                return health;
            // Loss - player dodges, enemy feints
            } else if(enemyMove == 1) {
                System.out.println("You try to dodge, but the skeleton predicts your move and counters with a feint!");
                health.setPlayerHealth(health.getPlayerHealth()-1);
                return health;
            // Draw - player dodges, enemy dodges
            } else {
                System.out.println("You both dodge eachother. Nothing happens.");
                return health;
            }
        } //else {
          //  System.out.println("Wrong!");
          //  health.setPlayerHealth(-999999999);
          //  System.out.println("Player health: " + health.getPlayerHealth());
          //  return health;
        }
    

    static String enemyPredictMove(int enemyMove) {
        // we do this exact logic in battle(), but i don't know how i'd get around that
        // not actually sure if i can
        if(enemyMove == 0) {
            return "The skeleton draws its sword.";
        } else if(enemyMove == 1) {
            return "The skeleton rattles its bones.";
        } else {
            return "The skeleton crouches down.";
        }
    }
}

//     if(input.equals("A")) {
        //         System.out.println("You lunge forward and attack.");
        //         enemyHealth--;
        //     } else if(input.equals("F")) {
        //         // if number is 1-3, feint succeeds
        //         // if it's 0, feint fails
        //         if(rand.nextInt(4) > 0) {
        //             System.out.println("You take a hit on purpose, and fight back twice as hard!");
        //             playerHealth--;
        //             enemyHealth-=2;
        //         } else {
        //             System.out.println("You take a hit on purpose,"+
        //             "but stumble before you can repay the favor.");
        //             playerHealth--;
        //         }
        //     }

        //     // 8/10 chance for enemy to hit
        //     if(rand.nextInt(10)+1 > 2) {
        //         System.out.println("The enemy hits!");
        //         playerHealth--;
        //     } else {
        //         System.out.println("The enemy misses!");
        //     }
        // }
