package com.hooroo.robot

/**
 * Thrown when a robot cannot be moved in desired direction
 */
class OutOfBoundsException extends Exception {

    OutOfBoundsException() {
        super("Specified coordinate is not within the bounds of the table")
    }
}
