package kh.dump1090;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Dump1090KafkaProducerApp {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        
        Dump1090KafkaProducer producer = (Dump1090KafkaProducer) ctx.getBean("dump1090KafkaProducer");
        producer.readStandardIn();
	}

}
