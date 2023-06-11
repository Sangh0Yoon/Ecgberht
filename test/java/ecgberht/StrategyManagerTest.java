package ecgberht;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecgberht.Strategies.*;
/*
Purpose: Test whether strategy manager with singleton pattern and other
methods in StrategyManager works well

Input: StrategyManager instance, gamestate variable, SubStrategies

Expected: The instance received by getInstance is always the same.

Verify that getUcbVal produces the correct output.

Verify that getSubStrategy returns the matching strategy.
*/
class StrategyManagerTest {
	StrategyManager manager = StrategyManager.getInstance();
	
	@Test
	void getInstanceTest() {
		StrategyManager manager1 = StrategyManager.getInstance();
		StrategyManager manager2 = StrategyManager.getInstance();
		assertNotNull(manager1);
		assertNotNull(manager2);
		assertSame(manager1,manager2); 
	}
	@Test
	void getSubStrategyTest() {
		 Strategy fullBio = new FullBio
	    		.FullBioBuilder("FullBio",15,10,2,3,0,2,4,36,32,15,2,2,0,0,0,false)
	    		.build();
	     Strategy proxyBBS = new ProxyBBS
	    		.ProxyBBSBuilder("ProxyBBS",0,0,0,2,0,0,0,400,8,100,0,0,0,0,0,false)
	    		.setProxy(true)
	    		.build();
	     Strategy bioMech = new BioMech
	    		.BioMechBuilder("BioMech",24,10,1,2,1,2,1,2,2,0,36,30,18,2,1,false)
	    		.build();
	     Strategy fullBioFE = new FullBioFE
	    		.FullBioFEBuilder("FullBioFE",15,10,2,3,0,2,4,36,28,8,2,2,0,0,0,false)
	    		.build();
	     Strategy bioMechFE = new BioMechFE
	    		.BioMechFEBuilder("BioMechFE",15,10,1,3,1,2,1,38,25,9,2,2,0,2,1,false)
	    		.build();
	     Strategy fullMech = new FullMech
	    		.FullMechBuilder("FullMech",15,10,1,1,2,1,1,28,30,8,1,2,1,2,1,false)
	    		.build();
	     Strategy bioGreedyFE = new BioGreedyFE
	    		.BioGreedyFEBuilder("BioGreedyFE", 15, 10, 2, 3, 0, 2, 3, 38, 25, 0, 2, 2, 0, 0, 0, true)
	    		.build();
	     Strategy mechGreedyFE = new MechGreedyFE
	    		.MechGreedyFEBuilder("MechGreedyFE",15,10,1,1,2,2,1,36,30,0,2,2,1,2,1,true).
	    		build();
	     Strategy bioMechGreedyFE = new BioMechGreedyFE
	    		.BioMechGreedyFEBuilder("BioMechGreedyFE",15,10,1,2,1,2,2,38,30,0,3,3,0,2,1,true)
	    		.build();
	     Strategy twoPortWraith = new TwoPortWraith
	    		.TwoPortWraithBuilder("TwoPortWraith",15,10,1,1,1,1,1,26,25,14,1,2,2,2,1,false)
	    		.setExtraSCVs(1)
	    		.setWorkerGas(3)
	    		.setHarass(false)
	    		.build();
	     Strategy proxyEightRax = new ProxyEightRax
	    		.ProxyEightRaxBuilder("ProxyEightRax",0,0,0,1,0,0,0,400,7,100,0,0,0,0,0,false)
	    		.setProxy(true)
	    		.build();
	     Strategy vultureRush = new VultureRush
	    		.VultureRushBuilder("VultureRush",15,10,1,1,3,1,1,24,5,14,1,2,1,2,1,false)
	    		.setNumFacForPort(2)
	    		.setWorkerGas(3)
	    		.build();
	     Strategy theNiteKat = new TheNitekat
	    		.TheNitekatBuilder("TheNitekat",30,30,0,1,2,3,1,26,5,25,2,2,0,3,1,false)
	    		.setExtraSCVs(1)
	    		.build();
	     Strategy joyORush = new JoyORush
	    		.JoyORushBuilder("JoyORush",30,30,0,1,2,3,1,26,5,25,2,2,0,3,1,false)
	    		.setExtraSCVs(1)
	    		.build();
	     Strategy fastCC = new FastCC
	    		.FastCCBuilder("14CC",15,10,1,2,1,2,2,38,30,0,3,3,0,2,1,true)
	    		.build();
	     
	     assertEquals(fullBio,manager.getSubStrategy("FullBio"));
	     assertEquals(proxyBBS,manager.getSubStrategy("ProxyBBS"));
	     assertEquals(bioMech,manager.getSubStrategy("BioMech"));
	     assertEquals(fullBioFE,manager.getSubStrategy("FullBioFE"));
	     assertEquals(bioMechFE,manager.getSubStrategy("BioMechFE"));
	     assertEquals(mechGreedyFE,manager.getSubStrategy("MechGreedyFE"));
	     assertEquals(twoPortWraith,manager.getSubStrategy("TwoPortWraith"));
	     assertEquals(proxyEightRax,manager.getSubStrategy("ProxyEightRax"));
	     assertEquals(vultureRush,manager.getSubStrategy("VultureRush"));
	     assertEquals(theNiteKat,manager.getSubStrategy("TheNitekat"));
	     assertEquals(joyORush,manager.getSubStrategy("JoyORush"));
	     assertEquals(fastCC,manager.getSubStrategy("14CC"));
	}
	

}
