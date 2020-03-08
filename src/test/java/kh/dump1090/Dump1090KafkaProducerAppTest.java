package kh.dump1090;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Dump1090KafkaProducerAppTest {

	@Test
	public void testSplitLineToSBSMessage() {
		
		String testLine = "MSG,3,1,1,AAABBB,1,2020/02/01,13:52:02.404,2020/02/01,13:52:02.422,,12900,,,38.61713,-121.85754,,,,,,";
		Dump1090KafkaProducer app = new Dump1090KafkaProducer();
		
		SBSMessage msg = app.splitLineToSBSMessage(testLine);
		
		assertEquals("MSG", msg.getMessageType());
		assertEquals(3, msg.getTransmissionType());
		assertEquals(1, msg.getSessionId());
		assertEquals(1, msg.getAircraftId());
		assertEquals("AAABBB", msg.getHexIdent());
	}
	
}
