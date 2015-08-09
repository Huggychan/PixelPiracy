package graphics;

import java.awt.Graphics;

import gameStates.GameState;
import gui.GameInstance;
import gui.GamePanel;

public interface AnimationHandler extends GameState
{
	public void play(GameState toPlayOver, GameInstance gi, int xOrigin, int yOrigin);

	@Override
	public void draw(Graphics g, GamePanel parent);

	@Override
	public void tick();

	
	public default void finish(GameState toPlayOver, GameInstance gi)
	{
		gi.setGameState(toPlayOver);
	}

}
