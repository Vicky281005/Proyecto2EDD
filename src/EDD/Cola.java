
package EDD;

import javax.swing.JOptionPane;

/**
 * Clase para hacer los recorridos
 * @author mishel
 */
public class Cola {
    private Nodo pFirts;
    private Nodo pLast;
    private int size;

    
/**
 * Constructor de la clase Cola
 */
    public Cola() {
        this.pFirts = this.pLast = null;
        this.size = 0;
    }

/**
 * Getters y Setters Clase Cola
 * @return 
 */
    public Nodo getpFirts() {
        return pFirts;
    }

    /**
     * Establece el primer nodo de la cola.
     * @param pFirts el nodo que se asignará como el primer nodo de la cola.
     */
    public void setpFirts(Nodo pFirts) {
        this.pFirts = pFirts;
    }

    /**
     * Obtiene el último nodo de la cola.
     *
     * @return el nodo que se encuentra al final de la cola. 
     */
    public Nodo getpLast() {
        return pLast;
    }

    /**
     * Establece el último nodo de la cola.
     *
     * @param pLast el nodo que se asignará como el último nodo de la cola.
     */
    public void setpLast(Nodo pLast) {
        this.pLast = pLast;
    }

    /**
     * Obtiene el tamaño actual de la cola.
     *
     * @return el número de elementos presentes en la cola.
     */
    public int getSize() {
        return size;
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return true si la cola no contiene elementos, false en caso contrario.
     */
    public boolean isEmpty() {
        return this.pFirts == null;
    }

/**
 * Inserta un nuevo elemento al final de la cola.
 * Si la cola está vacía, el nuevo nodo se establece como el primer y último nodo.
 * De lo contrario, el método recorre hasta el final de la cola y agrega el nuevo nodo
 * al final, actualizando también la referencia al último nodo.
 * 
 * @param dato el dato que se asociará al nuevo nodo a insertar en la cola.
 */
    public void insert(Object dato){
        Nodo pNew = new Nodo(dato);
        if(this.isEmpty()){
            this.setpFirts(pNew);
            this.setpLast(pNew);
        }else{
            Nodo aux = this.pFirts;
            while (aux.getpNext() != null) {
                aux = aux.getpNext();
            }
            aux.setpNext(pNew);
            this.setpLast(pNew);
        }
    }

    
/**
 * Elimina y retorna el primer elemento de la cola.
 * 
 * Este método sigue estas reglas:
 * 1. Si la cola está vacía, retorna null.
 * 2. Si la cola tiene un solo elemento, elimina ese elemento, actualiza las referencias
 *    del primer y último nodo a null, disminuye el tamaño y retorna el dato eliminado.
 * 3. Si la cola tiene más de un elemento, elimina el primer nodo, actualiza la referencia
 *    al siguiente nodo como el nuevo primer nodo, disminuye el tamaño y retorna el dato eliminado.
 * 
 * @return el dato del nodo eliminado, o null si la cola está vacía.
 */
    public Object delete(){
        if(!this.isEmpty()){
            if(size == 1){
                Object eliminado = this.pFirts.getData();
                this.setpFirts(null);
                this.setpLast(null);
                size--;
                return eliminado;
            }else{
                Object eliminado = this.pFirts.getData();
                this.setpFirts(this.pFirts.getpNext());
                size--;
                 return eliminado;
            }
            
        }
        
        return null;
    }

/**
 * Imprime los elementos de la cola en un cuadro de diálogo.
 * Si la cola está vacía, muestra un mensaje indicando que la lista está vacía.
 */

    public void print(){
        if(!this.isEmpty()){
            Nodo aux = this.pFirts;
            String colaStr = "";
            while(aux.getpNext() != null){
                colaStr += aux.getData() + "\n";
                aux = aux.getpNext();
            }
            
            colaStr += aux.getData();
            
            JOptionPane.showMessageDialog(null, colaStr);
            
        }else{
            JOptionPane.showMessageDialog(null, "La lista esta vacia.");
        }
    }
    
}
