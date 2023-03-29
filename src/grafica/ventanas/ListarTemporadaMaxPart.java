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

import grafica.controladores.ControladorListadoTemporadasMaxPart;
import grafica.controladores.ModeloDatosTemporadaMaxPart;
import logica.valueObjects.VOTempMaxParts;

public class ListarTemporadaMaxPart extends javax.swing.JFrame {

	private ModeloDatosTemporadaMaxPart modeloTabla;
	private ControladorListadoTemporadasMaxPart miControlador;

	public ListarTemporadaMaxPart() {
		getContentPane().setBackground(new Color(248, 248, 255));
		this.miControlador = new ControladorListadoTemporadasMaxPart(this);
		initComponents();
	}

	private void setModeloTabla(ArrayList<VOTempMaxParts> array) {
		modeloTabla = new ModeloDatosTemporadaMaxPart();
		modeloTabla.setTemporadas(array);
		this.tablePart.setModel(modeloTabla);
		this.tablePart.getColumnModel().getColumn(2);
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		setResizable(false);
		setTitle("Listado de temporada con más participantes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		jScrollPane1 = new javax.swing.JScrollPane();
		tablePart = new javax.swing.JTable();

		tablePart.setModel(new javax.swing.table.DefaultTableModel(null,
				new String[] { "Nro temporada", "Año", "Cant capítulos", "Cant participantes" }));
		jScrollPane1.setViewportView(tablePart);

		

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1,
										GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(Alignment.BASELINE))
						.addGap(52)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(107, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		pack();
		setLocationRelativeTo(null);

		VOTempMaxParts temp = miControlador.listarTemporadas();
		ArrayList<VOTempMaxParts> listado = new ArrayList<VOTempMaxParts>();
		if (temp != null) {
			listado.add(temp);
			setModeloTabla(listado);
		}

	}

	public void mensajeError(String e) {

		JOptionPane.showOptionDialog(null, e, "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, null,
				null);

	}

	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ListarTemporadaMaxPart().setVisible(true);
			}
		});
	}

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tablePart;
}
