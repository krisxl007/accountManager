package account_manager;

import account_manager.utils.ConfigReader;
import account_manager.utils.CsvReader;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import static account_manager.constants.Constants.FROM_ACCOUNT_COLUMN;
import static account_manager.constants.Constants.TO_ACCOUNT_COLUMN;
import static org.junit.Assert.*;

public class AccountManagerTest {

    private AccountManager manager;

    @Before
    public void setUp() throws Exception {
        String configPath = "src/test/resources/args.properties";
        String csvPath = "src/test/resources/transactions.csv";
        Properties config = ConfigReader.getInstance().getConfig(configPath);
        String accountId = config.getProperty("account.id");
        String from = config.getProperty("from");
        String to = config.getProperty("to");
        List<List<String>> transactions = CsvReader.read(csvPath);

        manager = new AccountManager(accountId, from, to, transactions);
    }

    @Test
    public void count() {
        manager.count();

        assertEquals("-25.00", manager.getBalance().toPlainString());
        assertEquals(Integer.valueOf(1), manager.getNumberOfTransactions());
    }

    @Test
    public void testGetRelatedTransaction_given_relatedUnknownTransactionId_then_returnNull() {
        List<String> relatedTransaction = manager.getRelatedTransaction("Dummy ID");

        assertNull(relatedTransaction);
    }

    @Test
    public void testGetRelatedTransaction_given_relatedTransactionId_then_returnRelatedTransaction() {
        List<String> relatedTransaction = manager.getRelatedTransaction("TX10002");

        assertEquals("ACC334455", relatedTransaction.get(FROM_ACCOUNT_COLUMN));
        assertEquals("ACC998877", relatedTransaction.get(TO_ACCOUNT_COLUMN));
    }
}