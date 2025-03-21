package EDD;

import Hashtable.ListaEnlazada;
import MainClass.Pregunta;
import java.util.Random;

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
    
    public int getNivelesDelArbol(){
        if (this.raiz == null) return -1;
        
        int levels = 0;
        NodoArbol aux = this.raiz;
        while (aux != null){
            aux = aux.getHijoIzq();            
            if (aux != null) levels++;
        }
        return levels;
    }
    
    public void insertarCaracteristasAux(NodoArbol root, Object data, int maxLevelAllowed) {
    NodoArbol nuevoNodo = new NodoArbol(data);
    
    // Si el árbol está vacío, establecer el nuevo nodo como raíz
    if (this.raiz == null) {
        this.raiz = nuevoNodo;
        System.out.println("Tomalo");
        return;
    }

    // Estructura de datos para manejar el recorrido por niveles
    Cola cola = new Cola();
    Cola niveles = new Cola(); // Para llevar seguimiento del nivel actual

    cola.insert(root); // Añadir la raíz a la cola
    niveles.insert(0); // Nivel inicial de la raíz

    while (!cola.isEmpty()) {
        NodoArbol actual = (NodoArbol) cola.delete(); // Extraer el nodo actual
        int nivelActual = Integer.valueOf(niveles.delete().toString()); // Extraer el nivel actual

        // Si alcanzamos el nivel máximo, insertar los nuevos hijos
        if (nivelActual == maxLevelAllowed) {
            if (actual.getHijoIzq() == null) {
                actual.setHijoIzq(nuevoNodo); // Asignar como hijo izquierdo
            }
            if (actual.getHijoDer() == null) {
                actual.setHijoDer(nuevoNodo); // Asignar como hijo derecho
            }
            System.out.println("Nodos añadidos en nivel " + maxLevelAllowed);
            continue;
        }

        // Continuar recorriendo los hijos, si existen
        if (actual.getHijoIzq() != null) {
            cola.insert(actual.getHijoIzq());
            niveles.insert(nivelActual + 1); // Incrementar el nivel
        }
        if (actual.getHijoDer() != null) {
            cola.insert(actual.getHijoDer());
            niveles.insert(nivelActual + 1); // Incrementar el nivel
        }
    }
}

    private void insertarCaracteristasGrafoAux(NodoArbol root, Object data, int maxLevelAllowed, int currentLevel, ListaEnlazada ids){
       
        NodoArbol nuevoNodo = new NodoArbol(data);
        
       if(this.raiz == null){
           this.raiz=nuevoNodo;
           System.out.println("Tomalo");
           return;
       }
       
       if (root != null){
           insertarCaracteristasGrafoAux(root.getHijoIzq(), data, maxLevelAllowed, currentLevel+1, ids);
           insertarCaracteristasGrafoAux(root.getHijoDer(), data, maxLevelAllowed, currentLevel+1, ids);
       }
       
       if (root != null && maxLevelAllowed==currentLevel){
           Random random = new Random();     
           int id = random.nextInt(1000);
                while (!ids.isNotInList(String.valueOf(id))){
                    id = random.nextInt(1000);
                    
                } 
                ids.addLast(String.valueOf(id));
                nuevoNodo = new NodoArbol(String.valueOf(data)+String.valueOf(id));
                
                System.out.println(nuevoNodo.getData());
                root.setHijoDer(nuevoNodo);
                
                while (!ids.isNotInList(String.valueOf(id))){
                    id = random.nextInt(1000);
                }           
                ids.addLast(String.valueOf(id));
                nuevoNodo = new NodoArbol(String.valueOf(data)+String.valueOf(id));
               
                System.out.println(nuevoNodo.getData());
                root.setHijoIzq(nuevoNodo);               
       }
    }
    
    public void insertarCaracteristicas(ListaEnlazada caracteristicas, boolean esParaGrafo){
        Nodo caracteristicaTomada = caracteristicas.eliminarYTomarpFirst();
        ListaEnlazada ids = new ListaEnlazada();
        int contador = 0;
        
        while (caracteristicaTomada != null){
            System.out.println(caracteristicas.getSize());
           String data = String.valueOf(caracteristicaTomada.getData());
            int nivelesDeArbol = this.getNivelesDelArbol();
            if (!esParaGrafo) {
                this.insertarCaracteristasAux(raiz, data, nivelesDeArbol);
            } else {
                this.insertarCaracteristasAux(raiz, data, nivelesDeArbol);
//                this.insertarCaracteristasGrafoAux(raiz, data, nivelesDeArbol, 0, ids);
            }

            caracteristicaTomada = caracteristicas.eliminarYTomarpFirst();
            contador++;
        }
        
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
    System.out.println("raiz");
    System.out.println(this.raiz.getData());
    System.out.println(this.raiz.getHijoIzq().getData());
    System.out.println(this.raiz.getHijoDer().getData());
    return imprimirArbol(raiz, "", n, 0);
}
}
