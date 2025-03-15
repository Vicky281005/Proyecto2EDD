package EDD;

/**
 *
 * @author jmmor
 */
public class NodoA {
    private Object data;
    private NodoA hijoIzq;
    private NodoA hijoDer;

    
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
    public NodoA getHijoIzq() {
        return hijoIzq;
    }

    /**
     * @param hijoIzq the hijoIzq to set
     */
    public void setHijoIzq(NodoA hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    /**
     * @return the hijoDer
     */
    public NodoA getHijoDer() {
        return hijoDer;
    }

    /**
     * @param hijoDer the hijoDer to set
     */
    public void setHijoDer(NodoA hijoDer) {
        this.hijoDer = hijoDer;
    }
    
    
    
    

}
