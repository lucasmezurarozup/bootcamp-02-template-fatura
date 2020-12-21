package br.com.zup.nossoCartao.fatura.fatura;

import br.com.zup.nossoCartao.fatura.transacao.Transacao;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "faturas"
)
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalId;

    @Transient
    private UUID externalId = UUID.randomUUID();

    @NotNull
    private BigDecimal limite;

    @NotNull
    private BigDecimal saldo;

    private String cartaoId;

    private LocalDate criadaEm = LocalDate.now();

    @UpdateTimestamp
    private LocalDate atualizadaEm;

    @OneToMany(cascade = CascadeType.ALL)
    private List<UltimaTransacao> transacoes;

    private Fatura() {

    }

    public Fatura(@NotNull String cartaoId, @NotNull BigDecimal limite, @NotNull BigDecimal saldo, List<UltimaTransacao> transacoes) {
        this.cartaoId = cartaoId;
        this.limite = limite;
        this.saldo = saldo;
        this.transacoes = transacoes;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public List<UltimaTransacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<UltimaTransacao> transacoes) {
        this.transacoes = transacoes;
    }

    public void atualizaSaldo(BigDecimal contabilizacao) {
        this.saldo = this.saldo.subtract(contabilizacao);
    }
    @Override
    public String toString() {
        return "Fatura{" +
                "internalId=" + internalId +
                ", externalId=" + externalId +
                ", limite=" + limite +
                ", saldo=" + saldo +
                ", cartaoId='" + cartaoId + '\'' +
                ", criadaEm=" + criadaEm +
                ", atualizadaEm=" + atualizadaEm +
                ", transacoes=" + transacoes +
                '}';
    }
}
