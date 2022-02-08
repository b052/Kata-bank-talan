package com.abdou.kataAccountBank;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Operation {
    private OperationType operation;
    private int amount;
    private int balance;
    private Date date;

    public Operation(OperationType operation, int amount, int balance, Date date) {
        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s of %d - DATE : %s - BALANCE : %d", operation, amount, new SimpleDateFormat("yyyy-MM-dd").format(date), balance);
    }

    private void getTimeOperation(){
        this.date = new Date();
    }
}
