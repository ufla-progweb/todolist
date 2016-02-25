/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Paulo
 */
public class TarefaDuplicada extends Exception {
    @Override
    public String getMessage() {
        return "Não pode haver duas tarefas com mesma descrição e deadline!";
    }
}
