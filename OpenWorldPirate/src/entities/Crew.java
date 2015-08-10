package entities;


import gameStates.OpenSeasGameState;
import graphics.ImageManager;
import items.ItemGenerator;

import java.util.Random;

import characterManagement.Fighter;
import characterManagement.FighterSpawner;
import combat.CombatGameState;
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
		this.randomDirections();
	}
}

public Crew(int i, int j, OpenSeasGameState ops, boolean b, int difficulty) 
{
	super(i,j,ImageManager.getImage(ImageManager.BOAT),ops,64,64,true);
	booty = difficulty*3;
	energy = maxEnergy = 100;
	this.generateCrew(difficulty);
	this.randomDirections();
}
private void generateCrew(int difficulty) 
{
	for(int i = 0; i < 5 && i < difficulty/25 + 1; i++)
	{
		Fighter temp = null;
		switch(r.nextInt(3))
		{
		case 0:
			temp = FighterSpawner.pirate(difficulty);
			break;
		case 1:
			temp = FighterSpawner.monkey();
			break;
		case 2:
			temp = FighterSpawner.pirateWizard(difficulty);
		}
		this.addFighter(temp);
	}
	for(int i = 0; i < 3; i++)
	{
		if(r.nextInt(100) < difficulty)
		{
			items.add(ItemGenerator.Random(this));
		}
	}
}
private void defaultCrew()
{
	int t = r.nextInt(4)+1;
	for(int i =0; i < t; i++)
	{
		if(r.nextBoolean())
		{
			this.addFighter(FighterSpawner.pirateWizard(1));
		}
		else
		{
			this.addFighter(FighterSpawner.pirate(1));
		}
	}

	items.add(ItemGenerator.Random(this));
	items.add(ItemGenerator.Random(this));
	items.add(ItemGenerator.Random(this));
}

private void randomDirections() 
{
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