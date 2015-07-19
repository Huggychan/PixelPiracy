package gameStates;

import graphics.ImageManager;
import gui.GamePanel;
import items.Item;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class ShopGameState implements GameState {
	OpenSeasGameState ops;
	Item[] items;
	int[] prices;
	Image[] options;

	int currentIndex =0;
	public ShopGameState(OpenSeasGameState parent, Item[] items, int[] prices) 
	{
		this.ops = parent;
		this.items = items;
		this.prices = prices;
		options = new Image[items.length];
		for(int i =0; i< items.length; i++)
		{
			options[i] = items[i].getImage();
		}
	}

	@Override
	public void draw(Graphics g, GamePanel parent) 
	{
		int widthPer = parent.getWidth()/options.length;
		for(int i =0; i < options.length; i++)
		{
			g.setFont(new Font("Rockwell", 32,32));
			g.drawImage(options[i], i*widthPer, 0,widthPer,widthPer,null);
		
			if(currentIndex == i)
			{
			g.setColor(Color.RED);
			}
			else
			{
				g.setColor(Color.BLACK);
			}
			if(items[i] != null)
			{
			g.drawString(items[i].getName()+": " + String.valueOf(prices[i]),i*widthPer + (widthPer/2) - g.getFontMetrics().stringWidth(items[i].getName()+": " + String.valueOf(prices[i])),widthPer+32);
			}
		}
		g.setColor(Color.BLACK);
		g.drawString("Availible Booty:  " +String.valueOf(ops.getPlayer().getBooty()), parent.getWidth()/2 - g.getFontMetrics().stringWidth("Availible Booty:  " +String.valueOf(ops.getPlayer().getBooty()/2)), parent.getHeight()*4/5);
		g.drawString("Escape to Exit", parent.getWidth()/2 - g.getFontMetrics().stringWidth("Escape to Exit"), parent.getHeight()*4/5 + 36);
	}
	@Override
	public void setLt(boolean b) 
	{
		if(b)
		{
			if(currentIndex < 1)
			{
				currentIndex = options.length-1;
			}
			else
			{
				currentIndex--;
			}
		}
	}

	@Override
	public void setRt(boolean b) {
		if(b)
		{
			if(currentIndex < options.length-1)
			{
				currentIndex++;
			}
			else
			{
				currentIndex = 0;
			}
		}
	}
	@Override
	public void setSpace(boolean b)
	{
		if(b)
		{
			if(items[currentIndex] != null)
			{
			if(prices[currentIndex] <= ops.getPlayer().getBooty())
			{
				ops.getPlayer().addItem(items[currentIndex]);
				ops.getPlayer().removeMoney(prices[currentIndex]);
				prices[currentIndex] = 0;
				items[currentIndex] = null;
			}
			for(int i =0; i< items.length; i++)
			{
				if(items[i] !=null)
				{
				options[i] = items[i].getImage();
				}
				else
				{
					options[i] = ImageManager.getImage(ImageManager.SOLDOUT);
				}
			}
			}
		}
	}
	@Override
	public void setEscape(boolean b)
	{
		if(b)
		{
			ops.getGameInstance().setGameState(ops);
		}
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
