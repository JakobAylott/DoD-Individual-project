/**
 * Class dealing with the map of the game.
 *
 */
import java.io.*; 
import java.util.*;
public class Map { 

//**initialisations*/
	private char[][] map;

	
  private char tileOn;

	private String mapName;

	private int goldRequired;
	
  private int xCoord;
  private int yCoord;
  private int coordArr[] = {0, 0}; 
  private int mapSize[] = {0, 0}; 
  private int i = 0;
  private int j = 0;
  private int rows = 0;
  private int columns = 0;
  private char[] splitList;

  private char currentTile;
//**Constructor for map object*/
	public Map()  {
		
    
    BufferedReader reader = null;
        
            // Open the file for reading.
            try{
              reader = new BufferedReader(new FileReader(new File("medium_map.txt")));
            }
            catch(Exception e){
              System.out.println("Error occurred");
            }
            // Read all contents of the file.
            String inputLine = null;
            try{
               while((inputLine = reader.readLine()) != null){
                 rows++;
                 columns = inputLine.length();
               }
              map = new char[rows - 2][columns];
              reader.close();

            try{
              reader = new BufferedReader(new FileReader(new File("medium_map.txt")));
            }
            catch(Exception e){
              System.out.println("Error occurred");
            }  
            while((inputLine = reader.readLine()) != null){
              if (inputLine.charAt(0) == 'n'){
                mapName = inputLine;
                mapName = mapName.replaceAll("name ", "");
                System.out.println(mapName);
              }
              if (inputLine.charAt(0) == 'w'){
                goldRequired = Character.getNumericValue((inputLine.charAt(4)));
              }
              if (inputLine.charAt(0) == '#'){
                splitList = inputLine.toCharArray();
                for (i =0;i<splitList.length;i++){
                  map[j][i] = splitList[i];
                }
                j++;
              }
            }
            }
            catch(Exception e){
              System.out.println("Error occurred");
            }
	}
	
    /**
    * Returns the number of rows and columns of the map
    *
    * @return : integer array, No. rows and columns
    */
    protected int[] getMapSize(){ //Returns the number of rows and columns of map
      mapSize[0] = rows - 2;//number of rows left at map size
      mapSize[1] = columns;//columns
      return mapSize; //returned as array as need to return 2 values
    }
    /**
    * Returns the coordiantes of the player object
    *
    * @return : array of current coords of a player
    */
    protected int[] getCurrentCoords(){//returns coordinates of player
      coordArr[0] = xCoord; 
      coordArr[1] = yCoord; 
      return coordArr;
    }
    /**
    * Returns gold needed to win 
    *
    * @return : Gold required to win.
    */
    protected int getGoldRequired() {
      return goldRequired;
    }
    /**
    * Returns the tile at a given coordinate
    *
    * @return : String tile at given location
    *
    * @param coordinates of tile
    */
    protected char getTile(int y, int x){
      return map[y][x];
    }

    /**
    * Changes a tile at given coords to a given char
    *
    * @param coordinates of tile and char to change it to
    */
    protected void setTile(int y, int x, char t){
      map[y][x] = t;
    }

    /**
    * Returns the tile at the players coordinates
    *
    * @return : String tile at players location
    */
    protected int getCurrentTile(){
      return tileOn;
    }

    /**
    * Used to change G to . after gold pickup
    */
    protected void removeGold(){
      tileOn = '.';
    }

    /**
    * Used to change the current coordinates of the player
    * checking for hash walls and displaying P on map if successful
    *
    * @param coordinates of current tile
    */
    protected void changeCurrentTileCoords(int x, int y){
      if (map[yCoord + y][xCoord + x] != '#'){ //If not moving into a wall
        map[yCoord][xCoord] = tileOn; //turn previous tile back to what you were standing on
        xCoord += x;
        yCoord += y;
        tileOn = map[yCoord][xCoord]; //save state of tile standing on
        map[yCoord][xCoord] = 'P';//sets tile to P for player
        if (Main.logic.spawning == 0){ //dont want to print during spawn at start
          System.out.println("SUCCESS");
        }
      }
      else{//error msg 
        System.out.println("FAIL");
      }
    }
}
