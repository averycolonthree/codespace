import java.util.Random;
import java.util.Scanner;

public class PlanetGenerator {
  
  public static void main(String[] args){
    
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("How many planets to make?");
    int numPlanets = scanner.nextInt();

    for(int i = 0; i < numPlanets; i++) {
      System.out.println("========"); // divider
      makePlanet(rand);
    }

  }
  // generate planet characteristics
  static void makePlanet(Random rand) {

    // setting bounds for if planet is small or big
    boolean planetIsSmall = rand.nextInt(2) == 1;
    int tempBound;
    int gravityBound;
    int moonsBound;
    int ringsBound;
    String planetType;
    if(planetIsSmall) {     // rocky planets like Earth
      tempBound = 300;
      gravityBound = 300;
      moonsBound = 10;
      ringsBound = 20;
      planetType = "rocky";
    } else {                // gas giants like Jupiter
      tempBound = 5000;
      gravityBound = 2500;
      moonsBound = 300;
      ringsBound = 2;
      planetType = "gaseous";
    }
    
    // make temp by rolling 2 odds and averaging them
    int temp = ((rand.nextInt(tempBound) + 100) + (rand.nextInt(tempBound) + 100))/2 - tempBound;

    // set gravity 
    double gravity = Math.floor(rand.nextDouble()*gravityBound) / 100;  
    
    // set moons
    if(planetIsSmall) {
      int moonCount = Math.max
      (0, ((rand.nextInt(moonsBound) - 5) + (rand.nextInt(moonsBound) - 5))/2);
    } else {
      int moonCount = rand.nextInt(moonsBound);
    }

    // generate odds of rings or tidal locking
    boolean hasRings = rand.nextInt(ringsBound)+1 == 1;

    boolean isTidalLocked = rand.nextInt(15)+1 == 1;
    
    // printing info
    System.out.println("Planet:");
    System.out.println("Type of planet: " + planetType);
    System.out.println("Surface temp: " + temp + "C");
    System.out.println("Gravity: " + gravity + "g");
    System.out.println("Amount of moons: " + moonCount);

    if(hasRings) {
      System.out.println("Planet has rings.");
    }

    if(isTidalLocked) {
      System.out.println("Planet is tidally locked.");
    }
    
  }

}