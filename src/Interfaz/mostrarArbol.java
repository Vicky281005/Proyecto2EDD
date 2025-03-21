package Interfaz;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import EDD.Arbol;
import EDD.Nodo;
import EDD.NodoArbol;
import Hashtable.ListaEnlazada;
import MainClass.Pregunta;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.ViewerPipe;

/**
 *
 * @author jmmor
 */
public class mostrarArbol extends javax.swing.JFrame {

    static Arbol arbol;

    /**
     * Creates new form mostrarArbol
     */
    public mostrarArbol(Arbol a) {
        initComponents();
        arbol = a;
        this.mostrar();

    }

    public void mostrar() {
    Graph graph = new SingleGraph("Árbol");
    boolean isFirstNode = true; // Cambié el nombre de la variable para mayor claridad
    Node first = null;
    ListaEnlazada aux = arbol.imprimir();
    Nodo aux2 = aux.getpFirst();
        System.out.println(aux.getSize());
       
    // Agregar nodos al grafo
    while (aux2 != null) {
        try {
            NodoArbol n = (NodoArbol) aux2.getData();
            String nodeId = n.getData().toString();
            // Agregar el nodo y establecer su etiqueta
            graph.addNode(nodeId).setAttribute("ui.label", nodeId); // Establecer la etiqueta del nodo
            
            // Cambiar el color del primer nodo a rojo
            if (isFirstNode) {
                first = graph.getNode(nodeId);
                graph.getNode(nodeId).setAttribute("ui.fill-color", "red");
                isFirstNode = false; // Cambiar a false después de colorear el primer nodo
            } else {
                graph.getNode(nodeId).setAttribute("ui.fill-color", "blue"); // O cualquier otro color para los demás nodos
            }

            System.out.println("       NODO AGREGADO --> " + nodeId);
        } catch (Exception e) {
//            e.printStackTrace(); // Imprimir la excepción para depuración
        }
        aux2 = aux2.getpNext();
    }

    aux2 = aux.getpFirst();

    // Crear aristas entre nodos
    while (aux2 != null) {
        NodoArbol n = (NodoArbol) aux2.getData();
        if (n != null) {
            String p = n.getData().toString();
            try {
                if (n.getHijoIzq() != null) {
                    String i = n.getHijoIzq().getData().toString();
                    graph.addEdge(p + "-" + i, p, i); // Usar el valor del nodo como identificador
                }
                if (n.getHijoDer() != null) {
                    String r = n.getHijoDer().getData().toString();
                    graph.addEdge(p + "-" + r, p, r); // Usar el valor del nodo como identificador
                }
            } catch (Exception e) {
//                e.printStackTrace(); // Imprimir la excepción para depuración
            }
        }
        aux2 = aux2.getpNext();
    }
    
    
// COLORES --------------------------------

//    // Establece las propiedades de visualización
//    graph.setAttribute("ui.stylesheet",
//        "node { size: 15px; text-alignment: under; fill-color: blue; } " + // Cambia el color de fondo de los nodos
//        "node.label { fill-color: red; size: 30px; } " + // Cambia el color y tamaño de la etiqueta
//        "edge { fill-color: black; }");
//first.setAttribute("ui.fill-color", "red");
//    // Ajustar el layout para evitar superposición
//    graph.setAttribute("ui.quality");
//    graph.setAttribute("ui.antialias");
//    graph.setAttribute("ui.layout", "force");
//    graph.setAttribute("ui.layout.force.springLength", 150); // Distancia entre nodos
//    graph.setAttribute("ui.layout.force.springConstant", 0.2); // Constante de resorte

// --------------------------------------------------

    // Crear un Viewer para mostrar el grafo
    System.setProperty("org.graphstream.ui", "swing");
    graph.display();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mostrarArbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mostrarArbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mostrarArbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mostrarArbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mostrarArbol(arbol).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
