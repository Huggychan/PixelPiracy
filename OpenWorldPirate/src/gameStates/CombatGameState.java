package gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import abilities.Ability;
import entities.FightingEntity;
import fighters.Fighter;
import gameStates.GameState;
import gui.GamePanel;

public class CombatGameState implements GameState 
{
	FightingEntity player;
	FightingEntity enemy;
	OpenSeasGameState parent;
	Fighter[] fighters;
	Fighter[] enemyFighters;
	int tickCount = 0;
	boolean pickingMove = false;
	String[] abilityNames = new String[]{""};
	String[] abilityDescriptions= new String[]{""};;
    String[] manaCosts = new String[]{""};
	int currentIndex = 0;
	Fighter activeFighter;
	Random r = new Random();
	public CombatGameState(FightingEntity entity, FightingEntity enemy, OpenSeasGameState parent) 
	{
		this.player = entity;
		this.enemy = enemy;
		this.parent = parent;
		this.fighters = player.getFighters();
		this.enemyFighters = enemy.getFighters();
	}
	private void drawUI(Graphics g, GamePanel parentPanel)
	{
		g.setColor(Color.WHITE);
		int width = parentPanel.getWidth()/2;
		g.fillRect(0, parentPanel.getHeight()*4/5, parentPanel.getWidth(), parentPanel.getHeight()/5);

		if(pickingMove)
		{
			g.setFont(new Font("Rockwell", 32, 32));
		
			int widthPer = width/abilityNames.length;
			for(int i =0; i < abilityNames.length; i++)
			{
				if(i == currentIndex)
				{
					g.setColor(Color.RED);
				}
				else
				{
					g.setColor(Color.BLACK);
					g.drawString(abilityDescriptions[currentIndex], 0, parentPanel.getHeight() - g.getFontMetrics().getHeight()-30);
					g.drawString("Mana Cost:" +manaCosts[currentIndex], 0, parentPanel.getHeight() - g.getFontMetrics().getHeight() +30);
				}
				g.drawString(abilityNames[i], width + (i * widthPer), parentPanel.getHeight() - g.getFontMetrics().getHeight());

			}
		}

		g.drawRect(0, parentPanel.getHeight()*4/5, parentPanel.getWidth(), parentPanel.getHeight()/5);
		g.drawRect(0, parentPanel.getHeight()*4/5, parentPanel.getWidth()/2, parentPanel.getHeight()/5);
	}
	@Override
	public void draw(Graphics g, GamePanel parentPanel) 
	{
		//g.drawImage(ImageManager.getImage(ImageManager.BACKGROUNDPIC), 0, 0, parentPanel.getWidth(), parentPanel.getHeight(), null);
		this.drawUI(g, parentPanel);
		
		int width = parentPanel.getWidth()/2;
		int availibleSpace = width/fighters.length;
		int availibleHeight = parentPanel.getHeight() *4/5;
		for(int i = 0; i < fighters.length; i++)
		{
			if(fighters[i] != null)
			{
			if(fighters[i].getLiving())
			{
			fighters[i].draw(g, availibleSpace, availibleHeight,(i * availibleSpace), true);
			}
			}
		}
		for(int i = 0; i < enemyFighters.length; i++)
		{
			if(enemyFighters[i] != null)
			{
			if(enemyFighters[i].getLiving())
			{
			enemyFighters[i].draw(g, availibleSpace, availibleHeight, width + (i * availibleSpace), false);
			}
			}
		}
	}

	@Override
	public void tick() 
	{
		if(!pickingMove)
		{
		tickCount++;
		if(tickCount > 3)
		{
		for(int i = 0; i < fighters.length; i++)
		{
			if(fighters[i] !=null)
			{
			fighters[i].tick();
			if(fighters[i].checkReady())
			{
				getUserInput(fighters[i]);
			}
			}
		}
		Fighter[] temp = new Fighter[fighters.length];
		for(int i =0; i< fighters.length; i++)
		{
			temp[fighters.length -(i+1)] = fighters[i];
		}
		for(int i = 0; i < enemyFighters.length; i++)
		{

			if(enemyFighters[i] !=null && enemyFighters[i].getLiving())
			{
			enemyFighters[i].tick();
			if(enemyFighters[i].checkReady())
			{
				boolean hasMoved = false;
				while(!hasMoved)
				{
					int f = r.nextInt(enemyFighters[i].getAbilities().length);
					if(enemyFighters[i].getAbilities()[f] != null)
					{
				enemyFighters[i].getAbilities()[f].use(enemyFighters, temp);
				enemyFighters[i].justMoved();
				enemyFighters[i].tick();
				hasMoved = true;
					}
				}
				}
			}
			
		}
		tickCount = 0;
		checkForWinner();
		}
		}
	}

	private void checkForWinner() 
	{
		boolean player1Living = false;
		for(int i = 0; i < fighters.length; i++)
		{
			if(fighters[i] != null)
			{
			if(fighters[i].getLiving())
			{
				player1Living = true;
			}
			}
		}
		boolean player2Living = false;
		for(int i = 0; i < enemyFighters.length; i++)
		{
			if(enemyFighters[i] != null)
			{
			if(enemyFighters[i].getLiving())
			{
				player2Living = true;
			}
			}
			
		}
		if(!player1Living)
		{
			displayLoss();
		}
		if(!player2Living)
		{
			displayVictory();
		}
	}
	private void displayVictory() 
	{
		parent.getGameInstance().setGameState(new CombatVictoryGameState(parent,enemy));

	}
	private void displayLoss()
	{
		parent.getGameInstance().setGameState(new GameOverScreen(parent.player, parent.gi));
	}
	private void getUserInput(Fighter fighter) 
	{
		Ability[] abilities = fighter.getAbilities();
		activeFighter = fighter;
		abilityNames = new String[abilities.length];
		abilityDescriptions = new String[abilities.length];
		manaCosts = new String[abilities.length];
		int count = 0;
		for(int i = 0; i < abilities.length; i++)
		{
			if(abilities[i] !=null)
			{
				count++;
			}
		}

		abilityNames = new String[count];
		abilityDescriptions = new String[count];
		for(int i = 0; i < abilities.length; i++)
		{
			if(abilities[i] !=null)
			{
				abilityNames[i] = abilities[i].getName();
				abilityDescriptions[i]= abilities[i].getDescription();
				manaCosts[i]= String.valueOf(abilities[i].getManaCost());

			}
		}
		pickingMove = true;
	}
	

	@Override
	public void setLt(boolean b) 
	{
		if(b && pickingMove)
		{
			if(currentIndex > 0)
			{
			currentIndex--;
			}
			else
			{
				currentIndex = abilityNames.length-1;
			}
		}
	}

	@Override
	public void setRt(boolean b) 
	{
		if(b && pickingMove)
		{
			
			if(currentIndex < abilityNames.length-1)
			{
			currentIndex++;
			}
			else
			{
				currentIndex = 0;
			}
		}
	}

	@Override
	public void setSpace(boolean b) 
	{
		if(b && pickingMove)
		{
			if(activeFighter.getMana() > activeFighter.getAbilities()[currentIndex].getManaCost())
			{
			activeFighter.lowerMana(activeFighter.getAbilities()[currentIndex].getManaCost());
			activeFighter.getAbilities()[currentIndex].use(fighters, enemyFighters);
			pickingMove = false;
			abilityNames = new String[]{""};
			activeFighter.justMoved();
			activeFighter.restoreMana(5);

			activeFighter = null;
			currentIndex = 0;
			
			}
		}
	}
	@Override
	public void setEscape(boolean b)
	{
		if(b && pickingMove)
		{

			pickingMove = false;
			abilityNames = new String[]{""};
			activeFighter.justMoved();
			activeFighter.restoreMana(20);
			activeFighter.heal(10);
			
			activeFighter = null;
			currentIndex = 0;
		}
	}
}
