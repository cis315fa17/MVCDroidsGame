package net.obviam.droids.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

import net.obviam.droids.model.Arena;
import net.obviam.droids.model.Droid;
import net.obviam.droids.model.Enemy;
import net.obviam.droids.model.Obstacle;

public class SimpleArenaRenderer implements Renderer {

	private Arena arena;
        private double theta = 0;
	
	public SimpleArenaRenderer(Arena arena) {
		this.arena = arena;
	}
	
	@Override
	public void render(Graphics g) {
		// render the grid
		int cellSize = 32; // hard coded
		g.setColor(new Color(0, 0.5f, 0, 0.75f));
		for (int i = 0; i <= Arena.WIDTH; i++) {
			g.drawLine(i * cellSize, 0, i * cellSize, Arena.HEIGHT * cellSize);
			if (i <= Arena.WIDTH)
				g.drawLine(0, i * cellSize, Arena.WIDTH * cellSize, i * cellSize);
		}
		
		// render the obstacles
		g.setColor(new Color(0, 0, 1f));
		for (Obstacle obs : arena.getObstacles()) {
			int x = (int) (obs.getX() * cellSize) + 2;
			int y = (int) (obs.getY() * cellSize) + 2;
			g.fillRect(x, y, cellSize - 4, cellSize - 4);
		}
		
		// render the enemies
		g.setColor(new Color(1f, 0, 0));
		for (Enemy enemy : arena.getEnemies()) {
			int x = (int) (enemy.getX() * cellSize);
			int y = (int) (enemy.getY() * cellSize);
			g.fillOval(x + 2, y + 2, cellSize - 4, cellSize - 4);
		}
		
		// render player droid
		g.setColor(new Color(0, 1f, 0));
		Droid droid = arena.getDroid();
		int x = (int) (droid.getX() * cellSize);
		int y = (int) (droid.getY() * cellSize);
                
                
                // **********************************************************
                // replaces drawing our oval (commented out below)
                //
                // **** use bufferedimage for droid 
                
                BufferedImage imgDroid;
                
                String sFilename = "src/Flat-Droid-Icon-Pack-8.3-APK.png"; //"small-robot.png";
                try {
                    imgDroid = ImageIO.read(new File(sFilename));
                    
                    // rotate my graphics centered on droid cell
                    Graphics2D gr = (Graphics2D)g; // imgDroid.createGraphics();
                    
                    theta = droid.getRotation();
                    gr.rotate(Math.toRadians(theta),
                            x + cellSize/2,
                            y + cellSize/2);
                   // g.drawImage(imgDroid, x, y, null);
                   
                   // draw and scale our buffered image
                    g.drawImage(imgDroid, x, y, x + cellSize, y + cellSize, 
                            0, 0, imgDroid.getWidth(),imgDroid.getHeight(),null);
 
                    gr.rotate(Math.toRadians(-theta),
                            x + cellSize/2,
                            y + cellSize/2);

                    theta += 1;  // increase our rotation by one degree each time
                    
                } catch (IOException e) {
                    System.out.println("Error: unable to read image! " + sFilename);
                }
 
                
              // this is just test code to find the default file location
              // find out what our exec file path is by writing one
              // result: JavaLibraryDroids folder 
              //
           /* try {
                String str = "Hello";
                BufferedWriter writer = new BufferedWriter(new FileWriter("ttt"));
                writer.write(str);

                writer.close();
            } catch (IOException e) {
                System.out.println("unable to write");

            }  */

                
                
                
                //  original code needed to draw an oval
                //
		//g.fillOval(x + 2, y + 2, cellSize - 4, cellSize - 4);
		// render square on droid
		//g.setColor(new Color(0.7f, 0.5f, 0f));
		//g.fillRect(x + 10, y + 10, cellSize - 20, cellSize - 20);
                
                
	}

}
