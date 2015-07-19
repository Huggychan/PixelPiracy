package gameStates;

import gui.GamePanel;

import java.awt.Graphics;

public interface GameState 
{
	public abstract void draw(Graphics g, GamePanel parent);

	public abstract void tick();

	public default void setUp(boolean b) {
	}

	public default void setDn(boolean b) {
	}

	public default void setLt(boolean b) {
	}

	public default void setRt(boolean b) {
	}
	public default void setSpace(boolean b) {
	}
	public default void setEscape(boolean b) {
	}
	public default void setNumber(int i, boolean b) {
	}
	public default void reset()
	{
		
	}

	public default void setTab(boolean b){}
}
