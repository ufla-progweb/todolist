/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.TarefaDAO;
import dao.jpa.TarefaJPA;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Tarefa;
import modelo.Usuario;
import util.Mensagens;
import util.Sessao;

@ManagedBean
@ViewScoped
public class TarefaBean implements Serializable {

    public static final String PR_ALTA = "Alta";
    public static final String PR_MEDIA = "Média";
    public static final String PR_BAIXA = "Baixa";

    private Tarefa novaTarefa = new Tarefa();
    private List<Tarefa> tarefas;
    private TarefaDAO tarefaDAO = new TarefaJPA();
    private Usuario usuarioSessao = Sessao.obterUsuarioSessao();
    
    @PostConstruct
    private void carregarTarefas() {
        tarefas = tarefaDAO.todos(usuarioSessao.getId());
    }

    public String salvar() {
        if (!getTarefas().contains(novaTarefa)) {
            novaTarefa.setUsuario(usuarioSessao);
            tarefaDAO.salvar(novaTarefa);
            novaTarefa = new Tarefa();
            carregarTarefas();
            Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                    "Tarefa cadastrada com sucesso!", null);
        } else {
            Mensagens.adicionarMensagem(FacesMessage.SEVERITY_ERROR,
                    "Não pode haver duas tarefas com mesma descrição e deadline!",
                    null);
        }
        return "tarefas.xhtml?faces-redirect=true";
    }

    public String concluir(Tarefa tarefa) {
        tarefaDAO.concluir(tarefa.getId());
        carregarTarefas();
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Tarefa concluída com sucesso!", null);

        return "tarefas.xhtml?faces-redirect=true";
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
