package com.hooroo.robot

import com.hooroo.robot.direction.CardinalDirection
import com.hooroo.robot.direction.RelativeDirection
import com.hooroo.robot.domain.Robot
import com.hooroo.robot.domain.Table

import static Application.Command.*

/**
 * Pseudo "Controller" that accepts input from a user via CLI (from {@link CommandLine}, or any other interface implementation of choice.<br/>
 * Responsible for communication with, and manipulation of domain objects.
 */
class Application {

    static final String HELP_MSG = "Application accepts the input commands " +
            "${values()}.\n" +
            "These can be issued in lowercase or uppercase.\n" +
            "A PLACE command must first be issued before any further commands.\n\n" +
            "${PLACE} X,Y,F\n" +
            "\t- Places a robot on table at coordinates specified in X,Y, \n" +
            "\t  facing the direction specified in F which can be one of ${CardinalDirection.values()}.\n" +
            "${MOVE}\n" +
            "\t- Moves robot by one coordinate in the direction its currently facing,\n" +
            "\t  unless it is at the edge of the table.\n" +
            "${LEFT}\n" +
            "\t- Turns robot left.\n" +
            "${RIGHT}\n" +
            "\t- Turns robot right.\n" +
            "${REPORT}\n" +
            "\t- Prints current coordinate of robot and the direction it is facing.\n" +
            "${HELP}\n" +
            "\t- Prints this message\n" +
            "${EXIT}\n" +
            "\t- Closes this app"

    Robot robot

    Application() {
        robot = new Robot()
    }

    /**
     * Accepts a single command from input source, validates and executes the necessary domain object methods corresponding to the command
     *
     * @param input User entered command from undetermined input source
     * @return A string if output is to be displayed, or null
     *
     * @throws InterruptedException When the user has issued an EXIT command
     * @throws IllegalArgumentException When a command is invalid
     * @throws InputMismatchException If the coordinate and direction in a PLACE command is malformed
     * @throws com.hooroo.robot.exception.NotOnTableException If a MOVE, LEFT, RIGHT or REPORT command is issued before a PLACE
     * @throws com.hooroo.robot.exception.OutOfBoundsException If a MOVE is issued that would result in the robot falling off the table
     */
    String execute(String input) {
        Command command = correspondsTo(input)

        switch (command) {
            case EXIT:
            case BYE:
                throw new InterruptedException()

            case HELP:
                return HELP_MSG

            case PLACE:
                String[] parts = input.trim().split("\\s+")
                String[] instructions

                Integer x
                Integer y
                CardinalDirection facing

                try {
                    instructions = parts[1].split(",")

                    x = Integer.parseInt(instructions[0])
                    y = Integer.parseInt(instructions[1])
                    facing = CardinalDirection.valueOf(instructions[2].toUpperCase())

                } catch (ArrayIndexOutOfBoundsException ignored) {
                    throw new InputMismatchException("Coordinate and direction must be provided in PLACE command in the form 'X,Y,F' where F = 'Facing'. E.g. PLACE 2,1,NORTH")

                } catch (NumberFormatException ignored) {
                    throw new InputMismatchException("Coordinate (X and Y) must be integers")

                } catch (IllegalArgumentException ignored) {
                    throw new InputMismatchException("Facing (F) must be one of ${CardinalDirection.values()}")
                }

                robot.putOn(new Table()).at(x, y).facing(facing)
                return null

            case MOVE:
                robot.move()
                return null

            case LEFT:
                robot.turn(RelativeDirection.LEFT)
                return null

            case RIGHT:
                robot.turn(RelativeDirection.RIGHT)
                return null

            case REPORT:
                return robot.output
        }

        return null
    }

    /**
     * Enumeration representing valid commands
     */
    enum Command {
        PLACE, MOVE, LEFT, RIGHT, REPORT, HELP, EXIT, BYE

        /**
         * Matches command input to a Command enum value
         * @param input Input from user
         * @return Corresponding enum constant
         * @throws IllegalArgumentException if the input is not recognised (does not match to an enum value)
         */
        static Command correspondsTo(String input) {
            String[] parts = input.trim().split("\\s+")

            try {
                return valueOf(parts[0]?.toUpperCase())
            } catch (IllegalArgumentException ignored) {
                throw new IllegalArgumentException("Command not found. Please issue one of ${values()}")
            }
        }
    }
}
