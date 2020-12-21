package br.com.zup.nossoCartao.fatura.fatura;

import br.com.zup.nossoCartao.fatura.transacao.Transacao;

import java.math.BigDecimal;
import java.util.List;

public class FaturaResponse {
    String numero;
    BigDecimal total;
    BigDecimal limiteDisponivel;
    BigDecimal limite;

    List<UltimaTransacao> transacoes;

    public FaturaResponse(String numero, List<UltimaTransacao> transacoes, BigDecimal limite) {
        this.numero = numero;
        this.transacoes = transacoes;
        this.total = calculaTotal();
        this.limite = limite;
        this.limiteDisponivel = this.limite.subtract(total);
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal calculaTotal() {
        return this.transacoes.stream()
                .map(UltimaTransacao::getValor)
                .reduce((total, atual) -> total.add(atual)).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<UltimaTransacao> getTransacoes() {
        return this.transacoes.size() > 10 ?
                this.transacoes.subList(0, 10) :
                this.transacoes;
    }

    public BigDecimal getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public BigDecimal getLimite() {
        return limite;
    }
}
