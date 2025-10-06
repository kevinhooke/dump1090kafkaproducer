package kh.dump1090.messageforwarder.kafka;

import java.io.IOException;
import java.util.Properties;

import kh.dump1090.SBSMessage;
import kh.dump1090.messageforwarder.MessageForwarder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaForwarder implements MessageForwarder {
	
	@Autowired
	private KafkaTemplate<String, SBSMessage> kafkaTemplate;

	public void forwardMessage(SBSMessage msg) throws IOException {

		//test kafka api
		Properties props = new Properties();
		props.load(this.getClass().getResourceAsStream("/producer.properties"));
		Producer<String, SBSMessage> producer = new KafkaProducer<>(props);
			
        //kafka api = works
        producer.send(new ProducerRecord<String, SBSMessage>("sbs-message", msg.getHexIdent(), msg));
        producer.flush();
		
		producer.close();
	}
}
