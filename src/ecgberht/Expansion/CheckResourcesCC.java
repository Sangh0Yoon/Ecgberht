package ecgberht.Expansion;

import org.iaie.btree.state.State;
import org.iaie.btree.task.leaf.Conditional;
import org.iaie.btree.util.GameHandler;
import ecgberht.GameState;

import bwapi.Pair;
import bwapi.UnitType;

public class CheckResourcesCC extends Conditional{

	public CheckResourcesCC(String name, GameHandler gh) {
		super(name, gh);
	}

	@Override
	public State execute() {
		try {
			if(((GameState)this.handler).movingToExpand == true) {
				return State.SUCCESS;
			}
			if(((GameState)this.handler).expanding == true) {
				Pair<Integer,Integer> cash = ((GameState)this.handler).getCash();
				if(cash.first >= (UnitType.Terran_Command_Center.mineralPrice()) && cash.second >= (UnitType.Terran_Command_Center.gasPrice())) {
					return State.SUCCESS;
				}
			}
			else {
				((GameState)this.handler).expanding = true;
				((GameState)this.handler).deltaCash.first += UnitType.Terran_Command_Center.mineralPrice();
				((GameState)this.handler).deltaCash.second += UnitType.Terran_Command_Center.gasPrice();
				return State.FAILURE;
			}
			return State.FAILURE;
		} catch(Exception e) {
			System.err.println(this.getClass().getSimpleName());
			System.err.println(e);
			return State.ERROR;
		}
	}

}
