import java.util.Random;

public class PlanetGeneratorRev {
    public static void main(String[] args) {

        Random rand = new Random(1);
        Star star = new Star();

        for(int i = 0; i < 10; i++) {

            // range 0.1-10000
            double orbitPeriod = Math.round((countLowest(12, 100000, rand)*10)/10); 
            orbitPeriod = orbitPeriod/10;

            int radius = (int) countLowest(2, 191700-1900, rand) + 1900;

            double mass = Math.round((countLowest(5, 4134.0-0.0041, rand) + 0.0041)*100);
            mass = mass/100;

            double gravity = Math.round(gravity(radius, mass)*10);
            gravity = gravity/10;

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
        double albedo = rand.nextInt(100)/100; // could be randomized

        double minDist = 124764624.164;
        double maxDist = 1.7951744e15;
        double distance = ((rand.nextDouble()*maxDist)+minDist)-minDist;

        // https://astronomy.stackexchange.com/questions/10113/ formula
        double temp = Math.pow(
            ((luminosity*(1-albedo))/
            ((16*Math.PI)*(distance*distance)*boltzmannConstant)), 
            0.25);
        return Math.round(temp*100);
    }

    // slightly off i don't know why
    static double gravity(double radius, double mass) {
        double earthMass = 5.972168e24;
        mass = mass * earthMass; // conversion to kg
        double newtonConstant = 6.67e-11;
        return ((newtonConstant*mass/(radius*radius))/1000000)*0.107;
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
