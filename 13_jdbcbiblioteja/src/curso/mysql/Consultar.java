package curso.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Consultar {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
		
		Statement stm = conn.createStatement();
		
		ResultSet rs = stm.executeQuery("select * from libros");
		
		while (rs.next()) {
			System.out.println("Precio: " + rs.getFloat(4));
			System.out.println("Titulo: " + rs.getString(2));
			System.out.println("Fecha: " + rs.getDate(5));
			System.out.println("Fecha: " + rs.getDate(5));
		}
		
		int insertar = stm.executeUpdate("Insert into libros (titulo, autor,precio,fechaplubli) values ('El quijote 2', 'Cervantes', 23.40, '1978-11-12')");
		
		System.out.println("Fila insertada" + insertar);
		
		PreparedStatement pstmt = conn.prepareStatement("select * from libros where titulo = ?");
		
		pstmt.setString(1,"El quijote 2");
		ResultSet rs1 = pstmt.executeQuery();
	
		while (rs1.next()) {
			System.out.println("Titulo:" + rs1.getString(2));
		}
		
		CallableStatement cstmt = conn.prepareCall("{call listalibros()}");
		
		ResultSet rs2 = cstmt.executeQuery();
	
		while (rs2.next()) {
			System.out.println("Titulo:" + rs2.getString(2));
		}
		
		CallableStatement castmt = conn.prepareCall("{call listalibrosporautor(?)}");
		
		castmt.setString(1, "Benjamin");
		
		ResultSet rs3 = castmt.executeQuery();
	
		while (rs3.next()) {
			System.out.println(rs3.getString(3) + " es el autor del libro: " + rs3.getString(2));
		}
		
	}

}
