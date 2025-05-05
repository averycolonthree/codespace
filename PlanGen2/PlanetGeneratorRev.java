import java.util.Random;

public class PlanetGeneratorRev {
    
    static final double BOLTZMANN_CONSTANT = 5.670373e-8;
    static final double NEWTON_CONSTANT = 6.67e-11;
    public static void main(String[] args) {

        Random rand = new Random(1);
        Star star = new Star();

        for(int i = 0; i < 10; i++) {

            doStarThings(star, rand);

            // range 0.1-10000
            double orbitPeriod = Math.round((countLowest(12, 100000, rand)*10)/10); 
            orbitPeriod = orbitPeriod/10;

            int radius = (int) countLowest(2, 191700-1900, rand) + 1900;

            double mass = Math.round((countLowest(12, 4134.0-0.0041, rand) + 0.0041)*100);
            mass = mass/100;

            double gravity = Math.round(gravity(radius, mass)*10);
            gravity = gravity/10;

            double surfaceTemp = Math.round(surfaceTemp(orbitPeriod, star, rand)*100);
            surfaceTemp = surfaceTemp/100;

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

    // set the values necessary for a star
    static void doStarThings(Star star, Random rand) {

        double solarTemp = 5778;
        double solarRadius = 695700;

        double temp = countLowest(5, 18000-2300, rand)+2300;
        star.setTemperature(temp);

        int radius = (rand.nextInt(100)*695700)/10;
        star.setRadius(radius);

        //double luminosity = BOLTZMANN_CONSTANT*((4*Math.PI)*(radius*radius))*Math.pow(temp, 4);
        double luminosity = 
            (Math.pow((radius/solarRadius), 2)*Math.pow((temp/solarTemp), 4));
        star.setLuminosity(Math.round(luminosity*100));

        double mass = Math.pow(luminosity, 1/3.5);
        star.setMass(Math.round(mass*100));
    }

    // use some bs to calculate an average surface temp for a planet
    static double surfaceTemp(double orbitPeriod, Star star, Random rand) {
        
        double luminosity = star.getLuminosity()*3.828e26;
        double albedo = rand.nextInt(100)/100;
        orbitPeriod = orbitPeriod*86400;

        //double minDist = 124764624.164;
        //double maxDist = 1.7951744e15;
        // https://hackanexoplanet.esa.int/challenges-orbital-period-and-distance/
        double distance = 
            Math.cbrt(
                (NEWTON_CONSTANT*(star.getMass()*3.90e30))/
                (4*Math.pow(Math.PI, 2))*(orbitPeriod*orbitPeriod));
                
        System.out.println("ohhhh my godddddddddddddddddddddddd" + distance);

        // https://astronomy.stackexchange.com/questions/10113/ formula
        double temp = Math.pow(
            ((luminosity*(1-albedo))/
            ((16*Math.PI)*(distance*distance)*BOLTZMANN_CONSTANT)), 
            0.25);
        return temp;
    }

    // slightly off i don't know why
    static double gravity(double radius, double mass) {
        double earthMass = 5.972168e24;
        mass = mass * earthMass; // conversion to kg
        return ((NEWTON_CONSTANT*mass/(radius*radius))/1000000)*0.107;
    }

    static void printPlanet(Star star, double orbitPeriod, int radius, double mass, double gravity, double surfaceTemp) {
        System.out.println("===================");
        System.out.println("    > Star");
        System.out.println("Surface temperature: " + (int) star.getTemp() + " K");
        System.out.println("Radius: " + star.getRadius() + " km");
        System.out.println("Luminosity: " + star.getLuminosity()/100 + " L⨀");
        System.out.println("Mass: " + star.getMass()/100 + " M⨀");
        System.out.println("    > Planet");
        System.out.println("Orbital period: " + orbitPeriod + " days");
        System.out.println("Radius: " + radius + " km");
        System.out.println("Mass: " + mass + " M🜨");
        System.out.println("Gravity: " + gravity + " g");
        System.out.println("Average surface temperature: " + surfaceTemp + " K");
    }
}
