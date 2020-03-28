/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportestpv;

import com.itextpdf.text.*;
import java.awt.Dimension;
import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author PrometeoNew666
 */
public class Reportes extends javax.swing.JFrame {

    /**
     * Creates new form Reportes
     */
    public Reportes() {
        initComponents();
        this.setSize(800, 600);
        JPanelMenu.setSize(800, 600);
        this.setLocationRelativeTo(null);        
    }
    
    public void ReporteReposicionNochero(final Integer CodigoDia, final JPanel Panel) {        
        Thread TreadImprimirRecaudacionDia_a = new Thread() {
            @Override
            public void run() {
                ReporteReposicionNocheroGlobal(CodigoDia, null);
            }
        };
        TreadImprimirRecaudacionDia_a.start();
        
    }
    public void ReporteReposicionNocheroGlobal(Integer CodigoDia, JPanel Panel) {
        //int Respuesta = Lib_Msj.JMsgbox(this, "Desea Imprimir La Informacion?", "Imprimir", Lib_Msj.SI_NO);
        //if (JOptionPane.YES_OPTION == Respuesta || JOptionPane.OK_OPTION == Respuesta) {
        
        try {
            //tblUsuarios Usuarios = new tblUsuarios();
            //Usuarios.buscarLogin(Usuario);
            //tblDia Dia = new tblDia().buscarUltimoDia("ABIERTO");

            //String idUsuario = "" + Usuarios.getID();
            String idUsuario = "01"; 
            //String idCodigoDia = "" + Dia.getCodigoDia();
            String InSql = "SELECT DescripcionProducto AS ARTICULO ,\n" +
                            "FORMAT(SUM(CantidadSistema),0) AS UNIDADES, \n" +
                            "FORMAT(SUM(EnvasesRetirados),0) AS ENVASES, \n" +
                            "FORMAT(SUM(CantidadRepuesta),0) AS REPUESTO\n" +
                            "FROM dbsempresas.tblreposicioninventario\n" +
                            "WHERE EstadoRegistro = 'ACTIVO' AND CodigoDia = "+CodigoDia.toString()+"\n" +
                            "GROUP BY descripcionproducto  ;";
   
            System.out.println(InSql);
            ResultSet resultSet = Main.CONEXION.Ejecutar_Sql(InSql);
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
            
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteReposicionNochero.jasper");

            //el codigo 1975 indica q el documento es tipo reserva
            String CodigoBarra = "200620062006";  
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("Usuario", "NOMBRE USUARIO");
            param.put("CodigoDia", CodigoDia);
            param.put("codigobarra", CodigoBarra);

            JasperPrint jasperprint = JasperFillManager.fillReport(reporte, param, resultSetDataSource);
            
            // visualiza la factura en el panel indicado siempre y cuando este
            // no sea nulo y respete las espesificacioes
            if (Panel == null) {
                new Impresoras().setImpresora(jasperprint, false, Impresoras.IMPRESORA_TICKET);
                //JasperPrintManager.printReport(jasperprint, false);
            } else {
                JRViewer VerReporte = new JRViewer(jasperprint);
                
                VerReporte.setOpaque(true);
                VerReporte.setVisible(true);
                VerReporte.setZoomRatio(Float.parseFloat("0.70"));
                VerReporte.setSize(new Dimension(800, 600));
                VerReporte.setLocation(0, 0);
                
                Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                Panel.add(VerReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));
                Panel.repaint();
                Panel.revalidate();
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e); 
        }               
    }
    
    
    public void ReporteReposicion(final Integer CodigoDia, final JPanel Panel) {        
        Thread TreadImprimirRecaudacionDia_a = new Thread() {
            @Override
            public void run() {
                ReporteReposicionGlobal(CodigoDia, null);
            }
        };
        TreadImprimirRecaudacionDia_a.start();
        
    }
    public void ReporteReposicionGlobal(Integer CodigoDia, JPanel Panel) {
        //int Respuesta = Lib_Msj.JMsgbox(this, "Desea Imprimir La Informacion?", "Imprimir", Lib_Msj.SI_NO);
        //if (JOptionPane.YES_OPTION == Respuesta || JOptionPane.OK_OPTION == Respuesta) {
        
        try {
            //tblUsuarios Usuarios = new tblUsuarios();
            //Usuarios.buscarLogin(Usuario);
            //tblDia Dia = new tblDia().buscarUltimoDia("ABIERTO");
            
            
            //String idUsuario = "" + Usuarios.getID();
            String idUsuario = "01";
            //String idCodigoDia = "" + Dia.getCodigoDia();
            String InSql = "SELECT descripcionproducto AS ARTICULO ,SUM(cantidad) AS UNIDADES\n" +
                           "FROM dbsempresas.tblfacturaitem\n" +
                           "WHERE EstadoRegistro = 'ACTIVO' AND CodigoDia = "+CodigoDia.toString()+"\n" +
                           "GROUP BY descripcionproducto  ;";
   
            System.out.println(InSql);
            ResultSet resultSet = Main.CONEXION.Ejecutar_Sql(InSql);
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
            
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteReposicion.jasper");

            //el codigo 1975 indica q el documento es tipo reserva
            String CodigoBarra = "200620062006";  
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("Usuario", "NOMBRE USUARIO");
            param.put("CodigoDia", CodigoDia);
            param.put("codigobarra", CodigoBarra);
            
            JasperPrint jasperprint = JasperFillManager.fillReport(reporte, param, resultSetDataSource);
            
            // visualiza la factura en el panel indicado siempre y cuando este
            // no sea nulo y respete las espesificacioes
            if (Panel == null) {
                new Impresoras().setImpresora(jasperprint, false, Impresoras.IMPRESORA_TICKET);
                //JasperPrintManager.printReport(jasperprint, false);
            } else {
                JRViewer VerReporte = new JRViewer(jasperprint);
                
                VerReporte.setOpaque(true);
                VerReporte.setVisible(true);
                VerReporte.setZoomRatio(Float.parseFloat("0.70"));
                VerReporte.setSize(new Dimension(800, 600));
                VerReporte.setLocation(0, 0);
                
                Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                Panel.add(VerReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));
                Panel.repaint();
                Panel.revalidate();
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            
        }               
    }
    
  
    public void ReporteCierreGeneral(final String CodigoDia, final JPanel Panel) {        
        Thread TreadImprimirCierreGeneral = new Thread() {
            @Override
            public void run() {
                try {
                    ReporteCierreGeneralGlobal(CodigoDia, Panel);
                } catch (DocumentException ex) {
                    Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        TreadImprimirCierreGeneral.start();
    }
    public void ReporteCierreGeneralGlobal(String CodigoDia, JPanel Panel) throws DocumentException, FileNotFoundException, IOException {
        
        try {
            
            ResultSet resultSet = Main.CONEXION.Ejecutar_Sql("CALL dbsempresas.cierre_sector("+CodigoDia+");");
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);

            ResultSet rs = Main.CONEXION.Ejecutar_Sql(" SELECT "
                                                    + " CASE WHEN DescripcionGasto IS NULL THEN 'GASTO' ELSE DescripcionGasto END AS DescripcionGasto, "
                                                    + " CASE WHEN TotalGasto IS NULL THEN FORMAT(0.0,1) ELSE TotalGasto END  AS TotalGasto"
                                                    + " FROM dbsempresas.tblgastos "
                                                    + " WHERE CodigoDia = "+CodigoDia+" and TipoPago = 'EFECTIVO' and Estado = 'PAGADO' and EstadoRegistro = 'ACTIVO'");
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();   
            /* List to hold Items */
            ArrayList listItems = new ArrayList<Item>();
            while (rs.next()) {
                Item item = new Item();
                item.setName(rs.getString(1));
                item.setPrice(rs.getDouble(2));
                System.out.println(item.getName() + " = " + item.getPrice().toString());
                listItems.add(item);
            }
            if (listItems.isEmpty()){
                Item item = new Item();
                item.setName("Sin Gasto");
                item.setPrice(0.0);
                listItems.add(item);
            }
            /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteCierre_v1.jasper");
            //reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteCierre_Sector.jasper");
            
            String CodigoBarra = "200620062006";  
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("CODIGO_DIA", CodigoDia);
            param.put("codigobarra", CodigoBarra);
            param.put("ItemDataSource", itemsJRBean);
            
            JasperPrint jasperprint = (JasperPrint)JasperFillManager.fillReport(reporte, param, resultSetDataSource);

            // visualiza la factura en el panel indicado siempre y cuando este
            // no sea nulo y respete las espesificacioes
            if (Panel == null) {
                
                new Impresoras().setImpresora(jasperprint, false, Impresoras.IMPRESORA_TICKET);
                //JasperPrintManager.printReport(jasperprint, false);
            } else {
                JRViewer VerReporte = new JRViewer(jasperprint);
                
                VerReporte.setOpaque(true);
                VerReporte.setVisible(true);
                VerReporte.setZoomRatio(Float.parseFloat("0.70"));
                //VerReporte.setSize(new Dimension(800, 600));
                VerReporte.setSize(new Dimension(600, 850));
                VerReporte.setLocation(0, 0);
                
                Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                Panel.add(VerReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));
                Panel.repaint();
                Panel.revalidate();
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            
        }
    }
       
        
    public void ReporteIngresos(final String fecha, final Boolean Impresion_Directa) {        
        Thread TreadImprimirIngresos = new Thread() {
            @Override
            public void run() {
                try {
                    ReporteIngresosGlobal(fecha, Impresion_Directa);
                } catch (DocumentException ex) {
                    Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        TreadImprimirIngresos.start();
    }  
    public void ReporteIngresosGlobal(String fecha, Boolean Impresion_Directa) throws DocumentException, FileNotFoundException, IOException {
        //int Respuesta = Lib_Msj.JMsgbox(this, "Desea Imprimir La Informacion?", "Imprimir", Lib_Msj.SI_NO);
        //if (JOptionPane.YES_OPTION == Respuesta || JOptionPane.OK_OPTION == Respuesta) {
        
        try {
            String InSql = "CALL dbsempresas.dias_mes_dinamico('2020/02/01');";
            System.out.println(InSql);
            ResultSet rs = Main.CONEXION.Ejecutar_Sql(InSql);
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            ArrayList<HashMap> list = new ArrayList<>();
            int row = 0;
            while (rs.next()){
               HashMap<String, String> map = new HashMap<>(columnCount);
               for(int i=1; i<=columnCount; ++i){ 
                    map.put(metadata.getColumnName(i),rs.getString(i));
               }
               list.add(map);
            }

            // Creacion de hoja de Excel
            
            String xArchivo = Lib_Archivo.NombreArchivoFecha("RP_Ingresos", ".xls") ;
            Lib_Reportes Ingresos = new Lib_Reportes(xArchivo,"Ingresos");
          
            int fontSize = 4;
            // Creacion de Estilos
            Ingresos.setEstilo(1, "NI",fontSize+2,"C","M-LRTB","[Red]","N",null);
            Ingresos.setEstilo(2, "NI",fontSize  ,"C","C LM RM BS","[Red]","N",null);
            Ingresos.setEstilo(22,"NI",fontSize+1,"C","C LM RM BS","[Red]","N",null);
            Ingresos.setEstilo(23,"NI",fontSize+2,"C","C LM RM BS","[Red]","N",null);
            
            Ingresos.setEstilo(3, "NI",fontSize  ,"C","C LM RM BS",null,"N",null);
            Ingresos.setEstilo(32,"NI",fontSize+1,"C","C LM RM BS",null,"N",null);
            
            Ingresos.setEstilo(4, "I" ,fontSize+1  ,"C","C LM RM BS",null,"N",null);
            Ingresos.setEstilo(42,"I" ,fontSize+2,"C","C LM RM BS",null,"N",null);
            Ingresos.setEstilo(43,"I" ,fontSize+2,"C","C LM RM BS",null,"N",null);
            Ingresos.setEstilo(44,"I" ,fontSize+3,"C","C LM RM BS",null,"N",null);
            
            Ingresos.setEstilo(5, "I" ,fontSize+2 ,"C","S-LRTB",null,"N",null);
            Ingresos.setEstilo(6, "I" ,fontSize+2 ,"C","S-LRTB",null,"N",null);
            Ingresos.setEstilo(7, "NI",fontSize+3 ,"C","C TM BM LS RS",null,"N",null); 
            Ingresos.setEstilo(8, "NI",fontSize+3 ,"C","C TM BM LS RS",null,"N","R"); 
            
            Ingresos.setEstilo(9, "NI",fontSize+2,"R","C LM RM BS","[Blue]","N",null);
            
            Ingresos.setEstilo(10,"NI",fontSize+2,"C","M-LRT","[White]","N","B");
            Ingresos.setEstilo(11,"NI",fontSize  ,"C","M-LR","[Red]","N","T");
            Ingresos.setEstilo(12,"NI",fontSize+3  ,"C","M-LRB",null,"N","L");
            
            Ingresos.setEstilo(13,"NI",fontSize+2  ,"C","M-LRTB",null,"N",null);
            
            Ingresos.setEstilo(14,"NI",fontSize+2,"C","C TM BS LS RS",null,"N",null);
            Ingresos.setEstilo(15,"I" ,fontSize+1,"C","C LS RS",null,"N",null);
            Ingresos.setEstilo(16,"I" ,fontSize+2,"C","C BM TS LS RS",null,"N",null);
            
            Ingresos.setEstilo(17,"NI",fontSize+1,"C","C LM RM TM BS",null,"N",null);
            Ingresos.setEstilo(18,"I",fontSize+1,"C","M-LR",null,"N",null);
            Ingresos.setEstilo(19,"I",fontSize+1,"C","C LM RM BM TS",null,"N",null);
            
            Ingresos.setEstilo(20,"NI",fontSize  ,"C",null,null,"N",null);
            // Configuracion de hoja 
            Ingresos.Print_Config("H","A4","S",150);
            
            // llenado de hoja de Excel
            Ingresos.setCelda(row++,0,"INGRESOS",1,null);
            Ingresos.setCelda(row++,0,"BARRA № Clientes",2,null);
            Ingresos.setCelda(row++,0,"EFECTIVO",3,null);
            Ingresos.setCelda(row++,0,"1ra RONDA",4,null);
            Ingresos.setCelda(row++,0,"2da RONDA",4,null);
            Ingresos.setCelda(row++,0,"3ra RONDA",4,null);
            Ingresos.setCelda(row++,0,"Otros Ingr.",43,null);
            Ingresos.setCelda(row++,0,"C. Especiales",43,null);
            Ingresos.setCelda(row++,0,"C. Normales",43,null);
            Ingresos.setCelda(row++,0,"INGR. VISAS",3,null);
            Ingresos.setCelda(row++,0,"Consumisiones H",42,null);         
            Ingresos.setCelda(row++,0,"Amazonas + Floridas",42,null);
            Ingresos.setCelda(row++,0,"Recep № Huesp",22,null);
            Ingresos.setCelda(row++,0,"Internas",42,null);
            Ingresos.setCelda(row++,0,"Externas",42,null);
            Ingresos.setCelda(row++,0,"Pension Compl",42,null);
            Ingresos.setCelda(row++,0,"1/2 Pension",42,null);
            Ingresos.setCelda(row++,0,"Rescuperadas",42,null);
            Ingresos.setCelda(row++,0,"Viajes Taxi",42,null);
            Ingresos.setCelda(row++,0,"Otros Ingr.",43,null);
            Ingresos.setCelda(row++,0,"KIT SABA 1/2H",43,null);
            Ingresos.setCelda(row++,0,"KIT SABA 1H",43,null);
            Ingresos.setCelda(row++,0,"INGR. EFECTIVO",32,null);
            Ingresos.setCelda(row++,0,"INGR. VISAS",32,null);
            Ingresos.setCelda(row++,0,"Otros Ingr.",43,null);
            Ingresos.setCelda(row++,0,"Fueras de hora № ",23,null);
            Ingresos.setCelda(row++,0,"DE BARRA",3,null);
            Ingresos.setCelda(row++,0,"C. Normales",44,null);
            Ingresos.setCelda(row++,0,"C. Especiales",44,null);
            Ingresos.setCelda(row++,0,"Kit de Sab. 1/2H",44,null);
            Ingresos.setCelda(row++,0,"Kit de Sab 1H",44,null);
            Ingresos.setCelda(row++,0,"INGR. EFECTIVO",43,null);
            Ingresos.setCelda(row++,0,"INGR. VISAS",43,null);
            Ingresos.setCelda(row++,0,"MAQUINA SNAK",42,null);
            Ingresos.setCelda(row++,0,"MAQUINA MUSICA",42,null);
            Ingresos.setCelda(row++,0,"MAQUINA TABACO",42,null);
            Ingresos.setCelda(row++,0,"Otros Ingr.",43,null);
            Ingresos.setCelda(row++,0,"Otros Ingr.",43,null);
            Ingresos.setCelda(row++,0,"TOTAL INGRESOS",10,null);
            Ingresos.setCelda(row++,0,"GASTOS x DIA",11,null);
            Ingresos.setCelda(row++,0,"Resultado Bruto",12,null);

            String strCol = "B C B E F G H I J K L M N O P Q R S T U V W X Y Z AA AB AC AD AE";   
            Calendar calendar = Calendar.getInstance();
            String[] fechaDia = fecha.split("/");
            int style = 5;
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String>  map = list.get(i);
                row = 0;
                int col = i+1;
                int StyleSunday = 7;
                calendar.set(Integer.parseInt(fechaDia[0]), Integer.parseInt(fechaDia[1]) - 1, Integer.parseInt(map.get("dia")));
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) StyleSunday = 8;
                
                Ingresos.setCelda(row++, col, map.get("dia"), StyleSunday, "N");
                Ingresos.setCelda(row++, col, map.get("numClientesBarra"),style,"N"); // BARRA № Clientes
                Ingresos.setCelda(row++, col, map.get("totalEfectivoBarra"),style,"N"); // EFECTIVO
                Ingresos.setCelda(row++, col, map.get("numClientesRonda1"),style,"N"); // 1ra RONDA
                Ingresos.setCelda(row++, col, map.get("numClientesRonda2"),style,"N"); // 2da RONDA
                Ingresos.setCelda(row++, col, map.get("numClientesRonda3"),style,"N"); // 3ra RONDA
                Ingresos.setCelda(row++, col, map.get("totalOtrosIngrBarra"),style,"N"); // Otros Ingr.
                Ingresos.setCelda(row++, col, "0",6,"N"); // C. Especiales
                Ingresos.setCelda(row++, col, "0",6,"N"); // C. Normales
                Ingresos.setCelda(row++, col, map.get("totalVisaBarra"),style,"N"); // INGR. VISAS
                Ingresos.setCelda(row++, col, map.get("totalConsumosHuespedes"),style,"N"); // Consumisiones H
                Ingresos.setCelda(row++, col, "0",6,"N"); // Amazonas + Floridas
                Ingresos.setCelda(row++, col, "0",6,"N"); // Recep № Huesp
                Ingresos.setCelda(row++, col, "0",6,"N"); // Internas
                Ingresos.setCelda(row++, col, "0",6,"N"); // Externas
                Ingresos.setCelda(row++, col, map.get("numPensionCompleta"),6,"N"); // Pension Compl
                Ingresos.setCelda(row++, col, map.get("numPensionMedia"),6,"N"); // 1/2 Pension
                Ingresos.setCelda(row++, col, map.get("numPensionRecuperada"),6,"N"); // Rescuperadas
                Ingresos.setCelda(row++, col, "0",6,"N"); // Viajes Taxi
                Ingresos.setCelda(row++, col, "0",6,"N"); // Otros Ingr.
                Ingresos.setCelda(row++, col, map.get("numKitSabMediaHora"),6,"N"); // KIT SABA 1/2H
                Ingresos.setCelda(row++, col, map.get("numKitSabUnaHora"),6,"N"); // KIT SABA 1H
                Ingresos.setCelda(row++, col, map.get("totalHotelEfectivo"),6,"N"); // INGR. EFECTIVO    
                Ingresos.setCelda(row++, col, map.get("totalHotelVisa"),6,"N"); // INGR. VISAS
                Ingresos.setCelda(row++, col, map.get("totalHotelOtros"),6,"N"); // Otros Ingr.
                Ingresos.setCelda(row++, col, "0",6,"N"); // Fueras de hora № 
                Ingresos.setCelda(row++, col, "0",6,"N"); // DE BARRA
                Ingresos.setCelda(row++, col, "0",6,"N"); // C. Normales
                Ingresos.setCelda(row++, col, "0",6,"N"); // C. Especiales
                Ingresos.setCelda(row++, col, map.get("numKitSabMediaHoraFueraHora"),6,"N"); // Kit de Sab. 1/2H
                Ingresos.setCelda(row++, col, map.get("numKitSabUnaHoraFueraHora"),6,"N"); // Kit de Sab 1H
                Ingresos.setCelda(row++, col, map.get("totalEfectivoFueraHora"),6,"N"); // INGR. EFECTIVO
                Ingresos.setCelda(row++, col, map.get("totalVisaFueraHora"),6,"N"); // INGR. VISAS
                Ingresos.setCelda(row++, col, "0",6,"N"); // MAQUINA SNAK
                Ingresos.setCelda(row++, col, "0",6,"N"); // MAQUINA MUSICA
                Ingresos.setCelda(row++, col, "0",6,"N"); // MAQUINA TABACO
                Ingresos.setCelda(row++, col, "0",6,"N"); // Otros Ingr.
                Ingresos.setCelda(row++, col, "0",6,"N"); // Otros Ingr.
                
                Ingresos.setCelda(row++, col, "SUM("+strCol.split("\\s+")[i]+"2:"+strCol.split("\\s+")[i]+"38)",14,"F");
                Ingresos.setCelda(row++, col, "0",15,"N");
                Ingresos.setCelda(row++, col, strCol.split("\\s+")[i]+"39-"+strCol.split("\\s+")[i]+"40",16,"F");   
            }

            // Calculo de Totales
            row = 0;
            int col = list.size()+1;
            Ingresos.setCelda(row++, col,"Total",13,null);
            for (int i=2;i<=38 ; i++){
                Ingresos.setCelda(row++, col, "SUM(B"+Integer.toString(i)+":AE"+Integer.toString(i)+")",9,"F");
            }
            Ingresos.setCelda(row++, col, "SUM(AF2:AF38)",17,"F");
            Ingresos.setCelda(row++, col, "SUM(B40:AE40)",18,"F");
            Ingresos.setCelda(row++, col, "AF39-AF40",19,"F");
            
            // Austando Tamaño de las Columnas
            //Ingresos.setCelda(row++, col, "0",20,"N");
            float sizeBase = 8; 
            for (int i=0;i<=40 ; i++) {
                if ( i == 38 ) Ingresos.Hoja_Actual.getSheetAt(0).getRow(i).setHeightInPoints((float)sizeBase+8);
                else if ( i == 39 ) Ingresos.Hoja_Actual.getSheetAt(0).getRow(i).setHeightInPoints((float) sizeBase+2);
                else if ( i == 40 ) Ingresos.Hoja_Actual.getSheetAt(0).getRow(i).setHeightInPoints((float) sizeBase+8);
                //else if ( i == 41 ) Ingresos.Hoja_Actual.getSheetAt(0).getRow(i).setHeightInPoints((float) sizeBase*5);
                else Ingresos.Hoja_Actual.getSheetAt(0).getRow(i).setHeightInPoints((float) sizeBase+(float) 1.2);
            }
            for (int i=1;i<=100 ; i++) 
                Ingresos.setColumna(i,713);
            
            Ingresos.setColumna(0,2750);
            Ingresos.setColumna(list.size()+1,950);
            Ingresos.setColumna(list.size()+2,5000);
            
            Ingresos.setColumna(41,950);  
            
            // Guardado de hoja de Excel
            Ingresos.Guardar();
            
            String filePath = Ingresos.toPDF();
            
            Ingresos.viewPDF(filePath, Impresion_Directa);
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e); 
        }
       
    }
    
    
    
    
    public String getMonthName(int month) {
        
        String monthName = null;
        switch (month) {
        case 0:
                monthName = "ENERO";
                break;
        case 1:
                monthName = "FEBRERO";
                break;
        case 2:
                monthName = "MARZO";
                break;
        case 3:
                monthName = "ABRIL";
                break;
        case 4:
                monthName = "MAYO";
                break;
        case 5:
                monthName = "JUNIO";
                break;
        case 6:
                monthName = "JULIO";
                break;
        case 7:
                monthName = "AGOSTO";
                break;
        case 8:
                monthName = "SEPTIEMBRE";
                break;
        case 9:
                monthName = "OCTUBRE";
                break;
        case 10:
                monthName = "NOVIEMBRE";
                break;
        case 11:
                monthName = "DICIEMBRE";
                break;
        }

        return monthName;
}
    
    public void ReporteGastosNew(final String fecha, final JPanel Panel) {        
        Thread TreadImprimirGastos = new Thread() {
            @Override
            public void run() {
                try {
                    ReporteGastosGlobalNew(fecha, Panel);
                } catch (ParseException ex) {
                    Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        TreadImprimirGastos.start();
    }  
    
    public void ReporteGastosGlobalNew(String fecha, JPanel Panel) throws ParseException{
        
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            dateFormat.setLenient(false);
            Date mifecha = dateFormat.parse(fecha);
            String sql ="SELECT Orden, LabelReporte, NombreProcedimiento, TipoDato\n" +
                        "FROM dbsempresas.tblformatoreportes \n" +
                        "WHERE NombreReporte = 'INGRESOS MENSUALES' \n" +
                        "AND Estado = 'ACTIVO' \n" +
                        "AND EstadoRegistro = 'ACTIVO'\n" +
                        "ORDER BY Orden;";
            System.out.println(sql);
            ResultSet rs = Main.CONEXION.Ejecutar_Sql(sql);
            ArrayList report = new ArrayList<ItemMes>();
            ArrayList encabezado = new ArrayList<ItemMes>();
            ArrayList totales_ingresos = new ArrayList<ItemMes>();
            ArrayList report_gastos = new ArrayList<ItemMes>();
            ArrayList totales_gastos = new ArrayList<ItemMes>();
            ArrayList totales_neto = new ArrayList<ItemMes>();
            
            while (rs.next()){
                ItemMes line = new ItemMes();
                line.setOrden((Integer) rs.getInt("Orden"));
                line.setLabel(rs.getString("LabelReporte"));
                line.setTipo_dato(rs.getString("TipoDato"));
                
                ResultSet rsStore = Main.CONEXION.Ejecutar_Sql("CALL "+rs.getString("NombreProcedimiento")+"('"+fecha+"')");
                while (rsStore.next()){
                    if ( line.getOrden() == 1 ) 
                        line.setValue(rsStore.getInt("dia"));
                    else
                        line.setValue(rsStore.getInt("dia"), rsStore.getInt("valor"));
                }
                if ( line.getOrden() == 1 ) 
                    encabezado.add(line); 
                else{
                    line.sumTotal();
                    line.println();
                    report.add(line); 
                }
            }
            
            //GASTOS
            ResultSet rsGastos = Main.CONEXION.Ejecutar_Sql("CALL reporte_gastos('"+fecha+"')");
            while (rsGastos.next()){
                ItemMes line = new ItemMes();
                line.setLabel(rsGastos.getString("label"));
                line.setValue(rsGastos.getInt("dia"), rsGastos.getInt("valor"));
                line.setTipo_dato("CONTABLE");
                line.sumTotal();
                line.println();    
                report_gastos.add(line);
            }    
            
            //ADD LINE BLANK
            int lines_blank = 29;
            int last_item = 0;
            if (report_gastos.size()> 0){
                lines_blank -= report_gastos.size();
                last_item = ((ItemMes)report_gastos.get(report_gastos.size()-1)).getOrden()+1;
            }   
            for (int i = 0; i < lines_blank; i++) {
                ItemMes line = new ItemMes();
                line.setOrden(last_item++);
                line.setTipo_dato("INFORMACION");
                line.sumTotal();
                line.println();
                report_gastos.add(line);
            }  
            
            //TOTALES INGRESOS
            ItemMes ingresos = new ItemMes();
            ingresos.calcularTotal(report);
            ingresos.setOrden(1);
            ingresos.setLabel("TOTAL INGRESOS");
            ingresos.setTipo_dato("CONTABLE");
            ingresos.sumTotal();
            ingresos.println();
            totales_ingresos.add(ingresos);
            
            //TOTALES GASTOS
            ItemMes gastos = new ItemMes();
            gastos.calcularTotal(report_gastos);
            gastos.setOrden(2);
            gastos.setLabel("GASTOS X DIA");
            gastos.setTipo_dato("CONTABLE");
            gastos.sumTotal();
            gastos.println();
            totales_gastos.add(gastos);
            
            //TOTAL NETO
            ItemMes neto = new ItemMes(((ItemMes)totales_ingresos.get(0)));
            neto.calcularDiferencia(((ItemMes)totales_gastos.get(0)));
            neto.setOrden(3);
            neto.setLabel("RESULTADO NETO");
            neto.setTipo_dato("CONTABLE");
            neto.sumTotal();
            neto.println();
            totales_neto.add(neto);
            
            Collections.sort(report, new Comparator() {
                @Override
                public int compare(Object p1, Object p2) {
                            return ((ItemMes)p1).getOrden().compareTo(((ItemMes)p2).getOrden());
                }
            });
            
            JRBeanCollectionDataSource JRdataEncabezado = new JRBeanCollectionDataSource(encabezado);          
            JRBeanCollectionDataSource JRdataReporte = new JRBeanCollectionDataSource(report_gastos);
            JRBeanCollectionDataSource JRdataTotalesIngresos = new JRBeanCollectionDataSource(totales_ingresos);
            JRBeanCollectionDataSource JRdataTotalesGastos = new JRBeanCollectionDataSource(totales_gastos);
            JRBeanCollectionDataSource JRdataTotalesNeto = new JRBeanCollectionDataSource(totales_neto);
            
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("ItemEncabezadoDataSource",JRdataEncabezado );
            param.put("ItemReporteDataSource",JRdataReporte );
            param.put("ItemTotalesIngresosDataSource",JRdataTotalesIngresos );
            param.put("ItemTotalesGastosDataSource",JRdataTotalesGastos );
            param.put("ItemTotalesNetoDataSource",JRdataTotalesNeto );
            param.put("Mes", getMonthName(mifecha.getMonth()));
            
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteGastos_Global.jasper");
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(Main.CONEXION.Ejecutar_Sql(sql));
            JasperPrint jasperprint = (JasperPrint)JasperFillManager.fillReport(reporte, param, resultSetDataSource);
                
            // Visualiza la factura en el panel indicado siempre y cuando este
            // no sea nulo y respete las espesificacioes
            if (Panel == null) {                
                new Impresoras().setImpresora(jasperprint, false, Impresoras.IMPRESORA_TICKET);
                //JasperPrintManager.printReport(jasperprint, false);
            } else {
                JRViewer VerReporte = new JRViewer(jasperprint);
                
                VerReporte.setOpaque(true);
                VerReporte.setVisible(true);
                VerReporte.setZoomRatio(Float.parseFloat("0.70"));
                //VerReporte.setSize(new Dimension(800, 600));
                VerReporte.setSize(new Dimension(600, 850));
                VerReporte.setLocation(0, 0);
                
                Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                Panel.add(VerReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));
                Panel.repaint();
                Panel.revalidate();
            }
            
        } catch (SQLException | JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ReporteIngresosNew(final String fecha, final JPanel Panel) {        
        Thread TreadImprimirIngresos = new Thread() {
            @Override
            public void run() {
                try {
                    ReporteIngresosGlobalNew(fecha, Panel);
                } catch (ParseException ex) {
                    Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        TreadImprimirIngresos.start();
    }  
    public void ReporteIngresosGlobalNew(String fecha, JPanel Panel) throws ParseException{
        
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            dateFormat.setLenient(false);
            Date mifecha = dateFormat.parse(fecha);
            String sql ="SELECT Orden, LabelReporte, NombreProcedimiento, TipoDato\n" +
                        "FROM dbsempresas.tblformatoreportes \n" +
                        "WHERE NombreReporte = 'INGRESOS MENSUALES' \n" +
                        "AND Estado = 'ACTIVO' \n" +
                        "AND EstadoRegistro = 'ACTIVO'\n" +     
                        "ORDER BY Orden;";
            System.out.println(sql);
            ResultSet rs = Main.CONEXION.Ejecutar_Sql(sql);
            ArrayList report = new ArrayList<ItemMes>();
            ArrayList encabezado = new ArrayList<ItemMes>();
            ArrayList totales_ingresos = new ArrayList<ItemMes>();
            ArrayList report_gastos = new ArrayList<ItemMes>();
            ArrayList totales_gastos = new ArrayList<ItemMes>();
            ArrayList totales_neto = new ArrayList<ItemMes>();
            
            while (rs.next()){
                ItemMes line = new ItemMes();
                line.setOrden((Integer) rs.getInt("Orden"));
                line.setLabel(rs.getString("LabelReporte"));
                line.setTipo_dato(rs.getString("TipoDato"));
                
                ResultSet rsStore = Main.CONEXION.Ejecutar_Sql("CALL "+rs.getString("NombreProcedimiento")+"('"+fecha+"')");
                while (rsStore.next()){
                    if ( line.getOrden() == 1 ) 
                        line.setValue(rsStore.getInt("dia"));
                    else
                        line.setValue(rsStore.getInt("dia"), rsStore.getInt("valor"));
                }
                if ( line.getOrden() == 1 ) 
                    encabezado.add(line); 
                else{
                    line.sumTotal();
                    line.println();
                    report.add(line); 
                }
            }
            //ADD LINE BLANK
            int lines_blank = 24;
            int last_item = ((ItemMes)report.get(report.size()-1)).getOrden()+1;
            for (int i = 0; i < lines_blank; i++) {
                ItemMes line = new ItemMes();
                line.setOrden(last_item++);
                line.setTipo_dato("INFORMACION");
                line.sumTotal();
                line.println();
                report.add(line);
            }
            
            //TOTALES INGRESOS
            ItemMes ingresos = new ItemMes();
            ingresos.calcularTotal(report);
            ingresos.setOrden(1);
            ingresos.setLabel("TOTAL INGRESOS");
            ingresos.setTipo_dato("CONTABLE");
            ingresos.sumTotal();
            ingresos.println();
            totales_ingresos.add(ingresos);
            
            //TOTALES GASTOS
            ResultSet rsGastos = Main.CONEXION.Ejecutar_Sql("CALL reporte_gastos('"+fecha+"')");
            while (rsGastos.next()){
                ItemMes line = new ItemMes();
                line.setLabel(rsGastos.getString("label"));
                line.setValue(rsGastos.getInt("dia"), rsGastos.getInt("valor"));
                report_gastos.add(line);
            }    
            ItemMes gastos = new ItemMes();
            gastos.calcularTotal(report_gastos);
            gastos.setOrden(2);
            gastos.setLabel("GASTOS X DIA");
            gastos.setTipo_dato("CONTABLE");
            gastos.sumTotal();
            gastos.println();
            totales_gastos.add(gastos);
            
            //TOTAL NETO
            ItemMes neto = new ItemMes(((ItemMes)totales_ingresos.get(0)));
            neto.calcularDiferencia(((ItemMes)totales_gastos.get(0)));
            neto.setOrden(3);
            neto.setLabel("RESULTADO NETO");
            neto.setTipo_dato("CONTABLE");
            neto.sumTotal();
            neto.println();
            totales_neto.add(neto);
            
            Collections.sort(report, new Comparator() {
                @Override
                public int compare(Object p1, Object p2) {
                            return ((ItemMes)p1).getOrden().compareTo(((ItemMes)p2).getOrden());
                }
            });
            
            JRBeanCollectionDataSource JRdataEncabezado = new JRBeanCollectionDataSource(encabezado);          
            JRBeanCollectionDataSource JRdataReporte = new JRBeanCollectionDataSource(report);
            JRBeanCollectionDataSource JRdataTotalesIngresos = new JRBeanCollectionDataSource(totales_ingresos);
            JRBeanCollectionDataSource JRdataTotalesGastos = new JRBeanCollectionDataSource(totales_gastos);
            JRBeanCollectionDataSource JRdataTotalesNeto = new JRBeanCollectionDataSource(totales_neto);
            
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("ItemEncabezadoDataSource",JRdataEncabezado );
            param.put("ItemReporteDataSource",JRdataReporte );
            param.put("ItemTotalesIngresosDataSource",JRdataTotalesIngresos );
            param.put("ItemTotalesGastosDataSource",JRdataTotalesGastos );
            param.put("ItemTotalesNetoDataSource",JRdataTotalesNeto );
            param.put("Mes", getMonthName(mifecha.getMonth()));
            
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteIngreso_Global.jasper");
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(Main.CONEXION.Ejecutar_Sql(sql));
            JasperPrint jasperprint = (JasperPrint)JasperFillManager.fillReport(reporte, param, resultSetDataSource);
                
            // Visualiza la factura en el panel indicado siempre y cuando este
            // no sea nulo y respete las espesificacioes
            if (Panel == null) {                
                new Impresoras().setImpresora(jasperprint, false, Impresoras.IMPRESORA_TICKET);
                //JasperPrintManager.printReport(jasperprint, false);
            } else {
                JRViewer VerReporte = new JRViewer(jasperprint);
                
                VerReporte.setOpaque(true);
                VerReporte.setVisible(true);
                VerReporte.setZoomRatio(Float.parseFloat("0.70"));
                //VerReporte.setSize(new Dimension(800, 600));
                VerReporte.setSize(new Dimension(600, 850));
                VerReporte.setLocation(0, 0);
                
                Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                Panel.add(VerReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));
                Panel.repaint();
                Panel.revalidate();
            }
            
        } catch (SQLException | JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void ReporteRecaudacionDiaVs3(final String Usuario, final Integer IDAbrirCajaUsuario, final Integer idCodigoDia, final JPanel Panel_a,  final JPanel Panel_b) {        
        Thread TreadImprimirRecaudacionDia_a = new Thread() {
            @Override
            public void run() {
                ReporteRecaudacionDiaGlobalVs3_a(Usuario, IDAbrirCajaUsuario, idCodigoDia, Panel_a);
                ReporteRecaudacionDiaGlobalVs3_b(Usuario, IDAbrirCajaUsuario, idCodigoDia, Panel_b);
            }
        };
        TreadImprimirRecaudacionDia_a.start();
        
    }
    public void ReporteRecaudacionDiaGlobalVs3_a(String Usuario, Integer IDAbrirCajaUsuario, Integer idCodigoDia, JPanel Panel) {
        //int Respuesta = Lib_Msj.JMsgbox(this, "Desea Imprimir La Informacion?", "Imprimir", Lib_Msj.SI_NO);
        //if (JOptionPane.YES_OPTION == Respuesta || JOptionPane.OK_OPTION == Respuesta) {
        
        try {
            //tblUsuarios Usuarios = new tblUsuarios();
            //Usuarios.buscarLogin(Usuario);
            //tblDia Dia = new tblDia().buscarUltimoDia("ABIERTO");
            
            //String idUsuario = "" + Usuarios.getID();
            String idUsuario = "01";
            //String idCodigoDia = "" + Dia.getCodigoDia();
            String InSql = "Select "
                         + "tbldenominaciones.Denominacion, "
                         + "tblcambiousuario.CodigoDia, "
                         + "tblcambiousuario.FechaCierre, "
                         + "tblcambiousuario.NombreUsuario, "
                         + "tblcambiousuario.Cantidad, "
                         + "tblcambiousuario.Cantidad * tbldenominaciones.Denominacion as total, "
                         + "tblimagenes.Imagen, "
                         + "tblcambiousuario.DescripcionGaveta, "
			 + "tblcambiousuario.Usuario "
                         + "from tbldenominaciones, tblcambiousuario, tblimagenes "
                         + "where tblcambiousuario.EstadoRegistro = 'ACTIVO' "
                         + "and tblcambiousuario.IDUsuario = " + idUsuario  + " "
                         + "and tblcambiousuario.IDAbrirCajaUsuario = " + IDAbrirCajaUsuario + " "
                         + "and tblcambiousuario.CodigoDenominacion = tbldenominaciones.CodigoDenominacion "
                         + "and tbldenominaciones.CodigoDenominacion = tblimagenes.IDCodigo "
                         + "and tblcambiousuario.Cantidad > 0";

            String InSql2 = "Select "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.tipopago = 'EFECTIVO' and tblfactura.CodigoDia = "+idCodigoDia+" and tblfactura.IDAbrirCajaUsuario = " + IDAbrirCajaUsuario + ")  as FacturadoEfectivo, "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.tipopago = 'VISA' and tblfactura.CodigoDia = " + idCodigoDia + " and tblfactura.IDAbrirCajaUsuario = " + IDAbrirCajaUsuario + ")  as FacturadoVisas, "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.tipopago != 'EFECTIVO' and tblfactura.tipopago != 'VISA' and tblfactura.CodigoDia = "+idCodigoDia+" and tblfactura.IDAbrirCajaUsuario = " + IDAbrirCajaUsuario + ")  as FacturadoOtros, "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.CodigoDia = " + idCodigoDia + "  and tblfactura.IDAbrirCajaUsuario = " + IDAbrirCajaUsuario + ")  as TotalFacturado ,"
                        + "(Select tblabrircajausuario.MontoApertura from tblabrircajausuario where tblabrircajausuario.EstadoRegistro = 'ACTIVO' and tblabrircajausuario.IDUsuario = "+idUsuario+" and tblabrircajausuario.CodigoDia = " +idCodigoDia + " and tblabrircajausuario.ID = " + IDAbrirCajaUsuario + ") as MontoApertura, "
                        + "(Select tblabrircajausuario.DescripcionGaveta from tblabrircajausuario where tblabrircajausuario.EstadoRegistro = 'ACTIVO' and tblabrircajausuario.IDUsuario = "+idUsuario+" and tblabrircajausuario.CodigoDia = " + idCodigoDia + " and tblabrircajausuario.ID = " + IDAbrirCajaUsuario + " )  as DescripcionGavetaCierre "
                        + "from tblfactura LIMIT 1";

            System.out.println(InSql);
            ResultSet resultSet = Main.CONEXION.Ejecutar_Sql(InSql);
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
            
            System.out.println(InSql2);
            ResultSet rs = Main.CONEXION.Ejecutar_Sql(InSql2);
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();   
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteRecaudacionDia_03_a.jasper");

            //el codigo 1975 indica q el documento es tipo reserva
            String CodigoBarra = "200620062006";  
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("nombrelocal", "Main.Empresa.getDescripcion()");
            param.put("CIF",  "Main.Empresa.getCif()"); 
            param.put("direccionlocal01", "Main.Empresa.getDireccion01()");
            param.put("direccionlocal02", "Main.Empresa.getDireccion02()");
            param.put("codigobarra", CodigoBarra);
           
            param.put("Gastos", 0);
            param.put("EfectivoEntrega", 0);
            param.put("Diferencia", 0);
            
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(metadata.getColumnName(i) + " = "+rs.getString(i));
                    if (metadata.getColumnName(i).equals("DescripcionGavetaCierre")){
                        param.put(metadata.getColumnName(i), rs.getString(i));
                    }else{
                        param.put(metadata.getColumnName(i), rs.getDouble(i)); 
                    }
                }
            }
            
            JasperPrint jasperprint = JasperFillManager.fillReport(reporte, param, resultSetDataSource);
            
            // visualiza la factura en el panel indicado siempre y cuando este
            // no sea nulo y respete las espesificacioes
            if (Panel == null) {
                new Impresoras().setImpresora(jasperprint, false, Impresoras.IMPRESORA_TICKET);
                //JasperPrintManager.printReport(jasperprint, false);
            } else {
                JRViewer VerReporte = new JRViewer(jasperprint);
                VerReporte.setOpaque(true);
                VerReporte.setVisible(true);
                VerReporte.setZoomRatio(Float.parseFloat("0.70"));
                VerReporte.setSize(new Dimension(800, 600));
                VerReporte.setLocation(0, 0);
                
                Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                Panel.add(VerReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));
                Panel.repaint();
                Panel.revalidate();
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            
        }               
    }
    public void ReporteRecaudacionDiaGlobalVs3_b(String Usuario, Integer IDAbrirCajaUsuario, Integer idCodigoDia, JPanel Panel) {
        //int Respuesta = Lib_Msj.JMsgbox(this, "Desea Imprimir La Informacion?", "Imprimir", Lib_Msj.SI_NO);
        //if (JOptionPane.YES_OPTION == Respuesta || JOptionPane.OK_OPTION == Respuesta) {
        
        try {
            //tblUsuarios Usuarios = new tblUsuarios();
            //Usuarios.buscarLogin(Usuario);
            //tblDia Dia = new tblDia().buscarUltimoDia("ABIERTO");
            
            
            //String idUsuario = "" + Usuarios.getID();
            String idUsuario = "01";
            //String idCodigoDia = "" + Dia.getCodigoDia();
            String InSql = "Select "
                         + "tbldenominaciones.Denominacion, "
                         + "tblcambiousuario.CodigoDia, "
                         + "tblcambiousuario.FechaCierre, "
                         + "tblcambiousuario.NombreUsuario, "
                         + "tblcambiousuario.Cantidad, "
                         + "tblcambiousuario.Cantidad * tbldenominaciones.Denominacion as total, "
                         + "tblimagenes.Imagen, "
                         + "tblcambiousuario.DescripcionGaveta, "
			 + "tblcambiousuario.Usuario "
                         + "from tbldenominaciones, tblcambiousuario, tblimagenes "
                         + "where tblcambiousuario.EstadoRegistro = 'ACTIVO' "
                         + "and tblcambiousuario.IDUsuario = " + idUsuario  + " "
                         + "and tblcambiousuario.IDAbrirCajaUsuario = " + IDAbrirCajaUsuario + " "
                         + "and tblcambiousuario.CodigoDenominacion = tbldenominaciones.CodigoDenominacion "
                         + "and tbldenominaciones.CodigoDenominacion = tblimagenes.IDCodigo "
                         + "and tblcambiousuario.Cantidad > 0";

            String InSql2 = "Select "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.tipopago = 'EFECTIVO' and tblfactura.CodigoDia = "+idCodigoDia+" and tblfactura.IDAbrirCajaUsuario = " + IDAbrirCajaUsuario + ")  as FacturadoEfectivo, "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.tipopago = 'VISA' and tblfactura.CodigoDia = " + idCodigoDia + " and tblfactura.IDAbrirCajaUsuario = " + IDAbrirCajaUsuario + ")  as FacturadoVisas, "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.tipopago != 'EFECTIVO' and tblfactura.tipopago != 'VISA' and tblfactura.CodigoDia = "+idCodigoDia+" and tblfactura.IDAbrirCajaUsuario = " + IDAbrirCajaUsuario + ")  as FacturadoOtros, "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.CodigoDia = " + idCodigoDia + "  and tblfactura.IDAbrirCajaUsuario = " + IDAbrirCajaUsuario + ")  as TotalFacturado ,"
                        + "(Select tblabrircajausuario.MontoApertura from tblabrircajausuario where tblabrircajausuario.EstadoRegistro = 'ACTIVO' and tblabrircajausuario.IDUsuario = "+idUsuario+" and tblabrircajausuario.CodigoDia = " +idCodigoDia + " and tblabrircajausuario.ID = " + IDAbrirCajaUsuario + ") as MontoApertura, "
                        + "(Select tblabrircajausuario.DescripcionGaveta from tblabrircajausuario where tblabrircajausuario.EstadoRegistro = 'ACTIVO' and tblabrircajausuario.IDUsuario = "+idUsuario+" and tblabrircajausuario.CodigoDia = " + idCodigoDia + " and tblabrircajausuario.ID = " + IDAbrirCajaUsuario + " )  as DescripcionGavetaCierre, "
                        + "(Select SUM(tblcambiousuario.Cantidad * tbldenominaciones.Denominacion) from tbldenominaciones, tblcambiousuario where tblcambiousuario.EstadoRegistro = 'ACTIVO' and tblcambiousuario.IDUsuario = " + idUsuario  + " and tblcambiousuario.IDAbrirCajaUsuario = " + IDAbrirCajaUsuario + " and tblcambiousuario.CodigoDenominacion = tbldenominaciones.CodigoDenominacion and tblcambiousuario.Cantidad > 0) as totalCierre, "
                        + "(Select SUM(tblgastos.TotalGasto) from tblgastos where tblgastos.EstadoRegistro = 'ACTIVO' and tblgastos.IDUsuario ="+ idUsuario +" and tblgastos.IDAbrirCajaUsuario = "+IDAbrirCajaUsuario+" and tblgastos.Estado = 'PAGADO') as totalGastos "                    
                        + "from tblfactura LIMIT 1";

            
            System.out.println(InSql);
            ResultSet resultSet = Main.CONEXION.Ejecutar_Sql(InSql);
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
            
            System.out.println(InSql2);
            ResultSet rs = Main.CONEXION.Ejecutar_Sql(InSql2);
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();

            //el codigo 1975 indica q el documento es tipo reserva
            String CodigoBarra = "200620062006";  
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("nombrelocal", "Main.Empresa.getDescripcion()");
            param.put("CIF",  "Main.Empresa.getCif()"); 
            param.put("direccionlocal01", "Main.Empresa.getDireccion01()");
            param.put("direccionlocal02", "Main.Empresa.getDireccion02()");
            param.put("codigobarra", CodigoBarra);
           
            double MontoApertura = 0.00;
            double FacturadoEfectivo = 0.00;
            double FacturadoOtros = 0.00;
            double totalCierre = 0.00;
            double totalGastos = 0.00;
            Double diferencia = 0.00;
            
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(metadata.getColumnName(i) + " = "+rs.getString(i));
                    if (metadata.getColumnName(i).equals("DescripcionGavetaCierre")){
                        param.put(metadata.getColumnName(i), rs.getString(i));
                    }else{
                        param.put(metadata.getColumnName(i), rs.getDouble(i)); 
                    }
                    
                    if (metadata.getColumnName(i).equals("MontoApertura")){
                        MontoApertura = rs.getDouble(i);
                    }
                    if (metadata.getColumnName(i).equals("FacturadoEfectivo")){
                        FacturadoEfectivo = rs.getDouble(i);
                    }
                    if (metadata.getColumnName(i).equals("FacturadoOtros")){
                        FacturadoOtros = rs.getDouble(i);
                    }
                    if (metadata.getColumnName(i).equals("totalCierre")){
                        totalCierre = rs.getDouble(i);
                    }
                    if (metadata.getColumnName(i).equals("totalGastos")){
                        totalGastos = rs.getDouble(i);
                    }
                }      
            }
            diferencia = Math.abs(totalCierre - (MontoApertura +FacturadoEfectivo + FacturadoOtros));
            param.put("Gastos", totalGastos);
            param.put("EfectivoEntrega", (FacturadoEfectivo - totalGastos) );
            param.put("Diferencia", diferencia.toString());
            
            JasperReport reporte = null;
            if (totalCierre == (MontoApertura +FacturadoEfectivo + FacturadoOtros)) {              
                reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteRecaudacionDia_03_b.jasper");
            } else if (totalCierre < (MontoApertura +FacturadoEfectivo + FacturadoOtros)){
                reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteRecaudacionDia_03_c.jasper");
            }
            JasperPrint jasperprint = JasperFillManager.fillReport(reporte, param, resultSetDataSource);
            
            // visualiza la factura en el panel indicado siempre y cuando este
            // no sea nulo y respete las espesificacioes
            if (Panel == null) {
                new Impresoras().setImpresora(jasperprint, false, Impresoras.IMPRESORA_TICKET);
                //JasperPrintManager.printReport(jasperprint, false);
            } else {
                JRViewer VerReporte = new JRViewer(jasperprint);
                
                VerReporte.setOpaque(true);
                VerReporte.setVisible(true);
                VerReporte.setZoomRatio(Float.parseFloat("0.70"));
                VerReporte.setSize(new Dimension(800, 600));
                VerReporte.setLocation(0, 0);
                
                Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                Panel.add(VerReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));
                Panel.repaint();
                Panel.revalidate();
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            
        }               
    }
    
    
    public void ReporteRecaudacionDia(final String idUsuario,final String idCodigoDia, final JPanel Panel) {        
        Thread TreadImprimirRecaudacionDia = new Thread() {
            @Override
            public void run() {
                ReporteRecaudacionDiaGlobal(idUsuario, idCodigoDia, Panel);
            }
        };
        TreadImprimirRecaudacionDia.start();
    }
    public void ReporteRecaudacionDiaGlobal(String idUsuario, String idCodigoDia, JPanel Panel) {
        //int Respuesta = Lib_Msj.JMsgbox(this, "Desea Imprimir La Informacion?", "Imprimir", Lib_Msj.SI_NO);
        //if (JOptionPane.YES_OPTION == Respuesta || JOptionPane.OK_OPTION == Respuesta) {
        
        try {
            
            String InSql = "Select "
                         + "tbldenominaciones.Denominacion, "
                         + "tblcierrecambiousuario.CodigoDia, "
                         + "tblcierrecambiousuario.FechaCierre, "
                         + "tblcierrecambiousuario.NombreUsuario, "
                         + "tblcierrecambiousuario.Cantidad, "
                         + "tblcierrecambiousuario.Cantidad * tbldenominaciones.Denominacion as total, "
                         + "tblimagenes.Imagen, "
                         + "tblcierrecambiousuario.DescripcionGaveta, "
                         + "tblcierrecambiousuario.Usuario "
                         + "from tbldenominaciones, tblcierrecambiousuario, tblimagenes "
                         + "where tblcierrecambiousuario.EstadoRegistro = 'ACTIVO' "
                         + "and tblcierrecambiousuario.IDUsuario = "+idUsuario+" "
                         + "and tblcierrecambiousuario.CodigoDia = "+idCodigoDia+" "
                         + "and tblcierrecambiousuario.CodigoDenominacion = tbldenominaciones.CodigoDenominacion "
                         + "and tbldenominaciones.CodigoDenominacion = tblimagenes.IDCodigo "
                         + "and tblcierrecambiousuario.Cantidad > 0";
            
            String InSql2 = "Select "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.tipopago = 'EFECTIVO' and tblfactura.CodigoDia = "+idCodigoDia+" )  as FacturadoEfectivo, "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.tipopago = 'VISA' and tblfactura.CodigoDia = "+idCodigoDia+" )  as FacturadoVisas, "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.tipopago != 'EFECTIVO' and tblfactura.tipopago != 'VISA' and tblfactura.CodigoDia = "+idCodigoDia+" )  as FacturadoOtros, "
                        + "(Select sum(tblfactura.total) from tblfactura where tblfactura.CodigoDia = "+idCodigoDia+" )  as TotalFacturado ,"
                        + "(Select tblabrircajausuario.MontoApertura from tblabrircajausuario where tblabrircajausuario.EstadoRegistro = 'ACTIVO' and tblabrircajausuario.IDUsuario = "+idUsuario+" and tblabrircajausuario.CodigoDia = "+idCodigoDia+" )  as MontoApertura, "
                        + "(Select tblabrircajausuario.DescripcionGaveta from tblabrircajausuario where tblabrircajausuario.EstadoRegistro = 'ACTIVO' and tblabrircajausuario.IDUsuario = "+idUsuario+" and tblabrircajausuario.CodigoDia = "+idCodigoDia+" )  as DescripcionGavetaCierre, "
                        + "(Select  sum(Cantidad * tbldenominaciones.Denominacion) as total from tbldenominaciones, tblcierrecambiousuario where tblcierrecambiousuario.EstadoRegistro = 'ACTIVO' and tblcierrecambiousuario.CodigoDenominacion = tbldenominaciones.CodigoDenominacion and tblcierrecambiousuario.Cantidad > 0 and tblcierrecambiousuario.IDUsuario ="+ idUsuario +" and tblcierrecambiousuario.CodigoDia = "+idCodigoDia+") as totalCierre " 
                        + "from tblfactura LIMIT 1";
            
            System.out.println(InSql);
            ResultSet resultSet = Main.CONEXION.Ejecutar_Sql(InSql);
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
            
            System.out.println(InSql2);
            ResultSet rs = Main.CONEXION.Ejecutar_Sql(InSql2);
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();   
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteRecaudacionDia_02.jasper");

            
            //el codigo 1975 indica q el documento es tipo reserva
            //String CodigoBarra = "2006" + Lib_String.Add_String_I(""+Huesped.getID(), '0', 10);  
            String CodigoBarra = "200620062006";  
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("nombrelocal", "STAY COPAS");
            param.put("CIF", "B123456789"); 
            //param.put("CIF", Empresa.getCif());
            param.put("direccionlocal01", "ALBACETE");
            param.put("direccionlocal02", "ALBACETE");
            param.put("codigobarra", CodigoBarra);

            double MontoApertura = 0.00;
            double FacturadoEfectivo = 0.00;
            double FacturadoOtros = 0.00;
            double totalCierre = 0.00;
            Double difetencia = 0.00;
            
            
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(metadata.getColumnName(i) + " = "+rs.getString(i));
                    if (metadata.getColumnName(i).equals("DescripcionGavetaCierre")){
                        param.put(metadata.getColumnName(i), rs.getString(i));
                    }else{
                        param.put(metadata.getColumnName(i), rs.getDouble(i)); 
                    }
                    
                    if (metadata.getColumnName(i).equals("MontoApertura")){
                        MontoApertura = rs.getDouble(i);
                    }
                    if (metadata.getColumnName(i).equals("FacturadoEfectivo")){
                        FacturadoEfectivo = rs.getDouble(i);
                    }
                    if (metadata.getColumnName(i).equals("FacturadoOtros")){
                        FacturadoOtros = rs.getDouble(i);
                    }
                    if (metadata.getColumnName(i).equals("totalCierre")){
                        totalCierre = rs.getDouble(i);
                    }
                }
                
            }
            difetencia = totalCierre - (MontoApertura +FacturadoEfectivo + FacturadoOtros);
            param.put("Gastos", 0.00);
            param.put("EfectivoEntrega", 0.00);
            param.put("Diferencia", difetencia.toString());
            //MontoApertura + FacturadoEfectivo + FacturadoOtros == 
            JasperPrint jasperprint = JasperFillManager.fillReport(reporte, param, resultSetDataSource);
            
            // visualiza la factura en el panel indicado siempre y cuando este
            // no sea nulo y respete las espesificacioes
            if (Panel == null) {
                new Impresoras().setImpresora(jasperprint, false, Impresoras.IMPRESORA_DOCUMENTO);
                //JasperPrintManager.printReport(jasperprint, false);
            } else {
                JRViewer VerReporte = new JRViewer(jasperprint);
                
                VerReporte.setOpaque(true);
                VerReporte.setVisible(true);
                VerReporte.setZoomRatio(Float.parseFloat("0.70"));
                VerReporte.setSize(new Dimension(800, 600));
                VerReporte.setLocation(0, 0);
                
                Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                Panel.add(VerReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));
                Panel.repaint();
                Panel.revalidate();
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            
        }
        
      
    }
    
    
    public void ReporteCambioUsuario(final String IdAbrirCajaUsuario, final JPanel Panel) {        
        Thread TreadImprimirCambioUsuario = new Thread() {
            @Override
            public void run() {
                ReporteCambioUsuarioGlobal(IdAbrirCajaUsuario, Panel);
            }
        };
        TreadImprimirCambioUsuario.start();
    }
    public void ReporteCambioUsuarioGlobal(String Codigo, JPanel Panel) {
        //int Respuesta = Lib_Msj.JMsgbox(this, "Desea Imprimir La Informacion?", "Imprimir", Lib_Msj.SI_NO);
        //if (JOptionPane.YES_OPTION == Respuesta || JOptionPane.OK_OPTION == Respuesta) {
        
        try {
            
            String InSql = "Select "
                         + "tbldenominaciones.Denominacion, "
                         + "tblcambiousuario.CodigoDia, "
                         + "tblcambiousuario.FechaApertura, "
                         + "tblcambiousuario.NombreUsuario, "
                         + "tblcambiousuario.Cantidad, "
                         + "tblcambiousuario.Cantidad * tbldenominaciones.Denominacion as total, "
                         + "tblimagenes.Imagen, "
                         + "tblcambiousuario.DescripcionGaveta "
                         + "from tbldenominaciones, tblcambiousuario, tblimagenes "
                         + "where tblcambiousuario.EstadoRegistro = 'ACTIVO' "
                         + "and tblcambiousuario.IDAbrirCajaUsuario = "+Codigo+" "
                         + "and tblcambiousuario.CodigoDenominacion = tbldenominaciones.CodigoDenominacion "
                         + "and tbldenominaciones.CodigoDenominacion = tblimagenes.IDCodigo "
                         + "and tblcambiousuario.Cantidad > 0";

            System.out.println(InSql);
            ResultSet resultSet = Main.CONEXION.Ejecutar_Sql(InSql);
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
            
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteCambioUsuarioNuevo.jasper");

            //el codigo 1975 indica q el documento es tipo reserva
            //String CodigoBarra = "2006" + Lib_String.Add_String_I(""+Huesped.getID(), '0', 10);  
            String CodigoBarra = "2006";  
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("nombrelocal", "STAY COPAS");
            param.put("CIF", "B123456789");
            param.put("direccionlocal01", "ALBACETE");
            param.put("direccionlocal02", "ALBACETE");
            param.put("codigobarra", CodigoBarra);
            JasperPrint jasperprint = JasperFillManager.fillReport(reporte, param, resultSetDataSource);

            // visualiza la factura en el panel indicado siempre y cuando este
            // no sea nulo y respete las espesificacioes
            if (Panel == null) {
                new Impresoras().setImpresora(jasperprint, false, Impresoras.IMPRESORA_TICKET);
                //JasperPrintManager.printReport(jasperprint, false);
            } else {
                new Impresoras().setImpresora(jasperprint, false, Impresoras.IMPRESORA_TICKET);
                JRViewer VerReporte = new JRViewer(jasperprint);
                
                VerReporte.setOpaque(true);
                VerReporte.setVisible(true);
                VerReporte.setZoomRatio(Float.parseFloat("0.70"));
                VerReporte.setSize(new Dimension(800, 600));
                VerReporte.setLocation(0, 0);
                
                Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                Panel.add(VerReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));
                Panel.repaint();
                Panel.revalidate();
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            
        }
        
      
    }
    
    
    public void ReporteLeyProteccionDatos(final String Codigo, final JPanel Panel) {        
        Thread TreadImprimir = new Thread() {
            @Override
            public void run() {
                ReporteLeyProteccionDatosGlobal(Codigo, Panel);
            }
        };
        TreadImprimir.start();
    }
    public void ReporteLeyProteccionDatosGlobal(String Codigo, JPanel Panel) {
        //int Respuesta = Lib_Msj.JMsgbox(this, "Desea Imprimir La Informacion?", "Imprimir", Lib_Msj.SI_NO);
        //if (JOptionPane.YES_OPTION == Respuesta || JOptionPane.OK_OPTION == Respuesta) {

        int id = 1;
        try {
            String InSql = "Select "
                    + " *, SUBSTRING(Sexo,1,1) sex, SUBSTRING(Tipodocumento,1,1) doc "
                    + "from tblHuespedes Where EstadoRegistro='ACTIVO' and CodigoEmpresa='" + Main.CodigoEmpresa + "' and NumeroDocumento='" + Codigo + "'";
            
            System.out.println(InSql);
            ResultSet resultSet = Main.CONEXION.Ejecutar_Sql(InSql);
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObjectFromFile("ReporteLeyProteccionDatos.jasper");

            //el codigo 1975 indica q el documento es tipo reserva
            //String CodigoBarra = "2006" + Lib_String.Add_String_I(""+Huesped.getID(), '0', 10);
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("nombrelocal", "STAY COPAS");
            param.put("CIF", "B123456789");
            param.put("direccionlocal01", "ALBACETE");
            param.put("direccionlocal02", "ALBACETE");
            param.put("correo", "staycopas@gmail.com");
            //param.put("codigobarra", CodigoBarra);
            JasperPrint jasperprint = JasperFillManager.fillReport(reporte, param, resultSetDataSource);

            // visualiza la factura en el panel indicado siempre y cuando este
            // no sea nulo y respete las espesificacioes
            if (Panel == null) {
                new Impresoras().setImpresora(jasperprint, false, Impresoras.IMPRESORA_DOCUMENTO);
                //JasperPrintManager.printReport(jasperprint, false);
            } else {
                JRViewer VerReporte = new JRViewer(jasperprint);
                
                VerReporte.setOpaque(true);
                VerReporte.setVisible(true);
                VerReporte.setZoomRatio(Float.parseFloat("0.70"));
                VerReporte.setSize(new Dimension(800, 600));
                VerReporte.setLocation(0, 0);
                
                Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                Panel.add(VerReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));
                Panel.repaint();
                Panel.revalidate();
            }
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            
        }
        //}
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanelMenu = new javax.swing.JPanel();
        JButtonOP01 = new javax.swing.JButton();
        JButtonOP2 = new javax.swing.JButton();
        JButtonOP3 = new javax.swing.JButton();
        JButtonOP4 = new javax.swing.JButton();
        JButtonOP5 = new javax.swing.JButton();
        JButtonOP6 = new javax.swing.JButton();
        JButtonOP7 = new javax.swing.JButton();
        JButtonOP8 = new javax.swing.JButton();
        JButtonOP9 = new javax.swing.JButton();
        JButtonOP10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanelMenu.setLayout(new java.awt.GridLayout(6, 0));

        JButtonOP01.setText("Reporte 01");
        JButtonOP01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonOP01MouseClicked(evt);
            }
        });
        JButtonOP01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOP01ActionPerformed(evt);
            }
        });
        JPanelMenu.add(JButtonOP01);

        JButtonOP2.setText("Reporte 02");
        JButtonOP2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonOP2MouseClicked(evt);
            }
        });
        JButtonOP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOP2ActionPerformed(evt);
            }
        });
        JPanelMenu.add(JButtonOP2);

        JButtonOP3.setText("Reporte 03");
        JButtonOP3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonOP3MouseClicked(evt);
            }
        });
        JButtonOP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOP3ActionPerformed(evt);
            }
        });
        JPanelMenu.add(JButtonOP3);

        JButtonOP4.setText("Reporte 04");
        JButtonOP4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonOP4MouseClicked(evt);
            }
        });
        JButtonOP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOP4ActionPerformed(evt);
            }
        });
        JPanelMenu.add(JButtonOP4);

        JButtonOP5.setText("Reporte 05");
        JButtonOP5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonOP5MouseClicked(evt);
            }
        });
        JButtonOP5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOP5ActionPerformed(evt);
            }
        });
        JPanelMenu.add(JButtonOP5);

        JButtonOP6.setText("Reporte 06");
        JButtonOP6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonOP6MouseClicked(evt);
            }
        });
        JButtonOP6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOP6ActionPerformed(evt);
            }
        });
        JPanelMenu.add(JButtonOP6);

        JButtonOP7.setText("Reporte 07 ( Ingresos Mensual  )");
        JButtonOP7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonOP7MouseClicked(evt);
            }
        });
        JButtonOP7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOP7ActionPerformed(evt);
            }
        });
        JPanelMenu.add(JButtonOP7);

        JButtonOP8.setText("Reporte 08");
        JButtonOP8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonOP8MouseClicked(evt);
            }
        });
        JPanelMenu.add(JButtonOP8);

        JButtonOP9.setText("Reporte 09");
        JButtonOP9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonOP9MouseClicked(evt);
            }
        });
        JPanelMenu.add(JButtonOP9);

        JButtonOP10.setText("Reporte 10 ( Gastos Mensual  )");
        JButtonOP10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonOP10MouseClicked(evt);
            }
        });
        JButtonOP10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOP10ActionPerformed(evt);
            }
        });
        JPanelMenu.add(JButtonOP10);

        getContentPane().add(JPanelMenu, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JButtonOP01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOP01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JButtonOP01ActionPerformed

    private void JButtonOP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOP2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JButtonOP2ActionPerformed

    private void JButtonOP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOP3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JButtonOP3ActionPerformed

    private void JButtonOP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOP4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JButtonOP4ActionPerformed

    private void JButtonOP5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOP5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JButtonOP5ActionPerformed

    private void JButtonOP6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOP6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JButtonOP6ActionPerformed

    private void JButtonOP01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JButtonOP01MouseClicked
        // TODO add your handling code here:
        //ReporteLeyProteccionDatos();
        ReporteCierreGeneral();
    }//GEN-LAST:event_JButtonOP01MouseClicked

    private void JButtonOP2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JButtonOP2MouseClicked
        // TODO add your handling code here:
        ReporteCambioUsuario();
    }//GEN-LAST:event_JButtonOP2MouseClicked

    private void JButtonOP3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JButtonOP3MouseClicked
        // TODO add your handling code here:
        ReporteRecaudacionDia();
    }//GEN-LAST:event_JButtonOP3MouseClicked

    private void JButtonOP4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JButtonOP4MouseClicked
        // TODO add your handling code here:
        ReporteRecaudacionDiaVs3();
    }//GEN-LAST:event_JButtonOP4MouseClicked

    private void JButtonOP5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JButtonOP5MouseClicked
        // TODO add your handling code here:
        ReporteIngresos();
    }//GEN-LAST:event_JButtonOP5MouseClicked

    private void JButtonOP6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JButtonOP6MouseClicked
        // TODO add your handling code here:
        ReporteCierreGeneral();
    }//GEN-LAST:event_JButtonOP6MouseClicked

    private void JButtonOP7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JButtonOP7MouseClicked
        // TODO add your handling code here:
        ReporteIngresosNew();
    }//GEN-LAST:event_JButtonOP7MouseClicked

    private void JButtonOP8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JButtonOP8MouseClicked
        // TODO add your handling code here:
        ReporteReposicion();
    }//GEN-LAST:event_JButtonOP8MouseClicked

    private void JButtonOP9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JButtonOP9MouseClicked
        // TODO add your handling code here:
        ReporteReposicionNochero();
    }//GEN-LAST:event_JButtonOP9MouseClicked

    private void JButtonOP7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOP7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JButtonOP7ActionPerformed

    private void JButtonOP10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JButtonOP10MouseClicked
        // TODO add your handling code here:
        ReporteGastosNew();
    }//GEN-LAST:event_JButtonOP10MouseClicked

    private void JButtonOP10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOP10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JButtonOP10ActionPerformed

    private void ReporteReposicionNochero(){
        Visor xVisor = new Visor();
        ReporteReposicionNochero(14, xVisor.JPanel01);
    }
    
    private void ReporteReposicion(){
        Visor xVisor = new Visor();
        ReporteReposicion(14, xVisor.JPanel01);
    }
    
    private void ReporteGastosNew(){
        Visor xVisorGastosNew = new Visor();
        ReporteGastosNew("2020/02/01", xVisorGastosNew.JPanel01);
        xVisorGastosNew.setVisible(true);
    }
    
    private void ReporteIngresosNew(){
        Visor xVisorIngresosNew = new Visor();
        ReporteIngresosNew("2020/03/01", xVisorIngresosNew.JPanel01);
        xVisorIngresosNew.setVisible(true);
    }
    
    private void ReporteCierreGeneral(){
        Visor xVisorCierre = new Visor();
        ReporteCierreGeneral("27", xVisorCierre.JPanel01);
        xVisorCierre.setVisible(true);  
    }
    
    private void ReporteIngresos(){
        Visor xVisorIngresos = new Visor();
        Boolean Impresion_Directa = false;
        ReporteIngresos("2019/11/01", Impresion_Directa);
    }
    
    private void ReporteRecaudacionDia(){
        Visor xVisorRecaudacionDia = new Visor();
        ReporteRecaudacionDia("02", "1", xVisorRecaudacionDia.JPanel01);
        xVisorRecaudacionDia.setVisible(true);        
    }
        
    private void ReporteRecaudacionDiaVs3(){
        Visor xVisorRecaudacionDia_a = new Visor();
        Visor xVisorRecaudacionDia_b = new Visor();
        //ReporteRecaudacionDiaVs3("CHISTIAN", 67, 11, null, null);
        ReporteRecaudacionDiaVs3("CHISTIAN", 97, 27, null, null);
        xVisorRecaudacionDia_a.setVisible(true); 
        xVisorRecaudacionDia_b.setVisible(true); 
    }
    
    private void ReporteCambioUsuario(){
        Visor xVisorCambioUsuario = new Visor();
        ReporteCambioUsuario("22", xVisorCambioUsuario.JPanel01);
        xVisorCambioUsuario.setVisible(true);        
    }
    
    private void ReporteLeyProteccionDatos(){
        Visor xVisor = new Visor();
        ReporteLeyProteccionDatos("Y5496361L", xVisor.JPanel01);
        xVisor.setVisible(true);        
    }
    
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
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonOP01;
    private javax.swing.JButton JButtonOP10;
    private javax.swing.JButton JButtonOP2;
    private javax.swing.JButton JButtonOP3;
    private javax.swing.JButton JButtonOP4;
    private javax.swing.JButton JButtonOP5;
    private javax.swing.JButton JButtonOP6;
    private javax.swing.JButton JButtonOP7;
    private javax.swing.JButton JButtonOP8;
    private javax.swing.JButton JButtonOP9;
    private javax.swing.JPanel JPanelMenu;
    // End of variables declaration//GEN-END:variables
}
