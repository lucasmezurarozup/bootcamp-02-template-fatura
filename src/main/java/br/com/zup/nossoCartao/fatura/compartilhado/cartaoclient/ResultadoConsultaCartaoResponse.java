package br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ResultadoConsultaCartaoResponse {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;
    private String idProposta;
    private List<ConsultaCartaoBloqueioResponse> bloqueios;
    private List<ConsultaCartaoAvisoResponse> avisos;
    private List<ConsultaCartaoCarteiraResponse> carteiras;
    private List<ConsultaCartaoParcelaResponse> parcelas;
    private ConsultaCartaoRenegociacaoResponse renegociacao;
    private ConsultaCartaoVencimentoResponse vencimento;

    public ResultadoConsultaCartaoResponse(String id,
                                           LocalDateTime emitidoEm,
                                           String titular,
                                           BigDecimal limite,
                                           String idProposta,
                                           List<ConsultaCartaoBloqueioResponse> bloqueios,
                                           List<ConsultaCartaoAvisoResponse> avisos,
                                           List<ConsultaCartaoCarteiraResponse> carteiras,
                                           List<ConsultaCartaoParcelaResponse> parcelas,
                                           ConsultaCartaoRenegociacaoResponse renegociacao,
                                           ConsultaCartaoVencimentoResponse vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.idProposta = idProposta;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
    }

    public void setBloqueios(List<ConsultaCartaoBloqueioResponse> bloqueios) {
        this.bloqueios = bloqueios;
    }

    public void setAvisos(List<ConsultaCartaoAvisoResponse> avisos) {
        this.avisos = avisos;
    }

    public void setCarteiras(List<ConsultaCartaoCarteiraResponse> carteiras) {
        this.carteiras = carteiras;
    }

    public void setParcelas(List<ConsultaCartaoParcelaResponse> parcelas) {
        this.parcelas = parcelas;
    }

    public void setRenegociacao(ConsultaCartaoRenegociacaoResponse renegociacao) {
        this.renegociacao = renegociacao;
    }

    public void setVencimento(ConsultaCartaoVencimentoResponse vencimento) {
        this.vencimento = vencimento;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public List<ConsultaCartaoBloqueioResponse> getBloqueios() {
        return bloqueios;
    }

    public List<ConsultaCartaoAvisoResponse> getAvisos() {
        return avisos;
    }

    public List<ConsultaCartaoCarteiraResponse> getCarteiras() {
        return carteiras;
    }

    public List<ConsultaCartaoParcelaResponse> getParcelas() {
        return parcelas;
    }

    public ConsultaCartaoRenegociacaoResponse getRenegociacao() {
        return renegociacao;
    }

    public ConsultaCartaoVencimentoResponse getVencimento() {
        return vencimento;
    }
}
