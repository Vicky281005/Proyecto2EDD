
package Interfaz;

import EDD.Arbol;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jmmor
 */
public class Arbolito extends javax.swing.JFrame {
    Arbol arbol;
    /**
     * Creates new form Arbolito
     */
    public Arbolito() {
        initComponents();
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
        jPanel3 = new javax.swing.JPanel();
        buscaEspecie = new javax.swing.JButton();
        mostarArbol = new javax.swing.JButton();
        determinarEspecie = new javax.swing.JButton();
        cargaClave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        salirbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buscaEspecie.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        buscaEspecie.setText("Buscar Especie");
        buscaEspecie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscaEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaEspecieActionPerformed(evt);
            }
        });
        jPanel3.add(buscaEspecie, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 300, 50));

        mostarArbol.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        mostarArbol.setText("Mostrar Arbol");
        mostarArbol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mostarArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostarArbolActionPerformed(evt);
            }
        });
        jPanel3.add(mostarArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 300, 50));

        determinarEspecie.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        determinarEspecie.setText("Determinar Especie del arbol");
        determinarEspecie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        determinarEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                determinarEspecieActionPerformed(evt);
            }
        });
        jPanel3.add(determinarEspecie, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 300, 50));

        cargaClave.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        cargaClave.setText("Cargar Clave Dicotomica");
        cargaClave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cargaClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargaClaveActionPerformed(evt);
            }
        });
        jPanel3.add(cargaClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 300, 50));

        jLabel1.setFont(new java.awt.Font("Book Antiqua", 0, 24)); // NOI18N
        jLabel1.setText("Clave Dicotomica");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, 30));

        salirbtn.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        salirbtn.setText("Salir");
        salirbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salirbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirbtnActionPerformed(evt);
            }
        });
        jPanel3.add(salirbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 300, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirbtnActionPerformed
        JOptionPane.showMessageDialog(null, "Gracias por participar");
        this.dispose();
    }//GEN-LAST:event_salirbtnActionPerformed

    private void cargaClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargaClaveActionPerformed
        Json.cargarJson();//        cargarClave carga = new cargarClave();
//        carga.setLocationRelativeTo(null);
//        carga.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_cargaClaveActionPerformed

    private void determinarEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_determinarEspecieActionPerformed
        determinarClave clave = new determinarClave(arbol);
        clave.setLocationRelativeTo(null);
        clave.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_determinarEspecieActionPerformed

    private void mostarArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostarArbolActionPerformed
        mostrarArbol mostrar = new mostrarArbol(arbol);
        mostrar.setLocationRelativeTo(null);
        mostrar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mostarArbolActionPerformed

    private void buscaEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaEspecieActionPerformed
        buscarEspecie buscar = new buscarEspecie(arbol);
        buscar.setLocationRelativeTo(null);
        buscar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buscaEspecieActionPerformed

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
            java.util.logging.Logger.getLogger(Arbolito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Arbolito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Arbolito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Arbolito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Arbolito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscaEspecie;
    private javax.swing.JButton cargaClave;
    private javax.swing.JButton determinarEspecie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton mostarArbol;
    private javax.swing.JButton salirbtn;
    // End of variables declaration//GEN-END:variables
}
