import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ReporteGeneral extends JFrame implements ActionListener {
    private JButton btnGenerar;

    public ReporteGeneral() {
        setLayout(null);
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Reporte General");
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
        setVisible(true);
    }

    public void iniciarComponentes() {
        JLabel lblTitulo = new JLabel("Generar Reporte General");
        lblTitulo.setBounds(50, 50, 200, 25);
        add(lblTitulo);

        btnGenerar = new JButton("Generar Reporte");
        btnGenerar.setBounds(225, 300, 150, 25);
        btnGenerar.addActionListener(this);
        add(btnGenerar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGenerar) {
            generarReporteGeneral();
        }
    }

    private void generarReporteGeneral() {
        List<String> responsables = new ArrayList<>();
        List<Integer> totalEquiposList = new ArrayList<>();
        List<Double> totalCostoList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("inventario.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("#");
                if (partes.length == 6) {  // Ajustamos a 6 partes en lugar de 7
                    String descripcion = partes[0];
                    int cantidad = Integer.parseInt(partes[1]);
                    double costoUnitario = Double.parseDouble(partes[2]);
                    String fecha = partes[3];
                    String numeroFactura = partes[4];
                    String responsable = partes[5].split(";")[0];  // Aseguramos que se divida correctamente y se elimine el ';'

                    if (!responsables.contains(responsable)) {
                        responsables.add(responsable);
                        totalEquiposList.add(0);
                        totalCostoList.add(0.0);
                    }

                    int index = responsables.indexOf(responsable);
                    totalEquiposList.set(index, totalEquiposList.get(index) + cantidad);
                    totalCostoList.set(index, totalCostoList.get(index) + (cantidad * costoUnitario));
                }
            }

            StringBuilder reporteFinal = new StringBuilder();
            for (int i = 0; i < responsables.size(); i++) {
                reporteFinal.append("Responsable: ").append(responsables.get(i))
                            .append("\nTotal Equipos: ").append(totalEquiposList.get(i))
                            .append("\nTotal Costo (Bs.): ").append(totalCostoList.get(i))
                            .append("\n\n");
            }

            JTextArea textArea = new JTextArea(reporteFinal.toString());
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Reporte General", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ReporteGeneral();
    }
}
