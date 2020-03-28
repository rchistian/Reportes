/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportestpv;

import byoscalendario.ByosDate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

// Funacion que busca y organiza los parametros del sistema

public class tblParametrosTPV implements Serializable {

    private String Tabla = "tblparametrostpv";
    public String Resultado; 
    public String GlobalCodigoEmpresa = "0001";


    private Integer Id;
    private String CodigoSeptor;
    private String Descripcion;
    private String ValorString01;
    private String ValorString02;
    private String EstadoRegistro;
    private String Estado;
    private String Usuario;
    private Date Fecha;
    private String CodigoEmpresa;
    private String ValorEntero01;
    private String ValorEntero02;
    private String ValorFecha01;
    private String ValorFecha02;
    private BigDecimal ValorDoble01;
    private BigDecimal ValorDoble02;
    private String Parametro;

    public boolean Estatus;
    
    public String StringSelect
            = "CodigoEmpresa,"
            + "id,"
            + "codigoseptor,"
            + "descripcion,"
            + "ValorString01,"
            + "ValorString02,"
            + "estadoRegistro,"
            + "estado,"
            + "usuario,"
            + "fecha,"
            + "ValorEntero01,"
            + "ValorEntero02,"
            + "ValorFecha01,"
            + "ValorFecha02,"
            + "ValorDoble01,"
            + "ValorDoble02,"
            + "Parametro";
    
    public String StringSelectUpdate
            = "CodigoEmpresa=?,"
            + "id=?,"
            + "codigoseptor=?,"
            + "descripcion=?,"
            + "ValorString01=?,"
            + "ValorString02=?,"
            + "estadoRegistro=?,"
            + "estado=?,"
            + "usuario=?,"
            + "fecha=?,"  
            + "ValorEntero01=?,"
            + "ValorEntero02=?,"
            + "ValorFecha01=?,"
            + "ValorFecha02=?,"
            + "ValorDoble01=?,"
            + "ValorDoble02=?,"
            + "Parametro=?";
    
    public String StringSelectInsert
            = "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"            
            + "?";

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getCodigoSeptor() {
        return CodigoSeptor;
    }

    public void setCodigoSeptor(String CodigoSeptor) {
        this.CodigoSeptor = CodigoSeptor;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getValorString01() {
        return ValorString01;
    }

    public void setValorString01(String ValorString01) {
        this.ValorString01 = ValorString01;
    }

    public String getValorString02() {
        return ValorString02;
    }

    public void setValorString02(String ValorString02) {
        this.ValorString02 = ValorString02;
    }

    public String getEstadoRegistro() {
        return EstadoRegistro;
    }

    public void setEstadoRegistro(String EstadoRegistro) {
        this.EstadoRegistro = EstadoRegistro;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getCodigoEmpresa() {
        return CodigoEmpresa;
    }

    public void setCodigoEmpresa(String CodigoEmpresa) {
        this.CodigoEmpresa = CodigoEmpresa;
    }

    public String getValorEntero01() {
        return ValorEntero01;
    }

    public void setValorEntero01(String ValorEntero01) {
        this.ValorEntero01 = ValorEntero01;
    }

    public String getValorEntero02() {
        return ValorEntero02;
    }

    public void setValorEntero02(String ValorEntero02) {
        this.ValorEntero02 = ValorEntero02;
    }

    public String getValorFecha01() {
        return ValorFecha01;
    }

    public void setValorFecha01(String ValorFecha01) {
        this.ValorFecha01 = ValorFecha01;
    }

    public String getValorFecha02() {
        return ValorFecha02;
    }

    public void setValorFecha02(String ValorFecha02) {
        this.ValorFecha02 = ValorFecha02;
    }

    public BigDecimal getValorDoble01() {
        return ValorDoble01;
    }

    public void setValorDoble01(BigDecimal ValorDoble01) {
        this.ValorDoble01 = ValorDoble01;
    }

    public BigDecimal getValorDoble02() {
        return ValorDoble02;
    }

    public void setValorDoble02(BigDecimal ValorDoble02) {
        this.ValorDoble02 = ValorDoble02;
    }

    public String getParametro() {
        return Parametro;
    }

    public void setParametro(String Parametro) {
        this.Parametro = Parametro;
    }

    public boolean isEstatus() {
        return Estatus;
    }

    public void setEstatus(boolean Estatus) {
        this.Estatus = Estatus;
    }
    
    public tblParametrosTPV() {
         
    }

    public tblParametrosTPV(String Codigo, String Parametro) {
        buscarParametro(Codigo, Parametro);
    }
    
    public boolean guardarParametroString01(String Codigo, String Parametro, String Valor){
        try {
            tblParametrosTPV Parametros = new tblParametrosTPV();
            Parametros.buscarCodigo(Main.Departamento.getCodigodepartamento(), Parametro);
            if(Parametros.isEstatus()){
                Parametros.setValorString01(Valor);
                Parametros.guardar(Parametros);
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(tblParametrosTPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String buscarParametroString01(String Codigo, String Parametro) {
        tblParametrosTPV ParametroTPV = buscarParametro(Codigo, Parametro);
        if(ParametroTPV!=null){
            return ParametroTPV.getValorString01();
        }
        return "";
    }
    
    public tblParametrosTPV buscarParametro(String Codigo, String Parametro) {
        try {
            tblParametrosTPV tbl = new tblParametrosTPV();
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String query = "Select " + StringSelect + " from " + Tabla + " where CodigoEmpresa='" + GlobalCodigoEmpresa + "' And EstadoRegistro='ACTIVO' And CodigoSeptor=? And Parametro=?";
            Connection con = Conexion.getNuevaConexion();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, Codigo);
            pstmt.setString(2, Parametro);
            rs = pstmt.executeQuery();
            int size = 0;
            
            if (rs != null) {
                if (rs.last() == true) {
                    size = rs.getRow();
                    setRegistro(rs, tbl);
                    ByosSql.CloseAll(con, pstmt, rs);
                    return tbl;
                }
            }
            ByosSql.CloseAll(con, pstmt, rs);
            limpiar();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(tblParametrosTPV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(tblParametrosTPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean buscarCodigo(String Codigo, String Parametro) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "Select " + StringSelect + " from " + Tabla + " where CodigoEmpresa='" + GlobalCodigoEmpresa + "' And EstadoRegistro='ACTIVO' And CodigoSeptor=? And Parametro=?";
        Connection con = Conexion.getNuevaConexion();
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, Codigo);
        pstmt.setString(2, Parametro);
        rs = pstmt.executeQuery();
        int size = 0;
        setEstatus(false);
        if (rs != null) {
            if (rs.last() == true) {
                size = rs.getRow();
                setRegistro(rs, this);
                ByosSql.CloseAll(con, pstmt, rs);
                setEstatus(true);
                return true;
            }
        }
        ByosSql.CloseAll(con, pstmt, rs);
        limpiar();
        return false;
    }
    
    
    public boolean existeCodigo(String Codigo, String Parametro) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "Select CodigoSeptor From " + Tabla + " Where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and CodigoSeptor=? and Parametro=?";
        Connection con = Conexion.getNuevaConexion();
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, Codigo);
        pstmt.setString(2, Parametro);
        rs = pstmt.executeQuery();
        int size = 0;

        if (rs != null) {
            if (rs.last() == true) {
                size = rs.getRow();
                ByosSql.CloseAll(con, pstmt, rs);
                return true;
            }
        }
        ByosSql.CloseAll(con, pstmt, rs);
        return false;
    }

        
    public String eliminar(tblParametrosTPV tbl) {
        int i = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = Conexion.getNuevaConexion();
        try {
            if (existeCodigo(tbl.getCodigoSeptor(),tbl.getParametro())) {
                String query = "Update " + Tabla + " set EstadoRegistro='BORRADO'  Where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and CodigoSeptor=? and Parametro=?";
                con.setAutoCommit(false);
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, tbl.getCodigoSeptor());
                i = pstmt.executeUpdate();
                con.commit();
                ByosSql.CloseAll(con, pstmt, rs);
                return utilString.SQL_ELIMINADO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ByosSql.RollBack(con);
        ByosSql.CloseAll(con, pstmt, rs);
        return utilString.SQL_ERROR;
    }

    public String guardar(tblParametrosTPV tbl) {
        int i = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = Conexion.getNuevaConexion();

        try {
            if (!existeCodigo(tbl.getCodigoSeptor(), tbl.getParametro())) {
                String query = "Insert into " + Tabla + "(" + StringSelect + ") values(" + StringSelectInsert + ")";
                con.setAutoCommit(false);
                pstmt = con.prepareStatement(query);
                ActualizarDatos(tbl, pstmt);
                i = pstmt.executeUpdate();
                con.commit();
                ByosSql.CloseAll(con, pstmt, rs);
                return utilString.SQL_INSERTADO;
            } else {
                String query = "Update " + Tabla + " set " + StringSelectUpdate + " where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and CodigoSeptor='" + tbl.getCodigoSeptor() + "' and Parametro='" + tbl.getParametro() + "'";
                con.setAutoCommit(false);
                pstmt = con.prepareStatement(query);
                ActualizarDatos(tbl, pstmt);
                i = pstmt.executeUpdate();
                con.commit();
                ByosSql.CloseAll(con, pstmt, rs);
                return utilString.SQL_MODIFICADO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ByosSql.RollBack(con);
        ByosSql.CloseAll(con, pstmt, rs);
        return utilString.SQL_ERROR;
    }

    public void ActualizarDatos(tblParametrosTPV tbl, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, GlobalCodigoEmpresa);
        pstmt.setInt(2, tbl.getId());
        pstmt.setString(3, tbl.getCodigoSeptor());
        pstmt.setString(4, tbl.getDescripcion());
        pstmt.setString(5, tbl.getValorString01());
        pstmt.setString(6, tbl.getValorString02());
        pstmt.setString(7, "ACTIVO");
        pstmt.setString(8, tbl.getEstado());
        pstmt.setString(9, tbl.getUsuario());
        pstmt.setTimestamp(10, ByosDate.DateToTimestamp(tbl.getFecha()));
        pstmt.setString(11, tbl.getValorEntero01());
        pstmt.setString(12, tbl.getValorEntero02());
        pstmt.setString(13, tbl.getValorFecha01());
        pstmt.setString(14, tbl.getValorFecha02());
        pstmt.setBigDecimal(15, tbl.getValorDoble01());
        pstmt.setBigDecimal(16, tbl.getValorDoble02());
        pstmt.setString(17, tbl.getParametro());
            
    }

    public void setRegistro(ResultSet rs, tblParametrosTPV Registros) throws Exception {
        Registros.setCodigoEmpresa(GlobalCodigoEmpresa);
        Registros.setId(rs.getInt("ID"));
        Registros.setCodigoSeptor(rs.getString("CodigoSeptor"));
        Registros.setDescripcion(rs.getString("Descripcion"));
        Registros.setValorString01(rs.getString("ValorString01"));
        Registros.setValorString02(rs.getString("ValorString02"));
        Registros.setEstadoRegistro(rs.getString("EstadoRegistro"));
        Registros.setEstado(rs.getString("Estado"));
        Registros.setUsuario(rs.getString("Usuario"));
        Registros.setFecha(rs.getDate("Fecha"));
        Registros.setValorEntero01(rs.getString("ValorEntero01"));
        Registros.setValorEntero02(rs.getString("ValorEntero02"));
        Registros.setValorFecha01(rs.getString("ValorFecha01"));
        Registros.setValorFecha02(rs.getString("ValorFecha02"));
        Registros.setValorDoble01(rs.getBigDecimal("ValorDoble01"));
        Registros.setValorDoble02(rs.getBigDecimal("ValorDoble02"));
        Registros.setParametro(rs.getString("Parametro"));
    }

    public tblParametrosTPV[] Buscar(tblParametrosTPV tbl) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        tblParametrosTPV[] Registros;

        String InSql = StringInSql(tbl);
        Connection con = Conexion.getNuevaConexion();
        pstmt = con.prepareStatement(InSql);
        rs = pstmt.executeQuery();
        int size = 0;

        if (rs != null) {
            if (rs.last() == true) {
                size = rs.getRow();
                Registros = new tblParametrosTPV[size];
                rs.first();
                for (int f = 0; f < size; f++) {
                    Registros[f] = new tblParametrosTPV();
                    setRegistro(rs, Registros[f]);

                    rs.next();
                }
                ByosSql.CloseAll(con, pstmt, rs);
                return Registros;
            }
        }
        ByosSql.CloseAll(con, pstmt, rs);
        return null;
    }

    public String StringInSql(tblParametrosTPV tbl) {
        String InSql = "";
        boolean Estado = false;

        if (tbl.getCodigoSeptor() != null && !tbl.getCodigoSeptor().equals("")) {
            InSql = InSql + " and CodigoSeptor like '" + tbl.getCodigoSeptor().replace("*", "%") + "'";
            Estado = true;
        }
        if (tbl.getDescripcion() != null && !tbl.getDescripcion().equals("")) {
            InSql = InSql + " and Descripcion like '" + tbl.getDescripcion().replace("*", "%") + "'";
            Estado = true;
        }
        if (tbl.getUsuario() != null && !tbl.getUsuario().equals("")) {
            InSql = InSql + " and Usuario like '" + tbl.getUsuario().replace("*", "%") + "'";
            Estado = true;
        }
        if (tbl.getFecha() != null && !tbl.getFecha().equals("")) {
            //InSql = InSql + " and Fecha like '" + tbl.getFecha().replace("*", "%") + "'";
            Estado = true;
        }
        if (tbl.getEstadoRegistro() != null && !tbl.getEstadoRegistro().equals("")) {
            InSql = InSql + " and EstadoRegistro like '" + tbl.getEstadoRegistro().replace("*", "%") + "'";
            Estado = true;
        }
        if (tbl.getEstado() != null && !tbl.getEstado().equals("")) {
            InSql = InSql + " and Estado like '" + tbl.getEstado().replace("*", "%") + "'";
            Estado = true;
        }

        String query = "Select " + StringSelect + " "
                + "from " + Tabla + " Where CodigoEmpresa = '" + GlobalCodigoEmpresa
                + "' and EstadoRegistro='ACTIVO' ";
        if (Estado) {
            query = query + " " + InSql;
        }
        query = query + " order by Parametro";
        //System.out.println(query);

        return query;
    }

    public String StringInSql(String Texto, String Numero) {
        String InSql = "";
        boolean Estado = false;

        Texto = Texto.toUpperCase();
        if (!Texto.equals("")) {
            InSql = InSql + " and (CodigoSeptor like '%" + Texto + "%'";
            InSql = InSql + " or Descripcion like '%" + Texto + "%'";
            InSql = InSql + " or Login like '%" + Texto + "%'";
            InSql = InSql + " or Usuario like '%" + Texto + "%'";
            InSql = InSql + " or EstadoRegistro like '%" + Texto + "%'";
            InSql = InSql + " or Estado like '%" + Texto + "%') ";
            Estado = true;
        }
        String query = "Select " + StringSelect + " "
                + "from " + Tabla + " Where CodigoEmpresa = '" + GlobalCodigoEmpresa
                + "' and EstadoRegistro='ACTIVO' ";
        if (Estado) {
            query = query + InSql;
        }
        query = query + " order by CodigoSeptor";
        //System.out.println(query);

        return query;
    }

    public void limpiar() {
        setCodigoEmpresa("");
        setId(null);
        setCodigoSeptor("");
        setDescripcion("");
        setValorString01("");
        setValorString02("");
        setUsuario("");
        setFecha(null);
        setEstadoRegistro("");
        setEstado("");
        setValorEntero01("");
        setValorEntero02("");
        setValorFecha01("");
        setValorFecha02("");
        setValorDoble01(null);
        setValorDoble02(null);
        setParametro("");        
    }

    public void setTblUsuarios(tblParametrosTPV tbl) {
        setCodigoEmpresa(tbl.getCodigoEmpresa());
        setCodigoSeptor(tbl.getCodigoSeptor());
        setId(tbl.getId());
        setUsuario(tbl.getUsuario());
        setFecha(tbl.getFecha());
        setEstadoRegistro(tbl.getEstadoRegistro());
        setEstado(tbl.getEstado());
        setValorString01(tbl.getValorString01());
        setValorString02(tbl.getValorString02());
        setDescripcion(tbl.getDescripcion());
        setValorEntero01(tbl.getValorEntero01());
        setValorEntero02(tbl.getValorEntero02());
        setValorFecha01(tbl.getValorFecha01());
        setValorFecha02(tbl.getValorFecha02());
        setValorDoble01(tbl.getValorDoble01());
        setValorDoble02(tbl.getValorDoble02());
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
        
}
