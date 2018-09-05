package com.javasa.lightstreamer.client.lightstreamer;

/**
 * @author: Ismael Sadeghi
 * @email: ismaelsadeghi111@gmail.com
 * @date: 12, May, 2018
 *
 * MERGE:    With MERGE subscription mode, an item represents a row in a table. Real-time updates to
 *           that item are used to update the contents of the cells (fields) for that row.
 *
 * DISTINCT: With DISTINCT subscription mode, an item represents a list of events. Real-time updates to
 *           that item are used to add lines to that list (where each line is made up of fields).
 *
 * COMMAND:  With COMMAND subscription mode, an item represents a full table. Real-time updates to
 *           that item are used to change the contents of that table, by adding rows, removing rows, and
 *           updating cells.
 *
 * RAW:      RAW assumption is made on the nature of the data, leading to less optimization in data delivery.
 */
public enum DataModelLs {
    MERGE("MERGE"),
    DISTINCT("DISTINCT"),
    COMMAND("COMMAND"),
    RAW("RAW");

    String value;

    DataModelLs(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
