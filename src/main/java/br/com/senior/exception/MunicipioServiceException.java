package br.com.senior.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class MunicipioServiceException extends RuntimeException {
    private static final long serialVersionUID = 6442843128288993500L;

    public MunicipioServiceException(String msg) {
        super(msg);
    }

}
