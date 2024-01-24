package com.hehnosky.javainterviewtask.model;

public enum DocumentType {
    DRIVER_LICENSE,
    PASSPORT;

    public static DocumentType getByName(String name){
        DocumentType result = null;
        for (DocumentType type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                result = type;
                break;
            }
        }
        return result;
    }
}
