package com.hehnosky.javainterviewtask.model;

public enum OperationType {
    ADD,
    SUBTRACT;

    public static OperationType getByName(String name){
        OperationType result = null;
        for (OperationType direction : values()) {
            if (direction.name().equalsIgnoreCase(name)) {
                result = direction;
                break;
            }
        }
        return result;
    }
}
