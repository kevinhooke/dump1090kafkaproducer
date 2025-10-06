package kh.dump1090.streamreader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kh.dump1090.SBSMessage;
import kh.dump1090.messageforwarder.MessageForwarder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Reads standard in, expected to be piped in from the output of dumm1090's
 * csv data stream on port 30003, e.g. using netcat: nc dump1090-ip 30003
 */
@Component
public class StreamReader {

    /**
     * Reads dump1090 stream and forwards to the provided 'forwarder'.
     * <p>
     * The forwarder sends each received message for processing and/or storage.
     *
     * @throws IOException
     */
    public void readStandardInAndForward(MessageForwarder forwarder) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(streamReader);

        String currentLine = null;
        while ((currentLine = reader.readLine()) != null) {
            System.out.println("Line: " + currentLine);
            SBSMessage msg = this.splitLineToSBSMessage(currentLine);

            //debug json
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(msg);
            System.out.println("JSON payload: " + json);

            if(msg != null) {
                forwarder.forwardMessage(msg);
            }
        }
    }

    /**
     * Splits SBS string from dump1090 port 30003. split() stops parsing if there are trailing
     * empty column values by default, e.g. ,,,, so we set the limit to the expected max number of
     * values in the String
     *
     * @param currentLine
     * @return
     */
    SBSMessage splitLineToSBSMessage(String currentLine) {
        SBSMessage msg = null;
        currentLine = currentLine.trim();
        if(!currentLine.equals("")) {
            msg = new SBSMessage();
            System.out.println("Processing line: " + currentLine);

            String[] fields = currentLine.split(",", 22); //22 is the max number of expected values
            System.out.println("... line has fields: " + fields.length);

            msg.setMessageType(fields[0]);

            int transmissionType = 0;
            if (fields[1] != null && !fields[1].trim().equals("")) {
                transmissionType = Integer.parseInt(fields[1]);
            }
            msg.setTransmissionType(transmissionType);

            int sessionId = 0;
            if (fields[2] != null && !fields[2].trim().equals("")) {
                sessionId = Integer.parseInt(fields[2]);
            }
            msg.setSessionId(sessionId);

            int aircraftId = 0;
            if (fields[3] != null && !fields[3].trim().equals("")) {
                aircraftId = Integer.parseInt(fields[3]);
            }
            msg.setAircraftId(aircraftId);

            msg.setHexIdent(fields[4]);
            msg.setFlightId(fields[5]);
            msg.setDateMessageGenerated(fields[6]);
            msg.setTimeMessageGenerated(fields[7]);
            msg.setDateMessageLogged(fields[8]);
            msg.setTimeMessageLogged(fields[9]);
            msg.setCallsign(fields[10]);

            int altitude = 0;
            if (fields[11] != null && !fields[11].trim().equals("")) {
                altitude = Integer.parseInt(fields[11]);
            }
            msg.setAltitude(altitude);

            int groundSpeed = 0;
            if (fields[12] != null && !fields[12].trim().equals("")) {
                groundSpeed = Integer.parseInt(fields[12]);
            }
            msg.setAltitude(groundSpeed);

            msg.setTrack(fields[13]);
            msg.setLatitude(fields[14]);
            msg.setLongitude(fields[15]);

            int verticalRate = 0;
            if (fields[16] != null && !fields[16].trim().equals("")) {
                verticalRate = Integer.parseInt(fields[16]);
            }
            msg.setVerticalRate(verticalRate);

            msg.setSquawk(fields[17]);
            msg.setAlertSquawkChange(fields[18]);
            msg.setEmergency(fields[19]);
            msg.setSpi(fields[20]);
            msg.setOnGround(fields[21]);
        }
        else{
            System.out.println("... skipping blank line");
        }
        return msg;
    }

}
