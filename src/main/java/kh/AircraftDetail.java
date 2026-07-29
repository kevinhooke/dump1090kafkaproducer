package kh;

/**
 * Represents each line from aircraft.csv in expacted format:
 *
 * icao;registration;type_code;flags;full_type_name
 */
public record AircraftDetail(
        String icaoHex,
        String registration,
        String type,
        String flags,
        String fullTypeName
        ) {
    //TODO do all rows not have a full type value? - String full_type_name
}
