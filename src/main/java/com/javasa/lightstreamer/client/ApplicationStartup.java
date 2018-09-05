package com.javasa.lightstreamer.client;

import com.javasa.lightstreamer.client.service.LightStreemerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * @author: ismael.sadeghi
 * @email: ismaelsadeghi111@gmail.com
 * @date: 09/05/2018
 */
public class ApplicationStartup {

    private static final Logger log = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    private LightStreemerService lightStreemerService;

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent(){
        lightStreemerService.subscribeAllBrokersForChangeSymbol();
    }

}
