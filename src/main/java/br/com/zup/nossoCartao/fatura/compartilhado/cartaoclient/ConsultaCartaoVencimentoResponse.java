package br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient;

import java.time.LocalDateTime;

public class ConsultaCartaoVencimentoResponse {
    private String id;
    private int dia;
    private LocalDateTime dataDeCriacao;

    public ConsultaCartaoVencimentoResponse(String id, int dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
