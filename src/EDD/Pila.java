
package EDD;

/**
 *
 * @author jmmor
 */
public class Pila {
    private Nodo pInicio;
    private int size;

    public Pila(Nodo pInicio, int size) {
        this.pInicio = pInicio;
        this.size = size;
    }
     /**
     * @return the pInicio
     */
    public Nodo getpInicio() {
        return pInicio;
    }

    /**
     * @param pInicio the pInicio to set
     */
    public void setpInicio(Nodo pInicio) {
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
