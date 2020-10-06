package com.springbootapi.springsenai.controller;

import com.springbootapi.springsenai.model.Usuario;
import com.springbootapi.springsenai.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepo repo;

    @GetMapping
    @CrossOrigin
    public List<Usuario> listUsuario(){
        List<Usuario> usuarios = (List<Usuario>) repo.findAll();
        for(int i = 0; i < usuarios.size(); i++){
            Usuario user = usuarios.get(i);
        };
        return usuarios;
    }

    @PostMapping
    @CrossOrigin
    public Object create(@RequestBody Usuario user){
        try {
            return repo.save(user);
        }catch (Exception e){
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public Object update(@RequestBody Usuario newUser, @PathVariable int id){
        try{
            Usuario user = repo.findById(id).get();

            user.setNome(newUser.getNome());
            user.setEmail(newUser.getEmail());
            user.setSenha(newUser.getSenha());

            return repo.save(user);
        }catch (Exception e){
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public Boolean delete(@PathVariable int id){
        try{
            repo.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public Object getById(@PathVariable int id){
        try {
            Usuario user = repo.findById(id).get();

            return user;
        }catch (Exception e){
            System.out.println(e);
            return "User not found!";
        }
    }
}
