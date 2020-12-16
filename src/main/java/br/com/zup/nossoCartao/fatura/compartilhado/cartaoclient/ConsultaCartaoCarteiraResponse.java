package br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient;

import java.time.LocalDateTime;

public class ConsultaCartaoCarteiraResponse {
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    public ConsultaCartaoCarteiraResponse(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }


}
