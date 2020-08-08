package account_manager.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static ConfigReader configReader;
    private Properties configFile = new Properties();

    public static synchronized ConfigReader getInstance() {
        if(null == configReader) {
            configReader = new ConfigReader();
        }
        return configReader;
    }

    public Properties getConfig(String path) {
        try (InputStream input = new FileInputStream(path)){
            configFile.load(input);
        }catch(Exception e){
            e.printStackTrace();
        }

        return configFile;
    }
}
