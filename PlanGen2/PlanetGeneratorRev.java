import java.util.Random;

public class PlanetGeneratorRev {
    public static void main(String[] args) {

        Random rand = new Random();
        for(int i = 0; i < 1000; i++) {
        double orbitPeriod = (countLowest(10, 100000, rand)*100)/1000;
        double radius = countLowest(2, 191700-1900, rand) + 1900;
        double surfaceTemp = surfaceTemp(orbitPeriod, rand);

        printPlanet(orbitPeriod, radius);
        }
    }
    
    // return a weighted random value within given bounds
    // weights by averaging loopCount number of integers within bounds
    static double weightedRandom(
        Random rand, 
        double lowerBound, 
        double upperBound, 
        int loopCount) {

            double output = 0.0;
            for(int i = 0; i < loopCount; i++) {
                // This nextInt looks ridiculous, but it fixes a bug w/ not respecting upper bounds.
                output += rand.nextDouble()*upperBound-(lowerBound+1) + lowerBound;
            }

            output = output/loopCount;
            return (Math.round(output*100.0))/100.0;
        }
    
    static double countHighest(int numDice, int bound, Random rand) {
        
        int highestRoll = 0;
        for(int i = 0; i < numDice; i++) {
            int currentRoll = rand.nextInt(bound);
            if(currentRoll > highestRoll) {
                highestRoll = currentRoll;
            }
        }
        return highestRoll;
    }    

    static double countLowest(int numDice, int bound, Random rand) {
        
        int lowestRoll = bound;
        for(int i = 0; i < numDice; i++) {
            int currentRoll = rand.nextInt(bound);
            if(currentRoll < lowestRoll) {
                lowestRoll = currentRoll;
            }
        }
        return lowestRoll;
    }    

    static double surfaceTemp(double orbitPeriod, Random rand) {
        
    }

    static void printPlanet(double orbitPeriod, double radius) {
        System.out.println("Orbital period: " + orbitPeriod + " days");
        System.out.println("Radius: " + radius + " km");
    }
}
