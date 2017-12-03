package net.obviam.droids;

// right-click run on this class file to run the game
//
// Thanks, JavaCodeGeeks for a good tutorial
// 
// source code explained here
// https://www.javacodegeeks.com/2012/02/building-games-using-mvc-pattern.html
//
// downloaded and converted to netbeans 8.2
//   https://github.com/obviam/mvc-droids 
//
//   in order to 'convert' for netbeans 8.2, the main thing I did was 
//   to move the files to the correct location as indicated in the 
//   package name
//
//   this new version is located in github as well:
//


/* *******************************************************************

YOUR NAME HERE:


Accomplish the following; (taken from the website above)
    Create a view that will display images/sprites for entities instead of 
    the drawn shapes.  Working code has been provided for the droid. Find
    a different image for the droid.
       Find download, and integrate images for obstacles and enebies.

    Hint: Use BufferedImage to achieve that.

    Extract the move action into a new class.

    Add new actions (attack) when an enemy is clicked

Hint:
Create a bullet entity that gets fired to the target. 
You can use the move action with a higher speed. When the hitpoint 
gets down to 0, then the enemy is destroyed. Use a different image 
to represent different states.

******************************************************************** */


import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.obviam.droids.controller.GameEngine;

public class Droids extends Applet implements Runnable {

	private static final long serialVersionUID = -2472397668493332423L;
	
	private GameEngine engine = new GameEngine();

	public void start() {
		new Thread(this).start();
	}

	public void run() {
		
		setSize(480, 320); // For AppletViewer, remove later.

		// Set up the graphics stuff, double-buffering.
		BufferedImage screen = new BufferedImage(480, 320, BufferedImage.TYPE_INT_RGB);
		Graphics g = screen.getGraphics();
		Graphics appletGraphics = getGraphics();

		long delta = 0l;
		
		// Game loop.
		while (true) {
			long lastTime = System.nanoTime();

			g.setColor(Color.black);
			g.fillRect(0, 0, 480, 320);

			// Update the state (convert to seconds)
			engine.update((float)(delta / 1000000000.0));
			// Render the world
			engine.render(g);
			
			// Draw the entire results on the screen.
			appletGraphics.drawImage(screen, 0, 0, null);

			// Lock the frame rate
			delta = System.nanoTime() - lastTime;
			if (delta < 20000000L) {
				try {
					Thread.sleep((20000000L - delta) / 1000000L);
				} catch (Exception e) {
					// It's an interrupted exception, and nobody cares
				}
			}

			if (!isActive()) {
				return;
			}
		}
	}

	public boolean handleEvent(Event e) {
		return engine.handleEvent(e);
	}
	
}