package reportestpv;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Indica la Descripcion del punto de vente intalado
 *
 */
public class tblDepartamento implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigodepartamento;

    private String descripcion;

    private int id;

    private String responsable;

    public tblDepartamento() {
        limpiar();
    }

    public String getCodigodepartamento() {
        return this.codigodepartamento;
    }

    public void setCodigodepartamento(String codigodepartamento) {
        this.codigodepartamento = codigodepartamento;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponsable() {
        return this.responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public boolean buscarCodigo(String Codigo) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "Select id, codigodepartamento, descripcion, responsable from tblDepartamentos where codigodepartamento=?";
        Connection con = Conexion.getNuevaConexion();
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, Codigo);
        rs = pstmt.executeQuery();

        if (rs != null) {
            if (rs.last() == true) {
                setId(rs.getInt("id"));
                setCodigodepartamento(rs.getString("codigodepartamento"));
                setDescripcion(rs.getString("descripcion"));
                setResponsable(rs.getString("responsable"));

                ByosSql.CloseAll(con, pstmt, rs);
                return true;
            }
        }
        limpiar();
        ByosSql.CloseAll(con, pstmt, rs);
        return false;
    }

    public boolean existeCodigo(String Codigo) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "select Codigodepartamento from tblDepartamentos where Codigodepartamento=?";

        Connection con = Conexion.getNuevaConexion();
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, Codigo);
        rs = pstmt.executeQuery();

        if (rs != null) {
            if (rs.last() == true) {
                ByosSql.CloseAll(con, pstmt, rs);
                return true;
            }
        }
        ByosSql.CloseAll(con, pstmt, rs);
        return false;
    }

    public String eliminar(tblDepartamento tbl) {
        int i = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = Conexion.getNuevaConexion();
        //Ingresar nuevo
        try {
            if (existeCodigo(tbl.getCodigodepartamento())) {
                String query = "Delete From tbldepartamentos Where Codigodepartamento='" + tbl.getCodigodepartamento() + "'";
                con.setAutoCommit(false);
                pstmt = con.prepareStatement(query);
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

    public String guardar(tblDepartamento tbl) {
        int i = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = Conexion.getNuevaConexion();
        try {
            if (!existeCodigo(tbl.getCodigodepartamento())) {
                String query = "Insert into tblDepartamentos(codigodepartamento, descripcion, responsable) values(?,?,?)";
                con.setAutoCommit(false);
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, tbl.getCodigodepartamento());
                pstmt.setString(2, tbl.getDescripcion());
                pstmt.setString(3, tbl.getResponsable());
                i = pstmt.executeUpdate();
                con.commit();
                ByosSql.CloseAll(con, pstmt, rs);
                return utilString.SQL_INSERTADO;
            } else {
                String query = "Update tblDepartamentos set descripcion=?, responsable=? where Codigodepartamento='" + tbl.getCodigodepartamento() + "'";
                con.setAutoCommit(false);
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, tbl.getDescripcion());
                pstmt.setString(2, tbl.getResponsable());
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

    public ArrayList<tblDepartamento> Buscar(tblDepartamento tbl) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String InSql = "";
        int Estado = 0;
        ArrayList<tblDepartamento> tblArray = new ArrayList();

        String query = "Select "
                + "id, "
                + "codigodepartamento, "
                + "descripcion, "
                + "responsable "
                + "from tblDepartamentos ";
        if (tbl.getCodigodepartamento() != null && !tbl.getCodigodepartamento().equals("")) {
            InSql = InSql + " and codigodepartamento like '" + tbl.getCodigodepartamento().replace("*", "%") + "'";
            Estado = 1;
        }
        if (tbl.getDescripcion() != null && !tbl.getDescripcion().equals("")) {
            InSql = InSql + " and descripcion like '" + tbl.getDescripcion().replace("*", "%") + "'";
            Estado = 1;
        }
        if (tbl.getResponsable() != null && !tbl.getResponsable().equals("")) {
            InSql = InSql + " and responsable like '" + tbl.getResponsable().replace("*", "%") + "'";
            Estado = 1;
        }

        if (Estado == 1) {
            query = query + " Where 1=1 " + InSql;
        }
        query = query + " order by descripcion";

        Connection con = Conexion.getNuevaConexion();
        pstmt = con.prepareStatement(query);
        rs = pstmt.executeQuery();
        int size = 0;

        if (rs != null) {
            if (rs.last() == true) {
                size = rs.getRow();

                rs.first();
                for (int f = 0; f < size; f++) {
                    tblDepartamento Registros = new tblDepartamento();
                    Registros.setId(rs.getInt("id"));
                    Registros.setCodigodepartamento(rs.getString("codigodepartamento"));
                    Registros.setDescripcion(rs.getString("descripcion"));
                    Registros.setResponsable(rs.getString("responsable"));
                    tblArray.add(Registros);
                    rs.next();
                }
                ByosSql.CloseAll(con, pstmt, rs);
                return tblArray;
            }
        }
        ByosSql.CloseAll(con, pstmt, rs);
        return null;
    }

    public void limpiar() {
        setId(0);
        setCodigodepartamento("");
        setDescripcion("");
        setResponsable("");
    }

    public void setDepartamento(tblDepartamento Departamento) {
        setId(Departamento.getId());
        setCodigodepartamento(Departamento.getCodigodepartamento());
        setDescripcion(Departamento.getDescripcion());
        setResponsable(Departamento.getResponsable());
    }

}