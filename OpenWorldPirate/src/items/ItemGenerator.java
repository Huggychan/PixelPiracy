package items;

import entities.Entity;
import entities.FightingEntity;
import gameStates.AbilityLearningGameState;
import gameStates.OpenSeasGameState;
import graphics.ImageManager;

import java.util.Random;

import abilities.Ability;
import abilities.AbilityTemplate;

public class ItemGenerator 
{
	static Random r = new Random();
	public static Item AbilityScroll(OpenSeasGameState os, AbilityTemplate abilityTemplate, Entity e)
	{
	//	Item abilityScroll = Cherry(e);
		Item abilityScroll =new Item(ImageManager.getImage(ImageManager.BOOK), e, new ItemEffect()
		{

			@Override
			public void use(FightingEntity fightingEntity) 
			{
				os.getGameInstance().setGameState(new AbilityLearningGameState(os,fightingEntity.getFighters(), abilityTemplate));
				//e.removeItem(abilityScroll);
			}}, true);
		return abilityScroll;
	}
public static Item Cherry(Entity e)
{
	return new Item(ImageManager.getImage(ImageManager.FOOD), e, new ItemEffect(){

		@Override
		public void use(FightingEntity fighter) 
		{
			fighter.reduceHunger(3000);
		}}, true);
}
public static Item FullRestore(Entity e)
{
	return new Item(ImageManager.getImage(ImageManager.ENERGY), e, new ItemEffect(){

		@Override
		public void use(FightingEntity fighter) 
		{
			for(int i = 0; i <fighter.getFighters().length; i++)
			{
				if(fighter.getFighters()[i] != null)
				{
					fighter.getFighters()[i].reset();
				}
			}
		}}, true);
}
public static Item Random(Entity e) 
{
	switch(r.nextInt(3))
	{
	case 0:
		return FullRestore(e);
	case 1:

	}
	return Cherry(e);
	/*
	 * 
	 	items.add(ItemGenerator.Cherry(this));
		items.add(ItemGenerator.FullRestore(this));
		items.add(ItemGenerator.AbilityScroll(ops, Ability.kick(null).getTemplate(), this));
	 */
}
}
