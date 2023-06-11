package ecgberht;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openbw.bwapi4j.unit.Unit;

class OnUnitTest {
	/*
	 * Purpose: Verify that OnUnitCreate with template method patterns is applied well.
	 * 	
	 * Input:  OnUnitCreate and Child classes with OnUnitAction as parents
	 * 
	 * Expected: all Instances are not equal to each other.
	 * 
	 */
	@Test
	void polymorphismTest() {
		Unit unit = null;
		OnUnitAction parent = new OnUnitCreate(unit);
		
		OnUnitAction complete = new OnUnitComplete(unit);
		OnUnitAction destroy = new OnUnitDestroy(unit);
		OnUnitAction morph = new OnUnitMorph(unit);
		OnUnitAction show = new OnUnitShow(unit);
		
		assertNotEquals(parent, complete);
		assertNotEquals(parent, destroy);
		assertNotEquals(parent, morph);
		assertNotEquals(parent, show);
		
	}

}
