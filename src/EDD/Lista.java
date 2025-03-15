
package EDD;

import javax.swing.JOptionPane;

/**
 *
 * @author mishel
 */
public class Lista {
    private Nodo pFirst; 
    private int size; 

/**
 * Constructor de la clase Lista
 */    
    public Lista() {
        this.pFirst = null;
        this.size = 0;
    }
  
/**
 * Getters y Setters de la clase Lista
 * @return 
 */    
    public Nodo getpFirst() {
        return pFirst;
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
    
    public boolean isEmpty() {
        return this.pFirst == null;
    }

/**
 * Método para insertar dato al principio
 * @param dato
 * @return 
 */    
    public Nodo insertarInicio(Object dato) {

        Nodo pNew = new Nodo(dato);

        if (this.isEmpty()) {
            pFirst = pNew;
        } else {
            pNew.setpNext(pFirst);
            pFirst = pNew;
        }
        size++;
        return pNew;
    }

/**
 * Método para insertar dato al final
 * @param dato 
 */
    public void insertarFinal(Object dato) {
        Nodo pNew = new Nodo(dato);
        if (this.isEmpty()) {
            pFirst = pNew;
        } else {
            Nodo aux = pFirst;
            while (aux.getpNext() != null) {
                aux = aux.getpNext();
            }
            aux.setpNext(pNew);
        }
        size++;
    }

/**
 * Método para insertar dato por posición
 * @param posicion
 * @param valor 
 */
    public void insertarPorPosicion(int posicion, Object valor) {
        if (posicion >= 0 && posicion < size) {
            Nodo nuevo = new Nodo(valor);
            if (posicion == 0) {
                nuevo.setpNext(pFirst);
                pFirst = nuevo;
            } else {
                if (posicion == size - 1) {
                    Nodo aux = pFirst;
                    while (aux.getpNext() != null) {
                        aux = aux.getpNext();
                    }
                    aux.setpNext(nuevo);
                } else {
                    Nodo aux = pFirst;
                    for (int i = 0; i < (posicion - 1); i++) {
                        aux = aux.getpNext();
                    }
                    Nodo siguiente = aux.getpNext();
                    aux.setpNext(nuevo);
                    nuevo.setpNext(siguiente);
                }
            }
            size++;
        }
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
                while (aux.getDato() != ref) {
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
    public boolean eliminarInicio() {
        if (!this.isEmpty()) {
            pFirst = pFirst.getpNext();
            size--;
            return true;
        } else {
            return false;
        }
    }

/**
 * Método para eliminar dato ubicado al final
 */
    public void eliminarFinal() {
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

/**
 * Método para eliminar dato por referencia
 * @param referencia 
 */
    public void eliminarPorReferencia(Object referencia) {

        if (this.buscar(referencia)) {
            if (pFirst.getDato() == referencia) {
                pFirst = pFirst.getpNext();
            } else {
                Nodo aux = pFirst;
                while (aux.getpNext().getDato() != referencia) {
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
            while (aux.getDato() != referencia) {
                aux = aux.getpNext();
            }
            aux.setDato(dato);
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
                pFirst.setDato(dato);
            } else {
                Nodo aux = pFirst;

                for (int i = 0; i < posicion; i++) {
                    aux = aux.getpNext();
                }
                aux.setDato(dato);
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
                return pFirst.getDato();
            } else {
                Nodo aux = pFirst;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getpNext();
                }
                return aux.getDato();
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
            while (referencia != aux.getDato()) {
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
            if (referencia == aux.getDato()) {
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
                expresion = expresion + aux.getDato().toString() + "\n";
                aux = aux.getpNext();
            }
            JOptionPane.showMessageDialog(null, expresion);

        } else {
            JOptionPane.showMessageDialog(null, "La lista esta vacia");
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
