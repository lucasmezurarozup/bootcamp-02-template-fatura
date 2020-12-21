package br.com.zup.nossoCartao.fatura.fatura;

import java.math.BigDecimal;

public class FaturaFakeResponse {
    String numero;
    BigDecimal saldo;
    BigDecimal limite;

    public FaturaFakeResponse(String numero, BigDecimal saldo, BigDecimal limite) {
        this.numero = numero;
        this.saldo = saldo;
        this.limite = limite;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getLimite() {
        return limite;
    }
}
