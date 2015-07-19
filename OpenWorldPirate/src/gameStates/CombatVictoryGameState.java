package gameStates;

import entities.FightingEntity;
import entities.Player;
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
		g.drawString("Booty won: " + String.valueOf(enemy.getBooty()), 0, 0);
		int widthPer = parent.getWidth()/items.size();
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
	public void tick() {
		// TODO Auto-generated method stub

	}

}
