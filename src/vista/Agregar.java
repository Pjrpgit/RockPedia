package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXDatePicker;

import controlador.Controlador;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Agregar extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String[] PROVINCIAS = { "ALAVA", "ALBACETE", "ALICANTE", "ALMERÍA", "ASTURIAS", "AVILA", "BADAJOZ",
			"BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CIUDAD REAL", "CÓRDOBA", "LA CORUÑA",
			"CUENCA", "GERONA", "GRANADA", "GUADALAJARA", "GUIPÚZCOA", "HUELVA", "HUESCA", "ISLAS BALEARES", "JAÉN",
			"LEÓN", "LÉRIDA", "LUGO", "MADRID", "MÁLAGA", "MURCIA", "NAVARRA", "ORENSE", "PALENCIA", "LAS PALMAS",
			"PONTEVEDRA", "LA RIOJA", "SALAMANCA", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "SANTA CRUZ DE TENERIFE",
			"TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA" };
	private final String[] GENERO = { "ELECTRÓNICA", "BLUES", "ROCK", "INDIE", "FUSION", "SOUL", "HEAVY", "PUNK",
			"REAGGE", "HIPHOP", "FOLK", "TECHNO", "JAZZ" };
	private JPanel contentPane;
	private JTextField fieldnombre;
	private JTextField fieldloc;
	private JTextArea area;
	private JLabel lblparking;
	private JLabel lblloc;
	private JLabel lblartistas;
	private JLabel labelfecha;
	private JLabel lblprecio;
	private JButton bsalir;
	private JButton breiniciar;
	private JButton bguardar;
	private JComboBox<String> combogenero;
	private JComboBox<String> comboparking;
	private JXDatePicker calen;
	private JSpinner sduracion;
	private JComboBox<String> comboprov;
	private JSpinner sprecio;
	private SpinnerNumberModel modeldur;
	private SpinnerNumberModel modelpre;
	private final int TOTALMAX = 50;
	private final int TOTAL = 500;
	private SimpleDateFormat formato;
	private String aux;
	private Date fdate;

	public Agregar(int edita, DefaultListModel<String> modelo) {
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Image icono = mipantalla.getImage("src/recursos/icon3.png");
		setIconImage(icono);
		setTitle("RockPedia - Agregar");
		formato = new SimpleDateFormat("dd/MM/yyyy");
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(661, 100, 462, 313);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 462, 313);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblnombre = new JLabel("NOMBRE");
		lblnombre.setForeground(Color.WHITE);
		lblnombre.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 12));
		lblnombre.setBounds(10, 26, 97, 22);
		panel.add(lblnombre);

		fieldnombre = new JTextField();
		fieldnombre.setToolTipText("Introduzca aquí el nombre del Festival");
		fieldnombre.setBounds(65, 28, 106, 20);
		fieldnombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (fieldnombre.getText().length() >= TOTALMAX) {
					e.consume();
				}
			}
		});
		panel.add(fieldnombre);
		fieldnombre.setColumns(10);

		lblloc = new JLabel("LOCALIZACION");
		lblloc.setForeground(Color.WHITE);
		lblloc.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 12));
		lblloc.setBounds(181, 26, 86, 22);
		panel.add(lblloc);

		fieldloc = new JTextField();
		fieldloc.setToolTipText(
				"Introduzca aqu\u00ED la localizacion del festival, eje: Av. Felipe II, s/n, 28009 Madrid");
		fieldloc.setBounds(275, 28, 176, 20);
		fieldloc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (fieldloc.getText().length() >= TOTALMAX) {
					e.consume();
				}
			}
		});
		panel.add(fieldloc);
		fieldloc.setColumns(10);

		lblparking = new JLabel("PARKING");
		lblparking.setForeground(Color.WHITE);
		lblparking.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 12));
		lblparking.setBounds(10, 66, 55, 22);
		panel.add(lblparking);

		comboparking = new JComboBox<String>();
		comboparking.setToolTipText("Introduzca si tiene parking");
		comboparking.addItem("SI");
		comboparking.addItem("NO");

		comboparking.setBounds(65, 68, 42, 20);
		panel.add(comboparking);
		JLabel lblgenero = new JLabel("GENERO");
		lblgenero.setForeground(Color.WHITE);
		lblgenero.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 12));
		lblgenero.setBounds(127, 66, 54, 22);
		panel.add(lblgenero);

		combogenero = new JComboBox<String>(GENERO); // (genero);
		combogenero.setToolTipText("Introduzca el g\u00E9nero musical");
		combogenero.setBounds(187, 68, 97, 20);
		panel.add(combogenero);

		area = new JTextArea();
		area.setLineWrap(true);
		area.setMargin(new Insets(2, 2, 2, 2));
		area.setToolTipText("Introduzca aquí el LineUp del festival");
		area.setBounds(10, 172, 441, 100);
		area.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (area.getText().length() >= TOTAL) {
					e.consume();
				}
			}
		});
		panel.add(area);

		lblartistas = new JLabel("ARTISTAS");
		lblartistas.setForeground(Color.WHITE);
		lblartistas.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 12));
		lblartistas.setBounds(10, 146, 97, 22);
		panel.add(lblartistas);

		calen = new JXDatePicker();
		calen.getEditor().setToolTipText("Introduzca aqu\u00ED la fecha del inicio de festival");
		calen.setBounds(359, 67, 92, 22);
		panel.add(calen);

		labelfecha = new JLabel("FECHA");
		labelfecha.setForeground(Color.WHITE);
		labelfecha.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 12));
		labelfecha.setBounds(314, 66, 45, 22);
		panel.add(labelfecha);

		lblprecio = new JLabel("PRECIO");
		lblprecio.setForeground(Color.WHITE);
		lblprecio.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 12));
		lblprecio.setBounds(10, 117, 55, 22);
		panel.add(lblprecio);

		modelpre = new SpinnerNumberModel(0.0, 0, 350.0, 0.1);// (0.0, 0 ,350.0,0.1);(0, 0, 350, 1);
		sprecio = new JSpinner(modelpre);
		sprecio.setToolTipText("Introduzca el precio ");
		sprecio.setBounds(65, 115, 52, 20);
		panel.add(sprecio);

		JLabel lblduracion = new JLabel("DURACION");
		lblduracion.setForeground(Color.WHITE);
		lblduracion.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 12));
		lblduracion.setBounds(127, 117, 67, 22);
		panel.add(lblduracion);

		modeldur = new SpinnerNumberModel(1, 1, 15, 1);
		sduracion = new JSpinner(modeldur);
		sduracion.setToolTipText("Introduzca el n\u00FAmero de d\u00EDas");
		sduracion.setBounds(196, 115, 29, 20);
		panel.add(sduracion);

		JLabel labeld = new JLabel("DIAS");
		labeld.setForeground(Color.WHITE);
		labeld.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 10));
		labeld.setBounds(227, 123, 29, 13);
		panel.add(labeld);

		JLabel lblProvincia = new JLabel("PROVINCIA");
		lblProvincia.setForeground(Color.WHITE);
		lblProvincia.setFont(new Font("ROG Fonts v1.6", Font.PLAIN, 12));
		lblProvincia.setBounds(266, 117, 67, 22);
		panel.add(lblProvincia);

		comboprov = new JComboBox<String>(PROVINCIAS); // (PROVINCIAS);
		comboprov.setBounds(337, 117, 114, 20);
		panel.add(comboprov);

		bsalir = new JButton("SALIR");
		bsalir.setToolTipText("Salir sin guardar");
		bsalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		bsalir.setForeground(Color.WHITE);
		bsalir.setBackground(Color.DARK_GRAY);
		bsalir.setFont(new Font("ROG Fonts v1.6", Font.BOLD, 16));
		bsalir.setBounds(10, 279, 127, 23);
		panel.add(bsalir);

		breiniciar = new JButton("REINICIAR");
		breiniciar.setToolTipText("Resetea los campos ");
		breiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (edita == 0) {
					fieldnombre.setText("");
					fieldloc.setText("");
					area.setText("");
					comboparking.setSelectedItem("SI");
					combogenero.setSelectedItem("ELECTRÓNICA");
					comboprov.setSelectedItem("ALAVA");
					calen.setDate(null);
					sprecio.setValue(0);
					sduracion.setValue(1);
				} else {
					fieldnombre.setText(Controlador.devolverNombre(edita));
					fieldloc.setText(Controlador.devolverUbicacion(edita));
					comboparking.setSelectedItem(Controlador.devolverParking(edita));
					combogenero.setSelectedItem(Controlador.devolverGenero(edita));
					comboprov.setSelectedItem(Controlador.devolverProvincia(edita));
					area.setText(Controlador.devolverArtistas(edita));
					modeldur.setValue(Controlador.devolverDuracion(edita));
					modelpre.setValue(Controlador.devolverPrecio(edita));
				}
			}
		});
		breiniciar.setForeground(Color.WHITE);
		breiniciar.setBackground(Color.DARK_GRAY);
		breiniciar.setFont(new Font("ROG Fonts v1.6", Font.BOLD, 16));
		breiniciar.setBounds(158, 279, 130, 23);
		panel.add(breiniciar);

		bguardar = new JButton("GUARDAR");
		bguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (calen.getDate() != null) {
					aux = formato.format(calen.getDate());
				}
				if (edita == 0 && calen.getDate() != null) {
					try {
						Controlador.agregarFestival(fieldnombre.getText(), fieldloc.getText(),
								comboprov.getSelectedItem().toString(), comboparking.getSelectedItem().toString(),
								combogenero.getSelectedItem().toString(), area.getText(), aux,
								Integer.parseInt(sduracion.getValue().toString()),
								Double.parseDouble(sprecio.getValue().toString())); // para matarme pero hay que hacerlo
																					// rapido
						modelo.addElement(fieldnombre.getText() + " | " + combogenero.getSelectedItem().toString()
								+ " | " + aux + " | " + comboprov.getSelectedItem().toString());
						setVisible(false);
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else if (edita != 0 && calen.getDate() != null) {
					try {
						Controlador.editarFestival(edita, fieldnombre.getText(), fieldloc.getText(),
								comboprov.getSelectedItem().toString(), comboparking.getSelectedItem().toString(),
								combogenero.getSelectedItem().toString(), area.getText(), aux,
								Integer.parseInt(sduracion.getValue().toString()),
								Double.parseDouble(sprecio.getValue().toString()));
						modelo.set(edita - 1,
								Controlador.devolverNombre(edita) + " | " + Controlador.devolverGenero(edita) + " | "
										+ Controlador.devolverFecha(edita) + " | "
										+ Controlador.devolverProvincia(edita));
						setVisible(false);
						dispose();
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					} // para matarme pero hay que hacerlo rapido

				} else {

				}
			}

		});
		bguardar.setToolTipText("Guardar el nuevo Festival");
		bguardar.setForeground(Color.WHITE);
		bguardar.setBackground(Color.DARK_GRAY);
		bguardar.setFont(new Font("ROG Fonts v1.6", Font.BOLD, 16));
		bguardar.setBounds(314, 279, 137, 23);
		panel.add(bguardar);

		JLabel labeleur = new JLabel("\u20AC");
		labeleur.setForeground(Color.WHITE);
		labeleur.setFont(new Font("Dialog", Font.PLAIN, 10));
		labeleur.setBounds(108, 123, 29, 13);
		panel.add(labeleur);
		if (edita > 0) {
			fieldnombre.setText(Controlador.devolverNombre(edita));
			fieldloc.setText(Controlador.devolverUbicacion(edita));
			comboparking.setSelectedItem(Controlador.devolverParking(edita));
			combogenero.setSelectedItem(Controlador.devolverGenero(edita));
			comboprov.setSelectedItem(Controlador.devolverProvincia(edita));
			area.setText(Controlador.devolverArtistas(edita));
			modeldur.setValue(Controlador.devolverDuracion(edita));
			modelpre.setValue(Controlador.devolverPrecio(edita));
			try {
				fdate = formato.parse(Controlador.devolverFecha(edita));
				calen.setDate(fdate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
