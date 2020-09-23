 import java.lang.Math;

 public class GameLogic {
  private int gold; 
  private int x;
  private int y;
  public int spawning = 1;
  
	 /**
	 * Spawns the player somewhere in the map at the start of the game,
   * ensuring they are not in a wall or on a gold spot
	 */
    protected void spawnPlayer(){
      int[] size = Main.map.getMapSize();
      int height = size[0];
      int width = size[1];
      int valid = 0;
      
      while (valid !=1){ //Loops until position is not a '#'
        y = ((int)(Math.random() * (height-2))); //generating random coordinates for a starting position
        y++;
        x = ((int)(Math.random() * (width-2)));//Minimum is one and maximum is the width/height
        x++;
        if (((Main.map.getTile(y, x)) != '#') && (Main.map.getTile(y, x) != 'G')){
          Main.map.changeCurrentTileCoords(x,y); //Sets current coords to spawn position
          spawning = 0;
          Main.map.setTile(y, x, 'P');
          valid = 1;
        }
      }
    }

	

    /**
	  * Displays the gold required to win.
    */
    protected void hello() {//outputs msg displaying required gold for map
        System.out.println("Gold to win: " + Main.map.getGoldRequired());
    }
	
	  /**
	  * Returns the gold currently owned by the player.
	  *
    * @return : Gold currently owned.
    */
    protected int getGold() { 
        return gold;
    }

    /**
	  * Displays the gold currently owned by the player.
    */
    protected void displayGold(){
      System.out.println("Gold owned: " + getGold());
    }

    /**
     * Handles player's movement around the map with a given direction using map methods
     * and switch cases 
     *
     * @param direction : The direction of the movement.
     */
    protected void move(String direction) {//chooses how much x and y need to be changed for each direction
      switch(direction) { //using second part of input from main switch case
        case "N": 
          y = -1;
          x = 0;
        break;
        case "E":
          x = 1;
          y = 0;
        break;
        case "S":
          y = 1;
          x = 0;
        break;
        case "W":
          x = -1;
          y = 0;
        break;
      }
      Main.map.changeCurrentTileCoords(x,y);
    }

    /**
    * Displays a 5x5 grid of map surrounding player
    */
    protected void look(int xCoord, int yCoord) {
      int x = 0;
      int y = 0;
      int[] size = Main.map.getMapSize();
      for (y=(yCoord-2);y<=(yCoord+2);y++){ //Runs through columns then rows from top to bottom
        for (x=(xCoord-2);x<=(xCoord+2);x++){//left to right
          if ((y>=size[0]) | (y<0)){ //If outside the map displays '#'
            System.out.print("#");
          }
          else if ((x>=size[1]) | (x<0)){
            System.out.print("#");
          }
          else{
            System.out.print(Main.map.getTile(y, x));//prints tile at current coords
          }
        }
        System.out.print("\n");//needs new line for each column
        }
    }

    /**
    * Checks wether gold can be picked up then does so
    */
    protected void pickup() {
      if (Main.map.getCurrentTile() == 'G'){ //Check if valid tile
        gold++;
        System.out.println("SUCCESS. Gold owned: " + Main.logic.getGold());
        Main.map.removeGold();//Picked up, so no longer there
      }
      else{//if not displays fail msg
        System.out.println("FAIL. Gold owned: " + Main.logic.getGold());
      }
    }

    /**
    * Quits the game, displaying win or lose dependent on conditions
    */
    protected void quitGame() {
      if ((Main.logic.getGold() == Main.map.getGoldRequired()) && (Main.map.getCurrentTile() == 'E')){ //if on exit tile with enough gold (dependent on map)
        System.out.println("WIN");
      }
      else{
        System.out.println("LOSE");
      }
      System.exit(0); //terminates program
    }
	
}