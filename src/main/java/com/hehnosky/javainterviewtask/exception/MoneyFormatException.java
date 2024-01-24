package com.hehnosky.javainterviewtask.exception;

public class MoneyFormatException extends RuntimeException {

    private static final String MONEY_FORMAT_MESSAGE_ERROR = "Number has more than 2 decimal places";

    public MoneyFormatException() {
        super(MONEY_FORMAT_MESSAGE_ERROR);
    }

}
