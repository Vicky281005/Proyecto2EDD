package EDD;

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
    public void insertar(Object data) {
        raiz = insertarNodo(raiz, data);
    this.size += 1;
    }

    private NodoArbol insertarNodo(NodoArbol nodo, Object data) {
        if (nodo == null) {
            nodo = new NodoArbol();
            nodo.setData(data);
            return nodo;
        }

        if (nodo.getHijoIzq() == null) {
            nodo.setHijoIzq(insertarNodo(nodo.getHijoIzq(), data));
        } else if (nodo.getHijoDer() == null) {
            nodo.setHijoDer(insertarNodo(nodo.getHijoDer(), data));
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
    public boolean buscar(Object data) {
        return buscarNodo(raiz, data);
    }

    private boolean buscarNodo(NodoArbol nodo, Object data) {
        if (nodo == null) {
            return false;
        }
        if (nodo.getData().equals(data)) {
            return true;
        }
        return buscarNodo(nodo.getHijoIzq(), data) || buscarNodo(nodo.getHijoDer(), data);
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
        return preordenRec(raiz, new NodoArbol[size], 0);
        
    }

    private NodoArbol[] preordenRec(NodoArbol nodo, NodoArbol[] recorrido, int index) {
        if (nodo != null) {
            recorrido[index] = nodo;
            recorrido = preordenRec(nodo.getHijoIzq(), recorrido, index +1);
            recorrido = preordenRec(nodo.getHijoDer(), recorrido, index +1);
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
            System.out.print(nodo.getData() + " ");
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
}
