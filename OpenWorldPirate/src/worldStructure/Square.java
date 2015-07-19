package worldStructure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import entities.Entity;

public abstract class Square 
{
protected boolean passable;
BufferedImage image;
public Image getImage() 
{
		return image;
}
public boolean isPassable()
{
	return passable;
}

public void bumped() 
{
	// TODO Auto-generated method stub
	
}
}
