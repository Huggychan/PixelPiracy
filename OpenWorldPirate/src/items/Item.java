package items;

import java.awt.Image;

import entities.Entity;
import entities.FightingEntity;

public class Item 
{
	protected Entity holder;
	private Image image;
	private ItemEffect itemEffect;
	boolean consumable;
	public Item(Image image, Entity holder, ItemEffect itemEffect, boolean consumable)
	{
		this.holder = holder;
		this.itemEffect = itemEffect;
		this.image = image;
		this.consumable = consumable;
	}
	public void setHolder(Entity e)
	{
		this.holder = e;
	}
	public Image getImage()
	{
		return image;
	}
	public void use(FightingEntity fe)
	{
		itemEffect.use(fe);
		if(consumable)
		{
			holder.removeItem(this);
		}
	}
}
