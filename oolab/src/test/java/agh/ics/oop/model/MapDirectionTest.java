package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void northNextReturnsEast() {
        // given
        MapDirection north = MapDirection.NORTH;

        // then
        assertEquals(MapDirection.EAST, north.next());
    }

    @Test
    void eastNextReturnsSouth() {
        // given
        MapDirection east = MapDirection.EAST;

        // then
        assertEquals(MapDirection.SOUTH, east.next());
    }

    @Test
    void southNextReturnsWest() {
        // given
        MapDirection south = MapDirection.SOUTH;

        // then
        assertEquals(MapDirection.WEST, south.next());
    }

    @Test
    void westNextReturnsNorth() {
        // given
        MapDirection west = MapDirection.WEST;

        // then
        assertEquals(MapDirection.NORTH, west.next());
    }

    @Test
    void westPreviousReturnsSouth() {
        // given
        MapDirection west = MapDirection.WEST;

        // then
        assertEquals(MapDirection.SOUTH, west.previous());
    }

    @Test
    void southPreviousReturnsEast() {
        // given
        MapDirection south = MapDirection.SOUTH;

        // then
        assertEquals(MapDirection.EAST, south.previous());
    }

    @Test
    void eastPreviousReturnsNorth() {
        // given
        MapDirection east = MapDirection.EAST;

        // then
        assertEquals(MapDirection.NORTH, east.previous());
    }

    @Test
    void northPreviousReturnsWest() {
        // given
        MapDirection north = MapDirection.NORTH;

        // then
        assertEquals(MapDirection.WEST, north.previous());
    }
}