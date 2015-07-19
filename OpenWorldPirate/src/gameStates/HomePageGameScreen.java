package gameStates;

import entities.Crew;
import graphics.FontManager;
import graphics.ImageManager;
import gui.GameInstance;
import gui.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

public class HomePageGameScreen implements GameState 
{
	Image homeScreen;
	Image boat;
	GameInstance gi;
	Font f;
	String[] options = new String[]{"New Game","Load Game", "Options", "Exit"};
	int selectedOption= 0;
	int x = 0;
	public HomePageGameScreen(GameInstance gi)
	{
		this.gi = gi;
		homeScreen=ImageManager.getImage(ImageManager.HOMEPAGE);
		boat = ImageManager.getImage(ImageManager.BOAT);
		f = FontManager.pirateFont;
	}
	@Override
	public void draw(Graphics g, GamePanel parent) 
	{
		g.drawImage(homeScreen, 0, 0, parent.getWidth(), parent.getHeight(), null);
		g.drawImage(boat, x, parent.getHeight()*9/10, 64, 64, null);
		g.setFont(f);
		for(int i = 0; i < options.length; i++)
		{
			if(i == selectedOption)
			{
				g.setColor(Color.red);
			}
			else
			{
				g.setColor(Color.black);
			}
		g.drawString(options[i],  (parent.getWidth()/5- g.getFontMetrics().stringWidth(options[i])/2),parent.getHeight()/3 + (i*2)*g.getFontMetrics().getHeight());
			
		}
	}

	@Override
	public void tick() 
	{
		x+= 3;
		if(x > 2000)
		{
			x = -100;
		}
	}

	@Override
	public void setUp(boolean b) 
	{
		if(b)
		{
			if(selectedOption > 0)
			{
			selectedOption--;
			}
			else
			{
				selectedOption = options.length-1;
			}
		}
	}

	@Override
	public void setDn(boolean b) 
	{
		if(b)
		{
		if(selectedOption < options.length-1)
		{
		selectedOption++;
		}
		else
		{
			selectedOption = 0;
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
			switch(selectedOption)
			{
			case 0:
				gi.setGameState(new CharacterCreationGameState(gi));
			break;
			case 1:
				gi.setGameState(new OpenSeasGameState(gi));
				break;
			case 2:
				break;
			case 3:
				System.exit(0);
				break;
			}
		}
	}
	@Override
	public void setEscape(boolean b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setNumber(int i, boolean b) 
	{

	}

}
