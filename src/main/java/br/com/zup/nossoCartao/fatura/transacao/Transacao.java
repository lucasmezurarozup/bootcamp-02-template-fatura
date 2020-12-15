package br.com.zup.nossoCartao.fatura.transacao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "transacoes"
)
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalId;

    @Transient
    private UUID externalId = UUID.randomUUID();

    @NotBlank
    private String id;

    @NotNull
    private BigDecimal valor;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Estabelecimento estabelecimento;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Cartao cartao;

    @NotNull
    @PastOrPresent
    private LocalDateTime efetivadaEm;

    public Transacao() {
    }

    public Long getInternalId() {
        return internalId;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public String getId() {
        return id;
    }


    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "internalId=" + internalId +
                ", externalId=" + externalId +
                ", id='" + id + '\'' +
                ", valor=" + valor +
                ", estabelecimento=" + estabelecimento +
                ", cartao=" + cartao +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }
}
