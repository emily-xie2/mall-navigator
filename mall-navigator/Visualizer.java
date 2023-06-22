// Import classes
import javax.swing.*; 
import java.awt.*; 

/** 
 * [Visualizer.java]
 * Visualizes the mall navigation
 * @author Emily Xie 
 * @version Oct 2022 
 */ 
 
public class Visualizer extends JFrame{ 
    // Declare variables
    Map map; 
    MapPanel panel; 
    
    // Declare constants
    final int MAX_X = (int)getToolkit().getScreenSize().getWidth(); 
    final int MAX_Y = (int)getToolkit().getScreenSize().getHeight();     
    final int GRIDSIZE = MAX_Y/60; 
 
    /**
     * Visualizer
     * This constructor creates a new Visualizer object.
     * map An object of the map
     */
    Visualizer (Map map) { 
        this.map = map; 
        this.panel = new MapPanel(); 
        this.panel.setBackground(Color.WHITE);
        this.getContentPane().add(BorderLayout.CENTER, panel); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setSize(MAX_X/4, MAX_Y/4); 
        this.setVisible(true); 
    } 
    
    private class MapPanel extends JPanel { 
        @Override 
        public void paintComponent(Graphics g) { 
            super.paintComponent(g); 
            for (int row=0; row < map.getNumRows(); row++){ 
                for (int col=0; col < map.getNumColumns(); col++){
                    if (map.getMap(row,col).equals(Utility.START)) {
                        g.setColor(Color.YELLOW); 
                    } else if (map.getMap(row,col).equals(Utility.END)) {
                        g.setColor(Color.RED);
                    } else if (map.getMap(row,col).equals(Utility.VISIT)) {
                        g.setColor(Color.MAGENTA); 
                    } else if (map.getMap(row,col).equals(Utility.WALL)) {
                        g.setColor(Color.LIGHT_GRAY);
                    } else if (map.getMap(row,col).equals(Utility.ALLEY)) {
                        g.setColor(Color.CYAN);
                    } else if (map.getMap(row,col).equals(Utility.NO_PATH)) {
                        g.setColor(Color.DARK_GRAY);
                    } else {
                        g.setColor(Color.BLUE);
                    }
                    g.fillRect(col*GRIDSIZE,row*GRIDSIZE,GRIDSIZE,GRIDSIZE); 
                } 
            } 
            this.repaint(); 
        } 
    } 
}