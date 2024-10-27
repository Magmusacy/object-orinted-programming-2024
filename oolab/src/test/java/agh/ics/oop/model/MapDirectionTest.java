package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void northNextReturnsEast() {
        // given
        MapDirection north = MapDirection.NORTH;

        // when
        MapDirection result = north.next();

        // then
        assertEquals(MapDirection.EAST, result);
    }

    @Test
    void eastNextReturnsSouth() {
        // given
        MapDirection east = MapDirection.EAST;

        // when
        MapDirection result = east.next();

        // then
        assertEquals(MapDirection.SOUTH, result);
    }

    @Test
    void southNextReturnsWest() {
        // given
        MapDirection south = MapDirection.SOUTH;

        // when
        MapDirection result = south.next();

        // then
        assertEquals(MapDirection.WEST, result);
    }

    @Test
    void westNextReturnsNorth() {
        // given
        MapDirection west = MapDirection.WEST;

        // when
        MapDirection result = west.next();

        // then
        assertEquals(MapDirection.NORTH, result);
    }

    @Test
    void westPreviousReturnsSouth() {
        // given
        MapDirection west = MapDirection.WEST;

        // when
        MapDirection result = west.previous();

        // then
        assertEquals(MapDirection.SOUTH, result);
    }

    @Test
    void southPreviousReturnsEast() {
        // given
        MapDirection south = MapDirection.SOUTH;

        // when
        MapDirection result = south.previous();

        // then
        assertEquals(MapDirection.EAST, result);
    }

    @Test
    void eastPreviousReturnsNorth() {
        // given
        MapDirection east = MapDirection.EAST;

        // when
        MapDirection result = east.previous();

        // then
        assertEquals(MapDirection.NORTH, result);
    }

    @Test
    void northPreviousReturnsWest() {
        // given
        MapDirection north = MapDirection.NORTH;

        // when
        MapDirection result = north.previous();

        // then
        assertEquals(MapDirection.WEST, result);
    }
}