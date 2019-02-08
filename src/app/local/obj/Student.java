package app.local.obj;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student extends Account{

    private final StringProperty fname;
    private final StringProperty mname;
    private final StringProperty lname;
    private final StringProperty extname;
    private final StringProperty address;
    private final StringProperty contact;

    public Student(){
        fname = new SimpleStringProperty();
        mname = new SimpleStringProperty("");
        lname = new SimpleStringProperty();
        extname = new SimpleStringProperty("");
        address = new SimpleStringProperty();
        contact = new SimpleStringProperty();
    }

    public String getFname() {
        return fname.get();
    }

    public StringProperty fnameProperty() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getMname() {
        return mname.get();
    }

    public StringProperty mnameProperty() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname.set(mname);
    }

    public String getLname() {
        return lname.get();
    }

    public StringProperty lnameProperty() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    public String getExtname() {
        return extname.get();
    }

    public StringProperty extnameProperty() {
        return extname;
    }

    public void setExtname(String extname) {
        this.extname.set(extname);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getContact() {
        return contact.get();
    }

    public StringProperty contactProperty() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }
}
