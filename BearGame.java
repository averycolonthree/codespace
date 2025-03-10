import java.util.Scanner;

public class BearGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // tracks which areas player has visited
        boolean hasHoney = false;
        boolean hasBerry = false;
        boolean hasSalmon = false;

        intro(); // prints possible inputs player can give; introduces game premise

        while (true) {

            // String input = scanner.nextLine();
            // input = input.toLowerCase();
            String input = scanner.nextLine().toLowerCase();
            //System.out.println(input);  //debug

            // calls methods for each location player can visit
            // treetrunks location, sets honey to true
            if(input.equals("treetrunks")) { 
                hasHoney = treetrunks(hasHoney);

            // underbrush location, sets berry to true
            } else if(input.equals("underbrush")) {
                hasBerry = underbrush(hasBerry);

            // river location, sets salmon to true
            } else if(input.equals("river")) {
                hasSalmon = river(hasSalmon);

            // den location, ends the game
            } else if(input.equals("den")) {
                ending(hasHoney, hasBerry, hasSalmon);
                break;
            
            // in case player gives invalid input
            } else {
                System.out.println("Huh? Where's that?");
            }
        }
    }
    static void intro() {
        System.out.println("You are a bear in the forest.");
        System.out.println("You must gather food before the winter to hibernate.");
        System.out.println("Where will you go?");
        System.out.println("Treetrunks");
        System.out.println("Underbrush");
        System.out.println("River");
        System.out.println("Den");
    }

    static boolean treetrunks(boolean visited) {
        if(visited) {
            System.out.println("The wasp nest lays cracked and empty beside the tree.");
        } else {
            System.out.println("You assault the wasps from their trees, licking honey from the fallen nest.");
        }
        System.out.println("Where will you go next?");
        return true; // set this location as visited
    }
    static boolean underbrush(boolean visited) {
        if(visited) {
            System.out.println("The bushes are empty of berries.. You ate them all already. :()");
        } else {
            System.out.println("You crunch the hell out of some yummy berries.");
        }
        
        System.out.println("Where will you go next?");
        return true; // set this location as visited
    }
    static boolean river(boolean visited) {
        if(visited) {
            System.out.println("The salmon hear your approach and scatter away, leaving the river barren.");
        } else {
            System.out.println("You dive into the crashing waves and soon find your jaws full of fresh salmon.");
        }
        
        System.out.println("Where will you go next?");
        return true; // set this location as visited
    }
    static void ending(boolean hasHoney, boolean hasBerry, boolean hasSalmon) {
        if(hasBerry && hasHoney && hasSalmon) {
            System.out.println("You lay in your den, full of yummy treats, and sleep away the winter.");
            System.out.println("YOU WIN!");
        } else {
            System.out.println("You starve to death and die forever.");
        }
    }
}