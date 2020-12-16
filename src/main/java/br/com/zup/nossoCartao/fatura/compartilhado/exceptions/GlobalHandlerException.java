package br.com.zup.nossoCartao.fatura.compartilhado.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> conflity(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", 422);
        body.put("path", request.getRequestURI().toString());
        List<Erro> errors = new ArrayList<>();
        errors.add(new Erro(ex.getConstraintName(), "o campo "+ ex.getConstraintName()+ " com o valor fornecido já existe em nosso banco de dados!"));
        body.put("errors", errors);
        return ResponseEntity.unprocessableEntity().body(body);
    }
}
