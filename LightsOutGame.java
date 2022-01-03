public class LightsOutGame{

    // instance variables
    private BFF bff;
    private Light[] lights;
    private boolean useEmoji;

    //constructor
    public LightsOutGame () {
        // Intro message
        System.out.println("Welcome to Lights Out!\nThe objective is to turn off all the lights.");
        bff = new BFF();
        int numLights = bff.inputInt("How many lights would you like to have (3-15)?",
         3, 15);
        lights = new Light[numLights];
        // construct each light object
        for (int i=0; i<lights.length; i++) {
            lights[i] = new Light();
        }
        // determine type of game -- emoji or no emoji(grid based) 
        useEmoji = bff.inputYesNo("Would you like to display game with emojis for lights (y/n)?");
    }

    //method: displaying game board
    public void displayBoard () {
        // (1) for emoji style display
        if (useEmoji) {
            displayEmojiBoard();
        }
        // (2) for grid base display (no emojis)
        else {
            displayGridBoard();
        }
    }

    public void displayEmojiBoard() {
        String lightDisplay = "";
        // iterate through lights array
        for (Light l: lights) {
            if (l.getStatus()) { // isOn = true
                lightDisplay += Light.ON;
            }
            else {
                lightDisplay += Light.OFF;
            }
        }
        System.out.println(lightDisplay);
        // print index at bottom
        for (int i = 1; i<= lights.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println(); // newline
    }

    public void displayGridBoard() {
        for (int i=0; i<4; i++) {  // does it 4 times for 4x4 blocks
            for (Light l: lights) {
                if (l.getStatus()) {
                    System.out.print("|****"); // light is on
                }
                else {
                    System.out.print("|    "); // light is off
                }
            }
            System.out.println("|"); // last | for the row
        }
        // print the index at the bottom
        for (int i=0; i < lights.length; i++){
            if (i>10){ // adjust spacing for double digits
                System.out.print("  " + i + " ");
            }
            else {
                System.out.print("  " + i + "  ");
            }
        }
        System.out.println(); // newline
    }

    //method(s): playing the game
    public void play() {
       int userChoice = 2; // initialized; holds light choice number
       boolean allOff = false; // indicates if all lights are off and player has won
        if (useEmoji) {
            playEmoji(userChoice, allOff);
        }
        else {
            playGrid(userChoice, allOff);
        }
        // thank you message
        System.out.println("Thanks for playing!");
    }

    public void playEmoji(int userChoice, boolean allOff) {
        // for the emoji board (numbering start at 1, 0 is quit)
        while (!(userChoice == 0 || allOff)) {
            displayBoard();
            // selecting the light 
            userChoice = bff.inputInt("Which light # do you select (or 0 to quit)?", 0, lights.length);
            if (userChoice == 0){
                System.out.println("Now leaving game.");
            }
            else { // if not quitting, toggle lights
                toggleEmoji(userChoice);
            }
            allOff = checkGameState();
        } 
        if (allOff) {
            System.out.println("You win! Well done!"); // victory message
            displayEmojiBoard(); // the final winning board
        }
    }

    public void toggleEmoji (int userChoice) {
        if (userChoice == 1) { // left border case
            lights[userChoice-1].flip();
            lights[userChoice].flip();
        }
        else if (userChoice == lights.length) { // right border case
            lights[userChoice-1].flip();
            lights[userChoice-2].flip();
        }
        else {
            lights[userChoice-1].flip();
            lights[userChoice].flip();
            lights[userChoice-2].flip();
            }
    }

    public void playGrid(int userChoice, boolean allOff) {
        // for the grid board (numbering start at 0, -1 is quit)
        while (!(userChoice == -1 || allOff)) {
            displayBoard();
            // selecting the light 
            userChoice = bff.inputInt("Which light # do you select (or -1 to quit)?", -1, lights.length-1);
            if (userChoice == -1){
                System.out.println("Now leaving game.");
            }
            else { // if not quitting, toggle lights
                toggleGrid(userChoice);
            }
            allOff = checkGameState();
        } 
        if (allOff) {
            System.out.println("You win! Well done!"); // victory message
            displayGridBoard(); // the final winning board
        }
    }

    public void toggleGrid (int userChoice) {
        if (userChoice == 0) { // left border case
            lights[userChoice].flip();
            lights[userChoice+1].flip();
        }
        else if (userChoice == lights.length-1) { // right border case
            lights[userChoice].flip();
            lights[userChoice-1].flip();
        }
        else {
            lights[userChoice].flip();
            lights[userChoice+1].flip();
            lights[userChoice-1].flip();
            }
    }

    public boolean checkGameState(){
        for (Light l: lights) {
            if (l.getStatus() == true) {
                return false; // if even a single light is ON, you have not won game
            }
        }
        return true; // means all lights are off (won game)
    }

    public static void main(String args[]) {
        LightsOutGame myGame = new LightsOutGame();
        // testing emoji print:
        //System.out.println("⬛💡 Welcome to Light's Out! 💡 ⬛ ");
        //myGame.displayBoard();
        myGame.play(); //add back in when ready.
    }

}


 
