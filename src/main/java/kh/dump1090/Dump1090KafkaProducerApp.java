package kh.dump1090;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Example run: nc piaware.local 30003 | java -jar dump1090-kafkaproducer-0.0.1-SNAPSHOT-jar-with-dependencies.jar
 */
public class Dump1090KafkaProducerApp {

    /**
     * See app runmode property in app.properties
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();

        AppRunModeHelper helper = (AppRunModeHelper) ctx.getBean("appRunModeHelper");
        helper.startBasedOnRunMode();
	}

}
