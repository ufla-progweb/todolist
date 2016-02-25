/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

    private List<Tarefa> tarefas = new ArrayList<Tarefa>();
    private Tarefa novaTarefa = new Tarefa();

    public void salvar() {
        FacesMessage mensagem;
        
        if (!tarefas.contains(novaTarefa)) {
            tarefas.add(novaTarefa);
            novaTarefa = new Tarefa();
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
        tarefas.remove(tarefa);
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

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public Tarefa getNovaTarefa() {
        return novaTarefa;
    }

    public void setNovaTarefa(Tarefa novaTarefa) {
        this.novaTarefa = novaTarefa;
    }

}
