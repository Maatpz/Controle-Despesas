package com.controle.despesas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controle.despesas.models.Usuario;


public interface UsuariorRepository extends JpaRepository<Usuario, UUID>{

    Optional<Usuario> findByNomeContainingIgnoreCaseAndEmailContainingIgnoreCase(
        String nome, String email);
       
    Optional<Usuario> findByEmail(String email);

   
    boolean existsByEmail(String email);


}     
