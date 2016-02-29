/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jpa;

import dao.TarefaDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import modelo.Tarefa;

/**
 *
 * @author Paulo
 */
public class TarefaJPA implements TarefaDAO, Serializable {

    @Override
    public void salvar(Tarefa tarefa) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(tarefa);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Tarefa> todos() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Tarefa> tq = em.createNamedQuery(Tarefa.TODOS,
                Tarefa.class);
        List<Tarefa> tarefas = tq.getResultList();
        em.close();
        return tarefas;
    }

    @Override
    public void concluir(Long tarefaId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Tarefa tarefa = em.find(Tarefa.class, tarefaId);
        if (tarefa != null) {
            em.getTransaction().begin();
            em.remove(tarefa);
            em.getTransaction().commit();
        }        
        em.close();
    }

}
