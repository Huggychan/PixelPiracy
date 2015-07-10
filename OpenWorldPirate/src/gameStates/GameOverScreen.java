package gameStates;

import entities.Crew;
import gui.GameInstance;
import gui.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOverScreen implements GameState {
	
	Crew toBeDisplayed;
	GameInstance gi;
	public GameOverScreen(Crew crew, GameInstance gi) 
	{
		this.toBeDisplayed = crew;
		this.gi = gi;
	}

	@Override
	public void draw(Graphics g, GamePanel parent) 
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, parent.getWidth(), parent.getHeight());
		g.setColor(Color.RED);
		g.setFont(new Font("Rockwell", 100,100));
		g.drawString("Yer Crew Has Starved", parent.getWidth()/2 -g.getFontMetrics().stringWidth("Yer Crew Has Starved")/2, parent.getHeight()/2);
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
		 gi.setGameState(new OpenSeasGameState(gi));
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
