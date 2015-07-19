package entities;

import gameStates.CombatGameState;
import gameStates.OpenSeasGameState;
import graphics.ImageManager;
import gui.GameInstance;
import items.Item;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import worldStructure.Square;

public abstract class Entity 
{
protected boolean isFighter = false;
int x, y;
OpenSeasGameState parent;
protected Image image;
double speed = .5;
int termVel = 5;
double drag = .1;
double xVel = 0, yVel = 0;
boolean up = false,dn= false,lt = false,rt= false;
int width, height;
boolean facingLeft = false;
protected ArrayList<Item> items = new ArrayList<Item>();
private boolean computerControlled;
public static Random r = new Random();
public Entity(int x, int y, Image img, OpenSeasGameState parent, int width, int height, boolean compControlled)
{
	this.computerControlled = compControlled;
	this.parent = parent;
	this.width = width;
	this.height = height;
	this.x = x;
	this.y = y;
	this.image = img;
}
public void removeItem(Item item)
{
	items.remove(item);
}
public ArrayList<Item> getItems() 
{
	return items;
}
private void changeDirections()
{
	if(this.computerControlled)
	{
		if(r.nextBoolean())
		{
			up = true;
			dn = false;
		}
		else
		{
			dn = true;
			up = false;
		}
		if(r.nextBoolean())
		{
			lt = true;
			rt = false;
		}
		else
		{
			lt = false;
			rt = true;
		}
	}
}
public boolean getIsFighter()
{
	return isFighter;
}
public abstract void triggerInteraction(Entity ent);
public Image getImage() 
{
	return image;
}
public int getX() 
{
	return x;
}
public int getY()
{
	return y;
}
public void tick(Square[][] world)
{
	int xSquareLoc = (x)/64, ySquareLoc = (y)/64;

	if(rt)
	{
		if(xVel < termVel)
		{
	xVel+= speed;
		}
	}
	else if(lt)
	{
		if(xVel > -termVel)
		{
	xVel -=speed;
		}
	}
	if(dn)
	{
		if(yVel < termVel)
		{
	yVel+= speed;
		}
	}
	else if(up)
	{
		if(yVel > -termVel)
		{
		yVel -=speed;
		}
	}
	if(!lt && !rt)
	{
		if(xVel > 0)
		{
			xVel-= drag;
		}
		if(xVel < 0)
		{
			xVel+=drag;
		}
	}
	if(!up && !dn)
	{
		if(yVel > 0)
		{
			yVel-= drag;
		}
		if(yVel < 0)
		{
			yVel+=drag;
		}
	}
	int nextSquareLocX =(int)  (x + xVel)/64, nextSquareLocY =(int)  (y + yVel)/64;
	if(xVel > 0)
	{
		if(!facingLeft)
		{
			image = ImageManager.flipImage(image);
			facingLeft = true;
		}
		if(xSquareLoc != nextSquareLocX)
		{
		if(parent.getSquare(nextSquareLocX, ySquareLoc).isPassable())
		{
	x+=xVel;
		}
		else
		{
			changeDirections();
		}
		}
		else
		{
			x+=xVel;
		}
	}
	else if(xVel < 0)
	{
		if(facingLeft)
		{
			image = ImageManager.flipImage(image);
			facingLeft = false;
		}
		if(xSquareLoc != nextSquareLocX)
		{
		if(parent.getSquare(nextSquareLocX, ySquareLoc).isPassable())
		{
	x+=xVel;
		}
		else
		{
			changeDirections();
		}
		}
		else
		{
			x+=xVel;
		}
	}
	if(yVel > 0)
	{
		if(ySquareLoc != nextSquareLocY)
		{
		if(parent.getSquare(xSquareLoc, nextSquareLocY).isPassable())
		{
	y+=yVel;
		}
		else
		{
			changeDirections();
		}
		}
		else
		{
			y+=yVel;
		}
	}
	else if(yVel < 0)
	{
		if(ySquareLoc != nextSquareLocY)
		{
		if(parent.getSquare(xSquareLoc, nextSquareLocY).isPassable())
		{
	y+=yVel;
		}
		else
		{
			changeDirections();
		}
		}
		else
		{
			y+= yVel;
		}
	}
}

public void setUp(boolean b)
{
	up = b;
}
public void setDn(boolean b)
{
	dn = b;
}
public void setLt(boolean b)
{
	lt = b;
}
public void setRt(boolean b)
{
	rt = b;
}
public int getWidth() 
{
	return width;
}
public int getHeight() 
{
	return height;
}

public void draw(Graphics tempg, int offSetX, int offSetY) 
{
	tempg.drawImage(image, x +offSetX, y + offSetY, width, height, null);
}

public static void triggerInteractions(Player player, ArrayList<Entity> entities) 
{
	int xSquareLoc = (player.x)/64, ySquareLoc = (player.y)/64;
	for(int i = 0; i < entities.size(); i++)
	{
		int entSquareLocX = entities.get(i).x/64, entSquareLocY = entities.get(i).y/64;
		if(xSquareLoc == entSquareLocX && ySquareLoc == entSquareLocY)
		{
			entities.get(i).triggerInteraction(player);
		}
	}
}
public void reduceHunger(int i) 
{

}

}
