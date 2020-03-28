/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportestpv;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import static reportestpv.Main.Departamento;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * Funciones para el Manejo de Las impresora y Busqueda de impreoras por Defectos
 */
public class Impresoras {
    
    public Impresoras() {

    }
    
    public static String IMPRESORA_DOCUMENTO = "DOCUMENTO";
    public static String IMPRESORA_TICKET = "TICKET";
    public static String IMPRESORA_PDF = "PDF";
    
    // Funcion donde se puede deleccion la inpresora del sistema por defecto de Douemntos
    public void setSelectImpresoraDocumentos(JFrame frame) {
        String impresora;
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);
        if (printService.length > 0) {

            String Impresoras[] = new String[printService.length+1];
            Impresoras[0] = new tblParametrosTPV().buscarParametroString01(Departamento.getCodigodepartamento(), "IMPRESORA.DE.DOCUMENTOS");
            for (int f = 1; f < printService.length; f++) {
                Impresoras[f] = printService[f].getName();
            }
            impresora = JOptionPane.showInputDialog(frame, 
                                                    "Eliga impresora:", 
                                                    "Imprimir Reporte", 
                                                    JOptionPane.QUESTION_MESSAGE, 
                                                    null, 
                                                    Impresoras, 
                                                    Impresoras[0]
            ).toString();
            
            if (impresora != null) {
                try {
                    Main.ImpresoraDocumentos = impresora;
                    new tblParametrosTPV().guardarParametroString01(Main.Departamento.getCodigodepartamento(), "IMPRESORA.DE.DOCUMENTOS", impresora);
                } catch (Exception ex) {
                    Logger.getLogger(Impresoras.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
    
    //Funcion para abrir el cajon
    public static boolean AbrirCajon() {
        
        try {
            PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
            PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);
            System.out.println("Impresora: " + Main.ImpresoraPorDefecto);
            if (printService.length > 0) {
               for (int f = 1; f < printService.length; f++) {
                    if(printService[f].getName().equals(Main.ImpresoraPorDefecto)){
                        System.out.println(printService[f].getName());
                        defaultPrintService = printService[f];
                    }
               }               
            }
                        
            String Comando = ((char) 27) + "p0" + ((char) 0xfd) + "" + ((char) 0x1a);
            byte[] bytes = Comando.getBytes();

            DocFlavor docFormat = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc document = new SimpleDoc(bytes, docFormat, null);

            if (defaultPrintService != null) {
                DocPrintJob printJob = defaultPrintService.createPrintJob();
                try {
                    printJob.print(document, null);
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            } else {
                System.err.println("No existen impresoras instaladas");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    // Funcion donde se puede deleccion la inpresora del sistema por defecto de ticket
    public void setSelectImpresora(JFrame frame) {
        String impresora;
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);
        if (printService.length > 0) {

            String Impresoras[] = new String[printService.length+1];
            Impresoras[0] = new tblParametrosTPV().buscarParametroString01(Departamento.getCodigodepartamento(), "IMPRESORAPORDEFECTO");
            for (int f = 1; f < printService.length; f++) {
                Impresoras[f] = printService[f].getName();
            }
            impresora = JOptionPane.showInputDialog(frame, 
                                                    "Eliga impresora:", 
                                                    "Imprimir Reporte", 
                                                    JOptionPane.QUESTION_MESSAGE, 
                                                    null, 
                                                    Impresoras, 
                                                    Impresoras[0]
            ).toString();
            
            if (impresora != null) {
                try {
                    Main.ImpresoraPorDefecto = impresora;
                    new tblParametrosTPV().guardarParametroString01(Main.Departamento.getCodigodepartamento(), "IMPRESORAPORDEFECTO", impresora);
                } catch (Exception ex) {
                    Logger.getLogger(Impresoras.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //Retorna la Impresora por Defecto Actual
    public String ImpresoraPorDefecto() {       
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);
        if (printService.length > 0) {
            return  printService[0].getName();
        }       
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        return defaultPrintService.getName();   
    }

    //Busca si existe una impresora
    public boolean existeImpresora(String Impresora) {
        AttributeSet aset = new HashAttributeSet();
        aset.add(new PrinterName(Impresora, null));
        PrintService[] defaultPrintService = PrintServiceLookup.lookupPrintServices(null, aset);

        if (defaultPrintService.length > 0) {
            return true;
        }
        return false;
    }

    
    public void setImpresora01(JasperPrint jasperprint, boolean VisualizarListaImpresorar) {
        long start = System.currentTimeMillis();
        if (Main.ImpresoraPorDefecto == null || Main.ImpresoraPorDefecto.equals("") || !existeImpresora(Main.ImpresoraPorDefecto)) {
            Main.ImpresoraPorDefecto = ImpresoraPorDefecto();
        }

        AttributeSet aset = new HashAttributeSet();
        aset.add(new PrinterName(Main.ImpresoraPorDefecto, null));
        PrintService[] defaultPrintService = PrintServiceLookup.lookupPrintServices(null, aset);

        System.out.println("Impresora por Defecto: " + Main.ImpresoraPorDefecto);

        JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
        jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperprint);
        jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, defaultPrintService[0]);
        jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, VisualizarListaImpresorar);
        try {
            jrprintServiceExporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Impresoras.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Printing time : " + (System.currentTimeMillis() - start));
    }

    public void setImpresora01(JasperPrint jasperprint, boolean VisualizarListaImpresorar, String Impresora) {
	long start = System.currentTimeMillis();
	//PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
	//printRequestAttributeSet.add(MediaSizeName.ISO_A4);
        String ImpresoraPorDefecto = Main.ImpresoraPorDefecto;
        if(Impresora.equals(Impresoras.IMPRESORA_DOCUMENTO)){
           ImpresoraPorDefecto = Main.ImpresoraDocumentos;
        }
        
        if (ImpresoraPorDefecto == null || ImpresoraPorDefecto.equals("")) {
            ImpresoraPorDefecto = ImpresoraPorDefecto();
        }
        Main.ImpresoraPorDefecto=ImpresoraPorDefecto;
        
        System.out.println("Impresora por Defecto: " + Main.ImpresoraPorDefecto);
	PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
        printServiceAttributeSet.add(new PrinterName(Main.ImpresoraPorDefecto, null));
        
        
        JRPrintServiceExporter exporter = new JRPrintServiceExporter();
	
	exporter.setExporterInput(new SimpleExporterInput(jasperprint));
	SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
	//configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
	configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
	configuration.setDisplayPageDialog(false);
	configuration.setDisplayPrintDialog(false);
	exporter.setConfiguration(configuration);
        try {
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Impresoras.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Printing time : " + (System.currentTimeMillis() - start));
    }
    
    
    
    public void setImpresoraDocumentos01(JasperPrint jasperprint, boolean VisualizarListaImpresorar) {

        if (Main.ImpresoraDocumentos == null || Main.ImpresoraDocumentos.equals("") || !existeImpresora(Main.ImpresoraDocumentos)) {
            Main.ImpresoraDocumentos = ImpresoraPorDefecto();
        }

        AttributeSet aset = new HashAttributeSet();
        aset.add(new PrinterName(Main.ImpresoraDocumentos, null));
        PrintService[] defaultPrintService = PrintServiceLookup.lookupPrintServices(null, aset);

        System.out.println("Impresora por Defecto: " + Main.ImpresoraDocumentos);

        JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
        jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperprint);
        jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, defaultPrintService[0]);
        jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, VisualizarListaImpresorar);
        try {
            jrprintServiceExporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Impresoras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Impresora Por Defecto: " + new Impresoras().ImpresoraPorDefecto());
            }
        });
    }


    public void setImpresora(JasperPrint jasperprint, boolean VisualizarListaImpresorar, String Impresora) {
	long start = System.currentTimeMillis();
	//PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
	//printRequestAttributeSet.add(MediaSizeName.ISO_A4);
        String ImpresoraPorDefecto = Main.ImpresoraPorDefecto;
        if(Impresora.equals(Impresoras.IMPRESORA_DOCUMENTO)){
           ImpresoraPorDefecto = Main.ImpresoraDocumentos;
        }
        
        if (ImpresoraPorDefecto == null || ImpresoraPorDefecto.equals("")) {
            ImpresoraPorDefecto = ImpresoraPorDefecto();
        }
        Main.ImpresoraPorDefecto=ImpresoraPorDefecto;
        
        System.out.println("Impresora por Defecto: " + Main.ImpresoraPorDefecto);
        estableceImpresoraPredeterminada(Main.ImpresoraPorDefecto);
        try {
            JasperPrintManager.printReport(jasperprint, false);
        } catch (JRException ex) {
            Logger.getLogger(Impresoras.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Printing time : " + (System.currentTimeMillis() - start));
    }
    

    private void estableceImpresoraPredeterminada(String printerName) {
        long start = System.currentTimeMillis();
        String cmdLine = String.format("RUNDLL32 PRINTUI.DLL,PrintUIEntry /y /n \"%s\"", printerName);
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", cmdLine);
        builder.redirectErrorStream(true);
        Process p = null;
        try {
            p = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = new String();
        while (true) {
            try {
                line = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
        }
    }    
    

}
