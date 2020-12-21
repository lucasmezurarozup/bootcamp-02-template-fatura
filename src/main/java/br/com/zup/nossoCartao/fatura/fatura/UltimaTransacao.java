package br.com.zup.nossoCartao.fatura.fatura;

import br.com.zup.nossoCartao.fatura.transacao.Cartao;
import br.com.zup.nossoCartao.fatura.transacao.Estabelecimento;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "ultimas_transacoes_fatura"
)
public class UltimaTransacao {

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

    private boolean contabilizada = false;

    @NotNull
    @PastOrPresent
    private LocalDateTime efetivadaEm;

    @ManyToOne
    private Fatura fatura;

    public UltimaTransacao() {
    }

    public UltimaTransacao(@NotBlank String id, @NotNull BigDecimal valor, @NotNull Estabelecimento estabelecimento, @NotNull Cartao cartao, boolean contabilizada, @NotNull @PastOrPresent LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.contabilizada = contabilizada;
        this.efetivadaEm = efetivadaEm;
    }

    public boolean isContabilizada() {
        return contabilizada;
    }

    public void setContabilizada(boolean contabilizada) {
        this.contabilizada = contabilizada;
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
                ", contabilizada=" + contabilizada +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }
}
