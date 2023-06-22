// Import Classes
import java.lang.Math;

/** [PathFinder.java]
  * Desc: The class that finds the shortest path in the mall
  * @author Emily Xie
  * @version Oct 2022
  */

public class PathFinder{ 
    // Declare variables
    private Map map;
    private boolean[][] shortestPath;
    private boolean[][] visitedPath;
    private int minDistance = Integer.MAX_VALUE; // must be the maximum value so the finder can get smaller paths
    
    /**
     * PathFinder
     * This constructor creates a new PathFinder object.
     * @param map A Map object of the map
     */
    PathFinder (Map map) { 
        this.map = map;
        // Create boolean arrays given the dimensions of the map
        shortestPath = new boolean[(map.getMapArray()).length][(map.getMapArray())[0].length];
        visitedPath = new boolean[(map.getMapArray()).length][(map.getMapArray())[0].length];
    }
    
    /** 
     * writeShortestPathArray
     * This method overwrites the shortest path array when an shorter path is found. 
     * @param path A 2-D boolean array of the path
     */ 
    private void writeShortestPathArray(boolean[][] path) {
        for (int row = 0; row < path.length; row++) {
            for (int col = 0; col < path[row].length; col++) {
                shortestPath[row][col] = path[row][col];
            }
        } 
    } 
    
    /** 
     * updateMap
     * This method updates the map.
     */
    private void updateMap() {
        for (int row = 0; row < shortestPath.length; row++) { 
            for (int col = 0; col < shortestPath[0].length; col++) { 
                if (shortestPath[row][col]) { 
                    map.setMap(row, col, Utility.VISIT);
                } else if (map.getMap(row, col).equals(Utility.VISIT)) {
                    map.setMap(row, col, Utility.ALLEY);
                }
            }
        }
    }
    
    /** 
     * canMove
     * This method checks if a movement to be made is possible.
     * @param visited A 2-D boolean array of the visited path
     * @param currentX An int of the x value of the current location
     * @param currentY An int of the y value of the current location
     * @return True if movement is possible, otherwise False
     */
    private boolean canMove(boolean[][] visited, int currentX, int currentY){
        boolean moveable = (currentX >= 0 && currentY >= 0 && currentX < map.getNumRows() && currentY < map.getNumColumns() && !map.getMap(currentX, currentY).equals(Utility.NO_PATH) && !map.getMap(currentX, currentY).equals(Utility.WALL) && !visited[currentX][currentY]);
        return moveable;
    }
    
    /** 
     * findShortestPath
     * This method recursively finds the shortest path.
     * @param currentX An int of the x value of the current location
     * @param currentY An int of the y value of the current location
     * @param destinationX An int of the x value of the destination
     * @param destinationY An int of the y value of the destination
     * @param distance An int of the distance
     */
    private void findShortestPath(int currentX, int currentY, int destinationX, int destinationY, int distance){
        // If the current x and y values are equivalent to the destination x and y values, update the minimum distance
        // and update the shortest path array to be equal to the recent visited path
        if (currentX == destinationX && currentY == destinationY) {
            if (distance < minDistance) {
                minDistance = Math.min(distance, minDistance);
                writeShortestPathArray(visitedPath);
                updateMap();
            }
        }
        
        // Set the current x and y values as visited
        visitedPath[currentX][currentY] = true;
        
        // Recursively check through each movement if movement is possible
        if (canMove(visitedPath, currentX + Utility.MOVE, currentY)) { // Right
            findShortestPath(currentX + Utility.MOVE, currentY, destinationX, destinationY, distance + Utility.MOVE);
        }
        if (canMove(visitedPath, currentX - Utility.MOVE, currentY)) { // Left
            findShortestPath(currentX - Utility.MOVE, currentY, destinationX, destinationY, distance + Utility.MOVE);
        }
        if (canMove(visitedPath, currentX, currentY + Utility.MOVE)) { // Up
            findShortestPath(currentX, currentY + Utility.MOVE, destinationX, destinationY, distance + Utility.MOVE);
        }
        if (canMove(visitedPath, currentX, currentY - Utility.MOVE)) { // Down
            findShortestPath(currentX, currentY - Utility.MOVE, destinationX, destinationY, distance + Utility.MOVE);
        }
        
        // Set the current x and y values as not visited if movement is not possible
        visitedPath[currentX][currentY] = false;
    }
    
    /** 
     * getShortestPath
     * This method takes in the start and end x,y coordinates and passes them.
     * @param startX An int of the starting x value
     * @param startY An int of the starting y value
     * @param endX An int of the ending x value 
     * @param endY An int of the ending y value
     * @return An int of the length of the shortest path
     */
    public int getShortestPath(int startX, int startY, int endX, int endY) {
        findShortestPath(startX, startY, endX, endY, 0);
        map.setMap(startX, startY, Utility.START);
        return minDistance;
    }
}