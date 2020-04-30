package id.io.portal.controller;

import id.io.portal.util.database.ConfiguratinDatabaseHelper;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public class ConfigurationController extends BaseController {

    private ConfiguratinDatabaseHelper configDatabaseHelper;

    public ConfigurationController() {
        log = getLogger(this.getClass());
        this.configDatabaseHelper = new ConfiguratinDatabaseHelper();
    }

    public JSONObject getListConfiguration() {

        ConcurrentHashMap<String, String> configValues = configDatabaseHelper.getAllConfiguration();
        JSONObject output = new JSONObject(configValues);

        return output;
    }

//    Expected JSON Input: 
//    {
//        "IDM_SERVICE_URL":"http://<IDM_IP>:<IDM_PORT>/openidm",
//        "IDM_PWD":"openidm-admin","COOKIE_KEY":"language"
//    }
    public JSONObject createConfig(JSONObject json) {

        Iterator<?> keys = json.keys();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            configDatabaseHelper.addConfig(key, json.get(key).toString());
        }
        ConcurrentHashMap<String, String> configValues = configDatabaseHelper.getAllConfiguration();
        JSONObject output = new JSONObject(configValues);

        return output;
    }

    public JSONObject updateConfig(JSONObject json) {

        Iterator<?> keys = json.keys();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            configDatabaseHelper.updateConfig(key, json.get(key).toString());
        }
        ConcurrentHashMap<String, String> configValues = configDatabaseHelper.getAllConfiguration();
        JSONObject output = new JSONObject(configValues);

        return output;
    }
}
