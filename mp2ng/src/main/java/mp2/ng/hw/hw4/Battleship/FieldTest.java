package mp2.ng.hw.hw4.Battleship;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

import javax.management.RuntimeErrorException;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {
	Field field = new Field();

	@Before
	public void setUp() throws Exception {


	}

	@Test
	public void testPopulate() {
		for(int i = 0; i < 1000; i++){
			field = new Field();
		}
	}

	@Test
	public void testPlaceShip() {
		//System.out.println("testPlaceShip");
		field.placeShip(1);
		//System.out.println("the 1st ship");
//		System.out.println("field.placeShip(1) " + field);
		assertEquals(1, field.countShipedCells());
		//System.out.println(field);
		for (int i = 0; i < 5; i++) {
			field.placeShip(1);
		}
		assertEquals(6, field.countShipedCells());

		
	
		for (int i = 0; i < 1; i++) {
			field.placeShip(4);

		assertEquals(10, field.countShipedCells());
		assertEquals(true, field.checkNeighbourhood());
		}
	}

	@Test
	public void testTryAddBrick() {

		int shipSize = 4;
		Deque<Cell> probableShip = new ArrayDeque<>();
		Ship ship = new Ship(shipSize);

		NextPosition:
			for(int trials = 0; trials < 15; trials++){

				probableShip.forEach((e) -> e.removeShip());
				probableShip.clear();

				for(int bricksPlaced = 0; bricksPlaced < shipSize; ){
					if (field.tryAddBrick(ship)){
						bricksPlaced++;

					}
					else 
						continue NextPosition;
				}// if all bricks have been placed then enough trials
				break NextPosition; 
			}						
		if(probableShip.size() < shipSize){
			probableShip.forEach((e) -> e.removeShip());
			return;
			//throw new RuntimeErrorException(new Error("Ship wasn't placed"));
		}
		assertEquals(shipSize, probableShip.size());
		probableShip.forEach((e) -> e.setShip(new Ship(4)));

		assertEquals(shipSize, ship.getSize());
	}

}
