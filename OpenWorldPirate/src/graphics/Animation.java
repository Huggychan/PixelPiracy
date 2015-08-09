package graphics;

import gameStates.GameState;
import gui.GameInstance;

public class Animation 
{
	AnimationHandler handler;
	
	public Animation(AnimationHandler handler)
	{
		this.handler = handler;
	}
	public void play(GameState toPlayOver, GameInstance gi, int xOrigin, int yOrigin)
	{
		handler.play(toPlayOver, gi, xOrigin, yOrigin);
	}
	
	
}
