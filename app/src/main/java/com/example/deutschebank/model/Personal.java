package com.example.deutschebank.model;

public class Personal {
    private int client_id;
    private String holder_name;
    private int account_number;
    private String ifsc_code;
    private String mobile;
    private int balance;

    public Personal(int client_id, String holder_name, int account_number, String ifsc_code, String mobile, int balance) {
        this.client_id = client_id;
        this.holder_name = holder_name;
        this.account_number = account_number;
        this.ifsc_code = ifsc_code;
        this.mobile = mobile;
        this.balance = balance;
    }

    public Personal() {
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
