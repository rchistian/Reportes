/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportestpv;

import java.io.IOException;
import java.net.ServerSocket;
import net.sf.jasperreports.engine.util.JRProperties;
/* 
Saludos,
Chistian Russian

Hola, te envió ajunto un archivo de prueba con los reportes y código en java 
ademas de un ejemplo de ireport, esto con la intención que veas como es la metodología 
de programación que yo uso, claro que solo es una prueba no tiene muchos comentarios 
pero si los necesarios para que lo puedas entender y configurar en tu ordenador, 
hora t digo los pasos a seguir para instalarlo:

Esta dirección es para descargar la  aplicación:
https://mega.nz/#!VxwBkIQC!U8nzg05NaEYQjcc_TnIRXYUR7B-S0-aL9lHYsCcrb8M

1) Descarga del NetBeans aquí te dejo el link. https://netbeans.org/;
2) Instala mysql;
3) En el directorio de la aplicación esta un respaldo de la base de datos, restaurarla en mysql;
4) Abre la Aplicación en NetBeans;
5) En el directorio de la aplicaron busca un archivo de que se llama Drivers.ini en el 
   puedes configurar el Sttring de conexión de la base de datos;
6) Busca la clase conexion en java, en ella hay 2 Variables:
    public static final String USER="TU USUARIO DE MYSQL";
    public static final String PASSWORD="TU CLAVE DE MYSQL";
    Coloca aquí el usuario y la contraseña de tu mysql, este proceso normalmente esta interceptado 
    pero como es un ejemplo lo vamos a hacer así por ahora;
7) Compila y Corre.

Si no hay ningún problema te debería correr con estos pasaos, lo jdbc de mysql y 
las librerías de ireport ya están en el directorio, por eso pesa un poco;

Solo funciona el Reporte 1, aquí podemos ir agregando en los otros botones los seguramente 
reportes que tu vallas realizando;

att.
Chistian Russian
Tlf: 604103159
*/

public class Main extends javax.swing.JFrame {

    // Coneccion unica estatica y global para cualquier operacion con la base de datos
    public static Conexion CONEXION = new Conexion();
    
    // Indica cual es el usuario actual activo en el sistema
    public static String UsuarioActual="";
    
    // Indica el tipo de dispensador del dinero, cajon o automatico
    public static String TipoDispensador="CAJON";
   
    // Indica la impresora por defecto del sistema
    public static String ImpresoraPorDefecto="\\\\tpv-0007\\EPSON TM-T70II Receipt";

    // Indica la impresora por defecto PDF
    public static String ImpresoraPorDefectoPdf="\\\\tpv-0007\\EPSON TM-T70II Receipt";

    // Indica la impresora por defecto del sistema
    public static String ImpresoraDocumentos="\\\\tpv-0007\\EPSON TM-T70II Receipt";
    //public static String ImpresoraDocumentos="PrimoPDF";
    
    // Setero de Estacion de Trabajo
    public static tblDepartamento Departamento = new tblDepartamento();
    
    public static String DirectorioPorDefecto="";
    
    public static int TiempoEsperaThread = 2000;
    
    public static int TiempoEsperaThreadBilletero = 200;
    
    public static String CodigoEmpresa = "0001";
    
    public static String TipoPuertoBalanza="";
    
    public static String PuertoBalanza="";
    
    public static double MedidaMinima=0.040;
    
    public static double MedidaMaxima=0.045;
    
    private static ServerSocket SERVER_SOCKET;
    
 
    public Main() {
        initComponents();
    }



    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SERVER_SOCKET = new ServerSocket(1334);
                } catch (IOException ex) {
                    System.out.println("El Programa Ya Se Esta Ejecutando");
                    System.exit(0);
                }
              
                JRProperties.setProperty("net.sf.jasperreports.xpath.executer.factory", "net.sf.jasperreports.engine.util.xml.JaxenXPathExecuterFactory");

                new Reportes().setVisible(true);
                //DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance();
                //JRPropertiesUtil.getInstance(context).setProperty("net.sf.jasperreports.xpath.executer.factory","net.sf.jasperreports.engine.util.xml.JaxenXPathExecuterFactory");
                
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Window".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }



            }
        });
    }
     
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
