/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import java.io.Serializable;

/**
 *
 * @author joseh
 */
public class Ordem implements Serializable{
    
    private String atributo;
    private String label;
    private String operador;

    /**
     * @return the atributo
     */
    public String getAtributo() {
        return atributo;
    }

    /**
     * @param atributo the atributo to set
     */
    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the operador
     */
    public String getOperador() {
        return operador;
    }

    /**
     * @param operador the operador to set
     */
    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Ordem(String atributo, String label, String operador) {
        this.atributo = atributo;
        this.label = label;
        this.operador = operador;
    }

    @Override
    public String toString() {
        return "Ordem{" + "label=" + label + '}';
    }
    
    
}
