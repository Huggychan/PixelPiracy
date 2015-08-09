package gameStates;

import gui.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import combat.Fighter;

import abilities.Ability;
import abilities.AbilityTemplate;

public class AbilityLearningGameState implements GameState {

	OpenSeasGameState os;
	Fighter[] fighters;
	String[] names;
	AbilityTemplate ability;
	int currentIndex = 0;
	public AbilityLearningGameState(OpenSeasGameState os, Fighter[] fighters, AbilityTemplate a) 
	{
		this.os = os;
		this.ability = a;
		this.fighters = fighters;
		this.names = new String[fighters.length+1];
	}

	@Override
	public void draw(Graphics g, GamePanel parent) 
	{
		os.draw(g, parent);
		int width = parent.getWidth()*3/5;
		BufferedImage temp = new BufferedImage(width, parent.getHeight()*3/5, BufferedImage.TYPE_INT_ARGB);
		Graphics tempg = temp.getGraphics();
		tempg.fillRect(0,0,width, parent.getHeight()*3/5);
		tempg.setFont(new Font("Rockwell", 30, 30));
		tempg.setColor(Color.BLACK);
		tempg.drawString(ability.getName() + ": " + ability.getDescription(),width/2 - (tempg.getFontMetrics().stringWidth(ability.getName() + ": " + ability.getDescription())/2),30);
		tempg.drawString("Who would you like to learn this?",width/2 - (tempg.getFontMetrics().stringWidth(ability.getName() + ": " + ability.getDescription())/2),75);
		int count = 0;
		for(int i = 0; i < fighters.length; i++)
		{
			if(fighters[i] != null)
			{
				count++;
			}
		}
		int widthPer = width/count;
		for(int i =0; i < fighters.length; i++)
		{
			if(fighters[i] !=null)
			{
				if(i != currentIndex)
				{
					tempg.setColor(Color.BLACK);
				}
				else
				{
					tempg.setColor(Color.RED);
				}
				tempg.drawString(fighters[i].getName(),(i*widthPer) + widthPer/2 - tempg.getFontMetrics().stringWidth(fighters[i].getName()), parent.getHeight()*2/5);
			}
		}
		g.drawImage(temp, parent.getWidth()/5, parent.getHeight()/5, null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUp(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDn(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLt(boolean b) 
	{
		if(b)
		{
			if(currentIndex < 1)
			{
				currentIndex = fighters.length-1;
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
			if(currentIndex > fighters.length-1)
			{
				currentIndex = 0;
			}
			else
			{
				currentIndex++;
			}
		}
	}

	@Override
	public void setSpace(boolean b) 
	{
		if(b)
		{
			Ability temp = ability.createAbility(fighters[currentIndex]);
			if(fighters[currentIndex].learnAbility(temp))
			{
			os.getGameInstance().setGameState(os);
			}
			else
			{
			os.getGameInstance().setGameState(new ReplaceAbilityGameState(fighters[currentIndex], temp, os));
			}
		}
	}

	@Override
	public void setEscape(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNumber(int i, boolean b) {
		// TODO Auto-generated method stub

	}

}
