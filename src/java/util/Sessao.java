/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.UsuarioBean;
import javax.faces.context.FacesContext;
import modelo.Usuario;

/**
 *
 * @author Paulo
 */
public class Sessao {

    public static Usuario obterUsuarioSessao() {
        FacesContext context = FacesContext.getCurrentInstance();
        UsuarioBean usuarioBean = (UsuarioBean) context.getELContext().getELResolver().getValue(context.getELContext(), null, "usuarioBean");
        if (usuarioBean != null) {
            return usuarioBean.getUsuarioSessao();
        }
        return null;
    }

}
