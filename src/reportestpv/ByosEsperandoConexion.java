/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportestpv;

/**
 *
 * Pantalla de Espera de Conexion
 */
public class ByosEsperandoConexion extends javax.swing.JFrame {
    private static final long serialVersionUID = 5474522369804563317L;
    /**
     * Creates new form ByosEsperandoConexion
     */
    int Contador=0;
    public ByosEsperandoConexion() {
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setType(Type.UTILITY);
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

        JPanelConexion = new javax.swing.JPanel();
        JLabelImagen = new javax.swing.JLabel();
        JLabelMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanelConexion.setBackground(new java.awt.Color(255, 255, 255));
        JPanelConexion.setPreferredSize(new java.awt.Dimension(1024, 770));
        JPanelConexion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabelImagen.setBackground(new java.awt.Color(255, 255, 255));
        JLabelImagen.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        JLabelImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reportestpv/EspadandoConexion01.gif"))); // NOI18N
        JPanelConexion.add(JLabelImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1024, 710));

        JLabelMensaje.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        JLabelMensaje.setForeground(new java.awt.Color(0, 153, 204));
        JLabelMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabelMensaje.setText("Esperando Conexion...");
        JLabelMensaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLabelMensajeMouseClicked(evt);
            }
        });
        JPanelConexion.add(JLabelMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 1024, 60));

        getContentPane().add(JPanelConexion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JLabelMensajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLabelMensajeMouseClicked
        // TODO add your handling code here:
        Contador++;
        if(Contador>=5){
            System.exit(0);
        }
    }//GEN-LAST:event_JLabelMensajeMouseClicked

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
            java.util.logging.Logger.getLogger(ByosEsperandoConexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ByosEsperandoConexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ByosEsperandoConexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ByosEsperandoConexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ByosEsperandoConexion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelImagen;
    private javax.swing.JLabel JLabelMensaje;
    private javax.swing.JPanel JPanelConexion;
    // End of variables declaration//GEN-END:variables
}
