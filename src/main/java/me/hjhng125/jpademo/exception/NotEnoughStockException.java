package me.hjhng125.jpademo.exception;

public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException(String s) {
        super(s);
    }
}
