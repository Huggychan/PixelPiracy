package worldStructure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BlankOceanSquare extends Square 
{
	public BlankOceanSquare()
	{
		this.image = new BufferedImage(64,64, 1);
		this.passable = true;
		Graphics temp = image.getGraphics();
		temp.setColor(new Color(0,150,200));
		temp.fillRect(0, 0, 64, 64);
		temp.setColor(Color.black);
		temp.drawRect(0,0,64,64);
	}
}
