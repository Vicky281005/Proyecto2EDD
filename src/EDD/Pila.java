/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import EDD.NodoA;

/**
 *
 * @author jmmor
 */
public class Pila {
    private NodoA pInicio;
    private int size;

    public Pila(NodoA pInicio, int size) {
        this.pInicio = pInicio;
        this.size = size;
    }
     /**
     * @return the pInicio
     */
    public NodoA getpInicio() {
        return pInicio;
    }

    /**
     * @param pInicio the pInicio to set
     */
    public void setpInicio(NodoA pInicio) {
        this.pInicio = pInicio;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean esVacio(){
        return pInicio == null;
    }
    
    public Object leerCabeza(){
        return pInicio.getData();
    }
    
    
}
