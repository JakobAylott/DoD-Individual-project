import java.util.Scanner;

public class HumanPlayer {

    /**
    * Returns the input from the player for next action
    *
    * @return Player's input
    */
    protected String getInputFromConsole() {
      Scanner in = new Scanner(System.in); 
      String inp = in.nextLine(); 
      return inp;
    }


}