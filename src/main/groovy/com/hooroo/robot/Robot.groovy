package com.hooroo.robot

class Robot {

    private Table table

    Boolean getOnTable() {
        return table != null
    }

    void putOnTable(Table table) {
        this.table = table
    }
}
