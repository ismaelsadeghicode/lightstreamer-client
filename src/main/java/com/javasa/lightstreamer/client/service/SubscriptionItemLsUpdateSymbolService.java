package com.javasa.lightstreamer.client.service;

import com.lightstreamer.ls_client.*;
import com.javasa.lightstreamer.client.lightstreamer.DataAdapterLs;
import com.javasa.lightstreamer.client.lightstreamer.DataModelLs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Copyright 2016 (C) sefryek.com
 *
 * @author: Ismael Sadeghi
 * @email: ismaelsadeghi111@gmail.com
 * @date: 09, May, 2018
 */
@Component
public class SubscriptionItemLsUpdateSymbolService {

    public static final Logger log = LoggerFactory.getLogger(SubscriptionItemLsUpdateSymbolService.class);

    @Autowired
    private LightStreemerService lightStreemerService;

    public SubscribedTableKey subscribedForUpdateSymbolByBrokers(LSClient lsClient, String listBrokCode, String group, String schemaName, DataModelLs mode, boolean snapshot,
                                                                 DataAdapterLs dataAdapter) throws SubscrException, PushUserException, PushServerException, PushConnException {

        String groupName = null;
        groupName = String.format("%s " + group, listBrokCode);

        groupName = groupName.replaceFirst(" ", "");
        SubscribedTableKey subscribedKey = lsClient.subscribeTable(
                new SimpleTableInfo(groupName, mode.getValue(), schemaName, snapshot) {
                    {
                        setDataAdapter(dataAdapter.getValue());
                    }
                },
                new HandyTableListener() {
                    public void onUpdate(int itemPos, String itemName, UpdateInfo update) {
//                        for (int i = 1; i <= update.getNumFields(); i++) {
//                            if (update.isValueChanged(i)) {
//                                log.info(" cache : " + update.getNewValue(i)); // show all log
//                                if (update.getNewValue(i).contains("Symbol")) {
                                    log.info("message = {}", update);
//                                }
//                            }
//                        }
                    }

                    public void onSnapshotEnd(int itemPos, String itemName) {
                        log.info("end of snapshot for " + itemPos);
                    }

                    public void onRawUpdatesLost(int itemPos, String itemName, int lostUpdates) {
//                        log.info(lostUpdates + " updates lost for " + itemPos);
                    }

                    public void onUnsubscr(int itemPos, String itemName) {
//                        log.info("unsubscr " + itemPos);
                    }

                    public void onUnsubscrAll() {
//                        log.info("unsubscr table");
                        log.info(String.format("\n------- unsubscr to lightStreemer service - %s -------", new Date().toString()));
                        lightStreemerService.subscribeAllBrokersForChangeSymbol();
                    }
                },
                false
        );

        return subscribedKey;
    }

}
