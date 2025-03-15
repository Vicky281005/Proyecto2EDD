package EDD;

/**
 *
 * @author jmmor
 */
public class NodoArbol {
    private Object data;
    private NodoArbol hijoIzq;
    private NodoArbol hijoDer;

    
    /**
     * Comprueba si el nodo tiene un hijo izquierdo
     * @return 
     */
    public boolean tieneIzquierda(){
        return this.getHijoIzq() != null;
    }    
    /**
     * Comprueba si el nodo tiene un hijo derecho
     * @return 
     */
    public boolean tieneDerecha(){
        return this.getHijoDer() != null;
    } 
    /**
     * Comprueba que no tenga hijos
     * @return 
     */
    public boolean esHoja() {
    return this.getHijoIzq() == null && this.getHijoDer() == null;}
    
    
    /**
     * Cuenta el numero de hijos que tiene los nodos
     * @return 
     */
    public int contarHijos() {
    int count = 0;
    if (this.getHijoIzq() != null) count++;
    if (this.getHijoDer() != null) count++;
    return count;
}

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return the hijoIzq
     */
    public NodoArbol getHijoIzq() {
        return hijoIzq;
    }

    /**
     * @param hijoIzq the hijoIzq to set
     */
    public void setHijoIzq(NodoArbol hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    /**
     * @return the hijoDer
     */
    public NodoArbol getHijoDer() {
        return hijoDer;
    }

    /**
     * @param hijoDer the hijoDer to set
     */
    public void setHijoDer(NodoArbol hijoDer) {
        this.hijoDer = hijoDer;
    }
    
    
    
    

}
