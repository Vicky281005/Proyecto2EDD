
package Hashtable;

import EDD.Nodo;

/**
 *
 * @author NITRO V 15
 */
public class MetodoHash {
    int key;
    public ListaEnlazada[] hash; // Declarar sin inicializar todavía

    // Constructor que recibe la cantidad de filas
    public MetodoHash(int filas) {
        hash = new ListaEnlazada[filas]; // Inicializamos con el tamaño indicado
        for (int i = 0; i < hash.length; i++) {
            hash[i] = new ListaEnlazada(); // Inicializamos cada posición con una lista enlazada
        }
    }

    public void agregar(int valor) {
        Nodo<Integer> nuevo = new Nodo<>(valor);
        nuevo.setData(valor);
        key = valor % hash.length; // Usamos 'hash.length' para calcular la clave de forma dinámica
        hash[key].addLast(valor);
    }
    
    public void imprimir(){
        System.out.println("Llave");
        for(int i=0; i<hash.length; i++){
            System.out.println(i + "\t");
            hash[i].imprimirLista();
        }
    }
    
    public void borrar(int valor){
       key = valor % hash.length;
       hash[key].eliminarPorReferencia(valor);
        
    }
}

    

