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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import grafica.controladores.ControladorRegistrarDragQueen;

public class RegistrarDragQueen extends JFrame {

	private JPanel contentPane;
	private ControladorRegistrarDragQueen miControlador;
	private JTextField textNombre;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarDragQueen frame = new RegistrarDragQueen();
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
	public RegistrarDragQueen() {

		this.miControlador = new ControladorRegistrarDragQueen(this);

		setEnabled(true);
		setResizable(false);
		setTitle("Registrar DragQueen");
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

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 48, 105, 14);
		contentPane.add(lblNombre);

		JSpinner spinnerNumero = new JSpinner();
		spinnerNumero.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerNumero.setBounds(135, 11, 48, 20);
		JFormattedTextField txt = ((JSpinner.NumberEditor) spinnerNumero.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		contentPane.add(spinnerNumero);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				miControlador.registrarDragQueen((Integer) spinnerNumero.getValue(), textNombre.getText());
			}
		});
		btnIngresar.setBounds(93, 154, 147, 23);
		contentPane.add(btnIngresar);

		textNombre = new JTextField();
		textNombre.setBounds(135, 45, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

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
