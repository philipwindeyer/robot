package com.hooroo.robot.exception

/**
 * Thrown when a coordinate altering method is called before robot is placed on table
 */
class NotOnTableException extends Exception {

    NotOnTableException() {
        super("Robot must first be placed on table at specified coordinate facing specified direction")
    }
}
