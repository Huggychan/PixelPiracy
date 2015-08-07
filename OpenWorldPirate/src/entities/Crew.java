package entities;

import fighters.FighterSpawner;
import gameStates.CombatGameState;
import gameStates.OpenSeasGameState;
import graphics.ImageManager;
import items.ItemGenerator;

import java.util.Random;

import worldStructure.Square;

public class Crew extends FightingEntity
{
int energy, maxEnergy;

public Crew(int x, int y, OpenSeasGameState ops,boolean compControlled)
{
	super(x,y,ImageManager.getImage(ImageManager.BOAT),ops,64,64,compControlled);
	booty = 100;
	energy = maxEnergy = 100;
	if(compControlled)
	{
		this.defaultCrew();
	}
}
private void defaultCrew()
{
	Random r = new Random();
	int t = r.nextInt(4)+1;
	for(int i =0; i < t; i++)
	{
		if(r.nextBoolean())
		{
			this.addFighter(FighterSpawner.pirateWizard());
		}
		else
		{
			this.addFighter(FighterSpawner.pirate());
		}
	}
	this.up = r.nextBoolean();
	this.dn = r.nextBoolean();
	this.lt = r.nextBoolean();
	this.rt = r.nextBoolean();
	items.add(ItemGenerator.Random(this));
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