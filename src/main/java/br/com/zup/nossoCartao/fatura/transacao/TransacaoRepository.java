package br.com.zup.nossoCartao.fatura.transacao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {
    List<Transacao> findTop10ByCartaoId(String numeroCartao);

    List<Transacao> findByCartaoIdAndEfetivadaEmBetween(String numeroCartao, LocalDateTime efetivadaEmInicio, LocalDateTime efetivadaEmFinal);

    Optional<List<Transacao>> findByCartaoId(String cartaoId);
}
