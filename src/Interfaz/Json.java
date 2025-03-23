package Interfaz;

import EDD.Arbol;
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

       private static Arbol arbolParaGrafo; // Variable estática para compartir entre métodos

    // Método encargado de cargar el JSON
    public static void cargarJson() {
        try {
            Arbol arbol = new Arbol();
            arbolParaGrafo = new Arbol(); // Inicializamos el árbol para el gráfico
            JFileChooser carga = new JFileChooser();
            int captar = carga.showOpenDialog(null);

            if (captar == JFileChooser.APPROVE_OPTION) {
                File archivoJson = carga.getSelectedFile();
                JsonParser parser = new JsonParser();
                FileReader lector = new FileReader(archivoJson.getPath());
                JsonElement elemento = parser.parse(lector);
                JsonObject objectoRaiz = elemento.getAsJsonObject();

                JsonArray listaEspecies = null;
                for (var claveValor : objectoRaiz.entrySet()) {
                    listaEspecies = claveValor.getValue().getAsJsonArray();
                    break;
                }

                ListaEnlazada clavesDelJson = new ListaEnlazada();
                ListaEnlazada valoresDelJsonSinRepeticion = new ListaEnlazada();

                for (var objectoEspecie : listaEspecies) {
                    var listaEspecieClaveValor = objectoEspecie.getAsJsonObject().entrySet();
                    for (var especieClaveValor : listaEspecieClaveValor) {
                        JsonArray listaPreguntas = especieClaveValor.getValue().getAsJsonArray();
                        clavesDelJson.addLast(especieClaveValor.getKey());

                        for (var preguntaObjeto : listaPreguntas) {
                            for (Entry<String, JsonElement> preguntaClaveValor : preguntaObjeto.getAsJsonObject().entrySet()) {
                                valoresDelJsonSinRepeticion.addLastIfItsNotInList(preguntaClaveValor.getKey());
                            }
                        }
                    }
                }

                arbolParaGrafo = arbol;
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


