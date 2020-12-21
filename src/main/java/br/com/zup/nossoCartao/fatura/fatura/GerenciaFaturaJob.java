package br.com.zup.nossoCartao.fatura.fatura;

import br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient.CartaoClient;
import br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient.ResultadoConsultaCartaoResponse;
import br.com.zup.nossoCartao.fatura.transacao.Cartao;
import br.com.zup.nossoCartao.fatura.transacao.Transacao;
import br.com.zup.nossoCartao.fatura.transacao.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@EnableAsync
@Component
public class GerenciaFaturaJob {

    Logger logger = LoggerFactory.getLogger(GerenciaFaturaJob.class);

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private FaturaFakeRepository faturaFakeRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Scheduled(fixedRate = 30000)
    private void contabilizaTransacoesFatura() {
        operacaoEmLote(new IntervaloTransacoesFatura());
    }

    @Transactional
    private void operacaoEmLote(IntervaloTransacoesFatura intervaloTransacoesFatura) {
           List<Transacao> transacoesPorCartao = transacaoRepository
                   .findTop10ByCartaoIsNotNull();

           logger.info("iniciando processo de contabilizacao/atualizacao de fatura.");

           transacoesPorCartao.forEach(transacaoAtual -> {
               logger.info("cartao em atualizacao/contabilizacao: "+transacaoAtual.getCartao().getId());
               List<Transacao> transacoesCartao = transacaoRepository
                       .findByCartaoIdAndEfetivadaEmBetweenAndContabilizadaFalseOrderByEfetivadaEmDesc(
                    transacaoAtual.getCartao().getId(),
                    intervaloTransacoesFatura.getDataInicial(),
                    intervaloTransacoesFatura.getDataFinal()
               );

              if (transacoesCartao.size() > 0) {
                  logger.info("contabilizando transacoes...");

                  BigDecimal contabilizacao = contabilizaTransacoes(transacoesCartao);
                  System.out.print(contabilizacao);

                  List<Transacao> ultimasDezTransacoes = devolveUltimasDezTransacoesCartao(transacoesCartao);

                  ResultadoConsultaCartaoResponse consultaCartaoResponse = null;
                  try {
                      System.out.println("Cartão n: " + transacaoAtual.getCartao().getId());
                      consultaCartaoResponse = cartaoClient.informacoesCartao(transacaoAtual.getCartao().getId());
                      System.out.println("Limite: " + consultaCartaoResponse.getLimite());
                  } catch (Exception e) {
                      System.out.println(e);
                      System.out.println("Cartão não localizado...");
                  }

                  transacoesCartao.forEach(transacao -> transacao.setContabilizada(true));
                  transacaoRepository.saveAll(transacoesCartao);


                  FaturaFake faturaFake = faturaFakeRepository.findByCartaoId(transacaoAtual.getCartao().getId());

                  System.out.println(faturaFake);
                  if (faturaFake != null) {
                      faturaFake.atualizaSaldo(contabilizacao);
                  } else {
                      faturaFake = new FaturaFake(consultaCartaoResponse.getLimite(), consultaCartaoResponse.getLimite().subtract(contabilizacao), transacaoAtual.getCartao().getId());
                  }
                  faturaFakeRepository.save(faturaFake);
              }else {
                  logger.warn("Não há novas transações a serem consideradas...");
              }
           });
                       /*






                       logger.info("atualizando situacao transacoes...");

                       transacaoRepository.saveAll(transacoesCartao);

                       logger.info("tentando salvar a fatura");

                       List<UltimaTransacao> ultimaTransacoes = transacoesCartao
                               .stream()
                               .map(transacao ->
                                       new UltimaTransacao(transacao.getId(),
                                               transacao.getValor(),
                                               transacao.getEstabelecimento(),
                                               transacao.getCartao(),
                                               transacao.isContabilizada(),
                                               transacao.getEfetivadaEm()))
                               .collect(Collectors.toList());

                       salvaFatura(contabilizacao, consultaCartaoResponse, ultimaTransacoes, transacaoAtual.getCartao());*/
    }

    public BigDecimal contabilizaTransacoes(List<Transacao> transacoes) {
        return transacoes
                .stream()
                .map(Transacao::getValor)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public List<Transacao> devolveUltimasDezTransacoesCartao(List<Transacao> transacoesCartao) {
       return transacoesCartao.size() > 10
                ? transacoesCartao.subList(0, 10)
                : transacoesCartao;
    }

    public void salvaFatura(BigDecimal contabilizacao, ResultadoConsultaCartaoResponse consultaCartaoResponse, List<UltimaTransacao> ultimasDezTransacoes, Cartao cartao) {

        BigDecimal saldo = consultaCartaoResponse.getLimite().subtract(contabilizacao);

        Fatura faturaCartao = faturaRepository.findByCartaoId(cartao.getId());

        if (faturaCartao == null) {
            if (consultaCartaoResponse != null) {
                faturaCartao = new Fatura(cartao.getId(), consultaCartaoResponse.getLimite(), saldo, ultimasDezTransacoes);
            }else {
                System.out.println("Problemas com o cartão... não foi localizado!");
            }
        }else{
            faturaCartao.atualizaSaldo(contabilizacao);
            faturaCartao.setTransacoes(ultimasDezTransacoes);
        }

        System.out.println(faturaCartao);

        faturaRepository.save(faturaCartao);
    }
}

