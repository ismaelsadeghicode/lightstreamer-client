package com.javasa.lightstreamer.client.service;

import com.lightstreamer.ls_client.*;
import com.javasa.lightstreamer.client.config.Constants;
import com.javasa.lightstreamer.client.lightstreamer.ConnectToLs;
import com.javasa.lightstreamer.client.lightstreamer.DataAdapterLs;
import com.javasa.lightstreamer.client.lightstreamer.DataModelLs;
import com.javasa.lightstreamer.client.lightstreamer.ServerPortLs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author: Ismael Sadeghi
 * @email: ismaelsadeghi111@gmail.com
 * @date: 09, May, 2018
 */
@Component
public class LightStreemerService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SubscriptionItemLsUpdateSymbolService subscriptionItemLsUpdateSymbolService;

    public void subscribeAllBrokersForChangeSymbol() {

        String serverUrl = "http://" + Constants.PUSH_URL_LIGHTSTRMER + ":" + ServerPortLs.PORT_A.getValue();
        String listBrokCode = "112";

        try {
            final LSClient lsClient = new LSClient();
            ConnectToLs connection = new ConnectToLs();
            connection.start(lsClient, serverUrl, Constants.SERVER_ADAPTER_LIGHTSTRMER, Constants.USER_NAME_LIGHTSTRMER, Constants.PASSWORD_LIGHTSTRMER);
            Thread.sleep(5000);
            log.info(String.format("\n------- connection to lightStreemer service %s ssuccessfully - %s -------", serverUrl, new Date().toString()));

            ArrayList<SubscribedTableKey> subscrRefs = new ArrayList<>();
            SubscribedTableKey tableRef = subscriptionItemLsUpdateSymbolService.subscribedForUpdateSymbolByBrokers(lsClient, listBrokCode, Constants.REQUEST_FORMAT_UPDATE_SYMBOL, Constants.SCHEMA_UPDATE_SYMPLE_LIGHTSTRMER, DataModelLs.RAW, Boolean.FALSE, DataAdapterLs.TADBIRLIGHTPRIVATEGATEWAYADAPTER);
            subscrRefs.add(tableRef);
            SubscribedTableKey[] subscrKeys = subscrRefs.toArray(new SubscribedTableKey[0]);
            log.info(String.format("\n------- items subscrib to lightStreemer service %s ssuccessfully - %s -------", serverUrl, new Date().toString()));

            System.in.read();
            log.info(String.format("\n------- exit the operation items subscrib to lightstreamer server %s - %s -------", serverUrl, new Date().toString()));

            Thread.sleep(10000);
            lsClient.changeSubscriptions(subscrKeys, new SubscriptionConstraints() {
                {
                    maxFrequency = new Double(0.1);
                }
            });

            Thread.sleep(10000);
            lsClient.unsubscribeTables(subscrKeys);
            Thread.sleep(5000);
            lsClient.closeConnection();
            Thread.sleep(2000);
            System.exit(0);
            log.info(String.format("exit lightstreamer server %S", serverUrl));

        } catch (IOException e) {
            log.error("input flow is not standard, message={}", e.getMessage());
        } catch (PushServerException e) {
            log.error(String.format("the server '%s' does not exist, message={}", serverUrl), e.getMessage());
        } catch (InterruptedException e) {
            log.error(String.format("the connection and subscription to the server %s failed, message={}", serverUrl), e.getMessage());
        } catch (PushConnException e) {
            log.error(String.format("the connection to the server %s failed, message={}", serverUrl), e.getMessage());
        } catch (SubscrException e) {
            log.error(String.format("the subscription to the server %s failed, message={}", serverUrl), e.getMessage());
        } catch (PushUserException e) {
            log.error("user name for subscription to the server failed, message={}", e.getMessage());
        }
    }
}
