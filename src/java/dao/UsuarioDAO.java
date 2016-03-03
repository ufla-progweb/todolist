package dao;

import java.util.List;
import modelo.Tarefa;
import modelo.Usuario;

public interface UsuarioDAO {

    public void salvar(Usuario usuario);

    public boolean existeUsuarioComEmail(String email);

    public Usuario porEmaileSenha(String email, String senha);
}
