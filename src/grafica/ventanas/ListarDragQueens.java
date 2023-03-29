package grafica.ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import grafica.controladores.ControladorListadoDragQueens;
import grafica.controladores.ModeloDatosDragQueens;
import logica.valueObjects.VODragQueenVictorias;

public class ListarDragQueens extends javax.swing.JFrame {

	private ModeloDatosDragQueens modeloTabla;
	private ControladorListadoDragQueens miControlador;

	public ListarDragQueens() {
		getContentPane().setBackground(new Color(248, 248, 255));
		this.miControlador = new ControladorListadoDragQueens(this);
		initComponents();
	}

	private void setModeloTabla(ArrayList<VODragQueenVictorias> array) {
		modeloTabla = new ModeloDatosDragQueens();
		modeloTabla.setDragQueens(array);
		this.tablePart.setModel(modeloTabla);
		this.tablePart.getColumnModel().getColumn(2);
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		setResizable(false);
		setTitle("Listado de DragQueens");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		jScrollPane1 = new javax.swing.JScrollPane();
		tablePart = new javax.swing.JTable();

		tablePart.setModel(new javax.swing.table.DefaultTableModel(null,
				new String[] { "Nombre", "Nro temporada", "Nro participante", "Victorias" }));
		jScrollPane1.setViewportView(tablePart);

		JSpinner spinnerTemp = new JSpinner();

		JLabel lblTemp = new JLabel("Temporada: ");

		JButton btnListar = new JButton("Listar");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(18).addComponent(lblTemp).addGap(18)
										.addComponent(spinnerTemp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(28).addComponent(btnListar))
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1,
										GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(spinnerTemp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTemp).addComponent(btnListar))
						.addGap(52)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(107, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		pack();
		setLocationRelativeTo(null);
		
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<VODragQueenVictorias> listado = miControlador.listarDragQueens((Integer) spinnerTemp.getValue());
				if (listado != null && listado.size() > 0)
					setModeloTabla(listado);
			}
		});

		
	}

	public void mensajeError(String e) {

		JOptionPane.showOptionDialog(null, e, "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, null,
				null);

	}

	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ListarDragQueens().setVisible(true);
			}
		});
	}

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tablePart;
}
