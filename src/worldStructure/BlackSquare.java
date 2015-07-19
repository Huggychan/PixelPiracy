package worldStructure;

import java.awt.image.BufferedImage;

public class BlackSquare extends Square 
{
	public BlackSquare()
	{
		this.image = new BufferedImage(64,64, 1);
		this.passable = false;
	}
}
