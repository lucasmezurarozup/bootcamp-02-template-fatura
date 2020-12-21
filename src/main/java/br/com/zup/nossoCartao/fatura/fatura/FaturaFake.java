package br.com.zup.nossoCartao.fatura.fatura;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(
        name = "faturas_fake"
)
public class FaturaFake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalId;

    @Transient
    private UUID externalId = UUID.randomUUID();

    @NotNull
    private BigDecimal limite;

    @NotNull
    private BigDecimal saldo;

    @NotBlank
    private String cartaoId;

    private FaturaFake() {

    }

    public FaturaFake(@NotNull BigDecimal limite, @NotNull BigDecimal saldo, @NotBlank String cartaoId) {
        this.limite = limite;
        this.saldo = saldo;
        this.cartaoId = cartaoId;
    }

    public Long getInternalId() {
        return internalId;
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

    public String getCartaoId() {
        return cartaoId;
    }

    public void atualizaSaldo(BigDecimal contabilizacao) {
        this.saldo = this.saldo.subtract(contabilizacao);
    }

    @Override
    public String toString() {
        return "FaturaFake{" +
                "internalId=" + internalId +
                ", externalId=" + externalId +
                ", limite=" + limite +
                ", saldo=" + saldo +
                ", cartaoId='" + cartaoId + '\'' +
                '}';
    }
}
