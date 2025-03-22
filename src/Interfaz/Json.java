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

   public static void cargarJson() {
        try {
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

                arbolParaGrafo.imprimir();

                mostrarArbol m = new mostrarArbol(arbolParaGrafo);
                m.setVisible(true);

                if (arbolParaGrafo.getRaiz() == null) {
                    m.setVisible(false);
                }
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo encontrar el archivo JSON.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (JsonSyntaxException e) {
            JOptionPane.showMessageDialog(null, "Error: El archivo que se intenta cargar no cumple con el formato esperado.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (JsonIOException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error: Ocurrió un problema al leer el archivo JSON.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

