import java.util.Random;

public class Scratchpad {

    static final double SOLAR_LUMINOSITY = 3.828e30;
    public static void main(String[] args) {
        
        Random rand = new Random();

        for(int i = 0; i < 1000; i++) {

            double luminosity = 0.0;
            double luminositySeed = weightedRandom(rand, 0.0, 17.0, 3);
            
            if(luminositySeed < 5) {
                luminosity = (logRand(rand, 1, 4.8));
            } else if(luminositySeed < 11) {
                luminosity = (logRand(rand,0.9, 1));
            } else {
                luminosity = (logRand(rand, 1.2, -2.5));
            }

            double mass = Math.pow(luminosity*SOLAR_LUMINOSITY, 1/3.5);
            System.out.println(mass);
            
        }
    }


    //https://stackoverflow.com/questions/21674599/
    static double logRand(Random rand, double stdDev, double mean) {
        double stdNormal   = rand.nextGaussian();
        double normalValue = stdDev * stdNormal + mean;
        return Math.exp(normalValue);
    }

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
}
