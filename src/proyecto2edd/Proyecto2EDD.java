
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
     * Método principal del programa.
     *
     * Este método muestra un mensaje de bienvenida con instrucciones
     * importantes para el uso del programa y luego abre la ventana principal de
     * la aplicación.
     *
     * @param args
     * @throws FileNotFoundException si ocurre un error al cargar archivos
     * necesarios.
     */
   public static void main(String[] args) throws FileNotFoundException {
    JOptionPane.showMessageDialog(null, """
                                        BIENVENID@ A NUESTRO PROGRAMA!
                                        
                                        Antes de iniciar, ten en cuenta:
                                        - Si deseas ver el árbol completo gráficamente,
                                          debes cargar la clave dicot\u00f3mica. Luego, presiona el botón "Mostrar Arbol".
                                        - En la seccion de  "Buscar especie" al escribir la especie que deseas buscar, 
                                        debes iniciar el nombre  con letra Mayuscula sin dejar espacios al final. y Posteriormente 
                                        presionar el boton de buscar
                                        - Para mostrar grafico, buscar y/o determinar especie es necesario cargar una clave
                                        dicotomica primero."""
                                        ,
        "Aviso",
        JOptionPane.INFORMATION_MESSAGE
    );

    Arbolito carga = new Arbolito();
    carga.setVisible(true);
    carga.setLocationRelativeTo(null);
}
}
