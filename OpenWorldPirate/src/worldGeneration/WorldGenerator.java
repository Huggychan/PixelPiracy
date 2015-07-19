package worldGeneration;

import java.util.ArrayList;
import java.util.Random;

import entities.Crew;
import entities.Entity;
import entities.Town;
import entities.TreasureChest;
import gameStates.OpenSeasGameState;
import worldStructure.BlankIslandSquare;
import worldStructure.BlankOceanSquare;
import worldStructure.Square;

public class WorldGenerator 
{

	public static Square[][] createWorld() 
	{
		Square[][] world = new Square[100][100];
		Random r = new Random();
		for(int i = 0; i < world.length; i++)
		{
			for(int j = 0; j < world[i].length; j++)
			{
				switch(r.nextInt(20))
				{
				case 0:
					world[i][j] = new BlankIslandSquare();
					break;
				default:
					world[i][j] = new BlankOceanSquare();
				break;
				}
			}
		}
		world[0][0]= new BlankOceanSquare();
		return world;
	}

	public static ArrayList<Entity> createEntities(Square[][] world, OpenSeasGameState ops) 
	{
		ArrayList<Entity> enemies = new ArrayList<Entity>();
		Random r = new Random();
		while(r.nextInt(100) != 0)
		{
			Crew temp = new Crew(r.nextInt(world.length*64), r.nextInt(world[0].length * 64), ops, true);
			enemies.add(new TreasureChest(ops));
			if(world[temp.getX()/64][temp.getY()/64].isPassable())
			{
			enemies.add(temp);
			}
	
		}
		enemies.add(new Town(10, 10, ops, false));
		return enemies;
	}

}
