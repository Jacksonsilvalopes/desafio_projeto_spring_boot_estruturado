package com.jacksspring.dscommerce.service.exceptions;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String msg) {
        super(msg);
    }
}