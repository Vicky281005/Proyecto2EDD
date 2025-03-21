
package proyecto2edd;

import Interfaz.Json;
import java.io.FileNotFoundException;
//importar las librerías necesarias de graphstream
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import Interfaz.Arbolito;
/**
 *
 * @author NITRO V 15
 */
public class Proyecto2EDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Json.cargarJson();
        Arbolito carga = new Arbolito();
        carga.setVisible(true);
        carga.setLocationRelativeTo(null);
        
    }
    
}
