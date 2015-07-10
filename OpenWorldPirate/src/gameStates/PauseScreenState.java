package gameStates;

import gui.GamePanel;

import java.awt.Color;
import java.awt.Graphics;

public class PauseScreenState implements GameState
{
	OpenSeasGameState gs;
	String[] options = new String[]{"Resume", "Arrange Team", "Exit"};
	int currentOption = 0;
	public PauseScreenState(OpenSeasGameState gs)
	{
		this.gs= gs;
	}
	@Override
	public void draw(Graphics g, GamePanel parent) 
	{
		gs.draw(g, parent);
		g.setColor(new Color(255,255,255,95));
		g.fillRect(parent.getWidth()/4, parent.getHeight()/4, parent.getWidth()/2, parent.getHeight()/2);
		for(int i = 0; i < options.length; i++)
		{
			if(i != currentOption)
			{
				g.setColor(Color.BLACK);
			}
			else
			{
				g.setColor(Color.RED);
			}
			g.drawString(options[i],parent.getWidth()*4/9, parent.getHeight()/3 + parent.getHeight()/10 + parent.getHeight()*i/9);
		}
	}

	@Override
	public void tick() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUp(boolean b) 
	{
		if(b)
		{
			if(currentOption > 0)
			{
				currentOption--;
			}
			else
			{
				currentOption = options.length-1;
			}
		}
	}

	@Override
	public void setDn(boolean b) 
	{
		if(b)
		{
			if(currentOption < options.length-1)
			{
			currentOption++;	
			}
			else
			{
				currentOption = 0;
			}
		}
	}

	@Override
	public void setLt(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRt(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSpace(boolean b) 
	{
	if(b)
	{
		if(currentOption ==0)
		{
			setEscape(true);
		}
		if(currentOption == 1)
		{
			gs.getGameInstance().setGameState(new TeamManageMentGameState(gs));
		}
		if(currentOption ==2)
		{
			System.exit(0);
		}
	}
	}
	@Override
	public void setEscape(boolean b) 
	{
		if(b)
		{
		gs.getGameInstance().setGameState(gs);
		}
	}
	@Override
	public void setNumber(int i, boolean b) {
		// TODO Auto-generated method stub
		
	}

}
