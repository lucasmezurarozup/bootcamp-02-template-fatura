package br.com.zup.nossoCartao.fatura.transacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerTransacao {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @KafkaListener(topics = "transacoes")
    public void ouvir(Transacao transacao) {
        Transacao transacaoSalva = transacaoRepository.save(transacao);
        System.out.println(transacaoSalva);
    }

}
