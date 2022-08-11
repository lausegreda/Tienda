package com.Tienda.dao;

import com.Tienda.domain.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {
    Usuario findByUsername(String username); // Username va a ser el campo a buscar findBy....campo a buscar
    
    List<Usuario> findByIdRol(Long idRol);
}
