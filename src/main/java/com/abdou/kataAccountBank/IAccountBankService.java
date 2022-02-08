package com.abdou.kataAccountBank;

public interface IAccountBankService {
    int getBalance();

    void depositOperation(int amount);

    void withdrawalOperation(int amount);

    String historyOperations();
}
