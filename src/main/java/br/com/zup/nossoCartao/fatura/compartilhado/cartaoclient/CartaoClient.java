package br.com.zup.nossoCartao.fatura.compartilhado.cartaoclient;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cartoes", url = "${apis_externas.cartoes.url:http://localhost:8888/api}")
public interface CartaoClient {

    @RequestLine("GET")
    @RequestMapping(method = RequestMethod.GET, value = "/cartoes/{id}")
    public ResultadoConsultaCartaoResponse informacoesCartao(@PathVariable("id") String id);

    @RequestLine("POST")
    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{id}/bloqueios")
    public BloqueioCartaoResultadoResponse bloquearCartao(@PathVariable("id") String id, @RequestBody BloquearCartaoRequest bloquearCartaoRequest);

}
