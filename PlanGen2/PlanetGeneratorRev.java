import java.util.Random;

public class PlanetGeneratorRev {
    public static void main(String[] args) {

        Random rand = new Random();
        for(int i = 0; i < 10; i++) {

            // range 0.1-10000
            double orbitPeriod = Math.round((countLowest(12, 100000, rand)*10)/10); 
            orbitPeriod = orbitPeriod/10;

            int radius = (int) countLowest(2, 191700-1900, rand) + 1900;

            double mass = Math.round((countLowest(3, 4134.0-0.0041, rand) + 0.0041)*100);
            mass = mass/100;

            double gravity = gravity(radius, mass);

            double surfaceTemp = surfaceTemp(orbitPeriod, rand)/100;

            printPlanet(orbitPeriod, radius, mass, gravity, surfaceTemp);
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
    static double countHighest(int numDice, double bound, Random rand) {
        
        double highestRoll = 0;
        for(int i = 0; i < numDice; i++) {
            double currentRoll = rand.nextDouble()*bound;
            if(currentRoll > highestRoll) {
                highestRoll = currentRoll;
            }
        }
        return highestRoll;
    }    

    // rolls numDice numbers within given bound and returns the lowest one
    static double countLowest(int numDice, double bound, Random rand) {
        
        double lowestRoll = bound;
        for(int i = 0; i < numDice; i++) {
            double currentRoll = rand.nextDouble()*bound;
            if(currentRoll < lowestRoll) {
                lowestRoll = currentRoll;
            }
        }
        return lowestRoll;
    }    

    // use some bs to calculate an average surface temp for a planet
    static double surfaceTemp(double orbitPeriod, Random rand) {
        double boltzmannConstant = 5.670373e-8;
        // if we ever implement star stats, can be replaced w/ that
        double luminosity = rand.nextDouble()*10e27;
        double albedo = 0.29; // could be randomized
        double distance = 149e10;

        // https://astronomy.stackexchange.com/questions/10113/ formula
        // TODO ask mctavish whether dist*dist or math.pow(dist, 2) is better
        double temp = Math.pow(
            ((luminosity*(1-albedo))/
            ((16*Math.PI)*(distance*distance)*boltzmannConstant)), 
            0.25);
        return Math.round(temp*100);
    }

    // bugged, only works if mass is less than 1 because of multiplication goofs
    static double gravity(double radius, double mass) {
        double earthMass = 5.9722e24;
        //mass = mass * earthMass; // conversion to kg
        radius = 58232;
        mass = 5.6834e26;
        double newtonConstant = 6.67e-11;
        return (newtonConstant*mass)/(radius*radius);
    }

    static void printPlanet(double orbitPeriod, int radius, double mass, double gravity, double surfaceTemp) {
        System.out.println("===================");
        System.out.println("Orbital period: " + orbitPeriod + " days");
        System.out.println("Radius: " + radius + " km");
        System.out.println("Mass: " + mass + " M🜨");
        System.out.println("Gravity: " + gravity + "g");
        System.out.println("Average surface temperature: " + surfaceTemp + "K");
    }
}
