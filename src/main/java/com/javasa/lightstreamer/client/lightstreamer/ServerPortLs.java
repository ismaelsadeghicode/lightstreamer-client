package com.javasa.lightstreamer.client.lightstreamer;

/**
 * @author: Ismael Sadeghi
 * @email: ismaelsadeghi111@gmail.com
 * @date: 12, May, 2018
 */
public enum ServerPortLs {
    PORT_A("80"),
    PORT_B("443"),
    PORT_C("8080");

    String value;

    ServerPortLs(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
