package vista;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Coleccion;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Favoritos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton bsalir;
	private JButton buttonmin;
	private DefaultListModel<String> modelofav;
	private JList<String> favolist;
	private int total;

	public Favoritos() {
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Image icono = mipantalla.getImage("src/recursos/icon3.png");
		setIconImage(icono);
		setTitle("RockPedia - Favoritos");
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(661, 414, 462, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 462, 196);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 440, 154);
		panel.add(scrollPane);

		modelofav = new DefaultListModel<String>();
		total = Coleccion.devolverTamanof();
		for (int i = 1; i <= total; i++) {
			modelofav.addElement(Coleccion.devolverNombref(i) + " | " + Coleccion.devolverGenerof(i) + " | "
					+ Coleccion.devolverFechaf(i) + " | " + Coleccion.devolverProvinciaf(i));
		}
		favolist = new JList<String>(modelofav);
		favolist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(favolist);

		JLabel lblFavoritos = new JLabel("FAVORITOS:");
		lblFavoritos.setForeground(Color.WHITE);
		lblFavoritos.setBounds(10, 11, 86, 14);
		panel.add(lblFavoritos);

		bsalir = new JButton("");
		bsalir.setToolTipText("Salir");
		bsalir.setBorderPainted(false);
		bsalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});

		bsalir.setForeground(Color.DARK_GRAY);
		bsalir.setIcon(new ImageIcon(Principal.class.getResource("/recursos/close3.png")));
		bsalir.setBounds(443, -3, 16, 20);
		panel.add(bsalir);

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
		buttonmin.setBounds(423, -3, 16, 20);
		panel.add(buttonmin);
	}
}
