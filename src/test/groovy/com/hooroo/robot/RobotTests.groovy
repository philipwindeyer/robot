package com.hooroo.robot

import spock.lang.Specification

import static com.hooroo.robot.direction.CardinalDirection.*
import static com.hooroo.robot.direction.RelativeDirection.*

class RobotTests extends Specification {

    Robot robot

    def setup() {
        robot = new Robot()
    }

    def "Robot should not think it is on table if not yet been placed on there"() {
        when:
        Boolean onTable = robot.onTable

        then:
        !onTable
    }

    def "Robot should not think it is on table if has not been placed on there at a specific coordinate"() {
        when:
        robot.putOn(new Table())
        Boolean onTable = robot.onTable

        then:
        !onTable
    }

    def "Robot should not think it is on table if has not been placed on there at a specific coordinate facing a specific direction"() {
        when:
        robot.putOn(new Table()).at(2, 1)
        Boolean onTable = robot.onTable

        then:
        !onTable
    }

    def "Robot should think it is on table after being placed on there correctly"() {
        when:
        robot.putOn(new Table()).at(2, 1).facing(NORTH)
        Boolean onTable = robot.onTable

        then:
        onTable
    }

    def "Robot should throw an out of bounds exception when invalid coordinate is specified"() {
        when:
        robot.putOn(new Table()).at(6, 7).facing(NORTH)
        Boolean onTable = robot.onTable

        then:
        thrown OutOfBoundsException
        !onTable
    }

    def "Robot should turn right to face east after facing north"() {
        given:
        robot.putOn(new Table()).at(2, 1).facing(NORTH)

        when:
        robot.turn(RIGHT)

        then:
        robot.facing == EAST
    }

    def "Robot should turn left to face west after facing north"() {
        given:
        robot.putOn(new Table()).at(2, 1).facing(NORTH)

        when:
        robot.turn(LEFT)

        then:
        robot.facing == WEST
    }

    def "Robot should turn right to face south after facing east"() {
        given:
        robot.putOn(new Table()).at(1, 3).facing(EAST)

        when:
        robot.turn(RIGHT)

        then:
        robot.facing == SOUTH
    }

    def "Robot should turn left to face north after facing east"() {
        given:
        robot.putOn(new Table()).at(1, 3).facing(EAST)

        when:
        robot.turn(LEFT)

        then:
        robot.facing == NORTH
    }

    def "Robot should turn right to face west after facing south"() {
        given:
        robot.putOn(new Table()).at(3, 4).facing(SOUTH)

        when:
        robot.turn(RIGHT)

        then:
        robot.facing == WEST
    }

    def "Robot should turn left to face east after facing south"() {
        given:
        robot.putOn(new Table()).at(3, 4).facing(SOUTH)

        when:
        robot.turn(LEFT)

        then:
        robot.facing == EAST
    }

    def "Robot should turn right to face north after facing west"() {
        given:
        robot.putOn(new Table()).at(4, 2).facing(WEST)

        when:
        robot.turn(RIGHT)

        then:
        robot.facing == NORTH
    }

    def "Robot should turn left to face south after facing west"() {
        given:
        robot.putOn(new Table()).at(4, 2).facing(WEST)

        when:
        robot.turn(LEFT)

        then:
        robot.facing == SOUTH
    }

    def "Robot should move north one coordinate successfully"() {
        given:
        robot.putOn(new Table()).at(2, 2).facing(NORTH)

        when:
        robot.move()

        then:
        robot.x == 2
        robot.y == 3
    }

    def "Robot should move east one coordinate successfully"() {
        given:
        robot.putOn(new Table()).at(2, 2).facing(EAST)

        when:
        robot.move()

        then:
        robot.x == 3
        robot.y == 2
    }

    def "Robot should move south one coordinate successfully"() {
        given:
        robot.putOn(new Table()).at(2, 2).facing(SOUTH)

        when:
        robot.move()

        then:
        robot.x == 2
        robot.y == 1
    }

    def "Robot should move west one coordinate successfully"() {
        given:
        robot.putOn(new Table()).at(2, 2).facing(WEST)

        when:
        robot.move()

        then:
        robot.x == 1
        robot.y == 2
    }

    def "Robot should not be able to move north off the edge of the table"() {
        given:
        robot.putOn(new Table()).at(2, 4).facing(NORTH)

        when:
        robot.move()

        then:
        thrown OutOfBoundsException
    }

    def "Robot should not be able to move east off the edge of the table"() {
        given:
        robot.putOn(new Table()).at(4, 2).facing(EAST)

        when:
        robot.move()

        then:
        thrown OutOfBoundsException
    }

    def "Robot should not be able to move south off the edge of the table"() {
        given:
        robot.putOn(new Table()).at(2, 0).facing(SOUTH)

        when:
        robot.move()

        then:
        thrown OutOfBoundsException
    }

    def "Robot should not be able to move west off the edge of the table"() {
        given:
        robot.putOn(new Table()).at(0, 2).facing(WEST)

        when:
        robot.move()

        then:
        thrown OutOfBoundsException
    }

    def "Robot should not be able to move north or east from the corner of the table"() {
        given:
        robot.putOn(new Table()).at(4, 4).facing(NORTH)

        when:
        robot.move()

        then:
        thrown OutOfBoundsException

        when:
        robot.turn(RIGHT)
        robot.move()

        then:
        thrown OutOfBoundsException
    }

    def "Robot should not be able to move east or south from the corner of the table"() {
        given:
        robot.putOn(new Table()).at(4, 0).facing(EAST)

        when:
        robot.move()

        then:
        thrown OutOfBoundsException

        when:
        robot.turn(RIGHT)
        robot.move()

        then:
        thrown OutOfBoundsException
    }

    def "Robot should not be able to move south or west from the corner of the table"() {
        given:
        robot.putOn(new Table()).at(0, 0).facing(SOUTH)

        when:
        robot.move()

        then:
        thrown OutOfBoundsException

        when:
        robot.turn(RIGHT)
        robot.move()

        then:
        thrown OutOfBoundsException
    }

    def "Robot should not be able to move west or north from the corner of the table"() {
        given:
        robot.putOn(new Table()).at(0, 4).facing(WEST)

        when:
        robot.move()

        then:
        thrown OutOfBoundsException

        when:
        robot.turn(RIGHT)
        robot.move()

        then:
        thrown OutOfBoundsException
    }

    def "Robot should throw exception if turn() is invoked without first being placed on a table"() {
        when:
        robot.move()

        then:
        thrown NotOnTableException
    }

    def "Robot should throw exception if move() is invoked without first being placed on a table"() {
        when:
        robot.turn(LEFT)

        then:
        thrown NotOnTableException
    }
}
