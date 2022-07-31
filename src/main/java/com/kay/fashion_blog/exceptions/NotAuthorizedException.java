package com.kay.fashion_blog.exceptions;

public class NotAuthorizedException extends Exception{
    public NotAuthorizedException(String message) {
        super(message);
    }
}
