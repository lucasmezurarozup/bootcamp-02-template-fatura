package br.com.zup.nossoCartao.fatura.fatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {
    Fatura findByCartaoId(String numeroCartao);
}
