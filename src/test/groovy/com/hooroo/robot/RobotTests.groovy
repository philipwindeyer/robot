package com.hooroo.robot

import spock.lang.Specification

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

    def "Robot should think it is on table after being placed on there"() {
        when:
        // TODO Should specify coordinate and cardinal direction its facing in this method call!
        robot.putOnTable(new Table())
        Boolean onTable = robot.onTable

        then:
        onTable
    }
}
