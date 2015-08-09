package combat;

import entities.FightingEntity;
import entities.Player;
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
		this.enemy = enemy;
		os.remove(enemy);
	}

	@Override
	public void draw(Graphics g, GamePanel parent) 
	{
		g.drawString("Booty won: " + String.valueOf(enemy.getBooty()), parent.getWidth()/2 - g.getFontMetrics().stringWidth("Booty won: " + String.valueOf(enemy.getBooty()))/2, g.getFontMetrics().getHeight());
		int widthPer = parent.getWidth()/items.size();
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
	public void setEnter(boolean b)
	{
		if(b)
		{
			os.getPlayer().addItem(items.get(currentIndex));
			items.remove(currentIndex);
			currentIndex = 0;
			if(items.size()==0)
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
