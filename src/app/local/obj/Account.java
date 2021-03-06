package app.local.obj;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {

    private StringProperty account_id;
    private StringProperty password;
    private StringProperty account_type;

    public Account(){
        account_id = new SimpleStringProperty();
        password = new SimpleStringProperty();
        account_type = new SimpleStringProperty();
    }

    public Account (String account_id, String account_type){
        this.account_id = new SimpleStringProperty(account_id);
        password = new SimpleStringProperty();
        this.account_type = new SimpleStringProperty(account_type);
    }

    public String getAccount_id() {
        return account_id.get();
    }

    public StringProperty account_idProperty() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id.set(account_id);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getAccount_type() {
        return account_type.get();
    }

    public StringProperty account_typeProperty() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type.set(account_type);
    }
}
