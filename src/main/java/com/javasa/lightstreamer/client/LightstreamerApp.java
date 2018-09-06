package com.javasa.lightstreamer.client;

import com.javasa.lightstreamer.client.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ismael.sadeghi
 * @email: ismaelsadeghi111@gmail.com
 * @date: 09/05/2018
 */
@SpringBootApplication
public class LightstreamerApp {

    private static final Logger log = LoggerFactory.getLogger(LightstreamerApp.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(LightstreamerApp.class);
        addDefultProfile(app);
        Environment env = app.run(args).getEnvironment();
        log.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

    public static void addDefultProfile(SpringApplication application) {
        Map<String, Object> defProfile = new HashMap<String, Object>();

        defProfile.put(Constants.SPRING_PROFILE_DEFAULT, Constants.SPRING_PROFILE_DEVELOPMENT);
        application.setDefaultProperties(defProfile);
    }
}
