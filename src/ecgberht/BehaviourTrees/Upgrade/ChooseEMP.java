package ecgberht.BehaviourTrees.Upgrade;

import ecgberht.GameState;
import org.iaie.btree.state.State;
import org.iaie.btree.task.leaf.Action;
import org.iaie.btree.util.GameHandler;
import org.openbw.bwapi4j.type.Race;
import org.openbw.bwapi4j.type.TechType;
import org.openbw.bwapi4j.unit.ResearchingFacility;
import org.openbw.bwapi4j.unit.ScienceFacility;

public class ChooseEMP extends Action {

    public ChooseEMP(String name, GameHandler gh) {
        super(name, gh);
    }

    @Override
    public State execute() {
        try {
            if (((GameState) this.handler).enemyRace != Race.Protoss) return State.FAILURE;
            boolean found = false;
            ScienceFacility chosen = null;
            for (ResearchingFacility r : ((GameState) this.handler).UBs) {
                if (r instanceof ScienceFacility && !r.isResearching()) {
                    found = true;
                    chosen = (ScienceFacility) r;
                    break;
                }
            }
            if (!found) return State.FAILURE;
            if (!((GameState) this.handler).getPlayer().isResearching(TechType.EMP_Shockwave) &&
                    !((GameState) this.handler).getPlayer().hasResearched(TechType.EMP_Shockwave)) {
                ((GameState) this.handler).chosenUnitUpgrader = chosen;
                ((GameState) this.handler).chosenResearch = TechType.EMP_Shockwave;
                return State.SUCCESS;
            }
            return State.FAILURE;
        } catch (Exception e) {
            System.err.println(this.getClass().getSimpleName());
            e.printStackTrace();
            return State.ERROR;
        }
    }
}
