package br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient;

public class ConsultaDisponibilidadeVinculacaoCartaoRequest {

    private String nome;
    private String documento;
    private String idProposta;

    @Deprecated
    private ConsultaDisponibilidadeVinculacaoCartaoRequest() {

    }

    public ConsultaDisponibilidadeVinculacaoCartaoRequest(String nome, String documento, String idProposta) {
        this.nome = nome;
        this.documento = documento;
        this.idProposta = idProposta;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
