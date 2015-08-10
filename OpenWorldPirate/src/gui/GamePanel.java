package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import gui.GameInstance;

public class GamePanel extends Component
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8463432215107864298L;
	GameInstance gi;
	public int width, height, xSpare, ySpare;
	Dimension screenDims;
public GamePanel(GameInstance gi, Dimension d) 
{
this.gi = gi;
screenDims = d;
width = d.width/64;
height = d.height/64;
xSpare = d.width%64;
ySpare = d.height%64;
this.setSize(screenDims);
}

@Override
public void paint(Graphics g)
{
	gi.getCurrentGameState().draw(g, this);
}

}
