package EDD;

/**
 *
 * @author jmmor
 */
public class NodoArbol {
    private Object data;
    private NodoArbol hijoIzq;
    private NodoArbol hijoDer;
    private NodoArbol papa;
    
    public NodoArbol (){
    }
    public NodoArbol (Object data){
        this.data = data;
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

    public NodoArbol getPapa() {
        return papa;
    }

    public void setPapa(NodoArbol papa) {
        this.papa = papa;
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
     public NodoArbol agregarHijo(NodoArbol hijo, boolean valor) {
         if (this.hijoIzq != null && !valor) {
            return hijoIzq;
        } else if (this.hijoDer != null && valor) {
            return hijoDer;
        }
         
         
        if (this.hijoIzq == null && !valor) {
            this.hijoIzq = hijo;
            return hijo;
        } else if (this.hijoDer == null && valor) {
            this.hijoDer = hijo;
            return hijo;
        } else {
            return null; 
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

   