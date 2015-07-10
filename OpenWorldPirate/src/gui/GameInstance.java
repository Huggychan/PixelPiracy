package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import worldGeneration.WorldGenerator;
import worldStructure.BlankIslandSquare;
import worldStructure.BlankOceanSquare;
import worldStructure.Square;
import entities.Crew;
import entities.Entity;
import gameStates.CharacterCreationGameState;
import gameStates.GameState;
import gameStates.HomePageGameScreen;
import gameStates.OpenSeasGameState;
import graphics.ImageManager;

public class GameInstance implements Runnable 
{
boolean running = true;
GameState currentGameState;
String playerName;
public GameInstance(Crew playersCrew)
{
	currentGameState = new OpenSeasGameState(this);
}
public GameInstance()
{
	currentGameState = new HomePageGameScreen(this);
}

public void setGameState(GameState gs)
{
	currentGameState = gs;
	gs.reset();
}

@Override
public void run() 
{
	long lastTime = System.currentTimeMillis();
	long delta = 25;
	while(true)
	{
			while(running)
			{
				if(lastTime + delta < System.currentTimeMillis())
				{
					tick();
					lastTime = System.currentTimeMillis();
				}
			}
	}

}
private void tick() 
{
	currentGameState.tick();
	GamePainter.repaint();
}
public GameState getCurrentGameState() 
{
	return currentGameState;
}
}
