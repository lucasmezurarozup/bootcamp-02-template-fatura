package br.com.zup.nossoCartao.fatura.fatura;

import br.com.zup.nossoCartao.fatura.transacao.Transacao;
import br.com.zup.nossoCartao.fatura.transacao.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/faturas")
public class FaturaController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    private List<LocalDateTime> datas = new ArrayList<>();

    private IntervaloTransacoesFatura intervaloTransacoesFatura;

    @GetMapping("/cartao/{id}")
    public ResponseEntity<?> consultaFaturaCartao(@PathVariable("id") String numeroCartao) {

        if(transacaoRepository.findByCartaoId(numeroCartao).isPresent()) {

            intervaloTransacoesFatura = new IntervaloTransacoesFatura();

            List<Transacao> transacaoList = transacaoRepository
                    .findByCartaoIdAndEfetivadaEmBetween(
                            numeroCartao,
                            intervaloTransacoesFatura.getDataInicial(),
                            intervaloTransacoesFatura.getDataFinal());

            FaturaResponse faturaResponse = new FaturaResponse(numeroCartao, transacaoList);

            return ResponseEntity.ok(faturaResponse);
        }else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O cartão não foi localizado!"
            );
        }
    }


 }
