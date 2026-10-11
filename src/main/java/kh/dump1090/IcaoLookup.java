package kh.dump1090;

import jakarta.annotation.PostConstruct;
import kh.AircraftDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IcaoLookup {

    private static final Logger logger = LogManager.getLogger(IcaoLookup.class);

    private Map<String, AircraftDetail> icaoMap = new HashMap<>();

    /**
     * Reads lines from aircraft.csv and builds a map for looking up each ICAO
     * hex code
     */
    @PostConstruct
    public void init() throws URISyntaxException, IOException {
        InputStream is = IcaoLookup.class.getResourceAsStream("/aircraft.csv");
        if (is == null) {
            throw new IOException("aircraft.csv not found on classpath");
        }
        List<String> lines = new BufferedReader(new InputStreamReader(is,
                StandardCharsets.UTF_8))
                .lines().toList();

        logger.debug("Loaded {} ICAO entries from aircraft.csv", icaoMap.size());

        //format: icao;registration;type_code;flags;full_type_name
        for(String line : lines){
            String[] fields = line.split(";");
            String fullDesc = "";
            // full type desc is only included for some rows
            if(fields.length == 5){
                fullDesc = fields[4];
            }
            icaoMap.put(fields[0], new AircraftDetail(fields[0], fields[1], fields[2], fields[3], fullDesc));
        }

    }

    public AircraftDetail lookup(String icaoHex){
        AircraftDetail aircraft = null;
        logger.debug("Looking up ICAO hex code: {}", icaoHex);
        aircraft = icaoMap.get(icaoHex);

        return aircraft;
    }
}
