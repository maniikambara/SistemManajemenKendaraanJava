import javax.swing.*;
import java.awt.*;

abstract class Kendaraan {
    protected String noPlat;
    protected String statusParkir;

    public Kendaraan(String noPlat) {
        this.noPlat = noPlat;
        this.statusParkir = "keluar";
    }

    public abstract void dimasukkan();
    public abstract void dikeluarkan();

    public String InnoPlat() {
        return noPlat;
    }

    public String InstatusParkir() {
        return statusParkir;
    }
}

class Mobil extends Kendaraan {
    public Mobil(String noPlat) {
        super(noPlat);
    }

    @Override
    public void dimasukkan() {
        statusParkir = "masuk";
    }

    @Override
    public void dikeluarkan() {
        statusParkir = "keluar";
    }
}

class Motor extends Kendaraan {
    public Motor(String noPlat) {
        super(noPlat);
    }

    @Override
    public void dimasukkan() {
        statusParkir = "masuk";
    }

    @Override
    public void dikeluarkan() {
        statusParkir = "keluar";
    }
}

public class SistemManajemenKendaraan extends JFrame {

    private JTextField inputnoPlat;
    private JTextArea areaOutput;
    private Kendaraan kendaraanSaatIni;

    public SistemManajemenKendaraan() {
        setTitle("Sistem Manajemen Kendaraan dari Kelompok 4 Kelas E");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelInput = new JPanel(new GridLayout(3, 2));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Kendaraan"));

        JLabel labelnoPlat = new JLabel("Nomor Plat:");
        inputnoPlat = new JTextField();

        JLabel labelTipeKendaraan = new JLabel("Tipe Kendaraan:");
        String[] tipeKendaraan = {"Mobil", "Motor"};
        JComboBox<String> comboTipeKendaraan = new JComboBox<>(tipeKendaraan);

        JButton tombolBuatKendaraan = new JButton("Buat Kendaraan");

        panelInput.add(labelnoPlat);
        panelInput.add(inputnoPlat);
        panelInput.add(labelTipeKendaraan);
        panelInput.add(comboTipeKendaraan);
        panelInput.add(tombolBuatKendaraan);

        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        areaOutput.setBorder(BorderFactory.createTitledBorder("Output"));
        JScrollPane scrollOutput = new JScrollPane(areaOutput);

        JPanel panelAksi = new JPanel(new FlowLayout());
        JButton tombolDimasukkan = new JButton("Masukkan");
        JButton tombolDikeluarkan = new JButton("Keluarkan");
        panelAksi.add(tombolDimasukkan);
        panelAksi.add(tombolDikeluarkan);

        add(panelInput, BorderLayout.NORTH);
        add(scrollOutput, BorderLayout.CENTER);
        add(panelAksi, BorderLayout.SOUTH);

        tombolBuatKendaraan.addActionListener(e -> {
            String noPlat = inputnoPlat.getText();
            String tipeYangDipilih = (String) comboTipeKendaraan.getSelectedItem();

            if (noPlat.isEmpty()) {
                areaOutput.setText("Harap masukkan nomor plat kendaraan.");
                return;
            }

            if (tipeYangDipilih.equals("Mobil")) {
                kendaraanSaatIni = new Mobil(noPlat);
                areaOutput.setText("Mobil dengan no. plat " + noPlat + " dibuat.");
            } else if (tipeYangDipilih.equals("Motor")) {
                kendaraanSaatIni = new Motor(noPlat);
                areaOutput.setText("Motor dengan no. plat " + noPlat + " dibuat.");
            }
        });

        tombolDimasukkan.addActionListener(e -> {
            if (kendaraanSaatIni != null) {
                kendaraanSaatIni.dimasukkan();
                areaOutput.append("\nKendaraan " + kendaraanSaatIni.getClass().getSimpleName() + " dengan no. plat " +
                        kendaraanSaatIni.InnoPlat() + " dimasukkan ke dalam parkir.");
            } else {
                areaOutput.setText("Harap buat kendaraan terlebih dahulu.");
            }
        });

        tombolDikeluarkan.addActionListener(e -> {
            if (kendaraanSaatIni != null) {
                kendaraanSaatIni.dikeluarkan();
                areaOutput.append("\nKendaraan " + kendaraanSaatIni.getClass().getSimpleName() + " dengan no. plat " +
                        kendaraanSaatIni.InnoPlat() + " dikeluarkan dari parkir.");
            } else {
                areaOutput.setText("Harap buat kendaraan terlebih dahulu.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SistemManajemenKendaraan().setVisible(true);
        });
    }
}