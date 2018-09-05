package com.javasa.lightstreamer.client;

import com.javasa.lightstreamer.client.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

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
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        addDefultProfile(app);
        ConfigurableApplicationContext context = app.run(args);
        Environment env = context.getEnvironment();
        ApplicationStartup applicationStartup = context.getBean(ApplicationStartup.class);
        app.addListeners((ApplicationListener<?>) applicationStartup);
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
