package ecgberht;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Purpose: Check if the builder pattern works well
 * 			
 * Input: SubStrategies which extends a Parent Class Strategy
 * 
 * Expected: Instances are allocated well.
 * 			 The variables of the concrete strategy builder constructor apply well. (test with name)
 * 			 Check the settings of additional variables. (test with gas)
 * 
 * 
 */

import org.junit.jupiter.api.Test;

import ecgberht.Strategies.BioGreedyFE;
import ecgberht.Strategies.BioMech;
import ecgberht.Strategies.BioMechFE;
import ecgberht.Strategies.BioMechGreedyFE;
import ecgberht.Strategies.FastCC;
import ecgberht.Strategies.FullBio;
import ecgberht.Strategies.FullBioFE;
import ecgberht.Strategies.FullMech;
import ecgberht.Strategies.JoyORush;
import ecgberht.Strategies.MechGreedyFE;
import ecgberht.Strategies.ProxyBBS;
import ecgberht.Strategies.ProxyEightRax;
import ecgberht.Strategies.TheNitekat;
import ecgberht.Strategies.TwoPortWraith;
import ecgberht.Strategies.VultureRush;

class StrategyTest {
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
	@Test
	void builderPatternConstructorTest() {
		assertNotNull(fullBio);
		assertNotNull(proxyBBS);
		assertNotNull(bioMech);
		assertNotNull(fullBioFE);
		assertNotNull(bioMechFE);
		assertNotNull(bioGreedyFE);
		assertNotNull(mechGreedyFE);
		assertNotNull(bioMechGreedyFE);
		assertNotNull(twoPortWraith);
		assertNotNull(proxyEightRax);
		assertNotNull(vultureRush);
		assertNotNull(theNiteKat);
		assertNotNull(joyORush);
		assertNotNull(fastCC);
	}

	@Test
	void builderPatternNameTest() {
	     assertEquals(fullBio.getName(),"FullBio");
	     assertEquals(proxyBBS.getName(),"ProxyBBS");
	     assertEquals(bioMech.getName(),"BioMech");
	     assertEquals(fullBioFE.getName(),"FullBioFE");
	     assertEquals(bioMechFE.getName(),"BioMechFE");
	     assertEquals(fullMech.getName(),"FullMech");
	     assertEquals(bioGreedyFE.getName(),"BioGreedyFE");
	     assertEquals(mechGreedyFE.getName(),"MechGreedyFE");
	     assertEquals(bioMechGreedyFE.getName(),"BioMechGreedyFE");
	     assertEquals(twoPortWraith.getName(),"TwoPortWraith");
	     assertEquals(proxyEightRax.getName(),"ProxyEightRax");
	     assertEquals(vultureRush.getName(),"VultureRush");
	     assertEquals(theNiteKat.getName(),"TheNitekat");
	     assertEquals(joyORush.getName(),"JoyORush");
	     assertEquals(fastCC.getName(),"14CC");
	}
	@Test
	void additionalElementGasTest() {
		assertTrue(twoPortWraith.getWorkerGas() == 3);
		assertTrue(vultureRush.getWorkerGas() == 3);
	}
	
}
