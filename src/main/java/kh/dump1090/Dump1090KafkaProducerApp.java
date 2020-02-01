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
		}
		
	}
	
}
