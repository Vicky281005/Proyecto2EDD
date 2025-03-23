package Interfaz;

import EDD.Arbol;
import EDD.Nodo;
import EDD.NodoArbol;
import Hashtable.ListaEnlazada;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map.Entry;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jmmor
 */
public class Json {
     public static ListaEnlazada valoresDelJsonSinRepeticionCopia = new ListaEnlazada();
     public  static JsonArray listaEspecies;   
     public static ListaEnlazada clavesDelJson;
     public static Arbol arbolParaGrafo; // Variable estática para compartir entre métodos
       
     public static void obtenerListaClaveValorJson(){
         Nodo aux = valoresDelJsonSinRepeticionCopia.getpFirst();
         NodoArbol raizParaArbol = new NodoArbol(aux.getData());
         arbolParaGrafo.volverRaizNula();
         arbolParaGrafo.setRaiz(raizParaArbol);
         NodoArbol auxArbol = raizParaArbol;
         
         int preguntasRealizadas = 0;
         
         Boolean haSidoadivinado = false;
          while (aux != null && !haSidoadivinado){
               String valorDelJson = String.valueOf(aux.getData());
//               NodoArbol auxArbol = new NodoArbol(aux.getData());
               
               
               int seleccion = JOptionPane.showConfirmDialog(null, valorDelJson, "Det si su clave tiene esta caract", JOptionPane.YES_NO_OPTION);
               
               boolean esVerdaderaLaRespuestaDelUsuario = (seleccion == JOptionPane.YES_OPTION);
               preguntasRealizadas++;
               
               Boolean especiesMatcheada = false;
               
               if (!especiesMatcheada){
                for (var objectoEspecie : listaEspecies) {
                     var listaEspecieClaveValor = objectoEspecie.getAsJsonObject().entrySet();
                     for (var especieClaveValor : listaEspecieClaveValor) {
                         JsonArray listaPreguntas = especieClaveValor.getValue().getAsJsonArray();
                         
                         
                         // ------------------------------------------------- modificar valores de clave (agregar valores que faltan a dicha lista de cada clave)
                         
                         Nodo auxi = clavesDelJson.getpFirst();
                         JsonArray nuevoArray = new JsonArray();

                         while (auxi != null) {

                             Boolean seEncuentra = false;
                             Boolean valorAAgregar = false;
                             if (String.valueOf(auxi.getData()).equalsIgnoreCase(String.valueOf(especieClaveValor.getKey()))) {
                                 
                                 Nodo auxilio = valoresDelJsonSinRepeticionCopia.getpFirst();
                                 while (auxilio != null){
                                    for (var preguntaObjeto : listaPreguntas) {
                                        for (Entry<String, JsonElement> preguntaClaveValor : preguntaObjeto.getAsJsonObject().entrySet()) {
                                            if (String.valueOf(auxilio.getData()).equalsIgnoreCase(String.valueOf(preguntaClaveValor.getKey()))) {
                                                seEncuentra = true;
                                                valorAAgregar = Boolean.valueOf(preguntaClaveValor.getValue().toString());
                                            }
                                        }
                                    }
                                    JsonObject nuevoElemento = new JsonObject();
                                    nuevoElemento.addProperty(String.valueOf(auxilio.getData()), seEncuentra ? valorAAgregar : false);
                                    nuevoArray.add(nuevoElemento);
                                    auxilio = auxilio.getpNext();
                                    seEncuentra = false;
                                    valorAAgregar = false;
                                 }
                                 

                             }
                             auxi = auxi.getpNext();

                         }
                         especieClaveValor.setValue(nuevoArray);
//                         JOptionPane.showMessageDialog(null, especieClaveValor.getKey() + nuevoArray);
                         // -------------------------------------------------
 //                        clavesDelJson.addLast(especieClaveValor.getKey());
                         NodoArbol auxNodoComparativo = arbolParaGrafo.getRaiz();
                         int largo = listaPreguntas.size();
                         int contador = 0;
                         Boolean ultimaPregunta = false;

                         if (largo == preguntasRealizadas){     
                             while (auxNodoComparativo != null) {
                                 for (var preguntaObjeto : listaPreguntas) {
                                     for (Entry<String, JsonElement> preguntaClaveValor : preguntaObjeto.getAsJsonObject().entrySet()) {
                                         if (auxNodoComparativo == null) continue;
//                                         JOptionPane.showMessageDialog(null, "// " + preguntaClaveValor.getKey());
//                                         JOptionPane.showMessageDialog(null, "evaluare " + auxNodoComparativo.getData() + " de " + especieClaveValor.getKey());
                                         if (!String.valueOf(preguntaClaveValor.getKey()).equalsIgnoreCase(String.valueOf(auxNodoComparativo.getData()))){
                                              auxNodoComparativo = auxNodoComparativo.getHijoIzq();
                                              ultimaPregunta = false;
                                         }
                                         
                                        if (String.valueOf(preguntaClaveValor.getKey()).equalsIgnoreCase(String.valueOf(auxNodoComparativo.getData())) ){
                                             especiesMatcheada = true;
//                                             JOptionPane.showMessageDialog(null, especieClaveValor.getKey());
                                             contador++;
                                             
                                             if (Boolean.parseBoolean(preguntaClaveValor.getValue().toString())){
                                                 
//                                         JOptionPane.showMessageDialog(null, "Nos vamos pa la derecha");
                                                 auxNodoComparativo = auxNodoComparativo.getHijoDer();
                                                 ultimaPregunta = true;
                                             }else{
//                                         JOptionPane.showMessageDialog(null, "Nos vamos pa la izquierda");
                                                 auxNodoComparativo = auxNodoComparativo.getHijoIzq();
                                                 ultimaPregunta = false;
                                             }
                                         }
                                     }
                                 }


                             }
                         }
                         
                         if (contador == largo && ultimaPregunta == esVerdaderaLaRespuestaDelUsuario) {
                             int  respuestaDeHaSidoadivinado = JOptionPane.showConfirmDialog(null, "TU CARTA ERA ESTA? "+ especieClaveValor.getKey());
                              haSidoadivinado = (respuestaDeHaSidoadivinado == JOptionPane.YES_OPTION);
                              
                              if (haSidoadivinado) {
                                  JOptionPane.showMessageDialog(null, "Yey soy lo maisimo");
                              }
                         }
 //                         JOptionPane.showMessageDialog(null, "Sali del condicional");
                     }
                }
                   
               }
               
               aux = aux.getpNext();
               
               if (aux != null){
                   
                   NodoArbol auxDelAuxArbol = new NodoArbol(aux.getData());
                   
                   if (esVerdaderaLaRespuestaDelUsuario){
                        auxArbol.setHijoDer(auxDelAuxArbol);
                   } else {
                        auxArbol.setHijoIzq(auxDelAuxArbol);
                   }
                   auxArbol = auxDelAuxArbol;
               }
               
               
           }
       }
    // Método encargado de cargar el JSON
    public static void cargarJson() {
        try {
            arbolParaGrafo = new Arbol();
            JFileChooser carga = new JFileChooser();
            int captar = carga.showOpenDialog(null);

            if (captar == JFileChooser.APPROVE_OPTION) {
                File archivoJson = carga.getSelectedFile();
                JsonParser parser = new JsonParser();
                FileReader lector = new FileReader(archivoJson.getPath());
                JsonElement elemento = parser.parse(lector);
                JsonObject objectoRaiz = elemento.getAsJsonObject();

                listaEspecies = null;
                for (var claveValor : objectoRaiz.entrySet()) {
                    listaEspecies = claveValor.getValue().getAsJsonArray();
                    break;
                }

                clavesDelJson = new ListaEnlazada();
                ListaEnlazada valoresDelJsonSinRepeticion = new ListaEnlazada();

                for (var objectoEspecie : listaEspecies) {
                    var listaEspecieClaveValor = objectoEspecie.getAsJsonObject().entrySet();
                    for (var especieClaveValor : listaEspecieClaveValor) {
                        JsonArray listaPreguntas = especieClaveValor.getValue().getAsJsonArray();
                        clavesDelJson.addLast(especieClaveValor.getKey());
//                        JOptionPane.showMessageDialog(null, especieClaveValor.getKey());
//                        JOptionPane.showMessageDialog(null, listaPreguntas);

                        for (var preguntaObjeto : listaPreguntas) {
                            for (Entry<String, JsonElement> preguntaClaveValor : preguntaObjeto.getAsJsonObject().entrySet()) {
                                valoresDelJsonSinRepeticion.addLastIfItsNotInList(preguntaClaveValor.getKey());
                                valoresDelJsonSinRepeticionCopia.addLastIfItsNotInList(preguntaClaveValor.getKey());
//                                JOptionPane.showMessageDialog(null, "A ver ya lo aniadi");
                            }
                        }
                    }
                }
                

                arbolParaGrafo.insertarCaracteristicas(valoresDelJsonSinRepeticion, true);
                Json.obtenerListaClaveValorJson();

                JOptionPane.showMessageDialog(null, "JSON cargado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo encontrar el archivo JSON.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (JsonSyntaxException e) {
            JOptionPane.showMessageDialog(null, "Error: El archivo que se intenta cargar no cumple con el formato esperado.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (JsonIOException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error: Ocurrió un problema al leer el archivo JSON.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra un gráfico del árbol si está cargado y no está vacío.
     *
     * Si el árbol está vacío o no se ha cargado un archivo JSON, muestra un
     * mensaje de advertencia o error mediante cuadros de diálogo.
     */ 
    public static void mostrarGrafico() {
        if (arbolParaGrafo != null) {
            arbolParaGrafo.imprimir();
            mostrarArbol m = new mostrarArbol(arbolParaGrafo);
            m.setVisible(true);

            if (arbolParaGrafo.getRaiz() == null) {
                m.setVisible(false);
                JOptionPane.showMessageDialog(null, "No se puede mostrar el gráfico porque el árbol está vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: No se ha cargado un archivo JSON aún.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


