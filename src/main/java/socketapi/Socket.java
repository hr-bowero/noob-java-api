package socketapi;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Socket {

    public String func, iban, revBank, senBank, pin;
    public double amount;

    public Socket(String revBank, String senBank, String func, String iban, String pin) {
        this.func = func;
        this.iban = iban;
        this.revBank = revBank;
        this.senBank = senBank;
        this.pin = pin;

        Request data = new Request();
        data.Func = func;
        data.IBAN = iban;
        data.IDRecBank = revBank;
        data.IDSenBank = senBank;
        data.PIN = pin;
        String json = new Gson().toJson(data);

        List<String> mainData = new ArrayList<String>();
        mainData.add(revBank);
        mainData.add(json);
        String mainJson = new Gson().toJson(mainData);

        Main.clientEndPoint2.sendMessage(mainJson);
    }

    public Socket(String func, String iban, String revBank, String senBank, String pin, double amount) {
        this.func = func;
        this.iban = iban;
        this.revBank = revBank;
        this.senBank = senBank;
        this.pin = pin;
        this.amount = amount;

        Request data = new Request();
        data.Func = func;
        data.IBAN = iban;
        data.IDRecBank = revBank;
        data.IDSenBank = senBank;
        data.PIN = pin;
        data.amount = amount;
        String json = new Gson().toJson(data);

        List<String> mainData = new ArrayList<String>();
        mainData.add(revBank);
        mainData.add(json);
        String mainJson = new Gson().toJson(mainData);

        Main.clientEndPoint2.sendMessage(mainJson);
    }

    /* setters */

    public void setFunc(String func) {
        this.func = func;
    }

    public void setIban(String func) {
        this.iban = iban;
    }

    public void setRevBank(String func) {
        this.revBank = revBank;
    }

    public void setSenBank(String func) {
        this.senBank = senBank;
    }

    public void setPin(String func) {
        this.pin = pin;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    /* getters */

    public String setFunc() {
        return func;
    }

    public String setIban() {
        return iban;
    }

    public String setRevBank() {
        return revBank;
    }

    public String setSenBank() {
        return senBank;
    }

    public String setPin() {
        return pin;
    }

    public double amount() {
        return amount;
    }
}