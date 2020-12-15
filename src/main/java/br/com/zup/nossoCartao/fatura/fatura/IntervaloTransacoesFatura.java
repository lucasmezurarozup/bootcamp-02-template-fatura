package br.com.zup.nossoCartao.fatura.fatura;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class IntervaloTransacoesFatura {

    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;

    public IntervaloTransacoesFatura(int diaFechamentoFatura) {
        this.criaIntervaloParaContabilizarFatura(diaFechamentoFatura);
    }

    public void criaIntervaloParaContabilizarFatura(int diaFechamentoFatura) {
        LocalDate dataHoje = LocalDate.now();
        int anoAtual = dataHoje.getYear();
        int mesAtual = dataHoje.getMonthValue();
        LocalDate dataInicial = LocalDate.of(anoAtual, mesAtual, 1);
        LocalDate dataFinal = dataInicial.withDayOfMonth(dataInicial.lengthOfMonth());

        this.dataInicial = LocalDateTime.of(dataInicial, LocalTime.of(0, 0, 0));
        this.dataFinal = LocalDateTime.of(dataFinal, LocalTime.of(23, 59, 59));

    }

    public LocalDateTime getDataInicial() {
        return dataInicial;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }
}
