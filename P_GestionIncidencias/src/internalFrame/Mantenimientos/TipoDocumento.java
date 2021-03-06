package internalFrame.Mantenimientos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Controlador.TipoDocumentoController;
import Entidades.BE_TipoDocumento;
import Utilitarios.General;

import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;

public class TipoDocumento extends JInternalFrame implements ActionListener {
	TipoDocumentoController TipoDocumentoController = new TipoDocumentoController();
	static TipoDocumento instance = null;

	public static TipoDocumento getInstance() {
		if (instance == null) {
			instance = new TipoDocumento();
		}
		return instance;
	}

	private JRadioButton rdbTodos;
	private JLabel lblEstado, lblCodigo, lblDescripcion, lblAbreviacion,
			lblBCodigo;
	private JTextField txtDescripcion, txtAbreviacion,
			txtBusquedaCodigo;
	private JComboBox cboEstado;
	private JButton btnRegistrar, btnEditar, btnEliminar, btnLimpiar,
			btnVolver, btnSalir;
	private DefaultTableModel modelo;
	private JTabbedPane tabbedPane;
	private JPanel panelListado, panelMantenimiento, panelFiltro;
	private JScrollPane scrollPane;
	private JTable table;
	private String estado;

	// EndRegion
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Area frame = new Area();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TipoDocumento() {
		setFrameIcon(new ImageIcon(
				TipoDocumento.class
						.getResource("/imagenesx16/documento_16.png")));
		setClosable(true);
		setTitle("Tipo Documento");
		setBounds(100, 100, 1300, 404);
		getContentPane().setLayout(null);

		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 1158, 372);
			getContentPane().add(tabbedPane);

			{
				panelListado = new JPanel();
				tabbedPane.addTab("Listado", null, panelListado, null);
				panelListado.setLayout(null);
				{
					scrollPane = new JScrollPane();
					scrollPane.setBounds(12, 73, 1129, 203);
					panelListado.add(scrollPane);
					{
						table = new JTable();
						// table.setAutoCreateRowSorter(true);
						table.getTableHeader().setToolTipText(
								"Click para alinear");
						table.setRowSelectionAllowed(false);
						table.setModel(modelo = new DefaultTableModel(
								new Object[][] {},
								new String[] { "Codigo", "Descripción",
										"Abreviación", "Estado" }));

						table.getColumnModel().getColumn(0).setMinWidth(50);
						table.getColumnModel().getColumn(0).setMaxWidth(50);
						table.getColumnModel().getColumn(3).setMinWidth(0);
						table.getColumnModel().getColumn(3).setMaxWidth(0);
						// table.getColumnModel().getColumn(9).setMinWidth(0);
						// table.getColumnModel().getColumn(9).setMaxWidth(0);
						table.getTableHeader().setReorderingAllowed(false);
						table.getTableHeader().setResizingAllowed(false);
						scrollPane.setViewportView(table);

					}
				}
				{
					panelFiltro = new JPanel();
					panelFiltro.setBounds(12, 12, 272, 57);
					panelFiltro.setBorder(BorderFactory
							.createTitledBorder("Filtro de búsqueda"));
					panelListado.add(panelFiltro);
					panelFiltro.setLayout(null);
					{
						lblBCodigo = new JLabel("Código:");
						lblBCodigo.setBounds(12, 32, 70, 15);
						panelFiltro.add(lblBCodigo);
					}
					{
						txtBusquedaCodigo = new JTextField();
						txtBusquedaCodigo.setBounds(84, 25, 105, 24);
						panelFiltro.add(txtBusquedaCodigo);
						txtBusquedaCodigo.setColumns(10);
						txtBusquedaCodigo.addActionListener(action);
						txtBusquedaCodigo.getDocument().addDocumentListener(
								new DocumentListener() {

									@Override
									public void insertUpdate(DocumentEvent e) {
										// TODO Auto-generated method stub
										if (rdbTodos.isSelected()
												|| !rdbTodos.isSelected()) {
											rdbTodos.setEnabled(true);
											rdbTodos.setSelected(false);
										}
									}

									@Override
									public void removeUpdate(DocumentEvent e) {
										// TODO Auto-generated method stub
										if (txtBusquedaCodigo.getText()
												.isEmpty())
											rdbTodos.setEnabled(true);
									}

									@Override
									public void changedUpdate(DocumentEvent e) {
										// TODO Auto-generated method stub

									}

								});
						{
							rdbTodos = new JRadioButton("Todos");
							rdbTodos.addActionListener(this);
							rdbTodos.setBounds(197, 28, 70, 23);
							panelFiltro.add(rdbTodos);

						}
					}
					{
						panelMantenimiento = new JPanel();
						tabbedPane.addTab("Mantenimiento", null,
								panelMantenimiento, null);
						tabbedPane.setEnabledAt(1, false);
						panelMantenimiento.setLayout(null);
						{
							lblCodigo = new JLabel("Código:");
							lblCodigo.setBounds(12, 12, 60, 20);
							panelMantenimiento.add(lblCodigo);
						}
						{
							lblDescripcion = new JLabel("Descripción:");
							lblDescripcion.setBounds(12, 42, 92, 20);
							panelMantenimiento.add(lblDescripcion);
						}
						{
							txtDescripcion = new JTextField();
							txtDescripcion.setColumns(10);
							txtDescripcion.setBounds(110, 40, 168, 24);
							panelMantenimiento.add(txtDescripcion);
						}
						{
							lblAbreviacion = new JLabel("Abreviacion:");
							lblAbreviacion.setBounds(12, 72, 87, 20);
							panelMantenimiento.add(lblAbreviacion);
						}
						{
							txtAbreviacion = new JTextField();
							txtAbreviacion.setColumns(10);
							txtAbreviacion.setBounds(110, 70, 201, 24);
							panelMantenimiento.add(txtAbreviacion);
						}
						{
							lblEstado = new JLabel("Estado:");
							lblEstado.setBounds(12, 102, 60, 20);
							panelMantenimiento.add(lblEstado);
						}
						{
							cboEstado = new JComboBox();
							cboEstado.setModel(new DefaultComboBoxModel(
									new String[] { "Activo", "No Activo" }));
							cboEstado.setBounds(79, 100, 155, 24);
							panelMantenimiento.add(cboEstado);
						}
						{
							txtCodigo = new JLabel("");
							txtCodigo.setBounds(110, 12, 46, 20);
							txtCodigo.setBorder(LineBorder.createGrayLineBorder());
							txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
							panelMantenimiento.add(txtCodigo);
						}
					}
				}
				{
					btnRegistrar = new JButton("Registrar");
					btnRegistrar.addActionListener(this);
					btnRegistrar.setBounds(1170, 12, 108, 47);
					getContentPane().add(btnRegistrar);
				}
				{
					btnEditar = new JButton("Editar");
					btnEditar.addActionListener(this);
					btnEditar.setBounds(1170, 65, 108, 47);
					getContentPane().add(btnEditar);
				}
				{
					btnEliminar = new JButton("Eliminar");
					btnEliminar.addActionListener(this);
					btnEliminar.setBounds(1170, 118, 108, 47);
					getContentPane().add(btnEliminar);
				}
				{
					btnLimpiar = new JButton("Limpiar");
					btnLimpiar.setEnabled(false);
					btnLimpiar.addActionListener(this);
					btnLimpiar.setBounds(1170, 171, 108, 47);
					getContentPane().add(btnLimpiar);
				}
				{
					btnVolver = new JButton("Volver");
					btnVolver.setEnabled(false);
					btnVolver.addActionListener(this);
					btnVolver.setBounds(1170, 224, 108, 47);
					getContentPane().add(btnVolver);
				}
				{
					btnSalir = new JButton("Salir");
					btnSalir.addActionListener(this);
					btnSalir.setBounds(1170, 277, 108, 47);
					getContentPane().add(btnSalir);
				}

			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rdbTodos) {
			do_rdbTodos_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnEditar) {
			do_btnEditar_actionPerformed(e);
		}
		if (e.getSource() == btnVolver) {
			do_btnVolver_actionPerformed(e);
		}
		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
		if (e.getSource() == btnLimpiar) {
			do_btnLimpiar_actionPerformed(e);
		}
		if (e.getSource() == btnRegistrar) {
			do_btnRegistrar_actionPerformed(e);
		}
	}

	private void do_btnVolver_actionPerformed(ActionEvent e) {
		General.limpiar(panelMantenimiento);
		habiltarBotones(true);
		General.changePanel(tabbedPane, false);
	}

	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		if (tabbedPane.getSelectedIndex() == 0) {
			General.changePanel(tabbedPane, true);
			habiltarBotones(true);
			txtCodigo.setText("" + TipoDocumentoController.generarCodigo());
			txtDescripcion.requestFocus();
			estado = "Guardar";
		} else if (tabbedPane.getSelectedIndex() == 1) {
			if (estado == "Guardar") {
				if(leerDescripcion().trim().isEmpty())
				{
					txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.RED,1));
					txtDescripcion.requestFocus();
					return;
				}else
					txtDescripcion.setBorder(LineBorder.createGrayLineBorder());
				BE_TipoDocumento a = new BE_TipoDocumento();
				setDataTipoDocumento(a);
				addRowInserted(a);
				TipoDocumentoController.agregarTipoDocumento(a);
				TipoDocumentoController.guardarTipoDocumento(a);
				General.changePanel(tabbedPane, false);
				habiltarBotones(true);
				General.limpiar(panelMantenimiento);
				estado = "";
			} else if (estado == "Editar") {
				int codigo = leerCodigo();
				if (TipoDocumentoController.isTDocumentoReferencedToUsuario(codigo) && cboEstado.getSelectedIndex() ==1) {
					JOptionPane.showMessageDialog(null, "El Tipo de Documento está referenciado a un usuario. Por lo tanto, usted no puede cambiar su estado"); 
					cboEstado.setSelectedIndex(0);
					return;
				}
				BE_TipoDocumento a = TipoDocumentoController
						.buscarTipoDocumentoxCodigo(codigo);
				int pos = TipoDocumentoController.getPosTipoDocumento(a);
				setDataTipoDocumento(a);
				TipoDocumentoController.modificarTipoDocumento(pos, a);
				TipoDocumentoController.guardarTipoDocumento();
				editRowSelected(a);
				General.changePanel(tabbedPane, false);
				habiltarBotones(true);
				General.limpiar(panelMantenimiento);
				estado = "";
			}
		}
	}

	private void editRowSelected(BE_TipoDocumento a) {
		int row = table.getSelectedRow();
		table.setValueAt(a.getCodigo(), row, 0);
		table.setValueAt(a.getDescripcion(), row, 1);
		table.setValueAt(a.getAbreviacion(), row, 2);
		table.setValueAt(a.getEstado(), row, 3);
	}

	protected void do_btnLimpiar_actionPerformed(ActionEvent e) {
		General.limpiar(panelMantenimiento);
	}

	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		try {
			int rowcount = table.getModel().getRowCount(), rowSelected = table
					.getSelectedRow();
			if (General.validateJTableisEmpty(rowcount, rowSelected))
				return;
			else {
				if (JOptionPane.showConfirmDialog(null, "Desea eliminar el registro","Adveretencia", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					BE_TipoDocumento a = new BE_TipoDocumento();
					int codigo = (int) table.getValueAt(rowSelected, 0);
					if (TipoDocumentoController.isTDocumentoReferencedToUsuario(codigo)) {
						JOptionPane.showMessageDialog(null, "El Tipo de Documento está referenciado a un usuario. Por lo tanto, usted no puede cambiar su estado"); 
						return;
					}
					a = TipoDocumentoController.buscarTipoDocumentoxCodigo(codigo);
					TipoDocumentoController.eliminarTipoDocumento(a);
					TipoDocumentoController.eliminarTipoDocumento(codigo);
					modelo.removeRow(rowSelected);
				} else
					return;
			}

		} catch (Exception e2) {
			// TODO: handle exception
		}

	}

	protected void do_btnEditar_actionPerformed(ActionEvent e) {
		estado = "Editar";
		int rowSelected = table.getSelectedRow(), rowCount = table.getModel()
				.getRowCount();
		if (General.validateJTableisEmpty(rowCount, rowSelected)) {
			return;
		} else {
			General.changePanel(tabbedPane, true);
			habiltarBotones(true);
			carryDataToControls(rowSelected);
			txtDescripcion.requestFocus();
		}
	}

	protected void do_rdbTodos_actionPerformed(ActionEvent e) {
		if (rdbTodos.isSelected()) {
			if (TipoDocumentoController.size() == 0) {
				JOptionPane.showMessageDialog(null, "No hay data");
				return;
			} else {
				fillTable();
				return;
			}

		}
	}

	Action action = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			modelo.setRowCount(0);
			if (txtBusquedaCodigo.getText().isEmpty()) {
				fillTable();
				return;
			} else {
				int codigo = Integer.parseInt(txtBusquedaCodigo.getText());
				BE_TipoDocumento a = new BE_TipoDocumento();
				a = TipoDocumentoController.buscarTipoDocumentoxCodigo(codigo);
				Object[] data = new Object[] { a.getCodigo(),
						a.getDescripcion(), a.getAbreviacion(), a.getEstado() };
				modelo.addRow(data);
			}
		}
	};
	private JLabel txtCodigo;

	protected void habiltarBotones(boolean estado) {
		JButton[] botones = { btnLimpiar, btnEditar, btnEliminar, btnVolver };
		General.habilitar(botones, estado);
	}

	// Region JTable()
	void addRowInserted(BE_TipoDocumento a) {
		Object[] data = new Object[] { a.getCodigo(), a.getDescripcion(),
				a.getAbreviacion(), a.getEstado() };
		modelo.addRow(data);
	}

	void carryDataToControls(int r) {
		txtCodigo.setText("" + modelo.getValueAt(r, 0));
		txtDescripcion.setText("" + modelo.getValueAt(r, 1));
		txtAbreviacion.setText("" + modelo.getValueAt(r, 2));
		cboEstado.setSelectedIndex((int) modelo.getValueAt(r, 3));
	}

	void fillTable() {
		modelo.setRowCount(0);
		for (int i = 0; i < TipoDocumentoController.size(); i++) {
			BE_TipoDocumento a = TipoDocumentoController.getTipoDocumento(i);
			Object[] data = new Object[] { a.getCodigo(), a.getDescripcion(),
					a.getAbreviacion(), a.getEstado() };
			modelo.addRow(data);
		}
	}

	// EndRegion
	// Region Lectura de controles
	public int leerCodigo() {
		return Integer.parseInt(txtCodigo.getText());
	}

	public String leerDescripcion() {
		return txtDescripcion.getText();
	}

	public String leerAbreviacion() {
		return txtAbreviacion.getText();
	}

	public int leerEstado() {
		return cboEstado.getSelectedIndex();
	}

	void setDataTipoDocumento(BE_TipoDocumento t) {
		t.setCodigo(Integer.parseInt(txtCodigo.getText()));
		t.setDescripcion(txtDescripcion.getText());
		t.setAbreviacion(txtAbreviacion.getText());
		t.setEstado(cboEstado.getSelectedIndex());

	}

	// EndRegion
}
