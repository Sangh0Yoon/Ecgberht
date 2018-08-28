package ecgberht.BehaviourTrees.Defense;

import bwem.Base;
import bwem.area.Area;
import ecgberht.EnemyBuilding;
import ecgberht.GameState;
import ecgberht.Squad;
import ecgberht.Squad.Status;
import ecgberht.Util.Util;
import org.iaie.btree.state.State;
import org.iaie.btree.task.leaf.Conditional;
import org.iaie.btree.util.GameHandler;
import org.openbw.bwapi4j.Position;
import org.openbw.bwapi4j.type.UnitType;
import org.openbw.bwapi4j.unit.*;

import java.util.*;

public class CheckPerimeter extends Conditional {

    public CheckPerimeter(String name, GameHandler gh) {
        super(name, gh);
    }

    @Override
    public State execute() {

        try {
            ((GameState) this.handler).enemyInBase.clear();
            ((GameState) this.handler).defense = false;
            Set<Unit> enemyInvaders = new TreeSet<>(((GameState) this.handler).enemyCombatUnitMemory);
            for (EnemyBuilding u : ((GameState) this.handler).enemyBuildingMemory.values()) {
                if (u.type.canAttack() || u.type == UnitType.Protoss_Pylon || u.type.canProduce() || u.type.isRefinery()) {
                    enemyInvaders.add(u.unit);
                }
            }
            for (Unit u : enemyInvaders) {
                UnitType uType = u.getType();
                if (u instanceof Building || ((uType.canAttack() || uType.isSpellcaster() || u instanceof Loadable)
                        && uType != UnitType.Zerg_Scourge && uType != UnitType.Terran_Valkyrie
                        && uType != UnitType.Protoss_Corsair && !(u instanceof Overlord))) {
                    for (Base b : ((GameState) this.handler).CCs.keySet()) {
                        Area enemyArea = ((GameState) this.handler).bwem.getMap().getArea(u.getTilePosition());
                        if (enemyArea != null && enemyArea.equals(b.getArea())) {
                            ((GameState) this.handler).enemyInBase.add(u);
                            break;
                        }
                    }
                    for (Map.Entry<SCV, Building> c : ((GameState) this.handler).workerTask.entrySet()) {
                        int dist = c.getValue() instanceof CommandCenter ? 500 : 200;
                        if (Util.broodWarDistance(u.getPosition(), c.getValue().getPosition()) <= dist) {
                            ((GameState) this.handler).enemyInBase.add(u);
                            break;
                        }
                    }
                    for (Unit c : ((GameState) this.handler).CCs.values()) {
                        if (Util.broodWarDistance(u.getPosition(), c.getPosition()) <= 500) {
                            ((GameState) this.handler).enemyInBase.add(u);
                            break;
                        }
                    }
                    for (Unit c : ((GameState) this.handler).DBs.keySet()) {
                        if (Util.broodWarDistance(u.getPosition(), c.getPosition()) <= 200) {
                            ((GameState) this.handler).enemyInBase.add(u);
                            break;
                        }
                    }
                    for (Unit c : ((GameState) this.handler).SBs) {
                        if (Util.broodWarDistance(u.getPosition(), c.getPosition()) <= 200) {
                            ((GameState) this.handler).enemyInBase.add(u);
                            break;
                        }
                    }
                    for (ResearchingFacility c : ((GameState) this.handler).UBs) {
                        if (Util.broodWarDistance(u.getPosition(), c.getPosition()) <= 200) {
                            ((GameState) this.handler).enemyInBase.add(u);
                            break;
                        }
                    }
                    if (!((GameState) this.handler).strat.name.equals("ProxyBBS")) {
                        for (Unit c : ((GameState) this.handler).MBs) {
                            if (Util.broodWarDistance(u.getPosition(), c.getPosition()) <= 200) {
                                ((GameState) this.handler).enemyInBase.add(u);
                                break;
                            }
                        }
                    }
                }
            }
            if (!((GameState) this.handler).enemyInBase.isEmpty()) {
                /*if ((((GameState) this.handler).getArmySize() >= 50 && ((GameState) this.handler).getArmySize() / ((GameState) this.handler).enemyInBase.size() > 10)) {
                    return State.FAILURE;
                }*/
                ((GameState) this.handler).defense = true;
                return State.SUCCESS;
            }
            int cFrame = ((GameState) this.handler).frameCount;
            List<Worker> toDelete = new ArrayList<>();
            for (Worker u : ((GameState) this.handler).workerDefenders.keySet()) {
                if (u.getLastCommandFrame() == cFrame) continue;
                Position closestDefense;
                if (((GameState) this.handler).EI.naughty) {
                    if (!((GameState) this.handler).DBs.isEmpty()) {
                        closestDefense = ((GameState) this.handler).DBs.keySet().iterator().next().getPosition();
                        u.move(closestDefense);
                        toDelete.add(u);
                        continue;
                    }
                }
                closestDefense = ((GameState) this.handler).getNearestCC(u.getPosition());
                if (closestDefense != null) {
                    u.move(closestDefense);
                    toDelete.add(u);
                }
            }
            for (Worker u : toDelete) {
                u.stop(false);
                ((GameState) this.handler).workerDefenders.remove(u);
                ((GameState) this.handler).workerIdle.add(u);
            }
            for (Squad u : ((GameState) this.handler).sqManager.squads.values()) {
                if (u.status == Status.DEFENSE) {
                    Position closestCC = ((GameState) this.handler).getNearestCC(u.getSquadCenter());
                    if (closestCC != null) {
                        Area squad = ((GameState) this.handler).bwem.getMap().getArea(u.getSquadCenter().toTilePosition());
                        Area regCC = ((GameState) this.handler).bwem.getMap().getArea(closestCC.toTilePosition());
                        if (squad != null && regCC != null) {
                            if (!squad.equals(regCC)) {
                                if (!((GameState) this.handler).DBs.isEmpty() && ((GameState) this.handler).CCs.size() == 1) {
                                    u.giveMoveOrder(((GameState) this.handler).DBs.keySet().iterator().next().getPosition());
                                } else {
                                    u.giveMoveOrder(Util.getClosestChokepoint(u.getSquadCenter()).getCenter().toPosition());
                                }
                                u.status = Status.IDLE;
                                u.attack = null;
                                continue;
                            }
                        }
                        u.status = Status.IDLE;
                        u.attack = null;
                        continue;
                    }
                    u.status = Status.IDLE;
                    u.attack = null;
                }
            }
            ((GameState) this.handler).defense = false;
            return State.FAILURE;
        } catch (Exception e) {
            System.err.println(this.getClass().getSimpleName());
            e.printStackTrace();
            return State.ERROR;
        }
    }
}