package grafica.ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import grafica.controladores.ControladorRegistrarTemporada;

public class RegistrarTemporada extends JFrame {

	private JPanel contentPane;
	private ControladorRegistrarTemporada miControlador;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarTemporada frame = new RegistrarTemporada();
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
	public RegistrarTemporada() {

		this.miControlador = new ControladorRegistrarTemporada(this);

		setEnabled(true);
		setResizable(false);
		setTitle("Registrar temporada");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 337, 308);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNroTemp = new JLabel("N\u00FAmero temporada:");
		lblNroTemp.setBounds(20, 11, 105, 14);
		contentPane.add(lblNroTemp);

		JLabel lblAnio = new JLabel("A\u00F1o");
		lblAnio.setBounds(20, 48, 105, 14);
		contentPane.add(lblAnio);

		JLabel lblCantCap = new JLabel("Cantidad cap\u00EDtulos:");
		lblCantCap.setBounds(20, 85, 105, 14);
		contentPane.add(lblCantCap);

		JSpinner spinnerNumero = new JSpinner();
		spinnerNumero.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		spinnerNumero.setBounds(135, 11, 86, 20);
		JFormattedTextField txt = ((JSpinner.NumberEditor) spinnerNumero.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		contentPane.add(spinnerNumero);

		JSpinner spinnerAnio = new JSpinner();
		spinnerAnio.setModel(new SpinnerNumberModel(2000, 2000, 2020, 1));
		spinnerAnio.setBounds(135, 45, 86, 20);
		JFormattedTextField txtDos = ((JSpinner.NumberEditor) spinnerAnio.getEditor()).getTextField();
		((NumberFormatter) txtDos.getFormatter()).setAllowsInvalid(false);
		contentPane.add(spinnerAnio);

		JSpinner spinnerCapitulos = new JSpinner();
		spinnerCapitulos.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		spinnerCapitulos.setBounds(135, 85, 86, 20);
		JFormattedTextField txtTres = ((JSpinner.NumberEditor) spinnerCapitulos.getEditor()).getTextField();
		((NumberFormatter) txtTres.getFormatter()).setAllowsInvalid(false);
		contentPane.add(spinnerCapitulos);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				miControlador.registrarTemporada((Integer) spinnerNumero.getValue(), (Integer) spinnerAnio.getValue(),
						(Integer) spinnerCapitulos.getValue());
			}
		});
		btnIngresar.setBounds(93, 154, 147, 23);
		contentPane.add(btnIngresar);

	}

	public void mensajeError(String e, boolean exit) {
		int input = 0;
		if (exit == false) {
			input = JOptionPane.showOptionDialog(null, e, "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE,
					null, null, null);
		} else {
			input = JOptionPane.showOptionDialog(null, e, "Correcto", JOptionPane.PLAIN_MESSAGE,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
		}

		if (input == JOptionPane.OK_OPTION && exit) {
			this.dispose();
		}

	}
}
