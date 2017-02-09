package com.hooroo.robot.exception

/**
 * Thrown when a robot cannot be moved in desired direction
 */
class OutOfBoundsException extends Exception {

    OutOfBoundsException() {
        super("Specified coordinate is not within the bounds of the table")
    }
}
