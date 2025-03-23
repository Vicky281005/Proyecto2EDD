
package Hashtable;

import EDD.Arbol;
import EDD.Nodo;

/**
 *
 * @author NITRO V 15
 */
public class TablaHash {
    private ListaEnlazada[] tabla;
    private int capacidad;
    

    // Constructor con capacidad inicial
    public TablaHash(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new ListaEnlazada[capacidad];

        // Inicializar cada posición con una nueva Lista
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new ListaEnlazada();
        }
    }

    public TablaHash(Arbol arbolParaGrafo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
   //Getters y Setters
    public ListaEnlazada[] getTabla() {
        return tabla;
    }

    public void setTabla(ListaEnlazada[] tabla) {
        this.tabla = tabla;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    // Función hash básica para claves tipo String
    private int hash(Object clave) {
        return Math.abs(clave.hashCode()) % capacidad;
    }

    // Insertar un elemento con una clave
    public void insertar(Object clave, Object valor) {
        int indice = hash(clave);
        Pair par = new Pair(clave, valor);

        // Evitar duplicados de clave
        if (!contieneClave(clave)) {
            tabla[indice].addFirst(par);
        } else {
            System.out.println("Clave duplicada: " + clave);
        }
    }
    
    // Buscar un valor por clave
    public Object obtener(Object clave) {
        int indice = hash(clave);
        ListaEnlazada lista = tabla[indice];

        Nodo actual = lista.getpFirst();
        while (actual != null) {
            Pair par = (Pair) actual.getData();
            if (par.getClave().equals(clave)) {
                return par.getValor();
            }
            actual = actual.getpNext();
        }
        return null;
    }
    
    // Verifica si existe una clave
    public boolean contieneClave(Object clave) {
        int indice = hash(clave);
        ListaEnlazada lista = tabla[indice];

        Nodo actual = lista.getpFirst();
        while (actual != null) {
            Pair par = (Pair) actual.getData();
            if (par.getClave().equals(clave)) {
                return true;
            }
            actual = actual.getpNext();
        }
        return false;
    }

    // Eliminar un elemento por clave
    public void eliminar(Object clave) {
        int indice = hash(clave);
        ListaEnlazada lista = tabla[indice];

        Nodo actual = lista.getpFirst();
        while (actual != null) {
            Pair par = (Pair) actual.getData();
            if (par.getClave().equals(clave)) {
                lista.eliminarPorReferencia(par);
                return;
            }
            actual = actual.getpNext();
        }
    }

    // Mostrar toda la tabla hash
    public void mostrarTabla() {
        for (int i = 0; i < capacidad; i++) {
            System.out.println("Índice " + i + ":");
            Nodo actual = tabla[i].getpFirst();
            while (actual != null) {
                Pair par = (Pair) actual.getData();
                System.out.println("  Clave: " + par.getClave() + " - Valor: " + par.getValor());
                actual = actual.getpNext();
            }
        }
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Clase interna para guardar clave y valor
    private class Pair {
        private Object clave;
        private Object valor;

        public Pair(Object clave, Object valor) {
            this.clave = clave;
            this.valor = valor;
        }

        public Object getClave() {
            return clave;
        }

        public Object getValor() {
            return valor;
        }
    }

//    int key;
//    public ListaEnlazada[] hash; // Declarar sin inicializar todavía
//
//    // Constructor que recibe la cantidad de filas
//    public TablaHash(int filas) {
//        hash = new ListaEnlazada[filas]; // Inicializamos con el tamaño indicado
//        for (int i = 0; i < hash.length; i++) {
//            hash[i] = new ListaEnlazada(); // Inicializamos cada posición con una lista enlazada
//        }
//    }
//
//    public void agregar(int valor) {
//        Nodo<Integer> nuevo = new Nodo<>(valor);
//        nuevo.setData(valor);
//        key = valor % hash.length; // Usamos 'hash.length' para calcular la clave de forma dinámica
//        hash[key].addLast(valor);
//    }
//    
//    public void imprimir(){
//        System.out.println("Llave");
//        for(int i=0; i<hash.length; i++){
//            System.out.println(i + "\t");
//            hash[i].imprimirLista();
//        }
//    }
//    
//    public void borrar(int valor){
//       key = valor % hash.length;
//       hash[key].eliminarPorReferencia(valor);
//        
//    }
}

    

