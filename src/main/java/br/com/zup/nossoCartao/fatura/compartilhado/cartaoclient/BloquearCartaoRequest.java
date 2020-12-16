package br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient;

public class BloquearCartaoRequest {
    private String id;

    public BloquearCartaoRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
