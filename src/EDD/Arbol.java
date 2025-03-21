package EDD;

import Hashtable.ListaEnlazada;
import MainClass.Pregunta;

/**
 *
 * @author jmmor
 */
public class Arbol {

    private NodoArbol raiz;
    public int size;

    public Arbol() {
        this.raiz = null;
        this.size = 0;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    /**
     * Metodo para corroborar si la raiz es vacia
     *
     * @return
     */
    public boolean esVacio() {
        return raiz == null;
    }

    /**
     * Inserta un nodo en el arbol binario
     *
     * @param data
     */
    public NodoArbol insertar(Object data, boolean respuesta) {
        if (this.buscar(data) != null) {
            if (this.raiz.getData().equals(data)) {
                return raiz;
            }
            return null;
        }
        raiz = insertarNodo(raiz, data, respuesta);
        this.size += 1;
        return raiz;
    }

    private NodoArbol insertarNodo(NodoArbol nodo, Object data, boolean respuesta) {
        if (nodo == null) {
            nodo = new NodoArbol();
            nodo.setData(data);
            return nodo;
        } else if (nodo.getData().equals(data)) {
            return nodo;
        }

        if (!respuesta) {
//            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            nodo.setHijoIzq(insertarNodo(nodo.getHijoIzq(), data, respuesta));
        } else if (respuesta) {
            nodo.setHijoDer(insertarNodo(nodo.getHijoDer(), data, respuesta));
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        } else {
            System.out.println("No se puede insertar: el nodo ya tiene dos hijos.");
        }

        return nodo;
    }

    /**
     * Busca un nodo en el arbol
     *
     * @param data
     * @return
     */
    public NodoArbol buscar(Object data) {
        return buscarNodo(raiz, data);
    }

    private NodoArbol buscarNodo(NodoArbol nodo, Object data) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getData().equals(data)) {
            return nodo;
        }
        NodoArbol n = buscarNodo(nodo.getHijoIzq(), data);
        if (n != null) {
            return n;
        }
        n = buscarNodo(nodo.getHijoDer(), data);

        return n;
    }

    /**
     * Elimina los nodos del arbol
     *
     * @param data
     */
    public void eliminar(Object data) {
        raiz = eliminarNodo(raiz, data);
    }

    private NodoArbol eliminarNodo(NodoArbol nodo, Object data) {
        if (nodo == null) {
            return null;
        }

        if (nodo.getData().equals(data)) {
            if (nodo.getHijoIzq() == null && nodo.getHijoDer() == null) {
                return null;
            }
            if (nodo.getHijoIzq() == null) {
                return nodo.getHijoDer();
            }
            if (nodo.getHijoDer() == null) {
                return nodo.getHijoIzq();
            }
            Object valorMin = encontrarMin(nodo.getHijoDer());
            nodo.setData(valorMin);
            nodo.setHijoDer(eliminarNodo(nodo.getHijoDer(), valorMin));
        } else {
            nodo.setHijoIzq(eliminarNodo(nodo.getHijoIzq(), data));
            nodo.setHijoDer(eliminarNodo(nodo.getHijoDer(), data));
        }

        return nodo;
    }

    /**
     * Metodo de recorrido de los arboles
     *
     * @param nodo
     * @return
     */
    private Object encontrarMin(NodoArbol nodo) {
        while (nodo.getHijoIzq() != null) {
            nodo = nodo.getHijoIzq();
        }
        return nodo.getData();
    }

    public NodoArbol[] preorden() {
        NodoArbol[] n = preordenRec(raiz, new NodoArbol[size], 0);
        System.out.println("*******************************************************************************************");
        for (int i = 0; i < n.length; i++) {
            if (n[i] != null) {
                System.out.println(n[i].getData());
            }
        }
        System.out.println("*******************************************************************************************");

        return n;

    }

    private NodoArbol[] preordenRec(NodoArbol nodo, NodoArbol[] recorrido, int index) {
        if (nodo != null) {
            recorrido[index] = nodo;
            System.out.println(nodo.getData());
            index += 1;
            recorrido = preordenRec(nodo.getHijoIzq(), recorrido, index);
            index += 1;
            recorrido = preordenRec(nodo.getHijoDer(), recorrido, index);
        }
        return recorrido;
    }

    public void inorden() {
        inordenRec(raiz);
        System.out.println();
    }

    private void inordenRec(NodoArbol nodo) {
        if (nodo != null) {
            inordenRec(nodo.getHijoIzq());
            System.out.println(nodo.getData() + " ");
            inordenRec(nodo.getHijoDer());
        }
    }

    public void postorden() {
        postordenRec(raiz);
        System.out.println();
    }

    private void postordenRec(NodoArbol nodo) {
        if (nodo != null) {
            postordenRec(nodo.getHijoIzq());
            postordenRec(nodo.getHijoDer());
            System.out.print(nodo.getData() + " ");
        }
    }

    public String posordenSearch(String clave) {
        return postordenRecSearch(raiz, "", clave);
    }

    private String postordenRecSearch(NodoArbol nodo, String recorrido, String clave) {
        if (nodo != null) {
            if (recorrido.equals("")) {
                recorrido = postordenRecSearch(nodo.getHijoIzq(), recorrido, clave);
            }
            if (recorrido.equals("")) {
                recorrido = postordenRecSearch(nodo.getHijoDer(), recorrido, clave);
            }
            Pregunta p = (Pregunta) nodo.getData();

            if (!recorrido.equals("") || p.getSinopsis().equals(clave)) {
                recorrido = p.getSinopsis() + "\n" + recorrido;
            }
        }
        return recorrido;
    }

  ListaEnlazada imprimirArbol(NodoArbol nodo, String espacio, ListaEnlazada n, int index) {
    if (nodo == null) {
        return n;
    }

    // Almacenar el nodo actual en el arreglo
    n.addLast(nodo);
    index++; // Incrementar el índice después de almacenar el nodo

    // Obtener los datos de los hijos para imprimir
    String r = "";
    String i = "";
    if (nodo.getHijoIzq() != null) {
        i = nodo.getHijoIzq().getData().toString();
    }
    if (nodo.getHijoDer() != null) {
        r = nodo.getHijoDer().getData().toString();
    }
    System.out.println(nodo.getData() + "   HIJOS:  " + i + "///////" + r);

    // Imprimir el subárbol izquierdo
    n = imprimirArbol(nodo.getHijoIzq(), espacio, n, index);
    
    // Imprimir el subárbol derecho
    n = imprimirArbol(nodo.getHijoDer(), espacio, n, index);
    
    return n;
}

// Método para iniciar la impresión desde la raíz
public ListaEnlazada imprimir() {
    ListaEnlazada n = new ListaEnlazada(); // Asegúrate de que 'size' sea el número total de nodos
    return imprimirArbol(raiz, "", n, 0);
}
}
