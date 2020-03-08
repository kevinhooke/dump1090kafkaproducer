package kh.dump1090;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@ComponentScan
@EnableKafka
@PropertySource("producer.properties")
public class AppConfig {

	@Value("${key.serializer}")
	private String keySerializer;
	
	@Value("${value.serializer}")
	private String valueSerializer;

	@Value("${bootstrap.servers}")
	private String bootstrapServers;

    @Bean
    public ProducerFactory<String, SBSMessage> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, this.keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, this.valueSerializer);
        
        return props;
    }

    @Bean
    public KafkaTemplate<String, SBSMessage> kafkaTemplate() {
        return new KafkaTemplate<String, SBSMessage>(producerFactory());
    }
	
}
