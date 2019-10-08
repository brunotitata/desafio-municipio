package br.com.senior.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UploadFileException extends RuntimeException {
    private static final long serialVersionUID = -1579024501067098225L;

    public UploadFileException(String msg) {
        super(msg);
    }

}
