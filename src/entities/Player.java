package entities;

import items.Item;
import items.ItemGenerator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import abilities.Ability;
import worldStructure.Square;
import fighters.FighterSpawner;
import gameStates.GameOverScreen;
import gameStates.OpenSeasGameState;
import graphics.ImageManager;

public class Player extends Crew 
{
	int food, maxFood;
	Image coin, foodImg, energyImg;
	
	public Player(int x, int y, OpenSeasGameState ops, int health, int attack, int speed, int magic, int crewMembers) 
	{
		super(x, y, ops
				,false);
		food = maxFood = 3000;
		coin = ImageManager.getImage(ImageManager.COIN);
		
		foodImg = ImageManager.getImage(ImageManager.FOOD);
		energyImg = ImageManager.getImage(ImageManager.ENERGY);
		
		this.addFighter(FighterSpawner.captain(health,attack,speed,magic));
		for(int i =0; i < crewMembers; i++)
		{
			this.addFighter(FighterSpawner.pirate(r));
		}


	}
	public void reset()
	{
		this.xVel = 0;
		this.yVel = 0;

	}
	@Override
	public void reduceHunger(int i) 
	{
		if(food + i <=maxFood)
		{
	food +=i;
		}
		else
		{
			food = maxFood;
			
		}
	}
	public boolean getLiving()
	{
		return food > 0;
	}
	@Override
	public void tick(Square[][] world)
	{
		super.tick(world);
		if(Math.abs(xVel) >= .01 && Math.abs(yVel) >= .01)
		{
		food += -1;
		}
		if(!getLiving())
		{
			this.parent.getGameInstance().setGameState(new GameOverScreen(this, this.parent.getGameInstance()));
		}
	}
	public int getBooty()
	{
		return booty;
	}
public void drawUI(Graphics g, int width, int height) 
{
	g.setColor(new Color(0,0,0,80));
	g.fillRect(0, 0, width, height);
	g.drawImage(coin, 0,0,height,height, null);
	g.setFont(new Font("KING OF PIRATE Regular", height, height));
	g.setColor(Color.yellow);
	g.drawString(String.valueOf(booty), height,height*9/10);
	int stringLength = g.getFontMetrics().stringWidth(String.valueOf(booty));
	g.drawImage(foodImg, height + stringLength, 0, height, height, null);
	int remainingSpace = width - stringLength - (3*height);
	g.setColor(Color.RED);
	int percentFood = (int)( ((double)food)/((double)maxFood) * 100);

	//int percentEnergy = (int)(((double)energy)/((double)maxEnergy) * 100);
	int percentEnergy = 0;
	int count = 0;
	for(int i =0; i <fighters.length; i++)
	{
		if(fighters[i] != null)
		{
			count++;
		}
	}
	for(int i = 0; i < fighters.length; i++)
	{
		if(fighters[i] !=null)
		{
			percentEnergy += (fighters[i].getEnergy() * (100/count));
		}
	}
	g.drawRect(2*height + stringLength, 0, remainingSpace/2, height);

	g.fillRect(2*height + stringLength, 0, (int) ( ((double)percentFood)/100 * remainingSpace/2), height);
	g.setColor(Color.BLUE);
	g.drawImage(energyImg, 2*height + stringLength + remainingSpace/2, 0, height, height, null);
	g.drawRect(3*height + stringLength+ remainingSpace/2, 0, remainingSpace/2, height);
	
	g.fillRect(3*height + stringLength+ remainingSpace/2, 0, (int) (((double)percentEnergy) /100  * remainingSpace/2), height);
	g.setColor(Color.BLACK);
	g.drawString(String.valueOf(percentFood) + "%", 2*height + stringLength + remainingSpace/4 - g.getFontMetrics().stringWidth(String.valueOf(percentFood) + "%")/2, 8*height/10);
	g.drawString(String.valueOf(percentEnergy) + "%", 3*height + stringLength+ 3*remainingSpace/4 - g.getFontMetrics().stringWidth(String.valueOf(percentEnergy) + "%")/2, 8*height/10);
}
public ArrayList<Item> getItems() 
{
	return items;
}
public void transferItems(FightingEntity enemy) 
{
	for(int i = 0; i < enemy.getItems().size(); i++)
	{
	enemy.getItems().get(i).setHolder(this);
	items.add(enemy.getItems().get(i));
	enemy.getItems().remove(i);
	}
}
public void addBooty(int num) 
{
booty+=num;	
}
public void addItem(Item item) 
{
	items.add(item);
}
public void removeMoney(int i) 
{
this.booty-=i;
}
}
