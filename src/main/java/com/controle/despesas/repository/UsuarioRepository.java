package com.controle.despesas.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.controle.despesas.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNomeContainingIgnoreCaseAndEmailContainingIgnoreCase(
        String nome, String email);
       
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}     
