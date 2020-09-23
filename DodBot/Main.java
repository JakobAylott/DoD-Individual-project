import java.io.*; //Importing libraries needed
import java.util.*;
/**
* Main class handles object creation and contains main() method to run the code
*/
public class Main {
  public static GameLogic logic = new GameLogic();//Creating objects/initalising strings
  public static HumanPlayer Player = new HumanPlayer();
  public static Map map = new Map();
  public static Bot bot = new Bot();
  static String playerInput;
  static String[] splitInput;
  public static void main(String[] args) { 
    logic.spawnPlayer();
    bot.spawnBot();
    System.out.println("Game started.");
      while (1 != 0){
        playerInput = Player.getInputFromConsole();
        playerInput = playerInput.toUpperCase();
        splitInput = playerInput.split(" ");
        if (splitInput.length <=2){ //No valid commands are longer than 2 'words'
          switch(splitInput[0]){ //Switch case to carry out a method based on input
            case "QUIT":
              logic.quitGame();
            break;

            case "HELLO":
              logic.hello();
            break;

            case "GOLD":
              logic.displayGold();
            break;

            case "MOVE":
            if (splitInput.length == 2){
              if (splitInput[1].equals("N") | splitInput[1].equals("E") | splitInput[1].equals("S") |splitInput[1].equals("W")){ //Checks if direction specified is valid
                logic.move(splitInput[1]);
              }
              else{ //Error message displayed if invalid
              System.out.println ("That was not a valid direction.");
            }
            }
            else{ //Error message displayed if invalid
              System.out.println ("Please provide a valid direction after the command (N, E, S, W).");
            }
            break;

            case "PICKUP":
              logic.pickup();
            break;

            case "LOOK":
              int[] coordArr = map.getCurrentCoords(); //Retrieves coords for look method
              logic.look(coordArr[0],coordArr[1]);
            break;

            default:
              System.out.println ("Invalid command.");
            break;
            }
          }
        else{ //if longer than 2 'words' its invalid
          System.out.println ("Invalid command.");
        }
        bot.runBot();
        }
      }
}

