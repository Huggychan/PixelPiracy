package characterManagement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import combat.Status;

import abilities.Ability;
import graphics.ImageManager;

public class Fighter 
{
Image img;
protected int width = 10, height = 10;
int currentHealth, attack, speed;
String name;
int maxHealth;
int maxMana = 100;
int currentMana = 100;
int level;
double ticks = 0;
int magicPower, magicPowerGrowth;
Ability[] abilities;
int growthFactor;
int experience;
int threshHoldXp = 10;
int availiblePoints = 0;
ArrayList<Status> statuses;
boolean ready = false;
boolean living = true;
public Fighter(Image img, String name, int health, int attack, int magic, int speed,int growth)
{
this.name = name;
this.statuses =  new ArrayList<Status>();
this.img = img;
this.maxHealth = health;
this.currentHealth = health;
this.attack = attack;
this.speed = speed;
this.magicPower = magic;
this.growthFactor = growth;
this.abilities = new Ability[5];

experience = 0;
level = 1;
}
public String getName()
{
	return name;
}
public void reset()
{
	currentHealth = maxHealth;
	currentMana = maxMana;
	ticks = 200;
}
public void draw(Graphics g, int availibleSpace, int availibleHeight, int x, boolean right)
{
	double ratio = height/width;
	Image temp;
	
	if(!right)
	{
		 temp = ImageManager.flipImage(img);
	}
	else
	{
		temp = img;
	}
	
	int heightToUse = availibleHeight/4;
	int arcDimension;
		arcDimension = availibleSpace/2;
	//TODO write in an hp bar and maybe energy bar? something like that.
	g.setColor(Color.GREEN);
	g.drawArc(x, availibleHeight- heightToUse - arcDimension, arcDimension, arcDimension, 0, 360);
	double percentDone = ticks/200;
	g.fillArc(x,  availibleHeight-	heightToUse - arcDimension, arcDimension, arcDimension, 0, (int)(360 * percentDone));
	
	g.setColor(Color.RED);
	double hpratio = (double) currentHealth / (double) maxHealth;
	double manaratio = (double) currentMana / (double) maxMana;
	
		ratio = width/height;
		int wdth = (int) ((ratio * heightToUse) /1.1);
		g.drawImage(temp, x, availibleHeight-heightToUse, wdth, heightToUse, null);
	
		g.fillRect(x, availibleHeight-heightToUse-(2*arcDimension), (int) (hpratio* wdth),arcDimension/2);
		
		
		g.setColor(Color.BLUE);
		
		g.fillRect(x, availibleHeight-heightToUse-(3*arcDimension/2),(int) (manaratio* wdth),arcDimension/2);
		
		g.setColor(Color.BLACK);
		g.drawRect(x, availibleHeight-heightToUse-(2*arcDimension), wdth,arcDimension/2);
		g.drawRect(x, availibleHeight-heightToUse-(3*arcDimension/2), wdth,arcDimension/2);
}
public boolean getLiving()
{
	return living;
}
public void tick()
{
	
	if(living)
	{
		if(currentHealth > 0)
		{
	if(!ready)
	{
	ticks +=speed;
	for(int i = 0; i < statuses.size(); i++)
	{
	statuses.get(i).trigger();
	}
	if(ticks >= 200)
	{
		ready = true;
	}
	}
		}
		else
		{
			living = false;
		}
	}
}
public boolean checkReady()
{
	return ready;
}
public void addExp(int xp)
{
	experience += xp;
	if(experience > threshHoldXp)
	{
		this.levelUp();
	}
}

private void levelUp()
{
	availiblePoints +=growthFactor;
	int tempXp = experience - threshHoldXp;
	experience = 0;
	level++;
	threshHoldXp = 8*level + 10;
	if(tempXp > 0)
	{
		addExp(tempXp);
	}
}
public double getEnergy()
{
	return ticks /(double) 200;
}
public Ability[] getAbilities() {
	return abilities;
}

public boolean learnAbility(Ability b)
{
	for(int i = 0; i < abilities.length; i++)
	{
		if(abilities[i] == null)
		{
			abilities[i] = b;
			return true;
		}
	}
	return false;
}
public void getHit(int i) 
{
	currentHealth -=i;
}

public void justMoved() 
{
	ticks = 0;
	ready = false;
}

public double getMaxHealth() 
{
	return (double) maxHealth;
}

public int getAttack() {
	return attack;
}
public void replace(Ability ability, int currentIndex) 
{
	abilities[currentIndex] = ability;
}
public int getMagicPower() 
{
	return magicPower;
}
public void heal(int magicPower2) 
{
	if(currentHealth + magicPower2 < maxHealth)
	{
		currentHealth +=magicPower2;
	}
	else
	{
		currentHealth = maxHealth;
	}
}
public int getMana() {
	return currentMana;
}
public void lowerMana(int manaCost) 
{
	this.currentMana-= manaCost;
}
public void restoreMana(int i) 
{
	if(currentMana+i < maxMana)
	{
	this.currentMana+=i;
	}
}
public int getSpeed() {
	return speed;
}
public int getAvailiblePoints()
{
	return availiblePoints;
}
public void levelHealth() {
maxHealth++;	
}
public void levelAttack() {
attack++;	
}
public void levelSpeed() {
speed++;	
}
public void levelMagic() {
magicPower++;	
}
public void usePoint() 
{
availiblePoints--;
}
public int getExpWorth() 
{
	int expWorth =0;
	expWorth += attack + speed + magicPower + maxHealth/2;
	return expWorth;
}
}
