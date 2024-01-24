package com.hehnosky.javainterviewtask.exception;

public class BankAccountNotFoundException extends RuntimeException{

    private static final String BANK_ACCOUNT_NOT_FOUND_MESSAGE_ERROR = "Bank account with id=[%s] don't exist";

    public BankAccountNotFoundException(Long id) {
        super(String.format(BANK_ACCOUNT_NOT_FOUND_MESSAGE_ERROR, id));
    }
}
