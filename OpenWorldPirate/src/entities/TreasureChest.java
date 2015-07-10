package entities;

import java.util.Random;

import abilities.Ability;
import gameStates.OpenSeasGameState;
import graphics.ImageManager;
import items.ItemGenerator;

public class TreasureChest extends Entity
{
	static Random r = new Random();
	public TreasureChest(OpenSeasGameState os)
	{
		super(r.nextInt(os.getWidth()), r.nextInt(os.getHeight()), ImageManager.getImage(ImageManager.CHEST), os, 64, 64, true);
		this.items.add(ItemGenerator.Random(this));
		this.items.add(ItemGenerator.AbilityScroll(os, Ability.TidalWave(null).getTemplate(), this));
	}

	@Override
	public void triggerInteraction(Entity ent) 
	{
		for(int i = 0; i < items.size(); i++)
		{
			items.get(i).setHolder(ent);
			ent.items.add(items.get(i));
			
		}
		this.items.removeAll(this.items);
		this.parent.remove(this);
	}
}
