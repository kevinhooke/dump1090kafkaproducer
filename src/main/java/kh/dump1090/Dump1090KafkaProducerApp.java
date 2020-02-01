package kh.dump1090;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dump1090KafkaProducerApp {

	public static void main(String[] args) throws Exception {
		Dump1090KafkaProducerApp app = new Dump1090KafkaProducerApp();
		app.readStandardIn();
	}

	public void readStandardIn() throws IOException {
		InputStreamReader streamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(streamReader);
		
		String currentLine = null;
		while((currentLine = reader.readLine()) != null) {
			System.out.println("Line: " + currentLine);
			
			SBSMessage msg = this.splitLineToSBSMessage(currentLine);
		}
		
	}

	SBSMessage splitLineToSBSMessage(String currentLine) {
		String[] fields = currentLine.split(",");
		SBSMessage msg = new SBSMessage();
		msg.setMessageType(fields[0]);
		
		int transmissionType = 0;
		if(fields[1] != null) {
			transmissionType = Integer.parseInt(fields[1]);
		}
		msg.setTransmissionType(transmissionType);
		
		int sessionId = 0;
		if(fields[2] != null) {
			sessionId = Integer.parseInt(fields[2]);
		}
		msg.setSessionId(sessionId);

		int aircraftId = 0;
		if(fields[3] != null) {
			aircraftId = Integer.parseInt(fields[3]);
		}
		msg.setAircraftId(aircraftId);

		msg.setHexIdent(fields[4]);
		
		return msg;
	}
	
}
