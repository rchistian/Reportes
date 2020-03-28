package reportestpv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/*

Funciones para falicilar la vaidacion y ejecucion de los sql

*/

public class ByosSql {
	
	public ByosSql(){
		
	}
	
	public static void CloseAll(Connection con, PreparedStatement pstmt, ResultSet rs) {
	   try{
	      if(rs!=null){
		     rs.close();
	         rs=null;
	      }
	      if(pstmt!=null){
	         pstmt.close();
	         pstmt=null; 
	      }
              
	      if(con!=null){
	         con.close();
	         con=null;     
	      }
              
	   }catch (SQLException e) {
		  e.printStackTrace();
	   }
	}
	
	public static void RollBack(Connection con){
	   try{
		  con.rollback();
	   }catch (SQLException e1) {
		  e1.printStackTrace();
	   }
	}
	
    public static int ObtenerAutonumerico(PreparedStatement pstmt) throws Exception {
        int id = -1;
        ResultSet rs = pstmt.getGeneratedKeys();
        if(rs.next()) 	 {
           id = rs.getInt(1);
        }
        return id;
     }
    	
}
