package grafica.ventanas;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import grafica.controladores.ControladorListadoTemporadas;
import grafica.controladores.ModeloDatosTemporadas;
import logica.valueObjects.VOTemporada;

public class ListarTemporadas extends javax.swing.JFrame {

	private ModeloDatosTemporadas modeloTabla;
	private ControladorListadoTemporadas miControlador;

	public ListarTemporadas() {
		getContentPane().setBackground(new Color(248, 248, 255));
		this.miControlador = new ControladorListadoTemporadas(this);
		initComponents();
	}

	private void setModeloTabla(ArrayList<VOTemporada> array) {
		modeloTabla = new ModeloDatosTemporadas();
		modeloTabla.setTemporadas(array);
		this.tablePart.setModel(modeloTabla);
		this.tablePart.getColumnModel().getColumn(2);
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		setResizable(false);
		setTitle("Listado de temporadas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		jScrollPane1 = new javax.swing.JScrollPane();
		tablePart = new javax.swing.JTable();

		tablePart.setModel(new javax.swing.table.DefaultTableModel(null,
				new String[] { "Nro temporada", "Año", "Cant capítulos" }));
		jScrollPane1.setViewportView(tablePart);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(24)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		getContentPane().setLayout(layout);

		pack();
		setLocationRelativeTo(null);

		ArrayList<VOTemporada> listado = miControlador.listarTemporadas();
		if (listado != null && listado.size() > 0)
			setModeloTabla(listado);
	}

	public void mensajeError(String e) {

		JOptionPane.showOptionDialog(null, e, "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, null,
				null);

	}

	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ListarTemporadas().setVisible(true);
			}
		});
	}

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tablePart;
}
