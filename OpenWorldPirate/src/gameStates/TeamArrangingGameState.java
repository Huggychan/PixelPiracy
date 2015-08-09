package gameStates;

import gui.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import combat.Fighter;

public class TeamArrangingGameState implements GameState {
	OpenSeasGameState os;
	Fighter selectedFighter;
	boolean holdingFighter = false;
	Fighter[] fighters;
	int currentIndex = 0;
	int count = 0;
	int parentIndex;
	public TeamArrangingGameState(OpenSeasGameState os)
	{
		this.os=os;
		this.fighters = os.player.getFighters();
		for(int i = 0; i < fighters.length; i++)
		{
			if(fighters[i] != null)
			{
				count++;
			}
		}
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
		if(holdingFighter)
		{
		tempg.drawString(selectedFighter.getName() + " selected",width/2 - (tempg.getFontMetrics().stringWidth(selectedFighter.getName() + " selected")/2),30);
		tempg.drawString("Switch with which pirate?",width/2 - (tempg.getFontMetrics().stringWidth("Switch with which pirate?")/2),75);
		}
		else
		{
			tempg.drawString("Escape to return to ye game",width/2 - (tempg.getFontMetrics().stringWidth("Escape to return to ye game")/2),30);
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
		tempg.setColor(Color.RED);
		tempg.drawRect(currentIndex*widthPer, parent.getHeight()*1/4, widthPer, parent.getHeight()
				/5);
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
			if(!holdingFighter)
			{
			selectedFighter = fighters[currentIndex]; 
			holdingFighter = true;
			parentIndex = currentIndex;
			}
			else
			{
				Fighter temp = fighters[currentIndex];
				fighters[currentIndex] = selectedFighter;
				fighters[parentIndex] = temp;
				holdingFighter = false;
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
	public void setLt(boolean b) 
	{
		if(b)
		{
			if(currentIndex < 1)
			{
				currentIndex = count-1;
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
			if(currentIndex >  count-2)
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
