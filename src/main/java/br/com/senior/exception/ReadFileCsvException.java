package br.com.senior.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ReadFileCsvException extends RuntimeException {
    private static final long serialVersionUID = -1570982501723652441L;

    public ReadFileCsvException(String msg) {
        super(msg);
    }

}
