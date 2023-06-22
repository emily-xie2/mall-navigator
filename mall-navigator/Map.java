/** [Map.java]
  * Desc: The class including all the information for the map
  * @author Emily Xie
  * @version Oct 2022
  */

public class Map{ 
    // Declare variables
    private String[][] map; 
    private int numRows, numColumns;
    
    /**
     * Map
     * This constructor creates a new Map object.
     * @param map A 2-D String array of the map
     * @param numRows An int of the number of rows
     * @param numColumns An int of the number of columns
     */
    public Map(String[][] map, int numRows, int numColumns){ 
        this.map = map; 
        this.numRows = numRows;
        this.numColumns = numColumns;
    }
    
    // Getters and setters
    /**
     * getNumRows
     * This method gets the number of rows of the map.
     * @return A current int of the number of rows
     */
    public int getNumRows(){ 
        return this.numRows; 
    } 
    
    /**
     * getNumColumns
     * This method gets the number of columns of the map.
     * @return A current int of the number of columns
     */
    public int getNumColumns(){ 
        return this.numColumns; 
    }

    /**
     * getMap
     * This method gets the map.
     * @param row An int of the number of rows
     * @param col An int of the number of columns
     * @return A current String of the map
     */
    public String getMap(int row, int col){ 
        return this.map[row][col]; 
    } 
    
    /**
     * getMapArray
     * This method gets the String array of the map.
     * @return A current String array of the map
     */
    public String[][] getMapArray() { 
        return this.map; 
    }
    
    /**
     * setMap
     * This method sets the map.
     * @param row An int of the number of rows
     * @param col An int of the number of columns
     * @param symbol A String of the symbol used in the map
     */
    public void setMap(int row, int col, String symbol){ 
        this.map[row][col] = symbol; 
    }
}