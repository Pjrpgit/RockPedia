package modelo;

import controlador.Controlador;

/**
 * Cada uno de los festivales mostrados en la aplicacion
 * 
 * @author Francisco-Pedro-Alejandro
 */
public class Festival implements Comparable<Festival> {

	private static int n = 0;
	private int id = 0;
	private String nombre;
	private String ubicacion;
	private String provincia;
	private String parking;
	private String genero;
	private String artistas;
	private String fecha;
	private int duracion;
	private double precio;

	/**
	 * Constructor de la clase Festival
	 * 
	 * @param nombre    Nombre del festival
	 * @param ubicacion Nombre de la localizacion
	 * @param provincia Nombre de la provincia
	 * @param parking   Boolean existencia de parking
	 * @param genero    Genero de musica
	 * @param artistas  Cadena de caracteres con los artistas invitados
	 * @param fecha     Fecha de celebracion
	 * @param duracion  Duracion del festival
	 * @param precio    Precio de la entrada
	 */
	public Festival(String nombre, String ubicacion, String provincia, String parking, String genero, String artistas,
			String fecha, int duracion, double precio) {

		Festival.n += 1;
		this.id = n;
		this.nombre = nombre;
		this.genero = genero;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.provincia = provincia;
		this.parking = parking;
		this.duracion = duracion;
		this.precio = precio;
		this.artistas = artistas;
	}

	public Festival(Festival c) {
		this.id = c.getId();
		this.nombre = c.getNombre();
		this.genero = c.getGenero();
		this.fecha = c.getFecha();
		this.ubicacion = c.getUbicacion();
		this.provincia = c.getProvincia();
		this.parking = c.getParking();
		this.duracion = c.getDuracion();
		this.precio = c.getPrecio();
		this.artistas = c.getArtistas();
	}

	/**
	 * @return Devuelve el identificador
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return Devuelve el nombre de la provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia Asigna el nombre de la provincia
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return Devuelve el tipo de genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero Asigna el tipo de genero
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * @return Devuelve una cadena de caracteres con los artistas
	 */
	public String getArtistas() {
		return artistas;
	}

	/**
	 * @param artistas Datos
	 */
	public void setArtistas(String artistas) {
		this.artistas += artistas;
	}

	/**
	 * @return Nombre festival
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Asigna nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Devuleve la ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion Asigna la ubicacion
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return Devuelve si existe parking
	 */
	public String getParking() {
		return parking;
	}

	/**
	 * @param parking Asigna la existencia de parking
	 */
	public void setParking(String parking) {
		this.parking = parking;
	}

	/**
	 * @return Devuelve la fecha del festival
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha Asigna la fecha de comienzo
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return Devuelve la duracion del festival (en dias)
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion Asigna la duracion del festival (en dias)
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/**
	 * @return Devuelve precio de la entrada
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio Asigna el precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Para editar un festival ya existente
	 * 
	 * @param nombre    Nombre del festival
	 * @param ubicacion Nombre de la localizacion
	 * @param provincia Nombre de la provincia
	 * @param parking   Boolean existencia de parking
	 * @param genero    Genero de musica
	 * @param artistas  Cadena de caracteres con los artistas invitados
	 * @param fecha     Fecha de celebracion
	 * @param duracion  Duracion del festival
	 * @param precio    Precio de la entrada
	 */
	public void editarFestival(String nombre, String ubicacion, String provincia, String parking, String genero,
			String artistas, String fecha, int duracion, double precio) {

		this.setNombre(nombre);
		this.setUbicacion(ubicacion);
		this.setProvincia(provincia);
		this.setParking(parking);
		this.setGenero(genero);
		this.setArtistas(artistas);
		this.setFecha(fecha);
		this.setDuracion(duracion);
		this.setPrecio(precio);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Festival o) {
		return this.provincia.compareTo(o.getProvincia());
	}

	@Override
	public String toString() {

		return "                        F E S T I V A L\nNOMBRE: " + Controlador.cambiarTamano(nombre) + "GÉNERO: "
				+ genero + "\nUBICACIÓN: " + Controlador.cambiarTamanosec(ubicacion) + "PARKING: " + getParking()
				+ "\nPROVINCIA: " + Controlador.cambiarTamanosec(provincia) + "FECHA: " + fecha + "  PRECIO:" + precio
				+ "€\nARTISTAS:\n" + artistas;
	}

}
