package com.controle.despesas.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.controle.despesas.models.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao,UUID>{

}

  

    
