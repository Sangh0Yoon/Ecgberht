package ecgberht;
import static ecgberht.Ecgberht.getGs;
import java.util.Map;
import java.util.TreeMap;
import org.openbw.bwapi4j.Player;
import org.openbw.bwapi4j.unit.PlayerUnit;
import org.openbw.bwapi4j.unit.SiegeTank;
import org.openbw.bwapi4j.unit.Unit;
public abstract class OnUnitAction {
	Unit unit;
	UnitStorage storage;
	protected Map<Unit, UnitInfo> ally = new TreeMap<>();
    protected Map<Unit, UnitInfo> enemy = new TreeMap<>();
	public OnUnitAction(Unit unit) {
		this.unit = unit;
	}
	public void setAlly(Map<Unit, UnitInfo> ally) {	//user input NULL for testcase
		this.ally = ally;
	}
	public void setEnemy(Map<Unit, UnitInfo> enemy) {	//user input NULL for testcase
		this.enemy = enemy;
	}
	abstract void action();
	protected void pushCreatedAllyUnit(Unit unit) {
		UnitInfo u = new UnitInfo((PlayerUnit) unit);
        ally.put(unit, u);
	}
	protected void pushCreatedEnemyUnit(Unit unit) {
		UnitInfo u = new UnitInfo((PlayerUnit) unit);
        enemy.put(unit, u);
	}
}
