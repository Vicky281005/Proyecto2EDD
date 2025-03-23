package EDD;

import Hashtable.ListaEnlazada;
import MainClass.Pregunta;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author jmmor
 */
public class Arbol {

    private NodoArbol raiz;
    public int size;

    
    
    /**
     * Constructor por defecto de la clase Arbol. Inicializa un árbol vacío con
     * la raíz establecida en null y un tamaño de 0.
     */
    public Arbol() {
        this.raiz = null;
        this.size = 0;
    }

    /**
     * Obtiene la raíz del árbol.
     *
     * @return el nodo raíz del árbol. Si el árbol está vacío, retorna null.
     */
    public NodoArbol getRaiz() {
        return raiz;
    }
    
    /**
     * Establece el nodo raíz del árbol.
     *
     * @param raiz el nodo que se asignará como la nueva raíz del árbol.
     */

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
     * Inserta un nuevo nodo en el árbol de forma recursiva.
     *
     * @param nodo el nodo actual del árbol en el que se está realizando la
     * inserción.
     * @param data el dato que se insertará en el árbol.
     * @param respuesta un valor booleano que indica si el nodo debe insertarse
    *    como hijo izquierdo (false) o hijo derecho (true).
     * @return el nodo actualizado después de la operación de inserción.
     */
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
     * Calcula el número de niveles en el árbol, basándose en los nodos hijos
     * izquierdos.
     *
     * @return el número de niveles del árbol. Si el árbol está vacío, retorna -1.
     */
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
    
/**
 * Inserta un nodo en el árbol de manera controlada por niveles.
 * Si el árbol está vacío, el nuevo nodo se establece como la raíz. De lo contrario,
 * el método recorre el árbol por niveles, utilizando estructuras de datos auxiliares
 * para mantener un seguimiento del nivel actual de cada nodo. Si se alcanza el nivel
 * máximo permitido, el nuevo nodo se inserta como hijo izquierdo o derecho del nodo actual,
 * 
 * @param root el nodo raíz desde el cual comienza el recorrido del árbol.
 * @param data los datos que se asignarán al nuevo nodo.
 * @param maxLevelAllowed el número máximo de niveles permitidos en el árbol.
 */
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

    /**
     * Inserta características en el árbol de manera recursiva, gestionando  identificadores únicos y verificando el nivel máximo permitido.
     *
     * @param root el nodo raíz desde el cual comienza la inserción.
     * @param data los datos del nuevo nodo.
     * @param maxLevelAllowed el número máximo de niveles permitidos en el  árbol. Cuando se alcanza este nivel,
     * se añaden nodos con identificadores únicos.
     * @param currentLevel el nivel actual del nodo procesado, utilizado para verificar la profundidad.
     * @param ids una lista enlazada que almacena los identificadores únicos generados para evitar duplicados.
     */
    private void insertarCaracteristasGrafoAux(NodoArbol root, Object data, int maxLevelAllowed, int currentLevel, ListaEnlazada ids){
         long startTime = System.nanoTime();

        NodoArbol nuevoNodo = new NodoArbol(data);
        
       if(this.raiz == null){
           this.raiz=nuevoNodo;
           return;
       }
       
       if (root != null){
           System.out.println("jola");
           insertarCaracteristasGrafoAux(root.getHijoIzq(), data, maxLevelAllowed, currentLevel+1, ids);
       long endTime = System.nanoTime();
        
       System.out.println(endTime-startTime);
            if (endTime - startTime > 3103800) {
                this.volverRaizNula();
                return;
            }
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
               System.out.println("?/?/?/?");
                System.out.println(nuevoNodo.getData());
                root.setHijoIzq(nuevoNodo);               
       }
       
    }
    
    /**
     * Inserta una lista de características en el árbol, manejando nodos.
     *
     * El método recorre la lista de características y las inserta en el árbol
     * dependiendo del valor del parámetro `esParaGrafo`. Si `esParaGrafo` es
     * true, utiliza lógica específica para gráficos, asignando identificadores
     * únicos a los nodos. De lo contrario, realiza una inserción estándar en el
     * árbol.
     *
     * @param caracteristicas una lista enlazada de características a insertar en el árbol.
     * @param esParaGrafo un valor booleano que indica si las inserciones son específicas para un grafo (true) o para un árbol (false).
     */
    public void insertarCaracteristicas(ListaEnlazada caracteristicas, boolean esParaGrafo){
        Nodo caracteristicaTomada = caracteristicas.eliminarYTomarpFirst();
        ListaEnlazada ids = new ListaEnlazada();
        int contador = 0;
        
        while (caracteristicaTomada != null){
            System.out.println(caracteristicas.getSize());
           String data = String.valueOf(caracteristicaTomada.getData());
            int nivelesDeArbol = this.getNivelesDelArbol();
            if (!esParaGrafo) {
                JOptionPane.showMessageDialog(null, "YA entre");
                this.insertarCaracteristasAux(raiz, data, nivelesDeArbol);
            } else {
                this.insertarCaracteristasGrafoAux(raiz, data, nivelesDeArbol, 0, ids);
                System.out.println("CARACTERISTICA TOMADA");
                System.out.println(caracteristicaTomada);
                
//Tienes que ir insertando y viendo si es True or False, y si llegaste al final de la lista ya no tienes que hacer más preguntas.

//                this.insertarCaracteristasGrafoAux(raiz, data, nivelesDeArbol, 0, ids);
            }

            caracteristicaTomada = caracteristicas.eliminarYTomarpFirst();
            contador++;
            if (this.raiz == null) {
                    JOptionPane.showMessageDialog(null, "El arbol es muy grande para ser mostrado por completo de forma rapida. Determine especie para mostrar un sub arbol de recorrido");
                    caracteristicaTomada = null;
                    caracteristicas = null;
                    break;
             }
            System.out.println("Sali //////////////////////////////////////////////////");
            
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

    
    /**
     * Busca de manera recursiva un nodo en el árbol que contenga el dato especificado.
     *
     * @param nodo el nodo desde el cual comienza la búsqueda.
     * @param data el dato que se busca en el árbol.
     * @return el nodo que contiene los datos especificados, o null si no se encuentra.
     */
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
    
    public void volverRaizNula() {
        this.raiz = null;
        this.size = 0;
    }

    
    /**
     * Elimina un nodo del árbol de manera recursiva según los datos especificados.
     *
     * Este método maneja tres casos principales para el nodo a eliminar: 
     * 1. Si el nodo no tiene hijos, simplemente se elimina retornando null. 
     * 2. Si el nodo tiene un solo hijo (izquierdo o derecho), se reemplaza por ese hijo.
     * 3. Si el nodo tiene dos hijos, se busca el valor mínimo del subárbol derecho, se 
     * reemplaza el valor del nodo actual con el valor mínimo, y  luego se elimina el nodo que contenía el valor mínimo.
     *
     * @param nodo el nodo actual desde el que se realiza la búsqueda y eliminación.
     * @param data el dato del nodo que se desea eliminar.
     * @return el nodo actualizado después de la operación de eliminación.
     */
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
 * Encuentra el valor mínimo en el subárbol proporcionado.
 * Este método recorre los nodos hijos izquierdos de manera iterativa
 * hasta alcanzar el nodo más bajo (sin hijo izquierdo).
 * @param nodo el nodo raíz del subárbol en el cual se realizará la búsqueda.
 * @return el valor mínimo contenido en el nodo más bajo del subárbol.
 */
    private Object encontrarMin(NodoArbol nodo) {
        while (nodo.getHijoIzq() != null) {
            nodo = nodo.getHijoIzq();
        }
        return nodo.getData();
    }

    /**
     * Realiza un recorrido en preorden del árbol y retorna un arreglo con los nodos visitados en ese orden.
     *
     * Este método utiliza un método auxiliar recursivo (`preordenRec`) para
     * recopilar los nodos del árbol en el orden de preorden. 
     * @return un arreglo de nodos que contiene los nodos del
     * árbol en el orden de preorden. 
     */
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

    
    /**
     * Método auxiliar recursivo para realizar un recorrido en preorden en el árbol.
     * Este método se utiliza internamente para llenar un arreglo con los nodos
     * del árbol en el orden de preorden. Actualiza el índice en cada llamada
     * recursiva para posicionar los nodos correctamente en el arreglo.
     *
     * @param nodo el nodo actual del árbol que se está procesando. Puede ser  null.
     * @param recorrido un arreglo de nodos que almacenará los nodos en el orden de preorden.
     * @param index la posición actual en el arreglo donde se debe colocar el  nodo procesado.
     * @return el arreglo actualizado con los nodos en orden de preorden.
     */
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
//    System.out.println(nodo.getData() + "   HIJOS:  " + i + "///////" + r);

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
