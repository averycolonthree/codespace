import java.util.Random;
import java.util.Scanner;

public class PlanetGenerator {
  public static void main(String[] args) {

    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    Planet planet = new Planet();

    System.out.println("How many planets to make?");
    String numPlanets = scanner.nextLine();

      for (int i = 0; i < Integer.valueOf(numPlanets); i++) {

        planet.setPlanetType(rand.nextInt(2));
        String planetType = planet.getPlanetType();
        System.out.println("========"); // divider
        System.out.println("Planet:");
        System.out.println("Type of planet: " + 
        planet.getPlanetType());
        System.out.println("Surface temp: " + 
        temp(rand, planetType, planet.getTempBound()) + "C");
        System.out.println("Gravity: " + 
        gravity(rand, planetType, planet.getGravityBound()) + "g");
        System.out.println("Amount of moons: " + moons(rand, planetType, planet.getMoonsBound()));
        hasRings(rand, planet.getRingsBound());
        isTidalLocked(rand);
      }
    }
  // generate planet characteristics
  // generates temperature based on 
  static int temp(Random rand, String planetType, int tempBound) {

    int minTemp = -274; // rounded absolute zero

    if (planetType.equals("rocky")) {
      // temp bound is 300
      // -200 thru 200
      return ((rand.nextInt(tempBound)) + (rand.nextInt(tempBound)))/ 2 - 200;
    } else {
      return Math.max(minTemp, rand.nextInt(tempBound) - 250);
    }
  }

  static double gravity(Random rand, String planetType, int gravityBound) {
    double gravity = Math.floor(rand.nextDouble() * gravityBound) / 100;
    return gravity;
  }

  static int moons(Random rand, String planetType, int moonsBound) {
    if (planetType.equals("rocky")) {
      return Math.max(0, ((rand.nextInt(moonsBound) - 5) + (rand.nextInt(moonsBound) - 5)) / 2);
    } else {
      return rand.nextInt(moonsBound);
    }
  }

  static void hasRings(Random rand, int ringsBound) {
    if (rand.nextInt(ringsBound) + 1 == 1) {
      System.out.println("Planet has rings.");
    }
  }

  static void isTidalLocked(Random rand) {
    if (rand.nextInt(15) + 1 == 1) {
      System.out.println("Planet is tidally locked.");
    }
  }

}