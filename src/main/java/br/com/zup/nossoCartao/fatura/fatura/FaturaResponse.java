package br.com.zup.nossoCartao.fatura.fatura;

import br.com.zup.nossoCartao.fatura.transacao.Transacao;

import java.math.BigDecimal;
import java.util.List;

public class FaturaResponse {
    String numero;
    BigDecimal total;
    List<Transacao> transacoes;

    public FaturaResponse(String numero, List<Transacao> transacoes) {
        this.numero = numero;
        this.transacoes = transacoes;
        this.total = calculaTotal();
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal calculaTotal() {
        return this.transacoes.stream()
                .map(Transacao::getValor)
                .reduce((total, atual) -> total.add(atual)).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
