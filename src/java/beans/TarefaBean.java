/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.TarefaDAO;
import dao.jpa.TarefaJPA;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Tarefa;

@ManagedBean
@ViewScoped
public class TarefaBean implements Serializable {

    public static final String PR_ALTA = "Alta";
    public static final String PR_MEDIA = "Média";
    public static final String PR_BAIXA = "Baixa";

    private Tarefa novaTarefa = new Tarefa();
    private List<Tarefa> tarefas;
    private TarefaDAO tarefaDAO = new TarefaJPA();

    @PostConstruct
    private void carregarTarefas() {
        tarefas = tarefaDAO.todos();
    }
    
    public void salvar() {
        FacesMessage mensagem;
        if (!getTarefas().contains(novaTarefa)) {
            tarefaDAO.salvar(novaTarefa);
            novaTarefa = new Tarefa();
            carregarTarefas();
            mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Tarefa cadastrada com sucesso!", null);
        } else {
            mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não pode haver duas tarefas com mesma descrição e deadline!",
                    null);
        }
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }

    public void concluir(Tarefa tarefa) {
        tarefaDAO.concluir(tarefa.getId());
        carregarTarefas();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Tarefa concluída com sucesso!", null));
    }

    public String[] getPrioridades() {
        String vetPrioridades[] = {PR_ALTA, PR_MEDIA, PR_BAIXA};
        return vetPrioridades;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public Tarefa getNovaTarefa() {
        return novaTarefa;
    }

    public void setNovaTarefa(Tarefa novaTarefa) {
        this.novaTarefa = novaTarefa;
    }

}
