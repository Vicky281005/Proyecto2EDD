
package EDD;

/**
 *
 * @author mishel
 */
public class Nodo {
    private Object dato;
    private Nodo pNext;


/**
 * Constructor de la Clase Nodo el cual es el nodo que se va a utilizar en la clase Lista y Pila/Cola
 * @param dato 
 */    
    public Nodo(Object dato) {
        this.dato = dato;
        this.pNext = null;
    }

    
/**
 * Getters y Setters de la Clase Nodo el cual es el nodo que se va a utilizar en la clase Lista y Pila/Cola
 * @return 
 */
    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Nodo getpNext() {
        return pNext;
    }

    public void setpNext(Nodo pNext) {
        this.pNext = pNext;
    }

    Object getData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     
}