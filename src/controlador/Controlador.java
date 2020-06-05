package controlador;

import java.sql.SQLException;
import java.text.ParseException;

import modelo.AccesoBBDD;
import modelo.Coleccion;
import modelo.Festival;

public class Controlador {
	
	private final static int TOTAL=24;
	private final static int TOTALS=21;
	
	public static void conectarBbdd() {
		AccesoBBDD.conectar();
	}
	public static void cerrarBbdd() throws SQLException {
		AccesoBBDD.cerrar();
	}
	
	
	public static void agregarFestival(String nombre, String ubicacion, String provincia, String parking, String genero,String artistas,
			String fecha, int duracion, double precio) throws SQLException {
		
		Festival f=new Festival(nombre,ubicacion,provincia,parking,genero,artistas,fecha,duracion,precio);
		modelo.Coleccion.anadirFestival(f);
		AccesoBBDD.insertarFestival(AccesoBBDD.generarId(),nombre,ubicacion,provincia,parking,genero,artistas,fecha,duracion,precio);
	}
	
	public static void eliminarFestival(int posicion) throws SQLException {	
		AccesoBBDD.eliminarFestival(modelo.Coleccion.devolverId(posicion));  //
		modelo.Coleccion.eliminarFestival(posicion);
	}
	public static void incluirFestival(int posicion) {
		Coleccion.anadirFestivalFavorito(Coleccion.devolverFestival(posicion));
	}
	
	public static void editarFestival(int posicion, String nombre, String ubicacion,String provincia, String parking, String genero,String artistas,
			String fecha, int duracion, double precio) throws SQLException {
		AccesoBBDD.actualizarFestival(posicion, nombre, ubicacion, provincia, parking, genero, artistas, fecha, duracion, precio);
		modelo.Coleccion.editarFestival(posicion, nombre, ubicacion, provincia, parking, genero, artistas, fecha, duracion, precio);
	}
	
	public static void anadirColeccion() throws SQLException, ParseException {
		modelo.Coleccion.anadirColeccion(AccesoBBDD.añadirColeccion());
	}
	
	public static String infoFestival(int posicion) {
		return modelo.Coleccion.infoFestival(posicion);
	}
	public static String devolverNombre(int posicion) {
		return modelo.Coleccion.devolverNombre(posicion);
	}
	 
	public static String devolverUbicacion(int posicion) {
	   return modelo.Coleccion.devolverUbicacion(posicion);
	}
	 
	public static String devolverProvincia(int posicion) {
	   return modelo.Coleccion.devolverProvincia(posicion);
	}
	
	public static String devolverParking(int posicion) {
		return modelo.Coleccion.devolverParking(posicion);
	}
	
	public static String devolverGenero(int posicion) {
		return modelo.Coleccion.devolverGenero(posicion);  
	}
	
	public static String devolverArtistas(int posicion) {
		return modelo.Coleccion.devolverArtistas(posicion);    
	}
	
	public static String devolverFecha(int posicion) {
	    return modelo.Coleccion.devolverFecha(posicion);
	}
	    
	public static int devolverDuracion(int posicion) { 
		return modelo.Coleccion.devolverDuracion(posicion);
	}
	   
	public static double devolverPrecio(int posicion) {
	    return modelo.Coleccion.devolverPrecio(posicion);    
	}
	public static void ordenarFecha() {
        Coleccion.ordenarFecha();
    }
	public static Festival devolverFestival(int posicion) {
		return Coleccion.devolverFestival(posicion);
	}
	public static String cambiarTamano(String s) {
		int blancos=TOTAL-s.length();
		for (int i=0;i<blancos;i++) {
			s=s+" ";
		}
		return s;
		
	}
	public static String cambiarTamanosec(String s) {
		int blancos=TOTALS-s.length();
		for (int i=0;i<blancos;i++) {
			s=s+" ";
		}
		return s;
	}

}
