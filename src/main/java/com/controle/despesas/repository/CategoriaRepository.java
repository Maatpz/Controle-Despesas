package com.controle.despesas.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controle.despesas.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,UUID>{


    List<Categoria> findByUsuarioId(UUID usuarioId);

    
    List<Categoria> findByUsuarioIdAndTipo(UUID usuarioId, String tipo);

    
    List<Categoria> findByNomeContainingIgnoreCaseAndUsuarioId(String nome, UUID usuarioId);
    
}
