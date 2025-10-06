package kh.dump1090;

import kh.dump1090.messageforwarder.kafka.KafkaForwarder;
import kh.dump1090.messageforwarder.restclient.RestClientForwarder;
import kh.dump1090.streamreader.StreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppRunModeHelper {

    @Value("${runmode}")
    private String appRunmode;

    @Autowired
    KafkaForwarder kafkaForwarder;

    @Autowired
    RestClientForwarder restClientForwarder;

    @Autowired
    StreamReader streamReader;

    public void startBasedOnRunMode() throws Exception{
        if(this.appRunmode.equals("kafka")){
            streamReader.readStandardInAndForward(kafkaForwarder);
        }
        else if(this.appRunmode.equals("rest")){
            streamReader.readStandardInAndForward(restClientForwarder);
        }
        else{
            System.out.println("Error: unknown appmode, check app.properties");
        }
    }
}
