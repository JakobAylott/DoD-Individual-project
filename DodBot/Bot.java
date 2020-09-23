import java.lang.Math;

/**
* Class for the bot character, with methods for movement and bot's win condition
*/
public class Bot {

  private int yCoord;
  private int xCoord;
  private char tileOn = '.';
/**
* Runs through bots turn using other methods
*/
  protected void runBot(){ 
      chasePlayer();
      checkCaught();
  }

  /**
  * Identical to spawnPlayer except checks tile for both hash and player, and displayed as B
  * 
  * @see spawnPlayer()
  */
  protected void spawnBot(){//
        int[] size = Main.map.getMapSize(); //and displays as B instead of P
        int height = size[0];//could potentially find a way to reduce repetition of this code
        int width = size[1];//but i haven't so far
        int valid = 0;
        int x = 0;
        int y = 0;

        while (valid !=1){ //Loops until position is not a '#'
          y = (int)(Math.random() * (height-1)); //generating random coordinates for a starting position
          x = (int)(Math.random() * (width-1));//Minimum is one and maximum is the width/height
          if ((Main.map.getCurrentTile() != '#') && (Main.map.getCurrentTile() != 'P')){
            yCoord = y; //Sets current coords to spawn position
            xCoord = x;
            Main.map.setTile(y, x, 'B');
            valid = 1;
          }
        }
      }

  /**
  * This method handles the movement of the bot, it will search for the player
  * in a 5x5 grid similar to the look method, and if found, move towards them
  *
  * @see look() 
  */
  protected void chasePlayer(){
      int x = 0;
      int y = 0;
      int rand1 = 0;
      int rand2 = 0;
      int playerFound = 0;
      int xCoordChange = 0;
      int yCoordChange = 0;
      rand1 = (int)(Math.random() * (1));//Creating random ints for direction choices
      rand2 = (int)(Math.random() * (3));
      int[] size = Main.map.getMapSize();
      Main.map.setTile(yCoord, xCoord, tileOn);
      for (y=(yCoord-2);y<=(yCoord+2);y++){ //Runs through columns then rows from top to bottom
        for (x=(xCoord-2);x<=(xCoord+2);x++){//left to right
          if ((y<size[0]) && (y>=0)){
            if ((x<size[1]) && (x>=0)){
                  if(Main.map.getTile(y, x) == 'P'){ //If the player is found, moves towards them, one of two directions randomly picked
                  playerFound = 1;
                  
                  if (x>xCoord && y<yCoord){ //NE
                    switch(rand1){
                    case 0:
                      xCoordChange += 1;
                      break;
                    case 1:
                      yCoordChange -=1;
                      break;
                  }
                  }
                  if (x<xCoord && y<yCoord){//NW
                    switch(rand1){
                    case 0:
                      xCoordChange -= 1;
                      break;
                    case 1:
                      yCoordChange -=1;
                      break;
                    }
                  }
                  if (x>xCoord && y>yCoord){//SE
                    switch(rand1){
                    case 0:
                      xCoordChange += 1;
                      break;
                    case 1:
                      yCoordChange +=1;
                      break;
                    }
                  }
                  if (x<xCoord && y>yCoord){//SW
                    switch(rand1){
                    case 0:
                      xCoordChange -= 1;
                      break;
                    case 1:
                      yCoordChange +=1;
                      break;
                    }
                  }
                  if (x==xCoord){//If on the same plane, will directly follow
                    if (y<yCoord){
                      yCoordChange -=1;
                    }
                    if (y>yCoord){
                      yCoordChange +=1;
                    }
                  }
                  else{
                    if (y==yCoord){
                    if (x<xCoord){
                      xCoordChange +=1;
                    }
                    if (x>xCoord){
                      xCoordChange -=1;
                    }
                  }
                  } 
                
                
                }
              }
            }
          }
        }
      
      if (playerFound == 0){ //If player is not found, moves in a random direction
              switch (rand2){
                case 0:
                  xCoordChange -= 1;
                  break;
                case 1:
                  yCoordChange +=1;
                  break;
                case 2:
                  xCoordChange += 1;
                  break;
                case 3:
                  yCoordChange -=1;
                  break;
              }
             }

          if ((Main.map.getTile((yCoord + yCoordChange), (xCoord + xCoordChange))) != '#'){ //Checks tile moving too is not a hash, if so, it just wont move
            xCoord += xCoordChange;
            yCoord += yCoordChange;
            tileOn = Main.map.getTile(yCoord, xCoord);
          }
          Main.map.setTile(yCoord, xCoord, 'B'); //Sets displayed position on map
      } //Generally seen a turn behind because using look command spends a turn
    
    /**
    * Checks to see if the bot is on the players position
    */
    protected void checkCaught(){
      int[] coords = {0,0};
      coords = Main.map.getCurrentCoords();
      if ((xCoord == coords[0]) && (yCoord == coords[1])){ //Compares coordinates of the two
        System.out.println("THE BOT HAS CAUGHT YOU, GAME OVER."); //Loss message, ends game
        System.exit(0);
      }
    }
}

