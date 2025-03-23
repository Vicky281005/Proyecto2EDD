
package MainClass;

/**
 * Clase que va a ayudar con el Hash Table 
 * @author mishel
 */
public class Pregunta {
    private String sinopsis; //sinopsis porque el hash table tiene una sección de sinopsis donde se pone la característica
    private boolean determinacion; //determinación porque el hash table tiene una sección de determinación donde dependiendo la respuesta te lleva a otra sinopsis (característica)

/**
 * Constructor clase Preguta
 * @param sinopsis 
 */
    public Pregunta(String sinopsis) {
        this.sinopsis = sinopsis;
        this.determinacion = false;
    }

/**
 * Getters y Setters clase Pregunta
 * @return 
 */
    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public boolean isDeterminacion() {
        return determinacion;
    }

    public void setDeterminacion(boolean determinacion) {
        this.determinacion = determinacion;
    }

    
    
    
}
