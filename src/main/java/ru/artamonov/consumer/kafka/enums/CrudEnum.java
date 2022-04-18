package ru.artamonov.consumer.kafka.enums;

public enum CrudEnum {

    CREATE(1),
    READ(2),
    UPDATE(3),
    DELETE(4);

    private Integer code;

    CrudEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
