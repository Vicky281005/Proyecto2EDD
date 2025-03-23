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
//     public static ListaEnlazada valoresDelJsonSinRepeticionCopia;
     public  static JsonArray listaEspecies;   
     public static ListaEnlazada clavesDelJson;
     public static Arbol arbolParaGrafo; // Variable estática para compartir entre métodos
       
//     public static void obtenerListaClaveValorJson(){
//          Nodo aux = valoresDelJsonSinRepeticionCopia.getpFirst();
//          
//          while (aux != null){
//               String valorDelJson = String.valueOf(aux.getData());
//               
//               int seleccion = JOptionPane.showConfirmDialog(null, "¿Quieres continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
//               boolean continuar = (seleccion == JOptionPane.YES_OPTION);
//               JOptionPane.showMessageDialog(null, "hola");
//
//               System.out.println(seleccion);
//               
//           }
//       }
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
//                                valoresDelJsonSinRepeticionCopia.addLastIfItsNotInList(preguntaClaveValor.getKey());
//                                JOptionPane.showMessageDialog(null, "A ver ya lo aniadi");
                            }
                        }
                    }
                }
                

                arbolParaGrafo.insertarCaracteristicas(valoresDelJsonSinRepeticion, true);
//                Json.obtenerListaClaveValorJson();

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


