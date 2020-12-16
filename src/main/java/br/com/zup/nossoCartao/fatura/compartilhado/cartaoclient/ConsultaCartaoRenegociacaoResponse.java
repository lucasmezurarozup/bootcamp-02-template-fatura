package br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ConsultaCartaoRenegociacaoResponse {

    private String id;
    private BigDecimal quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    public ConsultaCartaoRenegociacaoResponse(String id, BigDecimal quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
