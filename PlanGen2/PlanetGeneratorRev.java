import java.util.Random;

// this file is getting fucking huge
public class PlanetGeneratorRev {
    
    static final double BOLTZMANN_CONSTANT = 5.670373e-8;
    static final double NEWTON_CONSTANT = 6.6743e-11;
    
    // kelvin, wattage, kilometers, kilograms
    static final double SOLAR_TEMPERATURE = 5778;
    static final double SOLAR_LUMINOSITY = 3.828e30;
    static final double SOLAR_RADIUS = 695700;
    static final double SOLAR_MASS = 1.988416e30;

    public static void main(String[] args) {

        Random rand = new Random(1);
        Star star = new Star();

        for(int i = 0; i < 1; i++) {

            doStarThings(star, rand);

            // range 0.1-10000
            double orbitPeriod = countLowest(12, 10000, rand);

            int radius = (int) countLowest(2, 191700-1900, rand) + 1900;

            double mass = countLowest(15, 4134.0-0.0041, rand) + 0.0041;

            double gravity = gravity(radius, mass);

            double surfaceTemp = surfaceTemp(orbitPeriod, star, rand);

            printPlanet(star, orbitPeriod, radius, mass, gravity, surfaceTemp);
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
            return output;
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

    //https://stackoverflow.com/questions/21674599/
    static double logRand(Random rand, double stdDev, double mean) {
        double stdNormal   = rand.nextGaussian();
        double normalValue = stdDev * stdNormal + mean;
        return Math.exp(normalValue);
    }

    // set the values necessary for a star
    static void doStarThings(Star star, Random rand) {

        // luminosity
        // shoouuuuuld approximate mseq luminosity on a hertzsprung-russel diagram
        double luminositySeed = weightedRandom(rand, 0.0, 17.0, 3);
        if(luminositySeed > 5) {
            star.setLuminosity(logRand(rand, 1, 4.8));
        } else if(luminositySeed > 11) {
            star.setLuminosity(logRand(rand, 0.9, 1));
        } else {
            star.setLuminosity(logRand(rand, 1.2, -2.5));
        }

    }

    // use some bs to calculate an average surface temp for a planet
    static double surfaceTemp(double orbitPeriod, Star star, Random rand) {
        
        double luminosity = star.getLuminosity();
        double albedo = rand.nextInt(100)/100;
        orbitPeriod = orbitPeriod*86400; // conversion to seconds instead of days

        //double minDist = 124764624.164;
        //double maxDist = 1.7951744e15;
        // https://hackanexoplanet.esa.int/challenges-orbital-period-and-distance/
        double distance = 
            Math.cbrt(
                ((NEWTON_CONSTANT*(star.getMass()*SOLAR_MASS))/
                (4*Math.pow(Math.PI, 2)))*(orbitPeriod*orbitPeriod));

        // https://astronomy.stackexchange.com/questions/10113/ formula
        double temp = Math.pow(
            ((luminosity*(1-albedo))/
            ((16*Math.PI)*(distance*distance)*BOLTZMANN_CONSTANT)), 
            0.25);
        return temp;
    }

    // slightly off i don't know why
    // uhhh generates gravity in g's, remove *0.107 for grav in m/s^2
    static double gravity(double radius, double mass) {
        double earthMass = 5.972168e24;
        mass = mass * earthMass; // conversion to kg
        return ((NEWTON_CONSTANT*mass/(radius*radius))/1000000)*0.107;
    }

    // kept repeating this logic in 10 billion messy places so i cleaned up and made a method
    // rounds given double to given decimal places
    static double roundToDecimal(double num, int decimals) {

        double decimalPow = Math.pow(10, decimals);
        num = Math.round(num*decimalPow);
        num = num/decimalPow;
        return num;
    }

    static void printPlanet
    (Star star, double orbitPeriod, int radius, double mass, double gravity, double surfaceTemp) {

        // output yayyyy :3
        System.out.println("===================");
        System.out.println("    > Star");
        System.out.println("Surface temperature: " + roundToDecimal(star.getTemp(), 2) +
         " K / " + roundToDecimal(star.getTemp()/SOLAR_TEMPERATURE, 3) + " T⨀");

        System.out.println("Radius: " + roundToDecimal(star.getRadius(), 1) +
         " km / " + roundToDecimal(star.getRadius()/SOLAR_RADIUS, 2) + " R⨀");

        System.out.println("Luminosity: " + star.getLuminosity() +
         " W / " + roundToDecimal(star.getLuminosity()/SOLAR_LUMINOSITY, 3) + " L⨀");

        System.out.println("Mass: " + roundToDecimal(star.getMass(), 1) + 
        " kg / " + star.getMass()/SOLAR_MASS + " M⨀");

        System.out.println("    > Planet 1");
        System.out.println("Orbital period: " + roundToDecimal(orbitPeriod, 2) + " days");
        System.out.println("Radius: " + radius + " km");
        System.out.println("Mass: " + roundToDecimal(mass, 3) + " M🜨");
        System.out.println("Gravity: " + roundToDecimal(gravity, 2) + " g");
        System.out.println("Average surface temperature: " + roundToDecimal(surfaceTemp, 1) + " K");
    }
}
