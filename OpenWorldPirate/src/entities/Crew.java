package entities;

import fighters.FighterSpawner;
import gameStates.CombatGameState;
import gameStates.GameOverScreen;
import gameStates.OpenSeasGameState;
import graphics.ImageManager;
import items.ItemGenerator;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import worldStructure.Square;

public class Crew extends FightingEntity
{
int energy, maxEnergy;
int booty;
public Crew(int x, int y, OpenSeasGameState ops, boolean compControlled)
{
	super(x, y, ImageManager.getImage(ImageManager.BOAT), ops, 64 ,64, compControlled);
	//Image img, int width, int height, int health, int attack, int speed, int def, int hpGrowth, int atkGrowth, int spdGrowth, int dfGrowth
	//this.addFighter(FighterSpawner.captain());
	booty = 100;
	energy = maxEnergy = 100;
	items.add(ItemGenerator.Random(this));
	Random r = new Random();
	int t = r.nextInt(4)+1;
	for(int i =0; i < t; i++)
	{
		this.addFighter(FighterSpawner.pirate(r));
	}
	this.up = r.nextBoolean();
	this.dn = r.nextBoolean();
	this.lt = r.nextBoolean();
	this.rt = r.nextBoolean();
}

@Override
public void tick(Square[][] world)
{
	super.tick(world);
}

@Override
public void triggerInteraction(Entity ent) 
{
	if(ent.getIsFighter())
	{
		FightingEntity t = (FightingEntity) ent;
this.parent.getGameInstance().setGameState(new CombatGameState(t,this, this.parent));	
	}
}

}