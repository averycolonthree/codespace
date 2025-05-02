import java.util.Random;

public class scratchpad {
    public static void main(String[] args) {
        Random rand = new Random();
        for(int i=0; i < 1000; i++){
            int test = weightedRandom(rand, 50, 150, 1);
            System.out.println(test);
        }
    }
    
    // return a weighted random value within given bounds
    // weights by averaging loopCount number of integers within bounds
    static int weightedRandom(Random rand, int lowerBound, int upperBound, int loopCount) {
        int output = 0;
        for(int i = 0; i < loopCount; i++) {
            output += rand.nextInt(upperBound) + lowerBound;
        }
        output = output/loopCount;
        return output;
    }
}
