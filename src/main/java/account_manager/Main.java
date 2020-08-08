package account_manager;

import account_manager.utils.ConfigReader;
import account_manager.utils.CsvReader;

import java.util.List;
import java.util.Properties;

public class Main {

    public static final String ACCOUNT_ID = "account.id";
    public static final String FROM = "from";
    public static final String TO = "to";

    public static void main(String args[]) {
        if(args == null || args.length < 2) {
            System.out.println("Input parameters config/Transaction file not found, please specify correct config path.");
            return;
        }

        String configPath = args[0];
        String csvPath = args[1];
        Properties config = ConfigReader.getInstance().getConfig(configPath);
        String accountId = config.getProperty(ACCOUNT_ID);
        String from = config.getProperty(FROM);
        String to = config.getProperty(TO);
        List<List<String>> transactions = CsvReader.read(csvPath);

        AccountManager manager = new AccountManager(accountId, from, to, transactions);
        manager.count();
    }
}
