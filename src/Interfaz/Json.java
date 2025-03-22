package Interfaz;

import EDD.Arbol;
import EDD.NodoArbol;
import Hashtable.ListaEnlazada;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map.Entry;
import javax.swing.JFileChooser;

/**
 *
 * @author jmmor
 */
public class Json {

    public static void cargarJson() throws FileNotFoundException {
        Arbol arbol = new Arbol();
        Arbol arbolParaGrafo = new Arbol();
        JFileChooser carga = new JFileChooser();
        int captar = carga.showOpenDialog(null);
        if (captar == JFileChooser.APPROVE_OPTION) {
            File archivoJson = carga.getSelectedFile();
            JsonParser parser = new JsonParser();
            FileReader lector = new FileReader(archivoJson.getPath());
            JsonElement elemento = parser.parse(lector);
            JsonObject objectoRaiz = elemento.getAsJsonObject();
            objectoRaiz.entrySet(); // EntrySet es una lista de claves valor
            JsonArray listaEspecies = null;
            for (var claveValor : objectoRaiz.entrySet()) {
                listaEspecies = claveValor.getValue().getAsJsonArray();
                break;
            }
            System.out.println("lest go");
            ListaEnlazada clavesDelJson = new ListaEnlazada();
            ListaEnlazada valoresDelJsonSinRepeticion = new ListaEnlazada();
            
            for (var objectoEspecie : listaEspecies) {
                var listaEspecieClaveValor = objectoEspecie.getAsJsonObject().entrySet();
                for (var especieClaveValor : listaEspecieClaveValor) {
                    JsonArray listaPreguntas = especieClaveValor.getValue().getAsJsonArray();
                    
                    System.out.println(especieClaveValor.getKey());
                    clavesDelJson.addLast(especieClaveValor.getKey());
                    
                    System.out.println("------------------------------------");
                    System.out.println(listaPreguntas);
                    String key = "";
                    
                    
                    boolean value = false;
                    NodoArbol padre = null;

                    for (var preguntaObjeto : listaPreguntas) {
//                        System.out.println(preguntaObjeto);
                        for (Entry<String, JsonElement> preguntaClaveValor : preguntaObjeto.getAsJsonObject().entrySet()) {            
                            valoresDelJsonSinRepeticion.addLastIfItsNotInList(preguntaClaveValor.getKey());
                        }
                    }
                    
//                    NodoArbol nuevo = new NodoArbol();
//                    if (arbol.buscar(especieClaveValor.getKey()) == null) {
//                        nuevo.setData(especieClaveValor.getKey());
//                        try {
//                            padre.agregarHijo(nuevo, value);
//                            arbol.size += 1;
////                            System.out.println(" --------> " + especieClaveValor.getKey() + " ES HIJO " + value + " DE " + key);
//
//                        } catch (Exception e) {
////                            System.out.println("                                                                    --------> " + especieClaveValor.getKey() + " ES HIJO " + value + " DE " + key);
//
//                        }
////                        System.out.println(" --------> " + especieClaveValor.getKey() + " ES HIJO " + value + " DE " + key);
//                    }

                }

            }
            
            
            
            
            
            arbolParaGrafo = arbol;
            arbolParaGrafo.insertarCaracteristicas(valoresDelJsonSinRepeticion, true);
//            arbol.insertarCaracteristicas(valoresDelJsonSinRepeticion, false);

        }
        
//        arbol.imprimir();
        arbolParaGrafo.imprimir();

        mostrarArbol m = new mostrarArbol(arbolParaGrafo);
        m.setVisible(true);
        
        if (arbolParaGrafo.getRaiz() == null){
            m.setVisible(false);
        }
    }

}
