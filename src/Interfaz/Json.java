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
       
     
     /**
 * Procesa una lista de claves y valores obtenida de un archivo JSON para construir un árbol de decisión.
 * 
 * Este método realiza las siguientes tareas:
 * - Obtiene las claves y valores del archivo JSON previamente cargado.
 * - Construye un árbol binario basado en las respuestas del usuario mediante cuadros de diálogo.
 * - Intenta identificar una especie basándose en las respuestas del usuario y las características del JSON.
 * 
 * Lógica adicional:
 * - Maneja las respuestas del usuario para actualizar el árbol de decisión.
 * - Reestructura el JSON para reflejar las características seleccionadas.
 */

     public static void obtenerListaClaveValorJson(){
         
         Nodo aux = valoresDelJsonSinRepeticionCopia.getpFirst();
         NodoArbol raizParaArbol = new NodoArbol(aux.getData());
         arbolParaGrafo.volverRaizNula();
         arbolParaGrafo.setRaiz(raizParaArbol);
         NodoArbol auxArbol = raizParaArbol;
         
         int preguntasRealizadas = 0;
         
         NodoArbol ultimoNodoArbolAux = new NodoArbol(); // Obtendra el valor de lo adivinado
         
         Boolean haSidoadivinado = false;
          while (aux != null && !haSidoadivinado){
               String valorDelJson = String.valueOf(aux.getData());

               
               int seleccion = JOptionPane.showConfirmDialog(null, valorDelJson, "Su especie tiene: ", JOptionPane.YES_NO_OPTION);
               
               boolean esVerdaderaLaRespuestaDelUsuario = (seleccion == JOptionPane.YES_OPTION);
               preguntasRealizadas++;
               
               Boolean especiesMatcheada = false;
               
               if (!especiesMatcheada){
                for (var objectoEspecie : listaEspecies) {
                     var listaEspecieClaveValor = objectoEspecie.getAsJsonObject().entrySet();
                     for (var especieClaveValor : listaEspecieClaveValor) {
                         JsonArray listaPreguntas = especieClaveValor.getValue().getAsJsonArray();
                         
                         
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
                         NodoArbol auxNodoComparativo = arbolParaGrafo.getRaiz();
                         int largo = listaPreguntas.size();
                         int contador = 0;
                         Boolean ultimaPregunta = false;

                         if (largo == preguntasRealizadas){     
                             while (auxNodoComparativo != null) {
                                 for (var preguntaObjeto : listaPreguntas) {
                                     for (Entry<String, JsonElement> preguntaClaveValor : preguntaObjeto.getAsJsonObject().entrySet()) {
                                         if (auxNodoComparativo == null) continue;
                                         if (!String.valueOf(preguntaClaveValor.getKey()).equalsIgnoreCase(String.valueOf(auxNodoComparativo.getData()))){
                                              ultimoNodoArbolAux = auxNodoComparativo;
                                              auxNodoComparativo = auxNodoComparativo.getHijoIzq();
                                              ultimaPregunta = false;
                                         }
                                         
                                        if (String.valueOf(preguntaClaveValor.getKey()).equalsIgnoreCase(String.valueOf(auxNodoComparativo.getData())) ){
                                             especiesMatcheada = true;
                                             contador++;
                                             
                                             if (Boolean.parseBoolean(preguntaClaveValor.getValue().toString())){
                                                  ultimoNodoArbolAux = auxNodoComparativo;
                                                 auxNodoComparativo = auxNodoComparativo.getHijoDer();
                                                 ultimaPregunta = true;
                                             }else{
                                                  ultimoNodoArbolAux = auxNodoComparativo;
                                                 auxNodoComparativo = auxNodoComparativo.getHijoIzq();
                                                 ultimaPregunta = false;
                                             }
                                         }
                                     }
                                 }
                             }
                         }
                       
                         if (contador == largo && ultimaPregunta == esVerdaderaLaRespuestaDelUsuario) {
                             int  respuestaDeHaSidoadivinado = JOptionPane.showConfirmDialog(null, "La especie que buscas es esta? 🤔 "+ especieClaveValor.getKey());
                              haSidoadivinado = (respuestaDeHaSidoadivinado == JOptionPane.YES_OPTION);
                              
                              if (haSidoadivinado) {
                                  NodoArbol noditotemp = new NodoArbol(especieClaveValor.getKey());
                                   ultimoNodoArbolAux.setHijoDer(noditotemp);
                                  JOptionPane.showMessageDialog(null, "Yei !  me alegra haberte ayudado ;D ");
                              }
                              if (!haSidoadivinado) {
                                  NodoArbol noditotemp = new NodoArbol(especieClaveValor.getKey());
                                   ultimoNodoArbolAux.setHijoDer(noditotemp);
                                  JOptionPane.showMessageDialog(null, "Lo siento. Intentaré hacerlo mejor la próxima vez... :( ");
                              }
                         }
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
     
    /**
     * Realiza la búsqueda de una especie específica en un árbol y genera su
     * representación en una lista enlazada.
     *
     * @param especieBuscada el nombre de la especie a buscar.
     * @return una lista enlazada que contiene las características de la especie
     * buscada.
     */
    public static ListaEnlazada busqueda(String especieBuscada) {

        arbolParaGrafo.volverRaizNula();
        NodoArbol aux = new NodoArbol();
        ListaEnlazada preguntasDeLaEspecieABuscar = new ListaEnlazada();

        int largo = 0;
        int counter = 0;
        //System.out.println(listaEspecies);
        for (JsonElement elemento : listaEspecies) {
            JsonObject especie = elemento.getAsJsonObject();
            if (especie.has(especieBuscada)) {
                JsonArray caracteristicas = especie.getAsJsonArray(especieBuscada);
                largo = caracteristicas.size();
                for (JsonElement caracteristica : caracteristicas) {
                    JsonObject objetoCaracteristica = caracteristica.getAsJsonObject();
                    for (var entrada : objetoCaracteristica.entrySet()) {
                        String clave = entrada.getKey();
                        boolean valor = entrada.getValue().getAsBoolean();
                        preguntasDeLaEspecieABuscar.addLast(clave);
                        if (arbolParaGrafo.getRaiz() == null) {
                            arbolParaGrafo.setRaiz(new NodoArbol(clave));
                            aux = arbolParaGrafo.getRaiz();
                        } else {
                            if (valor) {
                                aux.setHijoDer(new NodoArbol(clave));
                                aux = aux.getHijoDer();
                            } else {
                                aux.setHijoIzq(new NodoArbol(clave));
                                aux = aux.getHijoIzq();
                            }
                        }
                        counter++;

                        if (counter >= largo) {
                            if (valor) {
                                aux.setHijoDer(new NodoArbol(especieBuscada));
                            } else {
                                aux.setHijoDer(new NodoArbol(especieBuscada));

                            }
                        }
                    }
                }
            }
        }
        return preguntasDeLaEspecieABuscar;
    }
  
     
     
     
     
    /**
     * Carga un archivo JSON y procesa su contenido para inicializar el árbol y
     * listas relacionadas.
     *
     * Este método utiliza un selector de archivos para permitir al usuario
     * cargar un archivo JSON. Extrae las características desde el archivo, las
     * organiza en una lista enlazada y construye un árbol basado en dichas
     * características.
     *
     * Además, maneja excepciones para proporcionar retroalimentación al usuario
     * en caso de errores como un archivo faltante, un formato inválido o
     * problemas al leer el archivo.
     */
    public static void cargarJson() {
        try {
    
            arbolParaGrafo = new Arbol();

            valoresDelJsonSinRepeticionCopia = new ListaEnlazada();
            
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


                        for (var preguntaObjeto : listaPreguntas) {
                            for (Entry<String, JsonElement> preguntaClaveValor : preguntaObjeto.getAsJsonObject().entrySet()) {
                                valoresDelJsonSinRepeticion.addLastIfItsNotInList(preguntaClaveValor.getKey());
                                valoresDelJsonSinRepeticionCopia.addLastIfItsNotInList(preguntaClaveValor.getKey());

                            }
                        }
                    }
                }
                

                arbolParaGrafo.insertarCaracteristicas(valoresDelJsonSinRepeticion, true);

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


