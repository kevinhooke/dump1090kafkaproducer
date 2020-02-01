package kh.dump1090;
/**
 * Example SBS message line from dump1090:
 * 
 * MSG,3,1,1,AAABBB,1,2020/02/01,13:52:02.404,2020/02/01,13:52:02.422,,12900,,,38.61713,-121.85754,,,,,,
 * 
 * Field reference: http://woodair.net/sbs/article/barebones42_socket_data.htm
 * 
 * @author kevinhooke
 *
 */
public class SBSMessage {
	
	private String messageType; // SEL, ID, AIR, STA, CLK, MSG
	private int transmissionType; // 1..8
	private int sessionId;
	private int aircraftId;
	private String hexIdent; // ICAO airframe id
	private String flightId;
	private String dateMessageGenerated;
	private String timeMessageGenerated;
	private String dateMessageLogged;
	private String timeMessageLogged;
	private String callsign;
	private int altitude;
	private int groundSpeed;
	private String track;
	private String latitude;
	private String longitude;
	private int verticalRate;
	private String squark;
	private String alertSquarkChange;
	private String emergency;
	private String spi;
	private String onGround;
	
	public SBSMessage() {
		
	}
	
	public SBSMessage(String messageType, int transmissionType, int sessionId, int aircraftId,
			String hexIdent, String flightId, String dateMessageGenerated, String timeMessageGenerated,
			String dateMessageLogged, String timeMessageLogged, String callsign, int altitude, int groundSpeed,
			String track, String latitude, String longitude, int verticalRate, String squark, String alertSquarkChange,
			String emergency, String spi, String onGround) {
		super();
		this.messageType = messageType;
		this.transmissionType = transmissionType;
		this.sessionId = sessionId;
		this.aircraftId = aircraftId;
		this.hexIdent = hexIdent;
		this.flightId = flightId;
		this.dateMessageGenerated = dateMessageGenerated;
		this.timeMessageGenerated = timeMessageGenerated;
		this.dateMessageLogged = dateMessageLogged;
		this.timeMessageLogged = timeMessageLogged;
		this.callsign = callsign;
		this.altitude = altitude;
		this.groundSpeed = groundSpeed;
		this.track = track;
		this.latitude = latitude;
		this.longitude = longitude;
		this.verticalRate = verticalRate;
		this.squark = squark;
		this.alertSquarkChange = alertSquarkChange;
		this.emergency = emergency;
		this.spi = spi;
		this.onGround = onGround;
	}
	
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public int getTransmissionType() {
		return transmissionType;
	}
	public void setTransmissionType(int transmissionType) {
		this.transmissionType = transmissionType;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public int getAircraftId() {
		return aircraftId;
	}
	public void setAircraftId(int aircraftId) {
		this.aircraftId = aircraftId;
	}
	public String getHexIdent() {
		return hexIdent;
	}
	public void setHexIdent(String hexIdent) {
		this.hexIdent = hexIdent;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getDateMessageGenerated() {
		return dateMessageGenerated;
	}
	public void setDateMessageGenerated(String dateMessageGenerated) {
		this.dateMessageGenerated = dateMessageGenerated;
	}
	public String getTimeMessageGenerated() {
		return timeMessageGenerated;
	}
	public void setTimeMessageGenerated(String timeMessageGenerated) {
		this.timeMessageGenerated = timeMessageGenerated;
	}
	public String getDateMessageLogged() {
		return dateMessageLogged;
	}
	public void setDateMessageLogged(String dateMessageLogged) {
		this.dateMessageLogged = dateMessageLogged;
	}
	public String getTimeMessageLogged() {
		return timeMessageLogged;
	}
	public void setTimeMessageLogged(String timeMessageLogged) {
		this.timeMessageLogged = timeMessageLogged;
	}
	public String getCallsign() {
		return callsign;
	}
	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}
	public int getAltitude() {
		return altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	public int getGroundSpeed() {
		return groundSpeed;
	}
	public void setGroundSpeed(int groundSpeed) {
		this.groundSpeed = groundSpeed;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getVerticalRate() {
		return verticalRate;
	}
	public void setVerticalRate(int verticalRate) {
		this.verticalRate = verticalRate;
	}
	public String getSquark() {
		return squark;
	}
	public void setSquark(String squark) {
		this.squark = squark;
	}
	public String getAlertSquarkChange() {
		return alertSquarkChange;
	}
	public void setAlertSquarkChange(String alertSquarkChange) {
		this.alertSquarkChange = alertSquarkChange;
	}
	public String getEmergency() {
		return emergency;
	}
	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}
	public String getSpi() {
		return spi;
	}
	public void setSpi(String spi) {
		this.spi = spi;
	}
	public String getOnGround() {
		return onGround;
	}
	public void setOnGround(String onGround) {
		this.onGround = onGround;
	}
}
