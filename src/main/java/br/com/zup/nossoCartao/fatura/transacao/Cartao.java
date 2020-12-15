package br.com.zup.nossoCartao.fatura.transacao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(
        name = "cartoes"
)
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalId;

    @NotBlank
    private String id;
    @NotBlank
    private String email;

    public Cartao(@NotBlank String id, @NotBlank String email) {
        this.id = id;
        this.email = email;
    }

    @Deprecated
    private Cartao() {

    }



    public Long getInternalId() {
        return internalId;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "internalId=" + internalId +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
