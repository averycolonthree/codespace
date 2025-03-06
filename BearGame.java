import java.util.Scanner;

public class BearGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean hasHoney = false;
        boolean hasBerry = false;
        boolean hasSalmon = false;

        intro();

        while (true) {

            String input = scanner.nextLine();
            //System.out.println(input);  //debug

            if(input.toLowerCase().equals("treetrunks")) {
                hasHoney = treetrunks(hasHoney);
            } else if(input.toLowerCase().equals("underbrush")) {
                hasBerry = underbrush(hasBerry);
            } else if(input.toLowerCase().equals("river")) {
                hasSalmon = river(hasSalmon);
            } else if(input.toLowerCase().equals("den")) {
                ending(hasHoney, hasBerry, hasSalmon);
                break;
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
        if(visited == false) {
            System.out.println("You assault the wasps from their trees, licking honey from the fallen nest.");
        } else {
            System.out.println("The wasp nest lays cracked and empty beside the tree.");
        }
        System.out.println("Where will you go next?");
        return true;
    }
    static boolean underbrush(boolean visited) {
        if(visited == false) {
            System.out.println("You crunch the hell out of some yummy berries.");
        } else {
            System.out.println("The bushes are empty of berries.. You are them all before!");
        }
        System.out.println("Where will you go next?");
        return true;
    }
    static boolean river(boolean visited) {
        if(visited == false) {
            System.out.println("You dive into the crashing waves and soon find your jaws full of fresh salmon.");
        } else {
            System.out.println("The salmon hear your approach and scatter away, leaving the river barren.");
        }
        System.out.println("Where will you go next?");
        return true;
    }
    static void ending(boolean hasHoney, boolean hasBerry, boolean hasSalmon) {
        if(hasBerry == true && hasHoney == true && hasSalmon == true) {
            System.out.println("You lay in your den, full of yummy treats, and sleep away the winter.");
            System.out.println("YOU WIN!");
        } else {
            System.out.println("You starve to death and die forever.");
        }
    }
}