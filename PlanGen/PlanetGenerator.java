import java.util.Random;
import java.util.Scanner;

public class PlanetGenerator {
  
  public static void main(String[] args){
    
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("How many planets to make?");
    int numPlanets = scanner.nextInt();
    for(int i = 0; i < numPlanets; i++) {
      makePlanet(rand);
      System.out.println("========"); // divider
    }

  }
  // generate planet characteristics
  public static int[] makePlanet(Random rand) {

    // make temp by rolling 2 odds and averaging them
    int hot1 = rand.nextInt(150) + 100;    // evil statistics hacking
    int hot2 = rand.nextInt(150) + 100;
    int temp = (hot1 + hot2)/2 - 150;

    // make gravity between 0.1x-3x gravity on Earth
    double gravity = rand.nextInt((30) + 1)/10;  

    // generate odds of rings or tidal locking
    int hasRings = rand.nextInt(100)+1;
    int isTidalLocked = rand.nextInt(100)+1;
    
    System.out.println("Planet:");
    System.out.println("Surface temp: " + temp + "C");
    System.out.println("Gravity: " + gravity + "g");
    if(hasRings == 1) {
      System.out.println("Planet is ringed.");
    }
    if(isTidalLocked == 1) {
      System.out.println("Planet is tidally locked.");
    }
  }

}