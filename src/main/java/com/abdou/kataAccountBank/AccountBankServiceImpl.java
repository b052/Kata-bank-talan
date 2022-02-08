package com.abdou.kataAccountBank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AccountBankServiceImpl implements IAccountBankService{
    private int balance;

    private List<Operation> operations = new ArrayList<>();

    @Override
    public int getBalance() {
        return this.balance;
    }

    @Override
    public void depositOperation(int amount) {
        this.balance +=amount;
        this.operations.add(new Operation(OperationType.DEPOSIT, amount, balance, new Date()));
    }

    @Override
    public void withdrawalOperation(int amount) {
        if (this.balance < amount)
            throw new UnsupportedOperationException("Please be sure to withdraw an amount not more than your balance");
        else {
            this.balance -= amount;
            this.operations.add(new Operation(OperationType.WITHDRAWAL, amount, balance, new Date()));
        }

    }

    @Override
    public String historyOperations() {
        return operations.stream().map(Operation::toString).collect(Collectors.joining("\n"));
    }
}
