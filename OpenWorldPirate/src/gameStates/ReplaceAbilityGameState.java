package gameStates;

import fighters.Fighter;
import gui.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import abilities.Ability;

public class ReplaceAbilityGameState implements GameState {
	Fighter fighter;
	Ability ability;
	Ability[] abilities;
	int currentIndex = 0;
	OpenSeasGameState os;
	public ReplaceAbilityGameState(Fighter fighter, Ability temp, OpenSeasGameState os) 
	{
		this.os = os;
		this.fighter = fighter;
		this.ability = temp;
		this.abilities = fighter.getAbilities();
	}

	@Override
	public void draw(Graphics g, GamePanel parent) 
	{
		int width = parent.getWidth()*3/5;
		os.draw(g, parent);
		BufferedImage temp = new BufferedImage(width, parent.getHeight()*3/5, BufferedImage.TYPE_INT_ARGB);
		Graphics tempg = temp.getGraphics();
		tempg.fillRect(0,0,width, parent.getHeight()*3/5);
		tempg.setFont(new Font("Rockwell", 30, 30));
		tempg.setColor(Color.BLACK);
		tempg.drawString(ability.getName() + ": " + ability.getDescription(),width/2 - (tempg.getFontMetrics().stringWidth(ability.getName() + ": " + ability.getDescription())/2),30);
		tempg.drawString("What do you want to replace with this?",width/2 - (tempg.getFontMetrics().stringWidth(ability.getName() + ": " + ability.getDescription())/2),75);
		int count = 0;
		for(int i = 0; i < abilities.length; i++)
		{
			if(abilities[i] != null)
			{
				count++;
			}
		}
		int widthPer = width/count;
		for(int i =0; i < abilities.length; i++)
		{
			if(abilities[i] !=null)
			{
				if(i != currentIndex)
				{
					tempg.setColor(Color.BLACK);
				}
				else
				{
					tempg.setColor(Color.RED);
				}
				tempg.drawString(abilities[i].getName(),(i*widthPer) + widthPer/2 - tempg.getFontMetrics().stringWidth(abilities[i].getName()), parent.getHeight()*2/5);
			}
		}
		g.drawImage(temp, parent.getWidth()/5, parent.getHeight()/5, null);
	}

	@Override
	public void tick() 
	{
		// TODO Auto-generated method stub

	}
	public void setSpace(boolean b) 
	{
		if(b)
		{
			fighter.replace(ability, currentIndex);
			os.getGameInstance().setGameState(os);
		}
	}
	@Override
	public void setLt(boolean b) 
	{
		if(b)
		{
			if(currentIndex < 1)
			{
				currentIndex = abilities.length-1;
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
			if(currentIndex >  abilities.length-1)
			{
				currentIndex = 0;
			}
			else
			{
				currentIndex++;
			}
		}
	}
}
