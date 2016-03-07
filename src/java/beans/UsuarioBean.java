/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UsuarioDAO;
import dao.jpa.UsuarioJPA;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import util.Mensagens;
import util.SenhaHashing;

@ManagedBean
@SessionScoped
public class UsuarioBean {

    private final UsuarioDAO usuarioDAO = new UsuarioJPA();
    private Usuario novoUsuario = new Usuario();
    private Usuario usuarioSessao = null;
    private String confirmarSenha;

    public boolean estahLogado() {
        return (usuarioSessao != null);
    }

    public String salvar() {
        if (!confirmarSenha.equals(novoUsuario.getSenha())) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "As senhas informadas não conferem!",
                    null);
            return null;
        }
        if (usuarioDAO.existeUsuarioComEmail(novoUsuario.getEmail())) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "Usuário já cadastrado com esse email!",
                    null);
            return null;
        }

        // Gera hash para senha do usuário
        novoUsuario.setSenha(
                SenhaHashing.hash(novoUsuario.getSenha()));
        usuarioDAO.salvar(novoUsuario);
        Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                "Usuário cadastrado com sucesso! Autentique-se para entrar...", null);
        novoUsuario = new Usuario();

        return "index.xhtml?faces-redirect=true";

    }

    public String autenticar() {
        // Gera hash para senha do usuário
        novoUsuario.setSenha(
                SenhaHashing.hash(novoUsuario.getSenha()));

        usuarioSessao = usuarioDAO.porEmaileSenha(novoUsuario.getEmail(),
                novoUsuario.getSenha());
        novoUsuario = new Usuario();
        if (usuarioSessao == null) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR,
                    "Email ou senha inválidos!",
                    null);
            return null;
        }
        return "tarefas.xhtml?faces-redirect=true";
    }

    public String sair() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();
        novoUsuario = new Usuario();
        return "index.xhtml?faces-redirect=true";
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public Usuario getNovoUsuario() {
        return novoUsuario;
    }

    public void setNovoUsuario(Usuario novoUsuario) {
        this.novoUsuario = novoUsuario;
    }

    public Usuario getUsuarioSessao() {
        return usuarioSessao;
    }

    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }

}
