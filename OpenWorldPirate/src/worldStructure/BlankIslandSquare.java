package worldStructure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BlankIslandSquare extends Square
{
	public BlankIslandSquare()
	{
		this.image = new BufferedImage(64,64, 1);
		this.passable = false;
		Graphics temp = image.getGraphics();
		temp.setColor(Color.GREEN);
		temp.fillRect(0, 0, 64, 64);
		temp.setColor(Color.black);
		temp.drawRect(0,0,64,64);
	}

}
