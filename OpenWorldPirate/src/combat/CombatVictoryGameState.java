package combat;

import entities.FightingEntity;
import gameStates.GameState;
import gameStates.OpenSeasGameState;
import gui.GamePanel;
import items.Item;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class CombatVictoryGameState implements GameState {
	OpenSeasGameState os;
	FightingEntity enemy;
	int currentIndex = 0;
	ArrayList<Item> items;
	int booty;
	public CombatVictoryGameState(OpenSeasGameState os, FightingEntity enemy) 
	{
		this.os = os;
		items = enemy.getItems();
		os.getPlayer().addBooty(enemy.getBooty());
		int exp = 0;
		for(int i = 0; i < enemy.getFighters().length; i++)
		{
			if(enemy.getFighters()[i] !=null)
			{
			exp+= enemy.getFighters()[i].getExpWorth();
			}
		}
		os.getPlayer().getExp(exp);
		this.enemy = enemy;
		os.remove(enemy);
		for(int i = 0; i < os.getPlayer().getFighters().length; i++)
		{
			if(os.getPlayer().getFighters()[i] == null)
			{
				for(int j = i; j < os.getPlayer().getFighters().length; j++)
				{
					if(os.getPlayer().getFighters()[j] != null)
					{
						os.getPlayer().getFighters()[i] = os.getPlayer().getFighters()[j];
						os.getPlayer().getFighters()[j] = null;
						j = 100;
					}
				}
			}
		}
	}

	@Override
	public void draw(Graphics g, GamePanel parent) 
	{
		g.drawString("Booty won: " + String.valueOf(enemy.getBooty()), parent.getWidth()/2 - g.getFontMetrics().stringWidth("Booty won: " + String.valueOf(enemy.getBooty()))/2, g.getFontMetrics().getHeight());
		int widthPer = 1;
		if(items.size() > 0)
		{
		widthPer = parent.getWidth()/items.size();
		}
		int maxHeightPer = parent.getHeight()/10;
		if(widthPer > maxHeightPer)
		{
			widthPer = maxHeightPer;
		}
		for(int i =0; i < items.size(); i++)
		{
			if(i == currentIndex)
			{
				g.setColor(Color.RED);
			}
			else
			{
				g.setColor(Color.BLACK);
			}
			g.drawRect(i*widthPer, parent.getHeight()/4, widthPer,widthPer);
			g.drawImage(items.get(i).getImage(),i*widthPer, parent.getHeight()/4, widthPer,widthPer,null);
		}
	}
	@Override
	public void setLt(boolean b) 
	{
		if(b)
		{
			if(currentIndex > 0)
			{
			currentIndex--;
			}
			else
			{
				currentIndex = items.size()-1;
			}
		}
	}
	@Override
	public void setSpace(boolean b)
	{
		if(b)
		{
			os.getPlayer().addItem(items.get(currentIndex));
			items.remove(currentIndex);
			currentIndex = 0;
			if(items.size()<=0)
			{
				setEscape(true);
			}
		}
	}
	@Override
	public void setRt(boolean b) 
	{
		if(b)
		{
			if(currentIndex < items.size()-1)
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
	public void setEscape(boolean b)
	{
		if(b)
		{
			os.getGameInstance().setGameState(os);
		}
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
