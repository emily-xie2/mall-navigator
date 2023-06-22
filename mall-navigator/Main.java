// Import Classes
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

/** [Main.java]
  * Desc: The main class
  * @author Emily Xie
  * @version Oct 2022
  */

public class Main{ 
    
    public static void main(String[] args) throws Exception{
        // Read from the text file and write into a jagged array
        String line = "";
        ArrayList rows = new ArrayList();
        FileReader fileReader = new FileReader("mapFiles/path.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        while((line = bufferedReader.readLine()) != null) {
            String[] currentLine = line.split("");
            rows.add(currentLine);
        }
        String[][] mall = new String[rows.size()][];
        mall = (String[][])rows.toArray(mall);
        
        // Find the longest row in the jagged array and get its length
        int max = mall[0].length;
        for(int i = 0; i < mall.length;i++){
            if(mall[i].length > max) {
                max = mall[i].length;
            }
        }
        
        // Create a rectangular array with the jagged array dimensions
        String[][] mapRectangle = new String[mall.length][max];
        for (int i=0; i< mall.length; i++) {
            for(int j=0; j<mall[i].length; j++) {
                mapRectangle[i][j]=mall[i][j];
            }
        }

        // Replace the null values in the rectangular array with spaces
        for(int i=0; i< mapRectangle.length; i++) {
            for(int j=0; j<mapRectangle[i].length; j++) {
                if (mapRectangle[i][j] == null) {
                    mapRectangle[i][j] = " ";
                } else {
                    mapRectangle[i][j] = mapRectangle[i][j];
                }
            }
        }
        
        
        // Initialize the map
        Map map = new Map(mapRectangle, mapRectangle.length, mapRectangle[0].length);

        // Generate random start and end points
        int[] start = generateRandomPoint(mapRectangle);
        int[] end = generateRandomPoint(mapRectangle);
        while (end.equals(start)) {
            end = generateRandomPoint(mapRectangle); // Do not permit start and end points to be the same
        }
        
        // Set the start and end of the path
        map.setMap(start[0], start[1], Utility.START);
        map.setMap(end[0], end[1], Utility.END);

        new Visualizer(map);

        // Search for the shortest path length
        PathFinder pathFinder = new PathFinder(map);
        int shortestDistance = pathFinder.getShortestPath(start[0], start[1], end[0], end[1]);
        System.out.println("Found! Path length: " + shortestDistance);
    }

    /**
     * generateRandomPoint
     * This method generates a random x,y coordinate.
     * @param array A string array with the coordinates
     * @return A 1-D int array of the x,y coordinates
     */
    public static int[] generateRandomPoint(String[][] array) {
        int randomX = (int) ((Math.random() * (0 - (array.length - 1))) + array.length - 1);
        int randomY = (int) ((Math.random() * (0 - (array[0].length - 1))) + array[0].length - 1);

        while (!array[randomX][randomY].equals(Utility.ALLEY)) {
            randomX = (int) ((Math.random() * (0 - (array.length - 1))) + array.length - 1);
            randomY = (int) ((Math.random() * (0 - (array[0].length - 1))) + array[0].length - 1);
        }

        int[] point = {randomX, randomY};
        return point;
    }
}