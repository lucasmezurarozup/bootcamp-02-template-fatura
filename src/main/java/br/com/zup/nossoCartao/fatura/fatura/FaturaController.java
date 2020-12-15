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

    @GetMapping("/cartao/{id}")
    public ResponseEntity<?> consultaFaturaCartao(@PathVariable("id") String numeroCartao) {

        List<LocalDateTime> intervalo = intervaloConsulta();

        if(transacaoRepository.findByCartaoId(numeroCartao).isPresent()) {
            List<Transacao> transacaoList = transacaoRepository
                    .findByCartaoIdAndEfetivadaEmBetween(numeroCartao, intervalo.get(0), intervalo.get(1));

            FaturaResponse faturaResponse = new FaturaResponse(numeroCartao, transacaoList);

            return ResponseEntity.ok(faturaResponse);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O cartão não foi localizado!");
        }
    }

    public List<LocalDateTime> intervaloConsulta() {

        List<LocalDateTime> datas = new ArrayList<>();

        LocalDate dataHoje = LocalDate.now();
        int anoAtual = dataHoje.getYear();
        int mesAtual = dataHoje.getMonthValue();
        LocalDate dataInicial = LocalDate.of(anoAtual, mesAtual, 1);
        LocalDate dataFinal = dataInicial.withDayOfMonth(dataInicial.lengthOfMonth());

        LocalDateTime dataInicialTime = LocalDateTime.of(dataInicial, LocalTime.of(0, 0, 0));
        LocalDateTime dataFinalTime = LocalDateTime.of(dataFinal, LocalTime.of(23, 59, 59));

        datas.add(dataInicialTime);
        datas.add(dataFinalTime);

        return datas;
    }
 }
