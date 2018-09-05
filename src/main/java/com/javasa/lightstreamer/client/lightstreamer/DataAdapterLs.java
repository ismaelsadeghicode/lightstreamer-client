package com.javasa.lightstreamer.client.lightstreamer;

/**
 * @author: Ismael Sadeghi
 * @email: ismaelsadeghi111@gmail.com
 * @date: 12, May, 2018
 */
public enum DataAdapterLs {
    CLOCK("clock"),
    TADBIRLIGHTRLC("TadbirLightRLC"),
    TADBIRLIGHTPRIVATEGATEWAYADAPTER("TadbirLightPrivateGatewayAdapter");

    String value;

    DataAdapterLs(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
