
package Hashtable;

import EDD.Nodo;
import javax.swing.JOptionPane;

/**
 *
 * @author mishel
 */
public class ListaEnlazada<T> {
    private Nodo pFirst; 
    private Nodo pLast;
    private int size; 

    /**
     * Constructor Crea una lista vacia
     */
    public ListaEnlazada() {
        this.pFirst = this.pLast = null;
    }

    /**
     * Constructor Crea una lista con un solo elemento
     *
     * @param n Nodo que se aniadira
     */
    public ListaEnlazada(Nodo n) {
        this.pFirst = this.pLast = n;
    }

  
/**
 * Getters y Setters de la clase Lista
 * @return 
 */    
    public Nodo getpFirst() {
        return pFirst;
    }

    public void setpLast(Nodo pLast) {
        this.pLast = pLast;
    }

    public Nodo getpLast() {
        return pLast;
    }

    public void setpFirst(Nodo pFirst) {
        this.pFirst = pFirst;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * Revisa si la lista esta vacia o no
     *
     * @return True si esta vacia, false si no
     */
    public boolean isEmpty() {
        return this.pFirst == null;
    }

 /**
     * Se encarga de añadir un nodo en el primer lugar de una lista
     *
     * @param dato
     */
    public void addFirst(T dato) {
        Nodo<T> n = new Nodo(dato);
        if (isEmpty()) {
            this.pFirst = n;
            this.pLast = n;
            this.pFirst.setpNext(this.pLast);
            this.pLast.setpNext(null);
        } else {
            n.setpNext(this.pFirst);
            this.pFirst = n;
        }
        size++;
    }

 public void addLast(T dato) {
    Nodo<T> n = new Nodo(dato);
    if (isEmpty()) {
        this.pFirst = this.pLast= n;
    } else {
        this.pLast.setpNext(n); // Conectar el último nodo actual al nuevo nodo
        this.pLast = n;         // Actualizar pLast al nuevo nodo
    }
    size++;
}

/**
 * Método para insertar dato por posición
 * @param posicion
 * @param valor 
 */
    public void insertarPorPosicion(int posicion, T valor) {
    if (posicion < 0 || posicion > size) {
        throw new IndexOutOfBoundsException("Posición inválida");
    }
    Nodo<T> nuevo = new Nodo<>(valor);
    if (posicion == 0) { // Insertar en la primera posición
        nuevo.setpNext(pFirst);
        pFirst = nuevo;
        if (size == 0) { // Si la lista estaba vacía, pLast también apunta a nuevo
            pLast = nuevo;
        }
    } else if (posicion == size) { // Insertar al final usando pLast
        pLast.setpNext(nuevo);
        pLast = nuevo;
    } else { // Insertar en una posición intermedia
        Nodo<T> aux = pFirst;
        for (int i = 0; i < (posicion - 1); i++) {
            aux = aux.getpNext();
        }
        nuevo.setpNext(aux.getpNext());
        aux.setpNext(nuevo);
    }
    size++;
}
    

/**
 * Método para insertar dato por referencia
 * @param ref
 * @param valor 
 */
    public void insertarPorReferencia(Object ref, Object valor) {

        Nodo nuevo = new Nodo(valor);

        if (!this.isEmpty()) {
            if (buscar(ref)) {
                Nodo aux = pFirst;
                // Recorre la lista hasta llegar al nodo de referencia
                while (aux.getData() != ref) {
                    aux = aux.getpNext();
                }
                // Crea un respaldo de la continuación de la lista
                Nodo siguiente = aux.getpNext();
                // Enlaza el nuevo nodo despues del nodo de referencia
                aux.setpNext(nuevo);
                // Une la continuacion de la lista al nuevo nodo
                nuevo.setpNext(siguiente);

                size++;
            }
        }
    }

/**
 * Función para eliminar dato ubicado al inicio
 * @return 
 */
    public boolean eliminarpFirst() {
        if (!this.isEmpty()) {
            pFirst = pFirst.getpNext();
            size--;
            return true;
        } else {
            return false;
        }
    }
    public Nodo eliminarYTomarpFirst() {
        Nodo aux = pFirst;
        
        if (!this.isEmpty()) {
            pFirst = pFirst.getpNext();
            size--;
        } 
        
        return aux;
    }

/**
 * Método para eliminar dato ubicado al final
 */
    public void eliminarpLast() {
        if (!this.isEmpty()) {
            if (getSize() == 1) {
                this.eliminar();
            } else {
                Nodo pointer = getpFirst();
                while (pointer.getpNext().getpNext() != null) {
                    pointer = pointer.getpNext();
                }
                pointer.setpNext(null);
            }
            size--;
        }
    }

    
    public void imprimirLista(){
        System.out.println("-----------------------------------");
        Nodo nodo= this.pFirst;
    
    while(nodo!= null){
        int data= (int) nodo.getData();
        System.out.println(data);
        nodo=nodo.getpNext();
    }
    }
    
/**
 * Método para eliminar dato por referencia
 * @param referencia 
 */
    public void eliminarPorReferencia(Object referencia) {

        if (this.buscar(referencia)) {
            if (pFirst.getData() == referencia) {
                pFirst = pFirst.getpNext();
            } else {
                Nodo aux = pFirst;
                while (aux.getpNext().getData() != referencia) {
                    aux = aux.getpNext();
                }
                Nodo siguiente = aux.getpNext().getpNext();
                aux.setpNext(siguiente);
            }
            size--;
        }
    }

/**
 * Método para eliminar dato por la posición
 * @param posicion 
 */
    public void eliminarPorPosicion(int posicion) {

        if (posicion >= 0 && posicion < size) {
            if (posicion == 0) {
                pFirst = pFirst.getpNext();
            } else {
                Nodo aux = pFirst;
                for (int i = 0; i < posicion - 1; i++) {
                    aux = aux.getpNext();
                }
                Nodo siguiente = aux.getpNext();
                aux.setpNext(siguiente.getpNext());
            }
            size--;
        }
    }

/**
 * Método para editar el valor de un nodo por referencia
 * @param referencia
 * @param dato 
 */
    public void editarPorReferencia(Object referencia, Object dato) {
        if (buscar(referencia)) {
            Nodo aux = pFirst;
            while (aux.getData() != referencia) {
                aux = aux.getpNext();
            }
            aux.setData(dato);
        }
    }

/**
 * Método para editar el valor de un nodo en una posición
 * @param posicion
 * @param dato 
 */
    public void editarPorPosicion(int posicion, Object dato) {

        if (posicion >= 0 && posicion < size) {
            if (posicion == 0) {
                pFirst.setData(dato);
            } else {
                Nodo aux = pFirst;

                for (int i = 0; i < posicion; i++) {
                    aux = aux.getpNext();
                }
                aux.setData(dato);
            }
        }
    }

/**
 * Método para obtener el valor de un nodo en una determinada posición
 * @param posicion
 * @return 
 */
    public Object getValor(int posicion) {

        if (posicion >= 0 && posicion < size) {

            if (posicion == 0) {
                return pFirst.getData();
            } else {
                Nodo aux = pFirst;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getpNext();
                }
                return aux.getData();
            }
        }
        return null;
    }

/**
 * Consulta la posición de un elemento en la lista
 * @param referencia
 * @return 
 */
    public int getPosicion(Object referencia) {

        if (buscar(referencia)) {

            Nodo aux = pFirst;
            int cont = 0;
            while (referencia != aux.getData()) {
                cont++;
                aux = aux.getpNext();
            }
            return cont;
        } else {
            return -1;
        }
    }

/**
 * Función para buscar un elemento en la lista
 * @param referencia
 * @return 
 */
    public boolean buscar(Object referencia) {
        Nodo aux = pFirst;
        boolean encontrado = false;
        while (aux != null && encontrado != true) {
            if (referencia == aux.getData()) {
                encontrado = true;
            } else {
                aux = aux.getpNext();
            }
        }
        return encontrado;
    }

/**
 * Método para mostrar un elemento de la lista
 */    
    public void mostrar() {
        if (!this.isEmpty()) {
            Nodo aux = pFirst;
            String expresion = "";
            while (aux != null) {
                expresion = expresion + aux.getData().toString() + "\n";
                aux = aux.getpNext();
            }
            JOptionPane.showMessageDialog(null, expresion);

        } else {
            JOptionPane.showMessageDialog(null, "La lista esta vacia");
        }
    }
    
    public boolean isNotInList(T data) {
        Nodo aux = this.pFirst;
        while (aux != null) {
            if (aux.getData().equals(data)){
                return false;
            }
            aux = aux.getpNext();
        }
        System.out.println("Agregado papu ////////////////////////////////////////// / / / / / / / / / / / /");
        return true;
    }
    
    public void addLastIfItsNotInList(T data) {
        if (this.isNotInList(data)){
            this.addLast(data);
        }
    }

/**
 * Destructor
 */
    public void eliminar() {
        pFirst = null;
        size = 0;
    }
    
}
