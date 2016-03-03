/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = Usuario.EXISTE_USUARIO_COM_EMAIL, query = "select count(u) from Usuario u where u.email = :email"),
    @NamedQuery(name = Usuario.POR_EMAIL_E_SENHA, query = "select u from Usuario u where u.email = :email and u.senha = :senha")
})
public class Usuario {

    public static final String EXISTE_USUARIO_COM_EMAIL = "Usuario.existeUsuarioComEmail";
    public static final String POR_EMAIL_E_SENHA = "Usuario.porEmailESenha";

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
