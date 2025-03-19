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
     /**
      * Agrega un nodo hijo
      * @param hijo
      * @return 
      */
     public boolean agregarHijo(NodoArbol hijo) {
        if (this.hijoIzq == null) {
            this.hijoIzq = hijo;
            return true;
        } else if (this.hijoDer == null) {
            this.hijoDer = hijo;
            return true;
        } else {
            return false; 
        }
    }
     /**
      * Elimina un nodo hijo
      * @param data
      * @return 
      */
      public boolean eliminarHijo(Object data) {
        if (this.hijoIzq != null && this.hijoIzq.getData().equals(data)) {
            this.hijoIzq = null;
            return true;
        } else if (this.hijoDer != null && this.hijoDer.getData().equals(data)) {
            this.hijoDer = null;
            return true;
        }
        return false;
    }
    
    
}

   