package account_manager.utils;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void stringToLocalDateTime() {
        LocalDateTime localDateTime = DateUtil.stringToLocalDateTime("20/10/2018 12:15:30");

        assertNotNull(localDateTime);
        assertEquals(20, localDateTime.getDayOfMonth());
        assertEquals(10, localDateTime.getMonth().getValue());
        assertEquals(2018, localDateTime.getYear());
        assertEquals(12, localDateTime.getHour());
        assertEquals(15, localDateTime.getMinute());
        assertEquals(30, localDateTime.getSecond());
    }
}