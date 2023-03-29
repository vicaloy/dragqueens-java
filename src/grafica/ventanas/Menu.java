package grafica.ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setVisible(boolean b) {
		super.setVisible(b);
	}

	/**
	 * Create the frame.
	 */
	public Menu() {

		setResizable(false);
		setTitle("Inicio");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 237);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.setVisible(true);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		mnMenu.setVisible(true);

		// MENU REGISTRAR
		JMenu mnRegistrar = new JMenu("Registrar..");
		mnMenu.add(mnRegistrar);

		JMenuItem mntmRegistrarTemporada = new JMenuItem("Registrar temporada");
		mntmRegistrarTemporada.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrarTemporada registrarTemp = new RegistrarTemporada();

			}
		});
		mnRegistrar.add(mntmRegistrarTemporada);

		JMenuItem mntmRegistrarDragQueen = new JMenuItem("Registrar DragQueen");
		mntmRegistrarDragQueen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarDragQueen registrarDrag = new RegistrarDragQueen();

			}
		});
		mnRegistrar.add(mntmRegistrarDragQueen);

		JMenuItem mntmRegistrarVictoria = new JMenuItem("Registrar victoria");
		mntmRegistrarVictoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarVictoria regVictoria = new RegistrarVictoria();
			}
		});
		mnRegistrar.add(mntmRegistrarVictoria);

		// MENU LISTAR
		JMenu mnListar = new JMenu("Listar..");
		mnMenu.add(mnListar);

		JMenuItem mntmTemporadas = new JMenuItem("Listar temporadas");
		mntmTemporadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarTemporadas listarTemp = new ListarTemporadas();
			}
		});
		mnListar.add(mntmTemporadas);
		
		JMenuItem mntmListarDragqueens = new JMenuItem("Listar DragQueens");
		mntmListarDragqueens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarDragQueens drag = new ListarDragQueens();
			}
		});
		mnListar.add(mntmListarDragqueens);
		

		JMenuItem mntmListarTempMasParticipante = new JMenuItem("Listar temporada más participantes");
		mntmListarTempMasParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarTemporadaMaxPart max = new ListarTemporadaMaxPart();
			}
		});
		mnListar.add(mntmListarTempMasParticipante);

		JMenuItem mntmObtenerGanadora = new JMenuItem("Obtener ganadora");
		mntmObtenerGanadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObtenerGanadora ganadora = new ObtenerGanadora();
			}
		});
		mnListar.add(mntmObtenerGanadora);

		JSeparator separator = new JSeparator();
		mnMenu.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnMenu.add(mntmSalir);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}

	public void mensajeError(String e, boolean ok) {

		if (ok == false) {
			JOptionPane.showOptionDialog(null, e, "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null,
					null, null);
		} else {
			JOptionPane.showOptionDialog(null, e, "Correcto", JOptionPane.PLAIN_MESSAGE,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
		}

	}
}