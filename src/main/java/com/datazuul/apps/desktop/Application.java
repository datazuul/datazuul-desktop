package com.datazuul.apps.desktop;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Implementing ApplicationRunner interface tells Spring Boot to automatically call the run method AFTER the application context has been loaded.
 */
@SpringBootApplication
public class Application implements ApplicationRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
//    SpringApplication.run(Application.class, args);
    new SpringApplicationBuilder(Application.class).headless(false).run(args);

//    SpringApplication app = new SpringApplication(Application.class);
//    app.setBannerMode(Banner.Mode.OFF);
//    app.setLogStartupInfo(false);
//    app.run(args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    LOGGER.info("STARTING THE APPLICATION");

    final List<String> names = args.getNonOptionArgs();
    //Get and display a list of graphics devices solely for information purposes.
    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice[] devices = graphicsEnvironment.getScreenDevices();
    for (int cnt = 0; cnt < 1; cnt++) {
      System.out.println(devices[cnt]);
    }//end for loop

    //Construct a full-screen object using graphicsDevice 0.
    new Desktop(devices[0]);
  }

}
