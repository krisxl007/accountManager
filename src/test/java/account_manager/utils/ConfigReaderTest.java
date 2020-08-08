package account_manager.utils;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class ConfigReaderTest {

    @Test
    public void getInstance() throws Exception {
        assertNotNull(ConfigReader.getInstance());
    }

    @Test
    public void getConfig() throws Exception {
        Properties properties = ConfigReader.getInstance().getConfig("src/test/resources/args.properties");

        assertEquals("ACC334455", properties.getProperty("account.id"));
        assertEquals("20/10/2018 12:00:00", properties.getProperty("from"));
        assertEquals("20/10/2018 19:00:00", properties.getProperty("to"));

    }

}