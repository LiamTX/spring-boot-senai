package com.springbootapi.springsenai.repo;

import com.springbootapi.springsenai.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

}
