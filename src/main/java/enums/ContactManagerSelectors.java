package enums;

public enum ContactManagerSelectors {

    ADD_BUTTON("com.example.android.contactmanager:id/addContactButton"),
    ADD_CONTACT_TITLE("android:id/title"),
    TARGET_ACCOUNT_FIELD("com.example.android.contactmanager:id/accountSpinner"),
    CONTACT_NAME_FIELD("com.example.android.contactmanager:id/contactNameEditText"),
    CONTACT_PHONE_FIELD("com.example.android.contactmanager:id/contactPhoneEditText");

    final String text;

    ContactManagerSelectors(String text) {
        this.text = text;
    }

    public String getSelector() {
        return text;
    }
}
