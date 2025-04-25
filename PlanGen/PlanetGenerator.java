import java.util.Random;
import java.util.Scanner;

public class PlanetGenerator {

  // setting bounds for if planet is small or big
  static int TEMP_BOUND;
  static int GRAVITY_BOUND;
  static int MOONS_BOUND;
  static int RINGS_BOUND;
  static String PLANET_TYPE;

  public static void main(String[] args) {

    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    System.out.println("How many planets to make?");
    String numPlanets = scanner.nextLine();

    // checks that the input is not a number
    if (!Character.isDigit(numPlanets.charAt(0))) {
      debugMode(numPlanets, rand, scanner);
    } else {
      for (int i = 0; i < Integer.valueOf(numPlanets); i++) {

        if (rand.nextInt(2) == 1) {
          // rocky planets like Earth
          TEMP_BOUND = 300;
          GRAVITY_BOUND = 300;
          MOONS_BOUND = 10;
          RINGS_BOUND = 20;
          PLANET_TYPE = "rocky";
        } else {
          // gas giants like Jupiter
          TEMP_BOUND = 250;
          GRAVITY_BOUND = 2500;
          MOONS_BOUND = 300;
          RINGS_BOUND = 2;
          PLANET_TYPE = "gaseous";
        }

        System.out.println("========"); // divider
        System.out.println("Planet:");
        System.out.println("Type of planet: " + PLANET_TYPE);
        System.out.println("Surface temp: " + temp(rand) + "C");
        System.out.println("Gravity: " + gravity(rand) + "g");
        System.out.println("Amount of moons: " + moons(rand));
        hasRings(rand);
        isTidalLocked(rand);
      }
    }
  }

  // generate planet characteristics
  static int temp(Random rand) {

    if (PLANET_TYPE.equals("rocky")) {
      return Math.max(-250, ((rand.nextInt(TEMP_BOUND) + 100) + (rand.nextInt(TEMP_BOUND) + 100)) / 2 - 150);
    } else {
      return Math.max(-250, rand.nextInt(TEMP_BOUND) - 250);
    }
  }

  static double gravity(Random rand) {
    double gravity = Math.floor(rand.nextDouble() * GRAVITY_BOUND) / 100;
    return gravity;
  }

  static int moons(Random rand) {
    if (PLANET_TYPE.equals("rocky")) {
      return Math.max(0, ((rand.nextInt(MOONS_BOUND) - 5) + (rand.nextInt(MOONS_BOUND) - 5)) / 2);
    } else {
      return rand.nextInt(MOONS_BOUND);
    }
  }

  static void hasRings(Random rand) {
    if (rand.nextInt(RINGS_BOUND) + 1 == 1) {
      System.out.println("Planet has rings.");
    }
  }

  static void isTidalLocked(Random rand) {
    if (rand.nextInt(15) + 1 == 1) {
      System.out.println("Planet is tidally locked.");
    }
  }

}