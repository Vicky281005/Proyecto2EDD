
package proyecto2edd;

import Interfaz.Json;
import java.io.FileNotFoundException;
//importar las librerías necesarias de graphstream
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import Interfaz.Arbolito;
import javax.swing.JOptionPane;
/**
 *
 * @author NITRO V 15
 */
public class Proyecto2EDD {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws FileNotFoundException {
    JOptionPane.showMessageDialog(null, """
                                        BIENVENID@ A NUESTRO PROGRAMA!
                                        
                                        Antes de iniciar, ten en cuenta:
                                        - Si deseas ver el árbol completo gráficamente,
                                          debes cargar la clave dicot\u00f3mica. Luego, presiona el bot\u00f3n "Mostrar Arbol".
                                        - En la seccion de  "Buscar especie" al escribir la especie que deseas buscar, debes iniciar 
                                        el nombre  con letra Mayuscula sin dejar espacios al final. y Posteriormente bpresionar el boton de buscar""",
        "Aviso",
        JOptionPane.INFORMATION_MESSAGE
    );

    Arbolito carga = new Arbolito();
    carga.setVisible(true);
    carga.setLocationRelativeTo(null);
}
}
