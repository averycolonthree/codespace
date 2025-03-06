import java.util.Random;

public class siclr {
    public static void main(String[] args) {

        Random rand = new Random();

        for(int i = 0; i < 13; i++) {
            for(int j = 0; j < 30; j++) {
                
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
}