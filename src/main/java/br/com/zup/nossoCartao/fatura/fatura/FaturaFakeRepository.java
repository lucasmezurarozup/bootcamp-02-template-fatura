package br.com.zup.nossoCartao.fatura.fatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaturaFakeRepository extends JpaRepository<FaturaFake, Long> {
    FaturaFake findByCartaoId(String numeroCartao);
}
