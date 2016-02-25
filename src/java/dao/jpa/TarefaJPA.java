/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.jpa;

import dao.TarefaDAO;
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
public class TarefaJPA implements TarefaDAO {

    @PersistenceUnit(unitName = "TodoListPU")
    private EntityManagerFactory emf;

    @Override
    public void salvar(Tarefa tarefa) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if (tarefa.getId() == null) {
            em.persist(tarefa);
        } else {
            em.merge(tarefa);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Tarefa> todos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Tarefa> tq = em.createNamedQuery(Tarefa.TODOS, 
                Tarefa.class);
        List<Tarefa> tarefas = tq.getResultList();
        em.close();
        return tarefas;    
    }

}
