package ecgberht;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.openbw.bwapi4j.BW;
import org.openbw.bwapi4j.BWEventListener;

import ecgberht.Clustering.Cluster;
import ecgberht.Simulation.SimInfo;
import ecgberht.Simulation.SimulationTheory;

public class SimulationTheoryTest {
    private BWEventListener BWEventListener;
	BW objBW = new BW(BWEventListener);

    @Test
    void testRunSimulationOnFrame(){
        SimulationTheory simulationTheory = new SimulationTheory(objBW);

        simulationTheory.runSimulationOnFrame();

        assertTrue(simulationTheory.time >= 0);

        assertNotNull(simulationTheory.friendly);

        assertNotNull(simulationTheory.enemies);

        assertNotNull(simulationTheory.simulations);
    }

    @Test
    void runSimulationOnFrame_NoNeedForSim_CorrectResult(){
        SimulationTheory simulationTheory = new SimulationTheory(objBW);

        simulationTheory.friendly.add(new Cluster());
        simulationTheory.enemies.add(new Cluster());
        simulationTheory.simulations.add(new SimInfo());

        simulationTheory.runSimulationOnFrame();

        assertTrue(simulationTheory.noNeedForSim());
    }
}
