/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author joseh
 */
public class DAOGenerico<TIPO> implements Serializable {

    private List<TIPO> listaObjetos;
    private List<TIPO> listaTodos;
    @PersistenceContext(unitName = "SeguradoraWebPU")
    protected EntityManager em;
    protected Class classePersistente;
    protected String filtro = "";
    protected List<Ordem> listordem = new ArrayList<>();
    protected Ordem ordemAtual;
    protected ConverterOrdem convverterOrdem;
    protected Integer maximoObjetos = 5;
    protected Integer posicao = 0;
    protected Integer totalObjetos = 0;

    public DAOGenerico() {

    }

    public List<TIPO> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName() + " order by id";
        String where = "";
        ///Filtros contra injeção de Sql
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length() > 0) {
            switch (ordemAtual.getAtributo()) {
                case " = ":
                    if (ordemAtual.getAtributo().equals("id")) {
                        try {
                            Integer.parseInt(filtro);
                        } catch (Exception e) {
                            filtro = "0";
                        }
                    }
                    where += " from " + ordemAtual.getAtributo() + " = '" + filtro + "' ";
                    break;
                case "like":
                    where += " where upper(" + ordemAtual.getAtributo() + " ) like '" + filtro.toUpperCase() + "%'";
                    break;
            }
        }
        jpql += where;
        jpql += " order by " + ordemAtual.getAtributo();
        System.out.println("JPQL:" + jpql);
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).
                setFirstResult(posicao).setMaxResults(maximoObjetos).
                getResultList();
    }

    public void primeiro() {
        posicao = 0;
    }

    public void anterior() {
        posicao -= maximoObjetos;
        if (posicao < 0) {
            posicao = 0;
        }
    }

    public void proximo() {
        if (posicao + maximoObjetos < totalObjetos) {
            posicao += maximoObjetos;
        }
    }

    public void ultimo() {
        int resto = totalObjetos % maximoObjetos;
        if (resto > 0) {
            posicao = totalObjetos - resto;
        } else {
            posicao = totalObjetos - maximoObjetos;
        }
    }

    public String getmensagemNavegacao() {
        int ate = posicao + maximoObjetos;
        if (ate > totalObjetos) {
            ate = totalObjetos;
        }
        if(totalObjetos > 0) {
            return "Listando de " + (posicao + 1) + " ate " + ate + " de " + totalObjetos + " registros";
        }else{
            return " Nenhum registro encontrado!";
        }
    }

    public List<TIPO> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName() + " order by " + ordemAtual.getAtributo();
        return em.createQuery(jpql).getResultList();
    }

    public void persist(TIPO obj) throws Exception {
        em.persist(obj);
    }

    public void merge(TIPO obj) throws Exception {
        em.merge(obj);
    }

    public TIPO getObjectByID(Object id) throws Exception {
        return (TIPO) em.find(classePersistente, id);
    }

    public void remove(TIPO obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }

    public void setListaObjetos(List<TIPO> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Ordem> getListordem() {
        return listordem;
    }

    public void setListordem(List<Ordem> listordem) {
        this.listordem = listordem;
    }

    public Ordem getOrdemAtual() {
        return ordemAtual;
    }

    public void setOrdemAtual(Ordem ordemAtual) {
        this.ordemAtual = ordemAtual;
    }

    public ConverterOrdem getConvverterOrdem() {
        return convverterOrdem;
    }

    public void setConvverterOrdem(ConverterOrdem convverterOrdem) {
        this.convverterOrdem = convverterOrdem;
    }

    public Integer getMaximoObjetos() {
        return maximoObjetos;
    }

    public void setMaximoObjetos(Integer maximoObjetos) {
        this.maximoObjetos = maximoObjetos;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }

}
