package br.com.hikanban.utils.exception;

@SuppressWarnings("serial")
public abstract class DefaultException extends RuntimeException {
    public abstract ErrorResponse getErrorResponse();
}
