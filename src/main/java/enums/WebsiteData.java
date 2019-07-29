package enums;

public enum WebsiteData {

    WEBSITE_TITLE("Internet Assigned Numbers Authority"),
    URL("https://www.iana.org/");

    final String text;

    WebsiteData(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
