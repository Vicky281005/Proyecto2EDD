package EDD;

/**
 *
 * @author jmmor
 */
public class NodoArbol {
    private Object data;
    private NodoArbol[] hijos;
    int numHijos;

    public NodoArbol(Object data, int maxHijos) {
        this.data = data;
        this.hijos = new NodoArbol[maxHijos];
        this.numHijos = 0;
    }
  
/**
 * Agrega un hijo al arbol
 * @param hijo
 * @return 
 */
    public boolean agregarHijo(NodoArbol hijo) {
        if (numHijos < hijos.length) {
            hijos[numHijos++] = hijo; 
            return true;
        } else {
            return false;
        }
    }
     public boolean eliminarHijo(String data) {
            for (int i = 0; i < numHijos; i++) {
                if (hijos[i].getData().equals(data)) {
                    for (int j = i; j < numHijos - 1; j++) {
                        hijos[j] = hijos[j + 1];
                    }
                    hijos[--numHijos] = null; 
                    return true;
                }
            }
            return false; 
        }
    
    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return the numHijos
     */
    public int getNumHijos() {
        return numHijos;
    }

    /**
     * @param numHijos the numHijos to set
     */
    public void setNumHijos(int numHijos) {
        this.numHijos = numHijos;
    }

    /**
     * @return the hijos
     */
    public NodoArbol[] getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(NodoArbol[] hijos) {
        this.hijos = hijos;
    }

}
