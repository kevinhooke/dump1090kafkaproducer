package kh.dump1090;

import kh.AircraftDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IcaoLookup.class)
public class IcaoLookupTest {

    @Autowired
    private IcaoLookup icaoLookup;

    @Test
    public void testLookupIcaoCode(){
        AircraftDetail detail = icaoLookup.lookup("004002");
        assertEquals("004002", detail.icaoHex());
        assertEquals("Z-WPA", detail.registration());
        assertEquals("B732", detail.type());
        assertEquals("BOEING 737-200", detail.fullTypeName());
    }

    @Test
    public void testLookupIcaoCodeWithNoFullType(){
        AircraftDetail detail = icaoLookup.lookup("004004");
        assertEquals("004004", detail.icaoHex());
        assertEquals("Z-WPC", detail.registration());
        assertEquals("B732", detail.type());
        assertEquals("", detail.fullTypeName());
    }


    @Test
    public void testLookupUnknownIcaoCodeReturnsNull(){
        AircraftDetail detail = icaoLookup.lookup("FFFFFF");
        assertNull(detail);
    }
}
