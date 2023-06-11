package ecgberht;

import org.openbw.bwapi4j.type.Race;
import org.openbw.bwapi4j.unit.Unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * 
 * Purpose: Verify normal behavior of member functions in SupplyMan Class
 * 
 * 
 * Input: Enum Class Race, final int condition variable
 * 
 * 
 * Expected: Expect a correct output of the supply
 * 
 */

class SupplyManTest {
	Race terran =Race.Terran;
	Race zerg =Race.Zerg;
	Race protoss =Race.Protoss;
	SupplyMan terranSupply;
	SupplyMan zergSupply;
	SupplyMan protossSupply;
	
	final int terranSupplyTotal = 20;
	final int otherSupplyTotal = 18;
	final int defaultSupplyUsed =8;
	
	@Test
	void getSupplyLeftTest() {
		terranSupply = new SupplyMan(terran);
		zergSupply = new SupplyMan(zerg);
		protossSupply = new SupplyMan(protoss);
		
		final int terranExpectedLeftOver = 12;
		final int otherExpectedLeftOver = 10;
		
		assertSame(terranSupply.getSupplyLeft(),terranExpectedLeftOver);
		assertSame(zergSupply.getSupplyLeft(),otherExpectedLeftOver);
		assertSame(protossSupply.getSupplyLeft(),otherExpectedLeftOver);
	}
	
	@Test
	void getSupplyUsedTest() {
		terranSupply = new SupplyMan(terran);
		zergSupply = new SupplyMan(zerg);
		protossSupply = new SupplyMan(protoss);
		
		assertSame(terranSupply.getSupplyUsed(),defaultSupplyUsed);
		assertSame(zergSupply.getSupplyUsed(),defaultSupplyUsed);
		assertSame(protossSupply.getSupplyUsed(),defaultSupplyUsed);
		
	}
	
	@Test
	void getSupplyTotalTest() {
		terranSupply = new SupplyMan(terran);
		zergSupply = new SupplyMan(zerg);
		protossSupply = new SupplyMan(protoss);
		
		assertSame(terranSupply.getSupplyTotal(),terranSupplyTotal);
		assertSame(zergSupply.getSupplyTotal(),otherSupplyTotal);
		assertSame(protossSupply.getSupplyTotal(),otherSupplyTotal);
	}

}
