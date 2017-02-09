package com.hooroo.robot

import com.hooroo.robot.direction.CardinalDirection
import com.hooroo.robot.direction.RelativeDirection

import static com.hooroo.robot.direction.CardinalDirection.*
import static com.hooroo.robot.direction.RelativeDirection.*

/**
 * A Robot that moves one coordinate at a time,
 * in a specific {@link CardinalDirection} on a {@link Table}
 */
class Robot {

    private Table table

    Integer x
    Integer y
    CardinalDirection facing

    /**
     * Determines if one has been placed on a table yet
     * @return Boolean indicated if robot is on table
     */
    Boolean getOnTable() {
        println("${table} - ${x},${y},${facing}")

        if (!table) {
            return false
        }

        if (x == null) {
            return false
        }

        if (y == null) {
            return false
        }

        if (!facing) {
            return false
        }

        return true
    }

    /**
     * Method chain to put robot on a table, at a specified coordinate, facing a specified direction<br/>
     * Example use: "putOn(new Table()).at(2, 1).facing(NORTH)" - places robot at coordinate 2,1 facing north on the table
     * @param table The {@link Table} to put the robot on
     */
    At putOn(Table table) {
        this.table = table
        return new At()
    }

    /**
     * Turns the robot left or right to face a {@link CardinalDirection cardinal direction}
     * @param direction {@link RelativeDirection} to turn
     */
    void turn(RelativeDirection direction) {
        if (!this.onTable) {
            throw new NotOnTableException()
        }

        switch (facing) {
            case NORTH:
                direction == LEFT ?
                        (facing = WEST) : (facing = EAST)
                break

            case SOUTH:
                direction == LEFT ?
                        (facing = EAST) : (facing = WEST)
                break

            case EAST:
                direction == LEFT ?
                        (facing = NORTH) : (facing = SOUTH)
                break

            case WEST:
                direction == LEFT ?
                        (facing = SOUTH) : (facing = NORTH)
        }
    }

    /**
     * Moves the robot forward by one coordinate, if possible
     * @throws OutOfBoundsException When destination coordinate is not reachable or out of bounds
     */
    void move() throws OutOfBoundsException {
        if (!this.onTable) {
            throw new NotOnTableException()
        }

        if (!table.canMove(facing).from(x, y)) {
            throw new OutOfBoundsException()
        }

        switch (facing) {
            case NORTH:
                y++
                break

            case SOUTH:
                y--
                break

            case EAST:
                x++
                break

            case WEST:
                x--
        }
    }

    String getOutput() {
        return "${x},${y},${facing}"
    }

    private class At {

        /**
         * Specify the coordinate to place the robot at.<br/>
         * Refer to {@link Robot#putOn(com.hooroo.robot.Table)} for example usage
         * @param x Position on X axis
         * @param y Position on Y axis
         * @throws OutOfBoundsException When desired starting coordinate is out of bounds
         */
        Facing at(Integer x, Integer y) throws OutOfBoundsException {

            if (!Robot.this.table.isValidCoordinate(x, y)) {
                throw new OutOfBoundsException()
            }

            Robot.this.x = x
            Robot.this.y = y

            return new Facing()
        }
    }

    private class Facing {

        /**
         * Specify the initial directio the robot is to face.<br/>
         * Refer to {@link Robot#putOn(com.hooroo.robot.Table)} for example usage
         * @param facing {@link CardinalDirection direction} the robot is to face
         */
        void facing(CardinalDirection facing) {
            Robot.this.facing = facing
        }
    }
}
