package org.crud.sregion;

import java.io.IOException;
import java.rmi.ConnectIOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sound.midi.VoiceStatus;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/*
 * crud:  create read update delete
 * */
public class CRUDS_Region {
	
	static Connection connection;
	static String driver ="oracle.jdbc.driver.OracleDriver";
	static String URL= "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectionOracleDataBase()throws IOException,SQLException{
		try {
			Class.forName(driver).newInstance();
			System.out.println("CARGO DRIVER CORRECTAMENTE");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("EXCEPTION"+ e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(URL,"system","adm1norcL");
			System.out.println("CONEXION EXITOSA A ORACLE DATA BASE");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("EXCEPTION"+ e.getMessage());
		}
		
	}
	public static void agregarS_Region(int id, String name) throws IOException,SQLException{
		
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql= "INSERT INTO S_REGION (ID,NAME )VALUES (?,?";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("INSERTO:"+id+","+name);
			
			
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
			
		}
	}
	public static void actualizarS_Region(String name, int id) throws IOException,SQLException{
		
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql= "INSERT INTO S_REGION (ID,NAME )VALUES (?,?";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setString(1,name);
			ps.setInt(2, id);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("ACTUALIZO"+id+","+name);
			
			
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}
		}
public static void eliminarS_Region( int id) throws IOException,SQLException{
		
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql= "SELECT * FROM S_REGION WHERE ID=?";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1,id);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("ELIMINO"+ id);
			
			
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}
		}
public static void consultaIndividualS_Region(String name, int id) throws IOException,SQLException{
	
	try {
		connectionOracleDataBase();
		//CONSULTA
		String sql= "SELECT * FROM S_REGION WHERE ID=?";
		PreparedStatement ps= connection.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rSet =ps.executeQuery();
		if (rSet.next()) {
			System.out.println(rSet.getInt("id")+","+ rSet.getString("name"));
		}
		
		
		
	} catch (Exception e) {
		System.out.println("Exception:"+e.getMessage());
	}
	}
         
public static void consultaGeneral_Region() throws IOException,SQLException{
	
	try {
		connectionOracleDataBase();
		//CONSULTA
		String sql= "SELECT * FROM S_REGION ";
		PreparedStatement ps= connection.prepareStatement(sql);
		ResultSet rSet=ps.executeQuery();
		while(rSet.next()) {
			System.out.println(rSet.getInt("id")+","+ rSet.getString("name"));
		}
		
		
		
	} catch (Exception e) {
		System.out.println("Exception:"+e.getMessage());
	}
	}
public static void invocaProcedimientoAlmacenado(int id, String name) throws IOException,SQLException{
	
	try {
		connectionOracleDataBase();
		//CONSULTA
		CallableStatement cs= connection.prepareCall("CALL proc(?,?");
		cs.setInt(1, id);
		cs.setString(2, name);
		cs.execute();
		
		
		}
		
		
		
	 catch (Exception e) {
		System.out.println("Exception:"+e.getMessage());
	}
	}
         

	public static void main(String[] args)throws IOException, SQLException {
		connectionOracleDataBase();
		//agregarS_Region(15,"baja california");
		//actualizarS_Region("Baja california", 15);
		//eliminarS_Region(15);
		//consultaIndividualS_Region(2);
		//consultaGeneral_Region();
		invocaProcedimientoAlmacenado(17, "coahuila");
	}
}