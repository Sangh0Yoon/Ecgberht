package ecgberht;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openbw.bwapi4j.BW;

import ecgberht.Clustering.Cluster;
import ecgberht.Simulation.SimInfo;
import ecgberht.Simulation.SimulationTheory;

public class SimulationTheoryTest {
    BW objBW = new BW(null);

    @Test
    void testRunSimulationOnFrame(){
        SimulationTheory simulationTheory = new SimulationTheory(objBW);

        simulationTheory.runSimulationOnFrame();

        Assertions.assertTrue(simulationTheory.time >= 0);

        Assertions.assertNotNull(simulationTheory.friendly);

        Assertions.assertNotNull(simulationTheory.enemies);

        Assertions.assertNotNull(simulationTheory.simulations);
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
