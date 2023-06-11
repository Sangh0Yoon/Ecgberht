package ecgberht;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openbw.bwapi4j.Position;
import org.openbw.bwapi4j.TilePosition;
import org.openbw.bwapi4j.WalkPosition;
import org.openbw.bwapi4j.type.Order;
import org.openbw.bwapi4j.type.UnitType;
import org.openbw.bwapi4j.unit.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UnitInfoUpdateTest {

    private UnitInfo unitInfo;

    @BeforeEach
    void setUp() {
        PlayerUnit unit = mock(PlayerUnit.class);
        when(unit.isVisible()).thenReturn(true);
        when(unit.getHitPoints()).thenReturn(50);
        when(unit.getShields()).thenReturn(25);
        when(unit.isCompleted()).thenReturn(true);
        UnitType unitType = UnitType.Zerg_Overlord;

        when(unit.getType()).thenReturn(unitType);

        unitInfo = new UnitInfo(unit);
        unitInfo.lastVisibleFrame = 100;
    }

    @Test
    void testUpdatePlayer() {
        UnitInfoUpdate.updatePlayer(unitInfo);
        assertEquals(unitInfo.unit.getPlayer(), unitInfo.player);
    }

    @Test
    void testUpdateUnitType() {
        UnitInfoUpdate.updateUnitType(unitInfo);
        assertEquals(unitInfo.unit.getType(), unitInfo.unitType);
    }

    @Test
    void testUpdateVisibility() {
        UnitInfoUpdate.updateVisibility(unitInfo);
        assertTrue(unitInfo.visible);
        assertTrue(unitInfo.unit.isVisible());
    }

    @Test
    void testUpdatePosition() {
        Position position = new Position(10, 10);
        when(unitInfo.unit.getPosition()).thenReturn(position);

        UnitInfoUpdate.updatePosition(unitInfo);

        assertNotNull(unitInfo.position);
        assertEquals(position, unitInfo.position);
    }

    @Test
    void testUpdateOrder() {
        Order order = Order.AttackMove;
        when(unitInfo.unit.getOrder()).thenReturn(order);

        UnitInfoUpdate.updateOrder(unitInfo);

        assertNotNull(unitInfo.currentOrder);
        assertEquals(order, unitInfo.currentOrder);
    }

    @Test
    void testUpdateCompletion() {
        UnitInfoUpdate.updateCompletion(unitInfo);

        assertTrue(unitInfo.completed);
        assertTrue(unitInfo.unit.isCompleted());
    }

    @Test
    void testUpdateTilePosition() {
        TilePosition tilePosition = new TilePosition(10, 10);
        when(unitInfo.unit.getTilePosition()).thenReturn(tilePosition);

        UnitInfoUpdate.updateTilePosition(unitInfo);

        assertNotNull(unitInfo.tileposition);
        assertEquals(tilePosition, unitInfo.tileposition);
    }

    @Test
    void testUpdateWalkPosition_NonBuildingUnit() {
        Position position = new Position(20, 20);
        when(unitInfo.unit.getLeft()).thenReturn(20);
        when(unitInfo.unit.getTop()).thenReturn(20);


        UnitInfoUpdate.updateWalkPosition(unitInfo);

        assertNotNull(unitInfo.walkposition);
        assertEquals(position.toWalkPosition(), unitInfo.walkposition);
    }


    @Test
    void testUpdateLastPositions_VisibleUnit() {
        unitInfo.visible = true;
        unitInfo.position = new Position(20, 20);
        unitInfo.tileposition = new TilePosition(20, 20);
        unitInfo.walkposition = new WalkPosition(20, 20);

        UnitInfoUpdate.updateLastPositions(unitInfo);

        assertEquals(unitInfo.position, unitInfo.lastPosition);
        assertEquals(unitInfo.tileposition, unitInfo.lastTileposition);
        assertEquals(unitInfo.walkposition, unitInfo.lastWalkposition);
    }

    @Test
    void testUpdateLastPositions_InvisibleUnit() {
        unitInfo.visible = false;
        unitInfo.position = new Position(20, 20);
        unitInfo.tileposition = new TilePosition(20, 20);
        unitInfo.walkposition = new WalkPosition(20, 20);

        UnitInfoUpdate.updateLastPositions(unitInfo);

        assertNull(unitInfo.lastPosition);
        assertNull(unitInfo.lastTileposition);
        assertNull(unitInfo.lastWalkposition);
    }


}
