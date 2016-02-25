package dao;

import java.util.List;
import modelo.Tarefa;

public interface TarefaDAO {
    public void salvar(Tarefa tarefa);
    public List<Tarefa> todos();
}
