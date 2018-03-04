package ecgberht.Defense;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.iaie.btree.state.State;
import org.iaie.btree.task.leaf.Action;
import org.iaie.btree.util.GameHandler;

import bwapi.Pair;
import bwapi.Position;
import bwapi.Unit;
import ecgberht.GameState;
import ecgberht.Squad;
import ecgberht.Squad.Status;

public class SendDefenders extends Action {

	public SendDefenders(String name, GameHandler gh) {
		super(name, gh);
	}

	@Override
	public State execute() {
		try {
			boolean air_only = true;
			for(Unit u : ((GameState)this.handler).enemyInBase) {
				if(u.isFlying() || u.isCloaked()) {
					continue;
				}
				air_only = false;
			}
			List<Unit> friends = new ArrayList<Unit>();
			for (Squad s : ((GameState)this.handler).squads.values())
			{
				for(Unit u : s.members) {
					friends.add(u);
				}
			}
//			if(!((GameState)this.handler).DBs.isEmpty()){
//				for(Pair<Unit, List<Unit>> u : ((GameState)this.handler).DBs) {
//					friends.add(u.first);
//					for(Unit m : u.second) {
//						friends.add(m);
//					}
//				}
//			}
			
//			boolean battleWin = ((GameState)this.handler).simulateBattle(friends, ((GameState)this.handler).enemyInBase);
			if(!air_only && ((GameState)this.handler).squads.isEmpty()) {
				while(((GameState)this.handler).workerDefenders.size() < 2 && !((GameState)this.handler).workerIdle.isEmpty()) {
					Unit closestWorker = null;
					Position chosen = ((GameState)this.handler).attackPosition;
					for (Unit u : ((GameState)this.handler).workerIdle) {
						if ((closestWorker == null || u.getDistance(chosen) < closestWorker.getDistance(chosen))) {
							closestWorker = u;
						}
					}
					if(closestWorker != null) {
						((GameState)this.handler).workerDefenders.add(new Pair<Unit, Position>(closestWorker,null));
						((GameState)this.handler).workerIdle.remove(closestWorker);
					}
				}
				int defenders = 2;
				if(((GameState)this.handler).enemyInBase.size() == 1 && ((GameState)this.handler).enemyInBase.iterator().next().getType().isWorker()) {
					defenders = 1;
				}
				while(((GameState)this.handler).workerDefenders.size() < defenders && !((GameState)this.handler).workerMining.isEmpty()) {
					Unit closestWorker = null;
					Position chosen = ((GameState)this.handler).attackPosition;
					for (Entry<Unit, Unit> u : ((GameState)this.handler).workerMining.entrySet()) {
						if ((closestWorker == null || u.getKey().getDistance(chosen) < closestWorker.getDistance(chosen))) {
							closestWorker = u.getKey();
						}
					}
					if(closestWorker != null) {
						if(((GameState)this.handler).workerMining.containsKey(closestWorker)) {
							Unit mineral = ((GameState)this.handler).workerMining.get(closestWorker);
							((GameState)this.handler).workerDefenders.add(new Pair<Unit, Position>(closestWorker,null));
							if(((GameState)this.handler).mineralsAssigned.containsKey(mineral)) {
								((GameState)this.handler).mining--;
								((GameState)this.handler).mineralsAssigned.put(mineral, ((GameState)this.handler).mineralsAssigned.get(mineral) - 1);
							}
							((GameState)this.handler).workerMining.remove(closestWorker);
						}
					}
				}
				for(Pair<Unit,Position> u: ((GameState)this.handler).workerDefenders) {
					if(((GameState)this.handler).attackPosition != null) {
						if(u.first.isIdle() || !((GameState)this.handler).attackPosition.equals(u.second)) {
							((GameState)this.handler).workerDefenders.get(((GameState)this.handler).workerDefenders.indexOf(u)).second = ((GameState)this.handler).attackPosition;
							if(((GameState)this.handler).enemyInBase.size() == 1) {
								u.first.attack(((GameState)this.handler).enemyInBase.iterator().next());
							}
							else {
								u.first.attack(((GameState)this.handler).attackPosition);
							}
							
						}
					}
				}
			} else {
				if(((GameState)this.handler).strat.name != "ProxyBBS") {
					for(Entry<String,Squad> u :((GameState)this.handler).squads.entrySet()) {
						if(((GameState)this.handler).attackPosition != null) {
							//if(u.getValue().estado == Status.IDLE || !((GameState)this.handler).attackPosition.equals(u.getValue().attack)) {
								u.getValue().giveAttackOrder(((GameState)this.handler).attackPosition);
								u.getValue().status = Status.DEFENSE;
							//}
						}
						else {
							u.getValue().status = Status.IDLE;
						}
					}
				}
			}
			return State.FAILURE;
		} catch(Exception e) {
			System.err.println(this.getClass().getSimpleName());
			System.err.println(e);
			return State.ERROR;
		}
	}
}
