/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Acessorios;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author joseh
 */
@Stateful
public class AcessoriosDAO<TIPO> extends DAOGenerico<Acessorios> implements Serializable {
    
    public AcessoriosDAO() {
        super();
        classePersistente = Acessorios.class;
        ///definir as possiveis ordens
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        ordemAtual = listaOrdem.get(1);
    }
    
}
