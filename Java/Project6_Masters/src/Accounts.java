/*
* Name:         Ayden Masters 
* Date:         Friday, April 9, 2021 14:15:12
* Exercise:     Project 6 Intro to JavaFX
* Class:        COP2552 
* File Name:    Accounts.java
* 
* Synopsis:     This class will be responsible for creating
                account objects from the file that is being deserialized.
*/

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Accounts implements Serializable {
    // #region Search Term: Serialization

    /**
     * Serialization variable
     *
     */
    private static final long serialVersionUID = 7465545626872487455L;

    // #endregion

    // #region Search Term: Values

    private int acctID;
    private String password;
    private String securityQ;
    private String securityA;
    private double balance;
    private char status;
    private double limit;

    // #endregion

    // #region Search Term: Constructor

    public Accounts(int acctID, String password, String securityQ, String securityA, double balance, char status,
            double limit) {
        this.acctID = acctID;
        this.password = password;
        this.securityQ = securityQ;
        this.securityA = securityA;
        this.balance = balance;
        this.status = status;
        this.limit = limit;
    }

    // #endregion

    // #region Search Term: Mutator Methods

    public Accounts acctID(int acctID) {
        setAcctID(acctID);
        return this;
    }

    public Accounts password(String password) {
        setPassword(password);
        return this;
    }

    public Accounts securityQ(String securityQ) {
        setSecurityQ(securityQ);
        return this;
    }

    public Accounts securityA(String securityA) {
        setSecurityA(securityA);
        return this;
    }

    public Accounts balance(double balance) {
        setBalance(balance);
        return this;
    }

    public Accounts status(char status) {
        setStatus(status);
        return this;
    }

    public Accounts limit(double limit) {
        setLimit(limit);
        return this;
    }

    public void setAcctID(int acctID) {
        this.acctID = acctID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public void setSecurityA(String securityA) {
        this.securityA = securityA;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
    // #endregion

    // #region Search Term: Output Methods

    public int getAcctID() {
        return this.acctID;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSecurityQ() {
        return this.securityQ;
    }

    public String getSecurityA() {
        return this.securityA;
    }

    public double getBalance() {
        return this.balance;
    }

    public char getStatus() {
        return this.status;
    }

    public double getLimit() {
        return this.limit;
    }

    @Override
    public String toString() {
        return "{" + " acctID='" + getAcctID() + "'" + ", password='" + getPassword() + "'" + ", securityQ='"
                + getSecurityQ() + "'" + ", securityA='" + getSecurityA() + "'" + ", balance='" + getBalance() + "'"
                + ", status='" + getStatus() + "'" + ", limit='" + getLimit() + "'" + "}";
    }
    // #endregion

    // #region Search Term: Output Format Methods

    public String getDollarBalance() {
        return getMoneyFormat(this.balance);
    }

    public String getDollarLimit() {
        return getMoneyFormat(this.limit);
    }

    public String getMoneyFormat(double value) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        return currencyFormat.format(value);
    }

    public String getStatusMessage() throws InvalidStatusException {
        String statusMessage = "";
        switch (Character.toString(getStatus()).toLowerCase()) {

        case "a":
            statusMessage = "Active and in good standing!";
            break;
        case "d":
            statusMessage = "Delinquent and not in good standing!";
            break;
        case "c":
            statusMessage = "Account closed!";
            break;
        default:
            throw new InvalidStatusException();
        }
        return statusMessage;
    }

    // #endregion

    // #region Search Term: Deserialize

    /**
     * Deserialize the data from a give object input stream and turn it into
     * Accounts objects
     * 
     * @param objectInputFile passed from the file input stream
     */
    public static Accounts deserializeData(ObjectInputStream objectInputStream) throws IOException {
        Accounts account = null;
        try {

            account = (Accounts) objectInputStream.readObject();

        } catch (ClassNotFoundException e) {
            // Custom exception
            e.printStackTrace();
        }
        return account;
    }

    // #endregion
}
