package entities;

import gameStates.OpenSeasGameState;
import items.Item;

import java.awt.Image;
import java.util.ArrayList;

import characterManagement.Fighter;


public abstract class FightingEntity extends Entity 
{
	protected Fighter[] fighters;
	int booty = 0;
	public FightingEntity(int x, int y, Image img, OpenSeasGameState parent,
			int width, int height, boolean compControlled) {
		super(x, y, img, parent, width, height, compControlled);
		fighters = new Fighter[5];
		this.items = new ArrayList<Item>();
		this.isFighter = true;
	}
	public void addFighter(Fighter f)
	{
		for(int i = 0; i < fighters.length; i++)
		{
			if(fighters[i] == null)
			{
				fighters[i] = f;
				return;
			}
		}
	}

	public Fighter[] getFighters()
	{
		return fighters;
	}
	public int getBooty() 
	{
		return booty;
	}

}
