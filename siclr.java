import java.util.Random;

public class siclr {
    public static void main(String[] args) {

        Random rand = new Random();

        randomField(rand);

        System.out.println();

        randomRows(rand);
        
        
    }
    static void randomField(Random rand) {
        for(int rows = 0; rows < 13; rows++) {
            for(int columns = 0; columns < 30; columns++) {
                
                double randnum = rand.nextDouble();
                if(randnum > 0.5) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }

            }
            System.out.println();
        }
    }
    static void randomRows(Random rand) {
        for(int rows = 0; rows < 13; rows++) {
            
            int randInt = rand.nextInt(10) + 1;

                for(int i = randInt; i < 10; i++) {
                    System.out.print("#");
                }
            
            System.out.println();
        }
        System.out.println("check out my sweet statistics vro");
    }
 }