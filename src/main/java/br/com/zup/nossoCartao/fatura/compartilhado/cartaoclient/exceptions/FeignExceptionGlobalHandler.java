package br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient.exceptions;

import br.com.zup.nossoCartao.fatura.compartilhado.exceptions.Erro;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestControllerAdvice
public class FeignExceptionGlobalHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> verificaProblema(FeignException feignException, HttpServletRequest request) {
        System.out.println("Erro inesperado!!!!");

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", feignException.status());
        body.put("path", request.getRequestURI().toString());
        List<Erro> errors = new ArrayList<>();

        String mensagem;
        switch (feignException.status()) {
            case 400:
                mensagem = "Há campos com informação invalida, por favor, verifique.";
                errors.add(new Erro("id", mensagem));
                body.put("errors", errors);

                return ResponseEntity.badRequest().body(body);

            case 404:
                mensagem = "O registro solicitado não foi encontrado no nosso banco de dados.";
                errors.add(new Erro("id", mensagem));
                body.put("errors", errors);
                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            case 422:
                mensagem = "Essa requisição possui uma atividade que não é esperada pelo servidor";
                errors.add(new Erro("id", mensagem));
                body.put("errors", errors);
                return ResponseEntity.unprocessableEntity().body(body);
            case 500:
                mensagem = "Houve um erro inesperado na api de cartões, por favor tente novamente após alguns minutos...";
                errors.add(new Erro("id", mensagem));
                body.put("errors", errors);
                return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            default:
                throw new IllegalStateException("Unexpected value: " + feignException.status());
        }
    }
}
