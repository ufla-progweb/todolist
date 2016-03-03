/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jpa;

import dao.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Tarefa;
import modelo.Usuario;

public class UsuarioJPA implements UsuarioDAO, Serializable {

    @Override
    public void salvar(Usuario usuario) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario porEmaileSenha(String email, String senha) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Usuario> tq = em.createNamedQuery(Usuario.POR_EMAIL_E_SENHA,
                    Usuario.class);
            tq.setParameter("email", email);
            tq.setParameter("senha", senha);
            List<Usuario> lu = tq.getResultList();
            if (lu == null || lu.isEmpty()) {
                return null;
            }
            return lu.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existeUsuarioComEmail(String email) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Number> tq = em.createNamedQuery(Usuario.EXISTE_USUARIO_COM_EMAIL, Number.class);
            tq.setParameter("email", email);
            int count = tq.getSingleResult().intValue();
            return (count != 0);
        } finally {
            em.close();
        }
    }

}
