package gameStates;

import gui.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import combat.Fighter;

public class FighterManagerState implements GameState {
	
	TeamManagingGameState tgs;
	Fighter f;
	String name;
	String[] traits = new String[5];
	int remainingPoints;
	int currentIndex = 0;
	String[] abilities = new String[5];
	public FighterManagerState(TeamManagingGameState tgs, Fighter f)
	{
		this.tgs=tgs;
		this.f = f;
		name = f.getName();
		traits[0] = "Remaining: " + f.getAvailiblePoints();
		traits[1] = "Health: " + String.valueOf(f.getMaxHealth()) + " >";
		traits[2] = "Attack: " + String.valueOf(f.getAttack()) + " >";
		traits[3] = "Magic: " + String.valueOf(f.getMagicPower()) + " >";
		traits[4] = "Speed:  " + String.valueOf(f.getSpeed()) + " >";
		for(int i = 0; i < abilities.length; i++)
		{
			if(f.getAbilities()[i] !=null)
			{
			abilities[i] = f.getAbilities()[i].getName() + ": " + f.getAbilities()[i].getDescription();
			}
		}
		remainingPoints= f.getAvailiblePoints();

	}

	@Override
	public void draw(Graphics g, GamePanel parent) 
	{
		int width = parent.getWidth()*3/5;

		tgs.getOPS().draw(g, parent);
		g.setColor(new Color(255,255,255));
		g.setFont(new Font("Rockwell", 0,32));
		g.fillRect(parent.getWidth()/5,parent.getHeight()/5,width, parent.getHeight()*3/5);
		g.setColor(Color.BLACK);
		g.drawString(name,parent.getWidth()/5 + width/2 -g.getFontMetrics().stringWidth(name), parent.getHeight()/5+g.getFontMetrics().getHeight());
		for(int i = 0; i < abilities.length; i++)
		{

			if(abilities[i] !=null)
			{
				g.drawString(abilities[i], parent.getWidth()/5,parent.getHeight()/5 + g.getFontMetrics().getHeight()* (8+i));
			}
		}
		for(int i = 0; i < traits.length; i++)
		{
			if(i == currentIndex)
			{
				g.setColor(Color.RED);
			}
			else
			{
				g.setColor(Color.BLACK);
			}
			g.drawString(traits[i], parent.getWidth()/5, parent.getHeight()/5 + g.getFontMetrics().getHeight()* (2+i));
		}

	}
	@Override
	public void setUp(boolean b)
	{
		if(b)
		{
			if(currentIndex > 0)
			{
			currentIndex--;
			}
			else
			{
				currentIndex = traits.length-1;
			}
		}
	}
	@Override
	public void setDn(boolean b)
	{
		if(b)
		{
			if(currentIndex < traits.length-1)
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
	public void setRt(boolean b)
	{
		if(b)
		{
			
			if(currentIndex !=5)
			{
				boolean pointUsed = false;
			if(remainingPoints > 0)
			{
			switch(currentIndex)
			{
			case 0: 
				f.levelHealth();
				pointUsed = true;
				traits[0] = "Health < " + String.valueOf(f.getMaxHealth()) + " >";
			break;
			case 1: f.levelAttack();;
			pointUsed = true;
			break;
			case 2: f.levelMagic();;
			pointUsed = true;
			break;
			case 3: f.levelSpeed();;
			pointUsed = true;
			break;
			}
			if(pointUsed)
			{
			remainingPoints--;
			f.usePoint();
			}
			}
			traits[0] = "Remaining: " + f.getAvailiblePoints();
			traits[1] = "Health: " + String.valueOf(f.getMaxHealth()) + " >";
			traits[2] = "Attack: " + String.valueOf(f.getAttack()) + " >";
			traits[3] = "Magic: " + String.valueOf(f.getMagicPower()) + " >";
			traits[4] = "Speed:  " + String.valueOf(f.getSpeed()) + " >";
			}
		}
	}
	@Override
	public void setEscape(boolean b)
	{
		if(b)
		{
			tgs.getOPS().getGameInstance().setGameState(tgs);
		}
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
