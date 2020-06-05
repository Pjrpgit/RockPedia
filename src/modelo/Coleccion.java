package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Coleccion {

	private static HashMap<Integer, Festival> festivales = new HashMap<Integer, Festival>();
	private static HashMap<Integer, Festival> favoritos = new HashMap<Integer, Festival>();
	private static int key;
	private static int keyf;
	private static int contador = 0;

	public static void anadirColeccion(ArrayList<Festival> lista) {
		Iterator<Festival> it = lista.iterator();
		while (it.hasNext()) {
			anadirFestival(it.next());
		}

	}

	public static void anadirFestival(Festival fest) {
		contador++;
		festivales.put(key += 1, fest);
	}

	public static void anadirFestivalFavorito(Festival fest) {
		favoritos.put(keyf += 1, fest);
	}

	public static void eliminarFestival(int posicion) {
		int cambios = contador - posicion;
		contador--;
		festivales.remove(posicion);
		if (contador > 1) {
			colocarFestivales(posicion, cambios);
		}
	}

	public static void colocarFestivales(int posicion, int cambios) {
		Festival recambio;
		while (cambios > 0) {
			recambio = new Festival(festivales.get(posicion + 1));
			festivales.put(posicion, recambio);
			posicion++;
			cambios--;
		}
	}

	public static void editarFestival(int posicion, String nombre, String ubicacion, String provincia, String parking,
			String genero, String artistas, String fecha, int duracion, double precio) {
		Iterator<Integer> it = festivales.keySet().iterator();
		int num;
		Festival f;

		while (it.hasNext()) {
			num = it.next();
			if (num == posicion) {
				f = festivales.get(num);
				f.editarFestival(nombre, ubicacion, provincia, parking, genero, artistas, fecha, duracion, precio);
			}
		}
	}

	public static void ordenarFecha() {
		ArrayList<Festival> lista = new ArrayList<Festival>(festivales.values());
		Collections.sort(lista);
		System.out.println(lista);
		anadirColeccion(lista);
	}

	public static int devolverId(int posicion) {
		return festivales.get(posicion).getId();
	}

	public static String devolverNombre(int posicion) {
		return festivales.get(posicion).getNombre();
	}

	public static String devolverNombref(int posicion) {
		return favoritos.get(posicion).getNombre();
	}

	public static String devolverUbicacion(int posicion) {
		return festivales.get(posicion).getUbicacion();
	}

	public static String devolverProvincia(int posicion) {
		return festivales.get(posicion).getProvincia();
	}

	public static String devolverProvinciaf(int posicion) {
		return festivales.get(posicion).getProvincia();
	}

	public static String devolverParking(int posicion) {
		return festivales.get(posicion).getParking();
	}

	public static String devolverGenero(int posicion) {
		return festivales.get(posicion).getGenero();
	}

	public static String devolverGenerof(int posicion) {
		return favoritos.get(posicion).getGenero();
	}

	public static String devolverArtistas(int posicion) {
		return festivales.get(posicion).getArtistas();
	}

	public static String devolverFecha(int posicion) {
		return festivales.get(posicion).getFecha();
	}

	public static String devolverFechaf(int posicion) {
		return favoritos.get(posicion).getFecha();
	}

	public static int devolverDuracion(int posicion) {
		return festivales.get(posicion).getDuracion();
	}

	public static double devolverPrecio(int posicion) {
		return festivales.get(posicion).getPrecio();
	}

	public static String infoFestival(int posicion) {
		Iterator<Integer> it = festivales.keySet().iterator();
		int num;
		Festival f;

		while (it.hasNext()) {
			num = it.next();
			if (num == posicion) {
				f = festivales.get(num);
				return f.toString();
			}
		}
		return "Festival no encontrado";
	}

	public static Festival devolverFestival(int posicion) {
		return festivales.get(posicion);
	}

	public static int devolverTamano() {
		return festivales.size();
	}

	public static int devolverTamanof() {
		return favoritos.size();
	}

}
