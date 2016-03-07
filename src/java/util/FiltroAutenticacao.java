/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.UsuarioBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author Paulo
 */
public class FiltroAutenticacao implements PhaseListener {

    private List<String> allowedPages = new ArrayList<String>();

    public FiltroAutenticacao() {
        allowedPages.add("/index.xhtml");
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        if (!allowedPages.contains(viewId)) {
            if (Sessao.obterUsuarioSessao() == null) {
                NavigationHandler navigator = context
                        .getApplication()
                        .getNavigationHandler();
                Mensagens.adicionarMensagem(FacesMessage.SEVERITY_ERROR,
                        "Usuário não autenticado!", null);
                navigator.handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
            }
        } else {
            if (Sessao.obterUsuarioSessao() != null) {
                NavigationHandler navigator = context
                        .getApplication()
                        .getNavigationHandler();
                navigator.handleNavigation(context, null, "/tarefas.xhtml?faces-redirect=true");
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
