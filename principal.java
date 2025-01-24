import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class principal extends JFrame implements ActionListener {
    private JTextField txtDescripcion, txtCantidad, txtCosto, txtFecha, txtFactura, txtResponsable;
    private JButton btnRegistrar, btnSalir, btnReporte;

    public principal() {
        setLayout(null);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Registro y Control de Equipos en Centro de Investigación");
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
        setVisible(true);
    }

    public void iniciarComponentes() {
        colocarEtiquetas();
        colocarCajaDeTexto();
        colocarBotones();
    }

    public void colocarEtiquetas() {
        JLabel lblTitulo = new JLabel("Ingrese la descripción del equipo:");
        lblTitulo.setBounds(50, 10, 200, 25);
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(50, 30, 150, 25);
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(50, 70, 150, 25);
        JLabel lblCosto = new JLabel("Costo unitario (Bs.):");
        lblCosto.setBounds(50, 110, 150, 25);
        JLabel lblFecha = new JLabel("Fecha de adquisición:");
        lblFecha.setBounds(50, 150, 150, 25);
        JLabel lblFactura = new JLabel("Nro. de Factura:");
        lblFactura.setBounds(50, 190, 150, 25);
        JLabel lblResponsable = new JLabel("C.I. del Responsable del equipo:");
        lblResponsable.setBounds(50, 230, 200, 25);

        add(lblTitulo);
        add(lblDescripcion);
        add(lblCantidad);
        add(lblCosto);
        add(lblFecha);
        add(lblFactura);
        add(lblResponsable);
    }

    public void colocarBotones() {
        btnRegistrar = new JButton("Registrar data");
        btnRegistrar.setBounds(50, 280, 150, 25);
        btnRegistrar.addActionListener(this);

        btnReporte = new JButton("Generar Reporte");
        btnReporte.setBounds(225, 280, 150, 25);
        btnReporte.addActionListener(this);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(400, 280, 100, 25);
        btnSalir.addActionListener(this);

        add(btnRegistrar);
        add(btnReporte);
        add(btnSalir);
    }

    public void colocarCajaDeTexto() {
        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(250, 30, 200, 25);
        txtCantidad = new JTextField();
        txtCantidad.setBounds(250, 70, 200, 25);
        txtCosto = new JTextField();
        txtCosto.setBounds(250, 110, 200, 25);
        txtFecha = new JTextField();
        txtFecha.setBounds(250, 150, 200, 25);
        txtFactura = new JTextField();
        txtFactura.setBounds(250, 190, 200, 25);
        txtResponsable = new JTextField();
        txtResponsable.setBounds(250, 230, 200, 25);

        add(txtDescripcion);
        add(txtCantidad);
        add(txtCosto);
        add(txtFecha);
        add(txtFactura);
        add(txtResponsable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalir) {
            System.exit(0);
        } else if (e.getSource() == btnRegistrar) {
            registrarData();
        } else if (e.getSource() == btnReporte) {
            new SeleccionReporte();
        }
    }

    private void registrarData() {
        String descripcion = txtDescripcion.getText();
        String cantidad = txtCantidad.getText();
        String costo = txtCosto.getText();
        String fecha = txtFecha.getText();
        String factura = txtFactura.getText();
        String responsable = txtResponsable.getText();

        String data = descripcion + "#" + cantidad + "#" + costo + "#" + fecha + "#" + factura + "#" + responsable + ";";

        try (PrintWriter writer = new PrintWriter(new FileWriter("inventario.txt", true))) {
            writer.println(data);
            JOptionPane.showMessageDialog(this, "Datos registrados correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        new principal();
    }
}

