
package EDD;

/**
 *
 * @author jmmor
 */
public class Arbol {
    private NodoA raiz;

    public Arbol() {
        this.raiz = null;
    }
       
    public NodoA getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoA raiz) {
        this.raiz = raiz;
    }
    
     public boolean esVacio(){
        return raiz == null;
    }
    
    public NodoA buscarPadre(NodoA raiz, String padre){
        NodoA encontrado = null;
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
         NodoA nodoHijo = new NodoA();
         
         if(this.esVacio()){
             this.raiz = nodoHijo;
         }
         else {
         NodoA nodoPadre = buscarPadre(this.raiz, padre);
         if(nodoPadre == null){
             System.out.println("No existe el padre");
         } else if (side == "left" & nodoPadre.getHijoIzq() == null){
             nodoPadre.setHijoIzq(nodoHijo);
         }else if (side == "right" & nodoPadre.getHijoDer() == null){
             nodoPadre.setHijoDer(nodoHijo);
         }else{
             System.out.println("No se puede agregar");
         }
         }
    }
     
     public String preOrden(){
         return preOrden(this.raiz, "");
     }
     
     private String preOrden(NodoA raiz, String ruta){
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
     private String inOrden(NodoA raiz, String ruta){
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
    private String postOrden(NodoA raiz, String ruta){
         if (raiz.getHijoDer()!= null){
             ruta = postOrden(raiz.getHijoDer(), ruta);}
         
         if (raiz.getHijoIzq()!= null){
             ruta = postOrden(raiz.getHijoIzq(), ruta);
         }
         ruta += raiz.getData();
         return ruta;
    }
         
         
     
     
     
    
   
    
    
    
}
