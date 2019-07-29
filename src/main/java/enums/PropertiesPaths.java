package enums;

public enum PropertiesPaths {

    WEB_TEST("/src/main/resources/webtest.properties"),
    NATIVE_TEST("/src/main/resources/nativetest.properties");

    final String path;

    PropertiesPaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
