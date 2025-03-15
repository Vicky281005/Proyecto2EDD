package Interfaz;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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
            for (var objectoEspecie : listaEspecies) {
                var listaEspecieClaveValor = objectoEspecie.getAsJsonObject().entrySet();
                for(var especieClaveValor : listaEspecieClaveValor ){
                    JsonArray listaPreguntas = especieClaveValor.getValue().getAsJsonArray();
//                    System.out.println(especieClaveValor.getKey());
                    for (var preguntaObjeto : listaPreguntas){
//                        System.out.println(preguntaObjeto);
                        for (Entry<String, JsonElement> preguntaClaveValor : preguntaObjeto.getAsJsonObject().entrySet()){
                            System.out.println(preguntaClaveValor);
                        }
                    }
                    
                }
                
            }
            
            
        }

    }

}
