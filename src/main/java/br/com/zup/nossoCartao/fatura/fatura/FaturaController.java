package br.com.zup.nossoCartao.fatura.fatura;

import br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient.CartaoClient;
import br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient.ResultadoConsultaCartaoResponse;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/faturas")
public class FaturaController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private FaturaFakeRepository faturaFakeRepository;

    @GetMapping("/cartao/{id}")
    public ResponseEntity<?> consultaFaturaCartao(@PathVariable("id") String numeroCartao) {

        if(transacaoRepository.findByCartaoId(numeroCartao).isPresent()) {

            /*intervaloTransacoesFatura = new IntervaloTransacoesFatura();

            List<Transacao> transacaoList = transacaoRepository
                    .findByCartaoIdAndEfetivadaEmBetweenOrderByEfetivadaEmDesc(
                            numeroCartao,
                            intervaloTransacoesFatura.getDataInicial(),
                            intervaloTransacoesFatura.getDataFinal());

            ResultadoConsultaCartaoResponse cartaoResponse = cartaoClient.informacoesCartao(numeroCartao);
*/
            Fatura fatura = faturaRepository.findByCartaoId(numeroCartao);

           FaturaFake faturaFake = faturaFakeRepository.findByCartaoId(numeroCartao);

          if (faturaFake != null) {
              /*FaturaResponse faturaResponse = new FaturaResponse(
                      numeroCartao,
                      null,
                      faturaFake.getLimite()
              );*/

              FaturaFakeResponse faturaResponse = new FaturaFakeResponse(
                  numeroCartao,
                  faturaFake.getSaldo(),
                  faturaFake.getLimite()
              );

              return ResponseEntity.ok(faturaResponse);
          }else {
              throw new ResponseStatusException(
                      HttpStatus.NOT_FOUND,
                      "O não há fatura formulada!"
              );
          }
        }else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "O cartão não foi localizado!"
            );
        }
    }


 }
