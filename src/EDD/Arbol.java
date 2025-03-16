
package EDD;

/**
 *
 * @author jmmor
 */
public class Arbol {
    private NodoArbol raiz;


    public Arbol() {
        this.raiz = null;
    }
       
    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }
    /**
     * Metodo para corroborar si la raiz es vacia
     * @return 
     */
     public boolean esVacio(){
        return raiz == null;
    }
    /**
     * Metodo para buscar un nodo especifico del arbol
     * @param raiz
     * @param valor
     * @return 
     */
    public NodoArbol buscarNodo(NodoArbol raiz, String valor) {
    if (raiz == null) {
        return null;
    }
    if (raiz.getData().equals(valor)) {
        return raiz;
    }
    for (int i = 0; i < raiz.getNumHijos(); i++) {
        NodoArbol encontrado = buscarNodo(raiz.getHijos()[i], valor);
        if (encontrado != null) {
            return encontrado;
        }
    }
    return null;
    }
    /**
     * Metodo para agregar nodos al arbol
     * @param hijo
     * @param padre
     * @param maxHijos 
     */
    public void agregarNodo(String hijo, String padre, int maxHijos) {
    NodoArbol nodoHijo = new NodoArbol(hijo, maxHijos);
    if (this.esVacio()) {
        this.raiz = nodoHijo;
    } else {
        NodoArbol nodoPadre = buscarNodo(this.raiz, padre);
        if (nodoPadre == null) {
        } else if (!nodoPadre.agregarHijo(nodoHijo)) {
        }
    }
    }
    /**
     * Metodo para buscar el padre
     * @param raiz
     * @param valor
     * @return 
     */
    public NodoArbol buscarPadre(NodoArbol raiz, String valor) {
    if (raiz == null || raiz.getNumHijos() == 0) {
        return null; 
    }
    for (int i = 0; i < raiz.getNumHijos(); i++) {
        if (raiz.getHijos()[i].getData().equals(valor)) {
            return raiz; 
        }
        NodoArbol padre = buscarPadre(raiz.getHijos()[i], valor);
        if (padre != null) {
            return padre; 
        }
    }
    return null; 
}

    public boolean eliminarNodo(NodoArbol raiz, String valor) {
    NodoArbol padre = buscarPadre(raiz, valor); 
    if (padre == null) {
        return false; 
    }
    for (int i = 0; i < padre.getNumHijos(); i++) {
        if (padre.getHijos()[i].getData().equals(valor)) {
            for (int j = i; j < padre.getNumHijos() - 1; j++) {
                padre.getHijos()[j] = padre.getHijos()[j + 1];
            }
            padre.getHijos()[padre.getNumHijos() - 1] = null; 
            padre.numHijos--; 
            return true; 
        }
    }
    return false; 
    
    }
    /**
     * Metodo de recorrido preOrden
     * @param raiz
     * @param ruta
     * @return 
     */
    public String preOrden(NodoArbol raiz, String ruta) {
    if (raiz == null) {
        return ruta;
    }
    ruta += raiz.getData() + " "; 
    for (int i = 0; i < raiz.getNumHijos(); i++) {
        ruta = preOrden(raiz.getHijos()[i], ruta); 
    }
    return ruta;
    }
    
    /**
     * Metodo de recorrido en postOrden
     * @param raiz
     * @param ruta
     * @return 
     */
    public String postOrden(NodoArbol raiz, String ruta) {
    if (raiz == null) {
        return ruta;
    }
    for (int i = 0; i < raiz.getNumHijos(); i++) {
        ruta = postOrden(raiz.getHijos()[i], ruta); 
    }
    ruta += raiz.getData() + " "; 
    return ruta;
    }
    
    /**
     * Metodo de recorrido en inOrden
     * @param raiz
     * @param ruta
     * @return 
     */
    public String inOrden(NodoArbol raiz, String ruta) {
    if (raiz == null) {
        return ruta;
    }
    int mitad = raiz.getNumHijos() / 2; 
    for (int i = 0; i < mitad; i++) {
        ruta = inOrden(raiz.getHijos()[i], ruta);
    }
    ruta += raiz.getData() + " "; 

    for (int i = mitad; i < raiz.getNumHijos(); i++) {
        ruta = inOrden(raiz.getHijos()[i], ruta);
    }
    return ruta;
    }



    
    



   
    
}
