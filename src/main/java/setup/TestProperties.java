package setup;

import enums.PropertiesPaths;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties using
 */
 class TestProperties {

    private Properties currentProps = new Properties();
    private String propertyPath;

    /**
     * Read set of properties
     * @param type
     * @return
     * @throws IOException
     */
    private Properties getCurrentProps(String type) throws IOException {

        switch(type) {
            case "web":
                propertyPath = PropertiesPaths.WEB_TEST.getPath();
                break;
            case "native":
                propertyPath = PropertiesPaths.NATIVE_TEST.getPath();
                break;
            default: System.out.println("Wrong transfer type");
        }

        FileInputStream in = new FileInputStream(System.getProperty("user.dir") + propertyPath);
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    /**
     * Return certain property value by key
     * @param type
     * @param propKey
     * @return
     * @throws IOException
     */
     String getProp(String type, String propKey) throws IOException {
        if(!currentProps.containsKey(propKey)) {
            currentProps = getCurrentProps(type);
        }
        // "default" from used to handle the absence of parameter
        return currentProps.getProperty(propKey, null);
    }
}
