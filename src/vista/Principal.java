package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Controlador;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Frame;

import modelo.AccesoBBDD;
import modelo.Coleccion;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String[] contenidocombo = { "Todos", "Género", "Fecha", "Localización" };
	private JPanel contentPane;
	private JTextField textField;
	private DefaultListModel<String> modelo;
	private JList<String> list;
	private JTextArea areaTexto;
	private JComboBox<String> comboFiltrar;
	private JButton bsalir;
	private JButton bagregar;
	private JButton bborrar;
	private JButton beditar;
	private JButton bfavoritos;
	private JButton bbuscar;
	private JButton bfavorito;
	private JButton buttonmin;
	private int total;
	private Selection selec;
	private Filtro filtro;

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 * @throws SQLException
	 */
	public Principal() throws SQLException, ParseException {
		Controlador.conectarBbdd();
		Controlador.anadirColeccion();
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Image icono = mipantalla.getImage("src/recursos/icon3.png");
		setIconImage(icono);
		setTitle("RockPedia");
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 510);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(151, 66, 401, 244);
		panel.add(scrollPane);

		modelo = new DefaultListModel<String>();
		list = new JList<String>(modelo);
		total = Coleccion.devolverTamano();
		for (int i = 1; i <= total; i++) {
			modelo.addElement(Controlador.devolverNombre(i) + " | " + Controlador.devolverGenero(i) + " | "
					+ Controlador.devolverFecha(i) + " | " + Controlador.devolverProvincia(i));
		}

		list.setToolTipText("Lista de Festivales");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selec = new Selection();
		list.addListSelectionListener(selec);

		scrollPane.setViewportView(list);

		areaTexto = new JTextArea();
		areaTexto.setLineWrap(true);
		areaTexto.setFont((new Font("Courier New", Font.PLAIN, 14)));
		areaTexto.setText("No hay ningun elemento seleccionado");
		areaTexto.setEditable(false);
		areaTexto.setMargin(new Insets(2, 2, 2, 2));
		areaTexto.setToolTipText("Descripcion del festival seleccionado");
		areaTexto.setBounds(10, 320, 542, 161);
		panel.add(areaTexto);

		bagregar = new JButton("AGREGAR");
		bagregar.setToolTipText("A\u00F1ade un Festival");
		bagregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar ventana = new Agregar(0, modelo);
				ventana.setVisible(true);
			}
		});

		bagregar.setForeground(Color.WHITE);
		bagregar.setBackground(Color.DARK_GRAY);
		bagregar.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 15));
		bagregar.setBounds(10, 75, 131, 30);
		panel.add(bagregar);

		beditar = new JButton("EDITAR");
		beditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int aux = list.getSelectedIndex();
				if (aux != -1) {
					Agregar ventana = new Agregar(aux + 1, modelo);
					ventana.setVisible(true);
				}

			}

		});
		beditar.setToolTipText("Edita un Festival seleccionado");
		beditar.setForeground(Color.WHITE);
		beditar.setBackground(Color.DARK_GRAY);
		beditar.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 15));
		beditar.setBounds(10, 116, 131, 30);
		panel.add(beditar);

		bborrar = new JButton("BORRAR");
		bborrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int aux = list.getSelectedIndex();
				if (aux != -1) {
					areaTexto.setText("");
					try {
						Controlador.eliminarFestival(aux + 1);
						modelo.removeElementAt(aux);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}

		});
		bborrar.setToolTipText("Elimina un festival");
		bborrar.setForeground(Color.WHITE);
		bborrar.setBackground(Color.DARK_GRAY);
		bborrar.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 16));
		bborrar.setBounds(10, 157, 131, 30);
		panel.add(bborrar);

		bfavoritos = new JButton("FAVORITOS");
		// bfavoritos.setEnabled(false);
		bfavoritos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Favoritos ventana = new Favoritos();
				ventana.setVisible(true);

			}

		});
		bfavoritos.setToolTipText("Consulta la lista de Favoritos");
		bfavoritos.setForeground(Color.WHITE);
		bfavoritos.setBackground(Color.DARK_GRAY);
		bfavoritos.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 16));
		bfavoritos.setBounds(10, 219, 131, 30);
		panel.add(bfavoritos);

		textField = new JTextField();
		textField.setToolTipText("Devuelve el primer registro que se encuentre");
		textField.setBounds(193, 29, 117, 20);
		panel.add(textField);
		textField.setColumns(10);

		bbuscar = new JButton("");
		bbuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().equals("")) {

				} else {
					Enumeration<String> contenido = modelo.elements();
					int numero = 1;
					while (contenido.hasMoreElements()) {
						String aux = contenido.nextElement();
						if (aux.contains(textField.getText().toUpperCase())) {
							areaTexto.setText(Controlador.infoFestival(numero));
						}
						numero++;
					}
				}

			}

		});
		bbuscar.setForeground(Color.DARK_GRAY);
		bbuscar.setIcon(new ImageIcon(Principal.class.getResource("/recursos/042g4.jpg")));
		bbuscar.setBounds(316, 29, 23, 20);
		panel.add(bbuscar);

		comboFiltrar = new JComboBox<String>(contenidocombo);
		filtro = new Filtro();
		comboFiltrar.addActionListener(filtro);
		comboFiltrar.setToolTipText("Filtra los resultados mostrados");
		comboFiltrar.setBounds(411, 30, 141, 20);
		panel.add(comboFiltrar);

		bfavorito = new JButton("INCLUIR");
		bfavorito.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int aux = list.getSelectedIndex();
				if (aux != -1) {
					Coleccion.anadirFestivalFavorito(Controlador.devolverFestival(aux + 1));
				}
			}

		});
		// bfavorito.setEnabled(false);
		bfavorito.setToolTipText("Se incluye en favoritos");
		bfavorito.setBackground(Color.DARK_GRAY);
		bfavorito.setForeground(Color.WHITE);
		bfavorito.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 16));
		bfavorito.setBounds(10, 260, 131, 50);
		panel.add(bfavorito);

		bsalir = new JButton("");
		bsalir.setToolTipText("Salir");
		bsalir.setBorderPainted(false);
		bsalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controlador.cerrarBbdd();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);

			}

		});

		bsalir.setForeground(Color.DARK_GRAY);
		bsalir.setIcon(new ImageIcon(Principal.class.getResource("/recursos/close3.png")));
		bsalir.setBounds(543, 0, 16, 20);
		panel.add(bsalir);

		JLabel lblNewLabel_1 = new JLabel("BUSCAR");
		lblNewLabel_1.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(130, 29, 63, 17);
		panel.add(lblNewLabel_1);

		JLabel lblFiltrar = new JLabel("FILTRAR");
		lblFiltrar.setForeground(Color.WHITE);
		lblFiltrar.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 14));
		lblFiltrar.setBounds(349, 29, 63, 17);
		panel.add(lblFiltrar);

		JLabel lblcc = new JLabel("JobLess Studio CC BY-NC-ND - Reconocimiento-NoComercial-SinObraDerivada ");
		lblcc.setForeground(Color.WHITE);
		lblcc.setFont(new Font("Arial", Font.PLAIN, 7));
		lblcc.setBounds(292, 497, 278, 14);
		panel.add(lblcc);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/recursos/logo.jpg")));
		lblNewLabel.setBounds(10, 11, 117, 45);
		panel.add(lblNewLabel);

		buttonmin = new JButton("");
		buttonmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setState(Frame.ICONIFIED);
			}
		});
		buttonmin.setBorderPainted(false);
		buttonmin.setIcon(new ImageIcon(Principal.class.getResource("/recursos/minm.png")));
		buttonmin.setToolTipText("Minimizar");
		buttonmin.setForeground(Color.DARK_GRAY);
		buttonmin.setBounds(522, 0, 16, 20);
		panel.add(buttonmin);

	}

	private class Selection implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			int aux = list.getSelectedIndex();
			if (aux != -1) {
				areaTexto.setText(Controlador.infoFestival(aux + 1));
			}
		}

	}

	private class Filtro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (comboFiltrar.getSelectedItem().equals("Todos")) {
				modelo.clear();

				list.addListSelectionListener(selec);
				total = Coleccion.devolverTamano();
				for (int i = 1; i <= total; i++) {
					modelo.addElement(Controlador.devolverNombre(i) + " | " + Controlador.devolverGenero(i) + " | "
							+ Controlador.devolverFecha(i) + " | " + Controlador.devolverProvincia(i));
				}
				bagregar.setEnabled(true);
				bborrar.setEnabled(true);
				beditar.setEnabled(true);
				bfavorito.setEnabled(true);
				bfavoritos.setEnabled(true);
				bbuscar.setEnabled(true);
				areaTexto.setText("");

			} else if (comboFiltrar.getSelectedItem().equals("Género")) {
				areaTexto.setText(
						"Mientras filtras no podrás ampliar información,por favor selecciona Todos para activar las opciones de administración.");
				ArrayList<String> resultado;
				String dato = JOptionPane.showInputDialog("Introduce el Género por el que quieres filtrar");
				if (dato != null) {
					modelo.clear();
					list.removeListSelectionListener(selec);
					try {
						resultado = AccesoBBDD.consultaPorGenero(dato.toUpperCase());
						for (int i = 0; i < resultado.size();) {
							modelo.addElement(resultado.get(i) + " | " + resultado.get(i + 1) + " | "
									+ resultado.get(i + 2) + " | " + resultado.get(i + 3));
							i = i + 4;
						}
					} catch (SQLException e1) {
						areaTexto.setText("Fallo al realizar la consulta");
					}
					bagregar.setEnabled(false);
					bborrar.setEnabled(false);
					beditar.setEnabled(false);
					bfavorito.setEnabled(false);
					bfavoritos.setEnabled(true);
					bbuscar.setEnabled(false);

				} else {
					comboFiltrar.setSelectedItem("Todos");
				}
			} else if (comboFiltrar.getSelectedItem().equals("Localización")) {
				areaTexto.setText(
						"Mientras filtras no podrás ampliar información,por favor selecciona Todos para activar las opciones de administración.");
				ArrayList<String> resultado;
				String dato = JOptionPane.showInputDialog("Introduce la Localizacion por el que quieres filtrar");
				if (dato != null) {
					modelo.clear();
					list.removeListSelectionListener(selec);
					try {
						resultado = AccesoBBDD.consultaPorLocalizacion(dato.toUpperCase());
						for (int i = 0; i < resultado.size();) {
							modelo.addElement(resultado.get(i) + " | " + resultado.get(i + 1) + " | "
									+ resultado.get(i + 2) + " | " + resultado.get(i + 3));
							i = i + 4;
						}
					} catch (SQLException e1) {
						areaTexto.setText("Fallo al realizar la consulta");
					}
					bagregar.setEnabled(false);
					bborrar.setEnabled(false);
					beditar.setEnabled(false);
					bfavorito.setEnabled(false);
					bfavoritos.setEnabled(true);
					bbuscar.setEnabled(false);

				} else {
					comboFiltrar.setSelectedItem("Todos");
				}
			} else if (comboFiltrar.getSelectedItem().equals("Fecha")) {
				areaTexto.setText(
						"Mientras filtras no podrás ampliar información,por favor selecciona Todos para activar las opciones de administración.");
				ArrayList<String> resultado;
				String dato = JOptionPane.showInputDialog("Introduce la fecha por el que quieres filtrar");
				if (dato != null) {
					modelo.clear();
					list.removeListSelectionListener(selec);
					try {
						resultado = AccesoBBDD.consultaPorFecha(dato.toUpperCase());
						for (int i = 0; i < resultado.size();) {
							modelo.addElement(resultado.get(i) + " | " + resultado.get(i + 1) + " | "
									+ resultado.get(i + 2) + " | " + resultado.get(i + 3));
							i = i + 4;
						}
					} catch (SQLException e1) {
						areaTexto.setText("Fallo al realizar la consulta");
					}
					bagregar.setEnabled(false);
					bborrar.setEnabled(false);
					beditar.setEnabled(false);
					bfavorito.setEnabled(false);
					bfavoritos.setEnabled(true);
					bbuscar.setEnabled(false);

				} else {
					comboFiltrar.setSelectedItem("Todos");
				}
			}

		}

	}
}
