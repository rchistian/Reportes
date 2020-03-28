/*
 * Lib_Reportes.java
 *
 * Created on 6 de agosto de 2007, 10:07 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package reportestpv;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;



/**
 *
 * @author Administrador
 */
public class Lib_Reportes {
    
    public String Archivo_xls="NuevoArchivo.xls";
    public HSSFWorkbook Hoja_Actual = new HSSFWorkbook();
    public HSSFCellStyle Estilos_Celda[] = {Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(),
                                            Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(),
                                            Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(),
                                            Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(),
                                            Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(),
                                            Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(),
                                            Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(),
                                            Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(),
                                            Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(),
                                            Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(),
                                            Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(),
                                            Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle(), Hoja_Actual.createCellStyle()
    };
    
    /** Creates a new instance of Lib_Reportes
     * @param Archivo
     * @param Hoja 
     */
    
    public Lib_Reportes(String Archivo, String Hoja) {
           this.Archivo_xls=Archivo;
           Hoja_Actual=crear_hoja(Hoja);
           Hoja_Actual.getSheetAt(0).setMargin(Hoja_Actual.getSheetAt(0).BottomMargin,0);
           Hoja_Actual.getSheetAt(0).setMargin(Hoja_Actual.getSheetAt(0).TopMargin,0.1);
           Hoja_Actual.getSheetAt(0).setMargin(Hoja_Actual.getSheetAt(0).LeftMargin,0.02);
           Hoja_Actual.getSheetAt(0).setMargin(Hoja_Actual.getSheetAt(0).RightMargin,0.02);
           Hoja_Actual.getSheetAt(0).setHorizontallyCenter(true);
           Hoja_Actual.getSheetAt(0).setZoom(150);
           Hoja_Actual.getSheetAt(0).setGridsPrinted(true);
           Hoja_Actual.getSheetAt(0).setDisplayZeros(false);
    }

    // Formatero de Estilo de Celda
    public void setEstilo(int Nro_Estilo, String Estilo_Fuente, int Tamanio_Fuente, String Justificacion_Fuente, String Bordes, String Formato, String Ajustar_Texto, String Background){
        Estilos_Celda[Nro_Estilo]=estilo_celda(Hoja_Actual, Estilo_Fuente, Tamanio_Fuente, Justificacion_Fuente, Bordes, Formato, Ajustar_Texto, Background); 
    }
   
    public void setColumna(int xColumna, int xTamanio){
        setColumnas(Hoja_Actual,xColumna, xTamanio);
    }
    
    public void setFila(int xTamanio){
        setFilas(Hoja_Actual, xTamanio);
    }
    
    public void autoSizeColumna(int xColumna) {
        Hoja_Actual.getSheetAt(0).autoSizeColumn(xColumna); 
    }
    
    public void autoSizeColumnas(int xColumnaA, int xColumnaB) {
        if (xColumnaA > xColumnaB){
            for (int i = xColumnaA; i <= xColumnaB; i++) {
                Hoja_Actual.getSheetAt(0).autoSizeColumn(i); 
            }
        } else {
            for (int i = xColumnaB; i <= xColumnaA; i++) {
                Hoja_Actual.getSheetAt(0).autoSizeColumn(i); 
            }
        }
         
    }
    
    public void setCelda(int xFila, int xColumna, String Valor, int Estilo_Celda, String Formato){
        celda(Hoja_Actual,xFila,xColumna,Valor,Estilos_Celda[Estilo_Celda], Formato);
    }
    
    public void Guardar(){
        guardar_hoja(Hoja_Actual,Archivo_xls);
    }
    
    public String toPDF(){
        return create_pdf(this.Archivo_xls);
    }
    
    public void viewPDF(final String filePath,final Boolean Impresion_Directa){
            
        Thread thread = new Thread() {
            @Override
            public void run() {
                //while (true) {

                    int Contador = 0;
                    while (Contador <= 2000) {
                        Contador++;
                        if (Lib_Archivo.Buscar_Archivo(filePath)) {
                            SwingController controller = new SwingController();
                            if (Impresion_Directa){
                                controller.openDocument(filePath);
                                controller.print(false);
                            } else {
                                SwingViewBuilder factory = new SwingViewBuilder(controller);
                                JPanel viewerComponentPanel = factory.buildViewerPanel();
                                ComponentKeyBinding.install(controller, viewerComponentPanel);
                                controller.getDocumentViewController().setAnnotationCallback( new org.icepdf.ri.common.MyAnnotationCallback( controller.getDocumentViewController() ) );
                                JFrame window = new JFrame("Using the Viewer Component");
                                window.getContentPane().add( viewerComponentPanel );
                                window.pack();
                                window.setVisible(true);
                                window.setLocationRelativeTo(null);
                                window.setExtendedState(window.getExtendedState() | window.MAXIMIZED_BOTH);
                                controller.openDocument(filePath);
                            }
                            break;
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Lib_Archivo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            //}
        };
        thread.start();
    }
            
            
    public void Cabecera_Deslizante(int Fila_Inicio, int Fila_Final){
        Hoja_Actual.getSheetAt(0).createFreezePane(Fila_Inicio,Fila_Final);
    }
    
    public void Repetir_Fila(int Fila_Inicia, int Fila_Final){
         Hoja_Actual.setRepeatingRowsAndColumns(0,0,0,Fila_Inicia, Fila_Final);    
    }   
    
    public void Print_Config(String Horientacion, String Tipo_Papel, String Numerar_Paginas, int Escala){
        HSSFPrintSetup Impresora = Hoja_Actual.getSheetAt(0).getPrintSetup();
        Impresora.setFooterMargin(0);
        Impresora.setHeaderMargin(0);
        Tipo_Papel=Tipo_Papel.toUpperCase();
        
        if(Tipo_Papel.equals("CARTA"))
           Impresora.setPaperSize(Impresora.LETTER_PAPERSIZE);

        if(Tipo_Papel.equals("OFICIO"))
           Impresora.setPaperSize(Impresora.EXECUTIVE_PAPERSIZE);

        if(Tipo_Papel.equals("A4"))
           Impresora.setPaperSize(Impresora.A4_PAPERSIZE);

        if(Tipo_Papel.equals("A5"))
           Impresora.setPaperSize(Impresora.A5_PAPERSIZE);

        if(Tipo_Papel.equals("LEGAL"))
           Impresora.setPaperSize(Impresora.LEGAL_PAPERSIZE);
         
        Impresora.setScale((short) Escala);
        
        
        Horientacion=Horientacion.toUpperCase();
        if(Horientacion.equals("H"))
           Impresora.setLandscape(true);
        else
           Impresora.setLandscape(false);
        
        //Numerar_Paginas=Numerar_Paginas.toUpperCase();
        //if(Numerar_Paginas.equals("S"))
        //   Hoja_Actual.getSheetAt(0).getHeader().setRight("Pagina: " + HSSFHeader.page() + " De " + HSSFHeader.numPages());

    }
    

    
    public static HSSFWorkbook crear_hoja(String Nombre_Hoja){
        HSSFWorkbook Hoja_Trabajo = null;
        HSSFSheet Hoja = null;
        try {
             Hoja_Trabajo = new HSSFWorkbook(); 
             Hoja = Hoja_Trabajo.createSheet(Nombre_Hoja);
             return Hoja_Trabajo;
        } catch (Throwable th) {
                th.printStackTrace();
        } 
        return null;
    }
  
    public static HSSFCellStyle estilo_celda(HSSFWorkbook Hoja, String Estilo_Fuente, int Tamanio_Fuente, String Justificacion_Fuente, String Bordes, String Formato, String Ajustar_Texto, String Background){
        HSSFFont Fuente = Hoja.createFont();
        HSSFCellStyle Estilo_Celda = Hoja.createCellStyle();
        
        Estilo_Celda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        if (Estilo_Fuente != null){
            if (Lib_String.strchar(Estilo_Fuente,"N")){
               Fuente.setBoldweight(Fuente.BOLDWEIGHT_BOLD);    
            }
            if (Lib_String.strchar(Estilo_Fuente,"I")){
               Fuente.setItalic(true);    
            }
        }
        if (Tamanio_Fuente>0)
           Fuente.setFontHeightInPoints((short) Tamanio_Fuente);
 
        if (Justificacion_Fuente.equals("C"))
            Estilo_Celda.setAlignment(Estilo_Celda.ALIGN_CENTER);

        if (Justificacion_Fuente.equals("I"))
            Estilo_Celda.setAlignment(Estilo_Celda.ALIGN_LEFT);

        if (Justificacion_Fuente.equals("D"))
            Estilo_Celda.setAlignment(Estilo_Celda.ALIGN_RIGHT);

        if (Justificacion_Fuente.equals("J"))
            Estilo_Celda.setAlignment(Estilo_Celda.ALIGN_JUSTIFY);
        
        
        Fuente.setFontName(Fuente.FONT_ARIAL);
        Estilo_Celda.setFont(Fuente);
        
        if (Formato!=null)
            Estilo_Celda.setDataFormat(Hoja.createDataFormat().getFormat(Formato));
        
        if (Bordes != null){
            Estilo_Celda.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            Estilo_Celda.setTopBorderColor(IndexedColors.BLACK.getIndex());
            Estilo_Celda.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            Estilo_Celda.setRightBorderColor(IndexedColors.BLACK.getIndex());
            if (Lib_String.strchar(Bordes,"C")){
                if (Lib_String.Buscar_Palabra2(Bordes,"LM")) Estilo_Celda.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                if (Lib_String.Buscar_Palabra2(Bordes,"RM")) Estilo_Celda.setBorderRight(HSSFCellStyle.BORDER_THIN);
                if (Lib_String.Buscar_Palabra2(Bordes,"TM")) Estilo_Celda.setBorderTop(HSSFCellStyle.BORDER_THIN);
                if (Lib_String.Buscar_Palabra2(Bordes,"BM")) Estilo_Celda.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                
                if (Lib_String.Buscar_Palabra2(Bordes,"LS")){
                    Estilo_Celda.setBorderLeft(HSSFCellStyle.BORDER_DOTTED);
                    Estilo_Celda.setLeftBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
                }
                if (Lib_String.Buscar_Palabra2(Bordes,"RS")){
                    Estilo_Celda.setBorderRight(HSSFCellStyle.BORDER_DOTTED);
                    Estilo_Celda.setRightBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
                }
                if (Lib_String.Buscar_Palabra2(Bordes,"TS")){
                    Estilo_Celda.setBorderTop(HSSFCellStyle.BORDER_DOTTED);
                    Estilo_Celda.setTopBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
                }
                if (Lib_String.Buscar_Palabra2(Bordes,"BS")) {
                    Estilo_Celda.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
                    Estilo_Celda.setBottomBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
                }
            } else {
                //BORDER_BIG
                if (Lib_String.strchar(Bordes,"M")){
                    if (Lib_String.strchar(Bordes,"L")) Estilo_Celda.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    if (Lib_String.strchar(Bordes,"R")) Estilo_Celda.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    if (Lib_String.strchar(Bordes,"T")) Estilo_Celda.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    if (Lib_String.strchar(Bordes,"B")) Estilo_Celda.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                }
                //BORDER_SMALL Estilo_Celda.
                if (Lib_String.strchar(Bordes,"S")){
                    if (Lib_String.strchar(Bordes,"L")) Estilo_Celda.setBorderLeft(HSSFCellStyle.BORDER_DOTTED);
                    if (Lib_String.strchar(Bordes,"R")) Estilo_Celda.setBorderRight(HSSFCellStyle.BORDER_DOTTED);
                    if (Lib_String.strchar(Bordes,"T")) Estilo_Celda.setBorderTop(HSSFCellStyle.BORDER_DOTTED);
                    if (Lib_String.strchar(Bordes,"B")) Estilo_Celda.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);

                    Estilo_Celda.setBottomBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
                    Estilo_Celda.setTopBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
                    Estilo_Celda.setLeftBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
                    Estilo_Celda.setRightBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
                }
            }
        }
        if (Ajustar_Texto.equals("S"))
            Estilo_Celda.setWrapText(true);       
        else
            Estilo_Celda.setWrapText(false);
        
        if (Background != null){
            if (Lib_String.strchar(Background,"Y")){
                Estilo_Celda.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                Estilo_Celda.setFillPattern(CellStyle.SOLID_FOREGROUND);
            }
            if (Lib_String.strchar(Background,"T")){
                Estilo_Celda.setFillForegroundColor(IndexedColors.ROSE.getIndex());
                Estilo_Celda.setFillPattern(CellStyle.SOLID_FOREGROUND);
            }
            if (Lib_String.strchar(Background,"R")){
                Estilo_Celda.setFillForegroundColor(IndexedColors.RED.getIndex());
                Estilo_Celda.setFillPattern(CellStyle.SOLID_FOREGROUND);
            }
            if (Lib_String.strchar(Background,"B")){
                Estilo_Celda.setFillForegroundColor(IndexedColors.BLUE.getIndex());
                Estilo_Celda.setFillPattern(CellStyle.SOLID_FOREGROUND);
            }
            if (Lib_String.strchar(Background,"G")){
                Estilo_Celda.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                Estilo_Celda.setFillPattern(CellStyle.SOLID_FOREGROUND);
            }
            if (Lib_String.strchar(Background,"P")){
                Estilo_Celda.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
                Estilo_Celda.setFillPattern(CellStyle.SOLID_FOREGROUND);
            }
            if (Lib_String.strchar(Background,"L")){
                Estilo_Celda.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
                Estilo_Celda.setFillPattern(CellStyle.SOLID_FOREGROUND);
            }
            if (Lib_String.strchar(Background,"V")){
                Estilo_Celda.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
                Estilo_Celda.setFillPattern(CellStyle.SOLID_FOREGROUND);
            }
        }

        return Estilo_Celda;
    }

    public static void setFilas(HSSFWorkbook Hoja, int xTamanio){
        Hoja.getSheetAt(0).setDefaultRowHeight((short) xTamanio);
    }
    
    public static void setColumnas(HSSFWorkbook Hoja, int xTamanio){
        Hoja.getSheetAt(0).setDefaultColumnWidth( xTamanio);
    }
    
    public static void setColumnas(HSSFWorkbook Hoja, int xColumna, int xTamanio){
        Hoja.getSheetAt(0).setColumnWidth( xColumna, xTamanio);
    }

    public static void guardar_hoja(HSSFWorkbook Hoja, String Archivo){
        try {
            FileOutputStream xFILE = new  FileOutputStream(Archivo);
            Hoja.write(xFILE);
            xFILE.close();
            Hoja.close();
            
        } catch (IOException th) {
            th.printStackTrace();
        } 
    }

    public static String create_pdf(String Archivo_xls){
        try {
            File file = new File(Archivo_xls);
            String input = file.getAbsolutePath();
            String output = System.getProperty("user.dir") + Lib_Archivo.NombreArchivoFecha("\\RP_ingresos", ".pdf");
            System.out.println("ExcelToPDF.exe "+input+" "+output );
            Process exec = Runtime.getRuntime().exec("ExcelToPDF.exe "+input+" "+output );
            return output;
        } catch (IOException ex) {
            Logger.getLogger(Lib_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static void celda(HSSFWorkbook Hoja, int xFile, int xColumna, String Valor, HSSFCellStyle Estilo_Celda, String Formato){

        HSSFRow xFila = Hoja.getSheetAt(0).getRow(xFile);
        if (xFila == null) xFila = Hoja.getSheetAt(0).createRow(xFile);
        HSSFCell xCelda = xFila.createCell(xColumna);
        if (Estilo_Celda!=null) 
           xCelda.setCellStyle(Estilo_Celda);

        if (Valor!=null){
            if (Formato==null)
               xCelda.setCellValue(Valor);
            else
            if (Formato.equals("N"))
               xCelda.setCellValue(Double.parseDouble(Valor));
            else
            if (Formato.equals("D") & Valor!=null & Valor.length()>8)
                  xCelda.setCellValue(java.sql.Date.valueOf(Valor));
            else            
            if (Formato.equals("F"))
               xCelda.setCellFormula(Valor);
            else
               xCelda.setCellValue(Valor);
        }
    }
    
    public static Integer Encabesado(Lib_Reportes Reporte, Integer line,  String Nombre_Empresa){
            Integer fontSize = 4;
            // Creacion de Estilos
            Reporte.setEstilo(1,"NI",fontSize ,"C",null,null,"N",null);
            
            // llenado de hoja de Excel
            Reporte.setCelda(1,0,"INGRESOS",1,null);
            
            
            return line;
    }
/*
  public static String Nombre_Empresa(Coneccion xCon){
        java.sql.ResultSet xEmpresa=xCon.Ejecutar_Sql("select rif,nit,nombre,rason_social,direccion,telefonos,fax from empresa where emp_codigo='" + Main.xEmpresa + "'" );
        try{
            xEmpresa.last();
            int Item=xEmpresa.getRow();
            xEmpresa.first();
            if (Item<=0) return "";
            return xEmpresa.getObject(3).toString()==null?"":xEmpresa.getObject(3).toString();
        }
        catch(java.sql.SQLException e){
             e.printStackTrace();
        }
        return "";
  }
  public static String Direccion_Empresa(Coneccion xCon){
        java.sql.ResultSet xEmpresa=xCon.Ejecutar_Sql("select rif,nit,nombre,rason_social,direccion,telefonos,fax from empresa where emp_codigo='" + Main.xEmpresa + "'" );
        try{
            xEmpresa.last();
            int Item=xEmpresa.getRow();
            xEmpresa.first();
            if (Item<=0) return "";
            return xEmpresa.getObject(3).toString()==null?"":xEmpresa.getObject(5).toString();
        }
        catch(java.sql.SQLException e){
             e.printStackTrace();
        }
        return "";
  }

  public static int Titulo(HSSFWorkbook Hoja, Coneccion xCon, String xTitulo){
      
        java.sql.ResultSet xEmpresa=xCon.Ejecutar_Sql("select rif,nit,nombre,rason_social,direccion,telefonos,fax from empresa where emp_codigo='" + Main.xEmpresa + "'" );
        HSSFCellStyle Titulo01=estilo_celda(Hoja,"N",18,"I","N",null,"N");
        HSSFCellStyle Titulo02=estilo_celda(Hoja,"N",12,"I","N",null,"N");
        HSSFCellStyle Titulo03=estilo_celda(Hoja,"N",8,"I","N",null,"N");
    
        try{
            xEmpresa.last();
            int Item=xEmpresa.getRow();
            xEmpresa.first();
            if (Item<=0) return 7;
            
            celda(Hoja,0,0,xEmpresa.getObject(3).toString()==null?"":xEmpresa.getObject(3).toString(),Titulo01,null);
            celda(Hoja,1,0,"RIF: " + xEmpresa.getObject(1).toString()==null?"":xEmpresa.getObject(1).toString(),Titulo02,null);
            //celda(Hoja,2,0,"NIT: " + xEmpresa.getObject(2).toString()==null?"":xEmpresa.getObject(2).toString(),Titulo02,null);
            celda(Hoja,3,0,"Direccion: " + xEmpresa.getObject(5).toString()==null?"":xEmpresa.getObject(5).toString(),Titulo03,null);
            celda(Hoja,4,0,"Telefonos: " + xEmpresa.getObject(6).toString()==null?"":xEmpresa.getObject(6).toString(),Titulo03,null);
            celda(Hoja,5,0,xTitulo,Titulo02,null);
        }
        
        catch(java.sql.SQLException e){
             e.printStackTrace();
        }           
        return 7;
    }*/
}

