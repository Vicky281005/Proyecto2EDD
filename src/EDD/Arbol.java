
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
    
     public boolean esVacio(){
        return raiz == null;
    }
    
    public NodoArbol buscarPadre(NodoArbol raiz, String padre){
        NodoArbol encontrado = null;
        if (raiz.getData() == padre){
            return raiz;
        }
        if(raiz.getHijoIzq()!= null && encontrado == null){
            encontrado = buscarPadre(raiz.getHijoIzq(), padre);
        }
        if (raiz.getHijoDer() != null && encontrado == null){
            encontrado = buscarPadre(raiz.getHijoDer(), padre);
        }
        return encontrado;
    }
    
     public void agregarNodo(String hijo, String padre, String side){
         NodoArbol nodoHijo = new NodoArbol();
         
         if(this.esVacio()){
             this.raiz = nodoHijo;
         }
         else {
         NodoArbol nodoPadre = buscarPadre(this.raiz, padre);
         if(nodoPadre == null){
             System.out.println("No existe el padre");
         } else if ("left".equals(side) && nodoPadre.getHijoIzq() == null){
             nodoPadre.setHijoIzq(nodoHijo);
         } else if ("right".equals(side) && nodoPadre.getHijoDer() == null){
             nodoPadre.setHijoDer(nodoHijo);
         } else{
             System.out.println("No se puede agregar");
         }
         }
    }
     
     public String preOrden(){
         return preOrden(this.raiz, "");
     }
     
     private String preOrden(NodoArbol raiz, String ruta){
         ruta += raiz.getData();
         if(raiz.getHijoIzq()!= null){
             ruta = preOrden(raiz.getHijoIzq(), ruta);

         }     
         if (raiz.getHijoDer()!= null){
             ruta = preOrden(raiz.getHijoDer(), ruta);
         
         }
         return ruta;
     }
     
     public String inOrden(){
         return preOrden(this.raiz, "");
     }
     private String inOrden(NodoArbol raiz, String ruta){
         if (raiz.getHijoDer()!= null){
             ruta = inOrden(raiz.getHijoDer(), ruta);
         } ruta += raiz.getData();
         
         if (raiz.getHijoIzq()!= null){
             ruta = inOrden(raiz.getHijoIzq(), ruta);
         }
     return ruta;
     }
    public String postOrden(){
        return preOrden(this.raiz, "");
    }
    private String postOrden(NodoArbol raiz, String ruta){
         if (raiz.getHijoDer()!= null){
             ruta = postOrden(raiz.getHijoDer(), ruta);}
         
         if (raiz.getHijoIzq()!= null){
             ruta = postOrden(raiz.getHijoIzq(), ruta);
         }
         ruta += raiz.getData();
         return ruta;
    }

    
}
