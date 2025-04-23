// Very simple Rock-Paper-Scissors RPG.
// Based on this Twine demo https://twine2.neocities.org/The_Basics_of_TBC
// I thought it was cool but I could not locate the source code...
// rewrote my own version in Java for fun, it works OK.

import java.util.Scanner;
import java.util.Random;

public class WhileDemo {
    public static void main(String[] args) {

        int[] health = {5,5};
        // first is player, second is enemy
        // this is fugly but its also easy

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random(1);

        System.out.println("Hark! A skeleton appears!");

        while(health[0] > 0) {
            
            int enemyMove = rand.nextInt(3);
            // 0 = Attack
            // 1 = Feint
            // 2 = Dodge

            // Attacking beats Feinting
            // Feinting beats Dodging
            // Dodging beats Attacking

            // Display text corresponding to next move enemy will use
            System.out.println(enemyPredictMove(enemyMove));
            // Show "scoreboard" (player & enemy health + controls)
            scoreboard(health);
            // Take player input, used in battle method
            String playerMove = scanner.next().toUpperCase();

            health = battle(health, playerMove, enemyMove);
            
            // Win case
            if(health[1] == 0) {
                System.out.println("You defeat the mighty skeleton!");
                health[0] = 0;
            }
        }
    }
    
    // Display basic info about game (Health & controls)
    static void scoreboard(int[] health) {
        System.out.println("Player HP: " + health[0]);
        System.out.println("Enemy HP: " + health[1]);
        System.out.println("What will you do?");
        System.out.println("A to Attack. F to Feint. D to Dodge.");
    }
    
    static int[] battle(int[] health, String playerMove, int enemyMove) {

        // TODO - Major bug, if player inputs anything else, it rerolls skeleton attacks.
        
        // player attacking cases
        if(playerMove.equals("A")) {
            // Draw - player & enemy attack
            if(enemyMove == 0) {
                System.out.println("You both attack, swords clashing.");
                return health;
            // Win - player attacks, enemy feints
            } else if(enemyMove == 1) {
                System.out.println("You spear the skeleton's bones before it can try to feint!");
                health[1]--;
                return health;
            // Loss - player attacks, enemy dodges
            } else {
                System.out.println("You try to attack, but the skeleton dodges and counters!");
                health[0]--;
                return health;
            }
        }

        // player feinting cases
        if(playerMove.equals("F")) {
            // Lose - player feints, enemy attacks
            if(enemyMove == 0) {
                System.out.println("You try to feint, but the skeleton catches you and attacks!");
                health[0]--;
                return health;
            // Draw - player feints, enemy feints
            } else if(enemyMove == 1) {
                System.out.println("You try to feint eachother and fail terribly.");
                return health;
            // Win - player feints, enemy dodges
            } else {
                System.out.println("You feint and jab the skeleton out before it can dodge!");
                health[1]--;
                return health;
            }
        }

        // player dodging cases
        if(playerMove.equals("D")) {
            // Win - player dodges, enemy attacks
            if(enemyMove == 0) {
                System.out.println("You dodge before the skeleton can get you, and it trips!");
                health[1]--;
                return health;
            // Loss - player dodges, enemy feints
            } else if(enemyMove == 1) {
                System.out.println("You try to dodge, but the skeleton predicts your move and counters with a feint!");
                health[0]--;
                return health;
            // Draw - player dodges, enemy dodges
            } else {
                System.out.println("You both dodge eachother. Nothing happens.");
                return health;
            }
        }
        return health;
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
