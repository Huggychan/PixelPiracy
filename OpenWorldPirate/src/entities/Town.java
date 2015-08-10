package entities;

import gameStates.OpenSeasGameState;
import gameStates.ShopGameState;
import graphics.ImageManager;
import items.Item;
import items.ItemGenerator;

import abilities.Ability;
import worldStructure.Square;

public class Town extends Entity {
	Item[] items;
	int[] prices; 
	public Town(int x, int y, OpenSeasGameState parent, boolean compControlled) 
	{
		super(x*64, y*64, ImageManager.getImage(ImageManager.CITY), parent, 64, 64, compControlled);
		items = new Item[]{ItemGenerator.AbilityScroll(parent, Ability.heal(null).getTemplate(), parent.getPlayer()), ItemGenerator.Cherry(parent.getPlayer()), ItemGenerator.FullRestore(parent.getPlayer())};
		prices = new int[]{100,100,100};
	}
	@Override
	public void tick(Square[][] world)
	{
		
	}
	@Override
	public void triggerInteraction(Entity ent) 
	{
		parent.remove(this);
		parent.getGameInstance().setGameState(new ShopGameState(parent, items, prices));
	}

}
