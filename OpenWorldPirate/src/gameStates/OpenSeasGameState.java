package gameStates;

import entities.Crew;
import entities.Entity;
import entities.FightingEntity;
import entities.Player;
import gui.GameInstance;
import gui.GamePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import worldGeneration.WorldGenerator;
import worldStructure.BlackSquare;
import worldStructure.BlankOceanSquare;
import worldStructure.Square;

public class OpenSeasGameState implements GameState
{
	ArrayList<Entity> entities = new ArrayList<Entity>();
	Player player;
	Square[][] world;
	boolean running = true;
	GameState currentGameState;
	GameInstance gi;
	public OpenSeasGameState(GameInstance gi)
	{
		this.player = new Player(100,100, this,15,15,15,15, 4);
		
		world = WorldGenerator.createWorld();
		this.gi = gi;
		this.entities = WorldGenerator.createEntities(world,this);
		//entities.add(new Crew(300,300,this));
	}
	public OpenSeasGameState(GameInstance gi, int health, int attack, int speed, int magic, int crewMembers)
	{
		this.player = new Player(100, 100, this, health, attack, speed, magic, crewMembers);
		
		world = WorldGenerator.createWorld();
		this.gi = gi;
		this.entities = WorldGenerator.createEntities(world,this);
			//entities.add(new Crew(300,300,this));
	}
	public GameInstance getGameInstance()
	{
		return gi;
	}
	@Override
	public void draw(Graphics g, GamePanel parent) 
	{
		BufferedImage temp = new BufferedImage(parent.getWidth(), parent.getHeight(), 1);
		Graphics tempg = temp.getGraphics();
		int playerOffsetX = player.getX()/64;
		int playerOffsetY = player.getY()/64;
		int playerOffdX = player.getX()%64;
		int playerOffdY = player.getY()%64;
		int mapOffsetX = parent.getWidth()/2;
		int mapOffsetY = parent.getHeight()/2;

		for(int i = (-parent.width/2)-1; i < parent.width/2+1; i++)
		{
			for(int j = (-parent.height/2)-2; j < parent.height/2+2; j++)
			{
				tempg.drawImage(getSquare(i + playerOffsetX,j + playerOffsetY).getImage(), i*64 + parent.xSpare - playerOffdX + mapOffsetX, j*64 + parent.ySpare - playerOffdY + mapOffsetY- player.getHeight()/2, 64, 64, null);
			}
		}
		tempg.drawImage(player.getImage(), mapOffsetX - player.getWidth()/2, mapOffsetY - player.getHeight()/2, 64, 64, null);
		
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).draw(tempg,  mapOffsetX  - (player.getX())- player.getWidth()/2, mapOffsetY - (player.getY()) - player.getHeight()/2);
		}
		Entity.triggerInteractions(player, entities);
		player.drawUI(tempg, parent.getWidth(), parent.getHeight()/10);
		drawItems(tempg, parent);
		g.drawImage(temp, 0, 0, null);
	}
	private void drawItems(Graphics g, GamePanel parentPanel)
	{
		g.setColor(new Color(255,255,255,80));
		int width = parentPanel.getWidth();
		//int bw = width/100;
		int bw = 64;
		int numBoxX = width/bw;
		int size= player.getItems().size();
		g.fillRect(0, parentPanel.getHeight()-bw, bw * size, bw);
		g.setColor(Color.BLACK);
		for(int i = 0; i < numBoxX && i < size; i++)
		{
			g.drawRect(i*bw, parentPanel.getHeight()-bw, bw, bw);
			
			g.drawImage(player.getItems().get(i).getImage(),i*bw,parentPanel.getHeight()-bw, bw, bw, null);
		}
		g.drawRect(0, parentPanel.getHeight()-bw, bw * size, bw);
	}
	public Square getSquare(int i, int j) 
	{
		try
		{
			if(world[i][j] != null)
			{
		return world[i][j];
			}
			else
			{
				return new BlackSquare();
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return new BlackSquare();
		}
	}
	
	@Override
	public void tick() 
	{
		player.tick(world);
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).tick(world);
		}
		
	}
	@Override
	public void setUp(boolean b) 
	{
		player.setUp(b);
	}
	@Override
	public void setDn(boolean b) 
	{
		player.setDn(b);
	}
	@Override
	public void setLt(boolean b) 
	{
		player.setLt(b);		
	}
	@Override
	public void setRt(boolean b) 
	{
		
		player.setRt(b);		
	}
	@Override
	public void setEscape(boolean b)
	{
		if(b)
		{
			gi.setGameState(new PauseScreenState(this));
		}
	}
	@Override
	public void setSpace(boolean b) {
		// TODO Auto-generated method stub
		
	}
	public void remove(Entity enemy) 
	{
	entities.remove(enemy);	
	}
	@Override
	public void setNumber(int i, boolean b) 
	{
		if(b)
		{
			if(i < player.getItems().size())
			{
			player.getItems().get(i).use(player);
			}
		}
	}
	public int getWidth()
	{
		return world.length*64;
	}
	public int getHeight()
	{
		return world[0].length*64;
	}
	@Override
	public void reset()
	{
		player.reset();
		this.setDn(false);
		this.setUp(false);
		this.setLt(false);
		this.setRt(false);
	}
	public Player getPlayer() 
	{
		return player;
	}

}
