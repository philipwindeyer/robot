package com.hooroo.robot.exception

class NotOnTableException extends Exception {

    NotOnTableException() {
        super("Robot must first be placed on table at specified coordinate facing specified direction")
    }
}
