package com.javasa.lightstreamer.client.lightstreamer;

import com.lightstreamer.ls_client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Ismael Sadeghi
 * @email: ismaelsadeghi111@gmail.com
 * @date: 09, May, 2018
 */
public class ConnectToLs {

    public static final Logger log = LoggerFactory.getLogger(ConnectToLs.class);

    public void start(LSClient lsClient, String serverUrl, String adapterName, String userName, String passwordLS)
            throws PushUserException, PushConnException, PushServerException {

        lsClient.openConnection(
                new ConnectionInfo() {
                    {
                        this.pushServerUrl = serverUrl;
                        this.adapter = adapterName;
                        this.user = userName;
                        this.password = passwordLS;
//                        log.info("start to connection to server {}, adapterName {}, userName {}", pushServerUrl, adapterName, userName);
                    }
                },
                new ExtendedConnectionListener() {
                    private long bytes = 0;

                    public void onConnectionEstablished() {
                        log.info("connection established");
                    }

                    public void onSessionStarted(boolean isPolling) {
                        //never called
                    }

                    public void onSessionStarted(boolean isPolling, String controlLink) {
                        String clAddendum = controlLink != null ? " to server " + controlLink : "";
                        if (isPolling) {
                            log.info("Session started in smart polling\n" + clAddendum);
                        } else {
                            log.info("Session started in streaming" + clAddendum);
                        }
                    }

                    public void onNewBytes(long newBytes) {
                        this.bytes += newBytes;
                    }

                    public void onDataError(PushServerException e) {
                        log.error("data error", e.getMessage());
                    }

                    public void onActivityWarning(boolean warningOn) {
                        if (warningOn) {
                            log.info("connection stalled");
                        } else {
                            log.info("connection no longer stalled");
                        }
                    }

                    public void onEnd(int cause) {
                        log.info("connection forcibly closed");
                    }

                    public void onClose() {
                        log.info("total bytes: {}", bytes);
                    }

                    public void onFailure(PushServerException e) {
                        log.error("server failure, message = {}", e.getMessage());
                    }

                    public void onFailure(PushConnException e) {
                        log.error("connection failure, message = {}", e.getMessage());
                    }
                }
        );
    }
}
