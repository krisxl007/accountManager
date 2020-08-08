package account_manager.utils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CsvReaderTest {

    @Test
    public void testRead() throws Exception {
        List<List<String>> data = CsvReader.read("src/test/resources/transactions.csv");

        assertEquals(6, data.size());
    }
}