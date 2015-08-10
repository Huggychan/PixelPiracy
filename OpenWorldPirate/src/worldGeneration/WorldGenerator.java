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
		for(int i = 0; i < world.length; i++)
		{
			for(int j = 0; j < world[i].length; j++)
			{
				switch(r.nextInt(200))
				{
				//numbers to add a enemy crew
				case 0:
					Crew temp = new Crew(i*64, j*64, ops, true, i+j);
					if(world[i][j].isPassable())
					{
					enemies.add(temp);
					}
					break;
				case 1:
					Crew tempC = new Crew(i*64, j*64, ops, true, i+j);
					if(world[i][j].isPassable())
					{
					enemies.add(tempC);
					}
					break;
					
					// add a town
				case 2:
					if(world[i][j].isPassable())
					{
					enemies.add(new Town(i,j, ops,true));
					}
					break;
					
					//add a chest
				case 3:
					if(world[i][j].isPassable())
					{
					enemies.add(new TreasureChest(i*64,j*64,ops));
					}
					break;

				}
			}
		}
		return enemies;
	}

}
