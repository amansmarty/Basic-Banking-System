package com.example.deutschebank.model;

public class User {
    private int client_id;
    private String holder_name;
    private int account_number;
    private String ifsc_code;

    public User(int client_id, String holder_name, int account_number, String ifsc_code) {
        this.client_id = client_id;
        this.holder_name = holder_name;
        this.account_number = account_number;
        this.ifsc_code = ifsc_code;
    }

    public User() {
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
}
