import javax.swing.*;
import java.awt.event.*;

public class SeleccionReporte extends JFrame implements ActionListener {
    private JButton btnReporteIndividual, btnReporteGeneral;

    public SeleccionReporte() {
        setLayout(null);
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Seleccionar Reporte");
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
        setVisible(true);
    }

    public void iniciarComponentes() {
        btnReporteIndividual = new JButton("Reporte Individual");
        btnReporteIndividual.setBounds(50, 50, 200, 25);
        btnReporteIndividual.addActionListener(this);

        btnReporteGeneral = new JButton("Reporte General");
        btnReporteGeneral.setBounds(50, 100, 200, 25);
        btnReporteGeneral.addActionListener(this);

        add(btnReporteIndividual);
        add(btnReporteGeneral);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReporteIndividual) {
            new ReporteIndividual();
        } else if (e.getSource() == btnReporteGeneral) {
            new ReporteGeneral();
        }
        dispose();
    }
}
