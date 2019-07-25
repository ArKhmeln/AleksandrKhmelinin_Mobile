package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

 class TestProperties {

    Properties currentProps = new Properties();

    Properties getCurrentProps() throws IOException {
        FileInputStream in = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/test.properties");
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    protected String getProp(String propKey) throws IOException {
        if(!currentProps.containsKey(propKey)) {
            currentProps = getCurrentProps();
        }
        // "default" from used to handle the absence of parameter
        return currentProps.getProperty(propKey, null);
    }
}
