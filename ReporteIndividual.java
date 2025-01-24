import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ReporteIndividual extends JFrame implements ActionListener {
    private JTextField txtResponsable;
    private JButton btnGenerar;

    public ReporteIndividual() {
        setLayout(null);
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Reporte Individual");
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
        setVisible(true);
    }

    public void iniciarComponentes() {
        JLabel lblResponsable = new JLabel("Ingrese la CI del Responsable:");
        lblResponsable.setBounds(50, 50, 200, 25);
        add(lblResponsable);

        txtResponsable = new JTextField();
        txtResponsable.setBounds(250, 50, 200, 25);
        add(txtResponsable);

        btnGenerar = new JButton("Generar Reporte");
        btnGenerar.setBounds(225, 300, 150, 25);
        btnGenerar.addActionListener(this);
        add(btnGenerar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGenerar) {
            generarReporte(txtResponsable.getText());
        }
    }

    private void generarReporte(String responsableBuscado) {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventario.txt"))) {
            String linea;
            int totalEquipos = 0;
            double totalCosto = 0.0;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("#");
                if (partes.length == 6) {  // Ajustamos a 6 partes en lugar de 7
                    String descripcion = partes[0];
                    int cantidad = Integer.parseInt(partes[1]);
                    double costoUnitario = Double.parseDouble(partes[2]);
                    String fecha = partes[3];
                    String numeroFactura = partes[4];
                    String responsable = partes[5].split(";")[0];  // Aseguramos que se divida correctamente y se elimine el ';'

                    if (responsable.equals(responsableBuscado)) {
                        totalEquipos += cantidad;
                        totalCosto += cantidad * costoUnitario;
                    }
                }
            }

            // Mostrar el reporte en un JTextArea
            String reporteTexto = "Responsable: " + responsableBuscado + "\n" +
                                  "Total Equipos: " + totalEquipos + "\n" +
                                  "Total Costo (Bs.): " + totalCosto;

            JTextArea textArea = new JTextArea(reporteTexto);
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Reporte Individual", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ReporteIndividual();
    }
}
