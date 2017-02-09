package com.hooroo.robot

import com.hooroo.robot.exception.NotOnTableException
import com.hooroo.robot.exception.OutOfBoundsException
import spock.lang.Specification

import static com.hooroo.robot.direction.CardinalDirection.*

class ApplicationTests extends Specification {

    Application application

    def setup() {
        application = new Application()
    }

    def "Should throw InterruptedException when EXIT in both upper and lower case is issued"() {
        when:
        application.execute("EXIT")

        then:
        thrown InterruptedException

        when:
        application.execute("exit")

        then:
        thrown InterruptedException
    }

    def "Should throw IllegalArgumentException when an unrecognised command is issued"() {
        when:
        application.execute("TEST")

        then:
        Exception exception = thrown()
        exception instanceof IllegalArgumentException
        exception.message == "Command not found. Please issue one of [PLACE, MOVE, LEFT, RIGHT, REPORT, HELP, EXIT, BYE]"
    }

    def "Should throw InputMismatchException when input data not provided for PLACE command"() {
        when:
        application.execute("PLACE ")

        then:
        Exception exception = thrown()
        exception instanceof InputMismatchException
        exception.message == "Coordinate and direction must be provided in PLACE command in the form 'X,Y,F' where F = 'Facing'. E.g. PLACE 2,1,NORTH"
    }

    def "Should throw InputMismatchException when integers are not used for X,Y in PLACE command"() {
        when:
        application.execute("PLACE A,B,NORTH")

        then:
        Exception exception = thrown()
        exception instanceof InputMismatchException
        exception.message == "Coordinate (X and Y) must be integers"
    }

    def "Should throw InputMismatchException when an unrecognised direction is used in PLACE command"() {
        when:
        application.execute("PLACE 2,1,NROTH")

        then:
        Exception exception = thrown()
        exception instanceof InputMismatchException
        exception.message == "Facing (F) must be one of [NORTH, SOUTH, EAST, WEST]"
    }

    def "Should not be able to execute MOVE command before PLACE is issued"() {
        when:
        application.execute("MOVE")

        then:
        Exception exception = thrown()
        exception instanceof NotOnTableException
        exception.message == "Robot must first be placed on table at specified coordinate facing specified direction"
    }

    def "Should not be able to execute LEFT command before PLACE is issued"() {
        when:
        application.execute("left")

        then:
        Exception exception = thrown()
        exception instanceof NotOnTableException
        exception.message == "Robot must first be placed on table at specified coordinate facing specified direction"
    }

    def "Should not be able to execute RIGHT command before PLACE is issued"() {
        when:
        application.execute("right")

        then:
        Exception exception = thrown()
        exception instanceof NotOnTableException
        exception.message == "Robot must first be placed on table at specified coordinate facing specified direction"
    }


    def "Should execute PLACE command successfully"() {
        when:
        application.execute("PLACE 0,0,NORTH")

        then:
        application.robot.x == 0
        application.robot.y == 0
        application.robot.facing == NORTH

        when:
        application.execute("PLACE 2,1,west")

        then:
        application.robot.x == 2
        application.robot.y == 1
        application.robot.facing == WEST
    }

    def "Should move west to 1,1 successfully"() {
        given:
        application.execute("PLACE 2,1,WEST")

        when:
        application.execute("move")

        then:
        application.robot.x == 1
        application.robot.y == 1
    }

    def "Should move east to 3,3 successfully"() {
        given:
        application.execute("PLACE 2,3,EAST")

        when:
        application.execute("MOVE")

        then:
        application.robot.x == 3
        application.robot.y == 3
    }

    def "Should not be able to fall off rhe table north from 3,4"() {
        given:
        application.execute("PLACE 3,4,NORTH")

        when:
        application.execute("MOVE")

        then:
        Exception exception = thrown()
        exception instanceof OutOfBoundsException
        exception.message == "Specified coordinate is not within the bounds of the table"
    }
}
