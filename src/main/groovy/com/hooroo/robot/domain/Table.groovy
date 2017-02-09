package com.hooroo.robot.domain

import com.hooroo.robot.direction.CardinalDirection

import static com.hooroo.robot.direction.CardinalDirection.*

/**
 * Table that a {@link Robot} can move on
 */
class Table {

    static final Integer DEFAULT_X_UNITS = 5
    static final Integer DEFAULT_Y_UNITS = 5

    Integer xUnits
    Integer yUnits

    /**
     * Default constructor. Uses default dimensions {@value #DEFAULT_X_UNITS} and {@value #DEFAULT_Y_UNITS} when a {@link Table} is created.<br/>
     * Use Groovy constructor to override defaults if necessary (e.g. "new Table(yUnits: 10)" instead of "new Table()")
     */
    Table() {
        this.xUnits = DEFAULT_X_UNITS
        this.yUnits = DEFAULT_Y_UNITS
    }

    Integer getPossibleCoordinateCount() {
        return xUnits * yUnits
    }

    /**
     * Checks if coordinate is within bounds of the {@link Table}
     * @param x Position on X axis
     * @param y Position on Y axis
     * @return {@link Boolean} to indicate if coordinate is within bounds
     */
    Boolean isValidCoordinate(Integer x, Integer y) {
        return (x * y) < this.possibleCoordinateCount
    }

    /**
     * Method chain to check if a move in a {@link CardinalDirection} from a specified coordinate is possible.<br/>
     * Example use: "canMove(NORTH).from(2, 1)" - checks if moving northbound from coordinate 2,1 is possible
     * @param direction {@link CardinalDirection} Direction you intend to move in
     */
    From canMove(CardinalDirection direction) {
        return new From(direction)
    }

    private class From {
        private CardinalDirection direction

        From(CardinalDirection direction) {
            this.direction = direction
        }

        /**
         * Specify current coordinate/location.<br/>
         * Refer to {@link Table#canMove(com.hooroo.robot.direction.CardinalDirection)} for example usage
         * @param x Current position on X axis
         * @param y Current position on Y axis
         * @return If the desired location is possible to move to
         */
        Boolean from(Integer x, Integer y) {
            switch (direction) {
                case NORTH:
                    return (y + 1) < yUnits

                case SOUTH:
                    return (y - 1) > -1

                case EAST:
                    return (x + 1) < xUnits

                case WEST:
                    return (x - 1) > -1
            }
        }
    }
}
