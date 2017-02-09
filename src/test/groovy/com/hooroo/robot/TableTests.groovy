package com.hooroo.robot

import com.hooroo.robot.domain.Table
import spock.lang.Specification

import static com.hooroo.robot.direction.CardinalDirection.*

class TableTests extends Specification {

    Table table

    def setup() {
        table = new Table()
    }

    def "Table should have 25 (5^2) possible coordinates"() {
        when:
        Integer possibleCoordinateCount = table.possibleCoordinateCount

        then:
        possibleCoordinateCount == 25
    }

    def "Table should have 30 (5*6) possible coordinates when 6 Y units used"() {
        given:
        table = new Table(yUnits: 6)

        when:
        Integer possibleCoordinateCount = table.possibleCoordinateCount

        then:
        possibleCoordinateCount == 30
    }

    def "Table should have 36 (6^2) possible coordinates when 6 X and Y units used"() {
        given:
        table = new Table(xUnits: 6, yUnits: 6)

        when:
        Integer possibleCoordinateCount = table.possibleCoordinateCount

        then:
        possibleCoordinateCount == 36
    }

    def "Table should have 35 (7*5) possible coordinates when 7 X units used"() {
        given:
        table = new Table(xUnits: 7)

        when:
        Integer possibleCoordinateCount = table.possibleCoordinateCount

        then:
        possibleCoordinateCount == 35
    }

    def "Should be able to move to specified coordinates within bounds of table"() {
        when:
        Boolean validCoordinate = table.isValidCoordinate(3, 5)

        then:
        validCoordinate
    }

    def "Should not be able to move to specified coordinates out of the table's bounds"() {
        when:
        Boolean validCoordinate = table.isValidCoordinate(6, 5)

        then:
        !validCoordinate
    }

    def "Should be able to move to neighbouring coordinate in specified direction"() {
        when:
        Boolean canMove = table.canMove(NORTH).from(2, 1)

        then:
        canMove
    }

    def "Should not be able to fall off ledge of table"() {
        when:
        Boolean canMove = table.canMove(SOUTH).from(1, 0)

        then:
        !canMove
    }

    def "Should be able to move away from ledge of table"() {
        when:
        Boolean canMove = table.canMove(EAST).from(0, 0)

        then:
        canMove
    }

    def "Should not be able to move westbound off the top left of the table"() {
        when:
        Boolean canMove = table.canMove(WEST).from(0, 4)

        then:
        !canMove
    }
}
