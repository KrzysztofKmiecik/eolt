package com.java26.eolt.exception;

public class FISVariantNotFoundExeption extends RuntimeException {

    public FISVariantNotFoundExeption(String message) {
        super("FIS server  response : "+ message);
    }


}
