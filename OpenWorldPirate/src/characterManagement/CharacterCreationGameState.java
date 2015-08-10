package characterManagement;

import gameStates.GameState;
import gameStates.OpenSeasGameState;
import gui.GameInstance;
import gui.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class CharacterCreationGameState implements GameState 
{
	// int health, int attack,
//	int speed, int def, int hpGrowth, int atkGrowth, int spdGrowth,
//	int dfGrowth)
	int health = 10, attack = 1,speed = 1,magic = 1,crewMembers = 1;
	String[] options = new String[]{"Health < " + String.valueOf(health) + " >"
			,"Attack < " + String.valueOf(attack) + " >"
			,"Speed < " + String.valueOf(speed) + " >"
			,"Magic < " + String.valueOf(magic) + " >"
			,"Crew Members < " + String.valueOf(crewMembers) + " >"
			,"Start"};
	int remainingSkillPoints = 2;
	int currentIndex = 0;
	GameInstance gi;
	public CharacterCreationGameState(GameInstance gi)
	{
		this.gi = gi;
	}
	@Override
	public void draw(Graphics g, GamePanel parent) 
	{
		g.setFont(new Font("Rockwell",32,32));
		g.setColor(Color.BLACK);
		g.drawString("Remaining Skill Points: "+ String.valueOf(remainingSkillPoints), parent.getWidth()*1/5, parent.getHeight()/(options.length+1));
		for(int i =0; i < options.length; i++)
		{
			if(i == currentIndex)
			{
				g.setColor(Color.RED);
			}
			else
			{
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], parent.getWidth()*3/5, parent.getHeight()*(1+i)/(options.length+1));
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
			if(remainingSkillPoints > 0)
			{
			switch(currentIndex)
			{
			case 0: 
				health++;
				pointUsed = true;
				options[0] = "Health < " + String.valueOf(health) + " >";
			break;
			case 1: attack++;
			pointUsed = true;
			options[1] = "Attack < " + String.valueOf(attack) + " >";
			break;
			case 2: speed++;
			pointUsed = true;
			options[2] = "Speed < " + String.valueOf(speed) + " >";
			break;
			case 3: magic++;
			pointUsed = true;
			options[3] = "Magic < " + String.valueOf(magic) + " >";
			break;

			case 4: 
			if(remainingSkillPoints > 0)
			{
			if(crewMembers< 4)
			{
			pointUsed = true;
			crewMembers++;
			}
			options[4] = "Crew Members < " + String.valueOf(crewMembers) + " >";
			}
			break;
			}
			if(pointUsed)
			{
			remainingSkillPoints--;
			}
			}
			}
		}
	}
	@Override
	public void setLt(boolean b)
	{
		if(b)
		{
			if(currentIndex !=5)
			{
				boolean pointUsed = false;
			switch(currentIndex)
			{
			case 0: 
				if(health > 10)
					{
					pointUsed = true;
					health--;
				options[0] = "Health < " + String.valueOf(health) + " >";
					}
			break;
			case 1: if(attack > 1)
				{
				pointUsed = true;
				attack--;
			options[1] = "Attack < " + String.valueOf(attack) + " >";
				}
			break;
			case 2: if(speed > 1)
				{
				pointUsed = true;
				speed--;
			options[2] = "Speed < " + String.valueOf(speed) + " >";
				}
			break;
			case 3:if(magic > 1) 
			{
				pointUsed = true;
			magic--;
			options[3] = "Magic < " + String.valueOf(magic) + " >";
			}
			break;
			case 4:
			if(crewMembers > 0) 
			{
			pointUsed = true;
			crewMembers--;
			options[4] = "Crew Members < " + String.valueOf(crewMembers) + " >";
			}
			break;
			}
			if(pointUsed)
			{
			remainingSkillPoints++;
			}
			}
		}
	}
	@Override
	public void tick() 
	{
		
	}
	@Override
	public void setSpace(boolean b)
	{
		if(b)
		{
			if(currentIndex == 5)
			{
				gi.setGameState(new OpenSeasGameState(gi, health, attack, speed, magic, crewMembers));
			}
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
				currentIndex = options.length-1;
			}
		}
	}
	@Override
	public void setDn(boolean b)
	{
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
}
