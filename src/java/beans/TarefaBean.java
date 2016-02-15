/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Tarefa;

@ManagedBean
@ViewScoped
public class TarefaBean {

    public static final String PR_ALTA = "Alta";
    public static final String PR_MEDIA = "Média";
    public static final String PR_BAIXA = "Baixa";

    private List<Tarefa> tarefas;
    private Tarefa novaTarefa = new Tarefa();

    @PostConstruct
    public void init() {
        tarefas = new ArrayList<Tarefa>();
        tarefas.add(new Tarefa("Tarefa 1", "Alta"));
        tarefas.add(new Tarefa("Tarefa 2", "Média"));
        tarefas.add(new Tarefa("Tarefa 3", "Baixa"));
        tarefas.add(new Tarefa("Tarefa 4", "Alta"));
    }

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

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public List<String> completarPrioridade(String entrada) {
        List<String> resultados = new ArrayList<String>();
        if (PR_ALTA.contains(entrada)) {
            resultados.add(PR_ALTA);
        }
        if (PR_MEDIA.contains(entrada)) {
            resultados.add(PR_MEDIA);
        }
        if (PR_BAIXA.contains(entrada)) {
            resultados.add(PR_BAIXA);
        }
        return resultados;
    }

    public Tarefa getNovaTarefa() {
        return novaTarefa;
    }

    public void setNovaTarefa(Tarefa novaTarefa) {
        this.novaTarefa = novaTarefa;
    }

}
