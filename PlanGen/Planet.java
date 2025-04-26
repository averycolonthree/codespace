public class Planet {
    private int tempBound;
    private int gravityBound;
    private int moonsBound;
    private int ringsBound;
    private String planetType;

    public void setPlanetType(int rand) {
        if(rand == 1) {
            tempBound = 400;
            gravityBound = 300;
            moonsBound = 10;
            ringsBound = 20;
            planetType = "rocky"; 
        } else {
            tempBound = 274;
            gravityBound = 2500;
            moonsBound = 300;
            ringsBound = 2;
            planetType = "gaseous"; 
        }
    }

    public int getTempBound() {
        return tempBound;
    }
    public int getGravityBound() {
        return gravityBound;
    }
    public int getMoonsBound() {
        return moonsBound;
    }
    public int getRingsBound() {
        return ringsBound;
    }
    public String getPlanetType() {
        return planetType;
    }

}