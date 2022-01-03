import java.util.Random;

public class Light{
    //instance variable(s)
    private boolean isOn;

    //two class constants
    public static final String ON = "💡"; 
    public static final String OFF = "⬛";

    //Default Constructor, randomly setting isOn
    public Light () {
        Random generator = new Random(); // create a random variable
        isOn = generator.nextBoolean(); // assign random boolean to isOn
    }

    //void 'flip' method
    public void flip () {
        //  toggle the current value of the light
        if (isOn) { // isOn = true
            isOn = false;
        }
        else { // isOn is currently false
            isOn = true;
        }
    }

    //method indicating light status (accessor)
    public boolean getStatus() {
        return isOn;  // returns current state of light
    }


}


 
