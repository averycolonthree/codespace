import java.util.Random;

public class PlanetGeneratorRev {
    public static void main(String[] args) {

        Random rand = new Random();
        for(int i = 0; i < 1000; i++) {
        double orbitPeriod = (countLowest(10, 100000, rand)*100)/1000;
        double radius = countLowest(2, 191700-1900, rand) + 1900;
        double surfaceTemp = surfaceTemp(orbitPeriod, rand)/100;

        printPlanet(orbitPeriod, radius, surfaceTemp);
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
    
    // rolls numDice numbers within given bound and returns the highest one
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

    // rolls numDice numbers within given bound and returns the lowest one
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
        double boltzmannConstant = 5.670373e-8;
        double luminosity = rand.nextDouble()*10e27; // if we ever implement star stats, can be replaced w/ that
        double albedo = 0.29;
        double distance = 149e9;

        double temp = Math.pow(((luminosity*(1-albedo))/((16*Math.PI)*(distance*distance)*boltzmannConstant)), 0.25);
        return Math.round(temp*100);
    }

    static void printPlanet(double orbitPeriod, double radius, double surfaceTemp) {
        System.out.println("Orbital period: " + orbitPeriod + " days");
        System.out.println("Radius: " + radius + " km");
        System.out.println("Average surface temperature: " + surfaceTemp + "K");
    }
}
