package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

public class AccesoBBDD {

	private static String bd = "XE";
	private static String login = "FESTP";
	private static String password = "FEST";
	private static String url = "jdbc:oracle:thin:@localhost:1521:" + bd;
	private static Connection connection = null;
	private static Statement st = null;
	private static ResultSet rs = null;
	private static PreparedStatement pst = null;

	/**
	 * Metodo que demuestra la conexion con la base de datos
	 */
	public static void conectar() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, login, password);
			connection.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertarFestival(int posicion, String nombre, String ubicacion, String provincia, String parking,
			String genero, String artistas, String fecha, int duracion, double precio) throws SQLException {
		pst = connection.prepareStatement("insert into FESTIVAL values(?,?,?,?,?,?,?,?,?,?)");
		pst.setInt(1, posicion);
		pst.setString(2, nombre);
		pst.setString(3, ubicacion);
		pst.setString(4, provincia);
		pst.setString(5, parking);
		pst.setString(6, genero);
		pst.setString(7, artistas);
		pst.setString(8, fecha);
		pst.setInt(9, duracion);
		pst.setDouble(10, precio);
		pst.executeUpdate();
	}

	public static ArrayList<String> consultaPorGenero(String genero) throws SQLException {
		ArrayList<String> resul = new ArrayList<String>();
		pst = connection.prepareStatement("SELECT NOMBRE,GENERO,FECHA,PROVINCIA FROM FESTIVAL WHERE GENERO=?");
		pst.setString(1, genero);
		rs = pst.executeQuery();
		while (rs.next()) {
			resul.add(rs.getString(1));
			resul.add(rs.getString(2));
			resul.add(rs.getString(3));
			resul.add(rs.getString(4));
		}
		return resul;
	}

	public static ArrayList<String> consultaPorFecha(String fecha) throws SQLException {
		ArrayList<String> resul = new ArrayList<String>();
		pst = connection.prepareStatement("SELECT NOMBRE,GENERO,FECHA,PROVINCIA FROM FESTIVAL WHERE FECHA=?");
		pst.setString(1, fecha);
		rs = pst.executeQuery();
		while (rs.next()) {
			resul.add(rs.getString(1));
			resul.add(rs.getString(2));
			resul.add(rs.getString(3));
			resul.add(rs.getString(4));
		}
		return resul;
	}

	public static ArrayList<String> consultaPorLocalizacion(String localizacion) throws SQLException {
		ArrayList<String> resul = new ArrayList<String>();
		pst = connection.prepareStatement("SELECT NOMBRE,GENERO,FECHA,PROVINCIA FROM FESTIVAL WHERE LOCALIZACION=?");
		pst.setString(1, localizacion);
		rs = pst.executeQuery();
		while (rs.next()) {
			resul.add(rs.getString(1));
			resul.add(rs.getString(2));
			resul.add(rs.getString(3));
			resul.add(rs.getString(4));
		}
		return resul;
	}

	public static ArrayList<Festival> añadirColeccion() throws SQLException, ParseException {
		st = connection.createStatement();
		rs = st.executeQuery(
				"select NOMBRE,LOCALIZACION,PROVINCIA,PARKING,GENERO,GRUPOS,FECHA,DURACION,PRECIO from FESTIVAL");
		ArrayList<Festival> lista = new ArrayList<Festival>();

		while (rs.next()) {
			lista.add(new Festival(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9)));
		}
		return lista;
	}

	public static void actualizarFestival(int id, String nombre, String localizacion, String provincia, String parking,
			String genero, String grupos, String fecha, int duracion, double precio) throws SQLException {

		pst = connection.prepareStatement("UPDATE FESTIVAL SET NOMBRE=?,LOCALIZACION=?,PROVINCIA=?,"
				+ "PARKING=?,GENERO=?,GRUPOS=?,FECHA=?,DURACION=?,PRECIO=? WHERE ID=?");

		pst.setInt(10, id);
		pst.setString(1, nombre);
		pst.setString(2, localizacion);
		pst.setString(3, provincia);
		pst.setString(4, parking);
		pst.setString(5, genero);
		pst.setString(6, grupos);
		pst.setString(7, fecha);
		pst.setInt(8, duracion);
		pst.setDouble(9, precio);

		pst.executeUpdate();

	}

	public static void eliminarFestival(int id) throws SQLException {
		st = connection.createStatement();
		st.executeQuery("delete from Festival where id=" + id);
	}

	/**
	 * @throws SQLException
	 */
	public static void cerrar() throws SQLException {
		if (st != null)
			st.close();
		if (connection != null)
			connection.close();
	}

	public static int generarId() throws SQLException {
		st = connection.createStatement();
		rs = st.executeQuery("select max(id) from FESTIVAL");
		while (rs.next()) {
			return rs.getInt(1) + 1;
		}
		return 0;
	}

}
