import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    // Method untuk generate CAPTCHA
    static String generateCaptcha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }

        return captcha.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Login
        System.out.println("Selamat datang! Silakan login.");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Verifikasi login (contoh sederhana)
        if (!username.equals("user123") || !password.equals("pass123")) {
            System.out.println("Login gagal. Program berhenti.");
            return;
        }

        System.out.println("Login berhasil!");

        // CAPTCHA
        String captcha = generateCaptcha();
        System.out.println("Silakan masukkan CAPTCHA berikut: " + captcha);
        System.out.print("Masukkan CAPTCHA: ");
        String userCaptcha = scanner.nextLine();

        // Verifikasi CAPTCHA
        if (!userCaptcha.equals(captcha)) {
            System.out.println("CAPTCHA salah. Program berhenti.");
            return;
        }

        System.out.println("CAPTCHA benar!");

        int pilihan;
        List<TasParent> semuaTas = TasJDBC.tampilkanSemuaTas();
        do {
            // Menampilkan menu
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Data Tas");
            System.out.println("2. Tampilkan Semua Tas");
            System.out.println("3. Hapus Data Tas");
            System.out.println("4. Edit Data Tas");
            System.out.println("5. Pesan Tas");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu (0-5): ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline dari buffer

            switch (pilihan) {
                case 1:
                    // Tambah Data Tas
                    System.out.print("Masukkan merk tas: ");
                    String merkBaru = scanner.nextLine();
                    System.out.print("Masukkan harga tas: ");
                    double hargaBaru = scanner.nextDouble();
                    System.out.print("Masukkan warna tas: ");
                    String warnaBaru = scanner.next();
                    System.out.print("Masukkan ukuran tas: ");
                    String ukuranBaru = scanner.next();
                    System.out.print("Masukkan tipe tas: ");
                    String tipeBaru = scanner.next();
                    scanner.nextLine();

                    System.out.println("Pilih jenis tas:");
                    System.out.println("1. Tas Kain");
                    System.out.println("2. Tas Kulit");
                    System.out.print("Pilihan (1/2): ");
                    int jenisTas = scanner.nextInt();

                    if (jenisTas == 1) {
                        System.out.print("Masukkan jenis kain tas: ");
                        String jenisKainBaru = scanner.next();
                        TasKain tasKainBaru = new TasKain(merkBaru, hargaBaru, warnaBaru, ukuranBaru, tipeBaru,
                                jenisKainBaru);
                        TasJDBC.tambahTas(tasKainBaru);
                    } else if (jenisTas == 2) {
                        System.out.print("Masukkan jenis kulit tas: ");
                        String jenisKulitBaru = scanner.next();
                        TasKulit tasKulitBaru = new TasKulit(merkBaru, hargaBaru, warnaBaru, ukuranBaru, tipeBaru,
                                jenisKulitBaru);
                        TasJDBC.tambahTas(tasKulitBaru);
                    } else {
                        TasParent tasBaru = new TasParent(merkBaru, hargaBaru, warnaBaru, ukuranBaru, tipeBaru);
                        TasJDBC.tambahTas(tasBaru);
                    }
                    break;

                case 2:
                    semuaTas = TasJDBC.tampilkanSemuaTas(); // Perbarui list semuaTas

                    System.out.println("Semua tas dalam database:");

                    // Tampilkan tabel tas kain
                    System.out.println("\nTas Kain:");
                    System.out.println(
                            "+------------------+------------------+------------------+------------------+------------------+------------------+");
                    System.out.format("| %-16s | %-16s | %-16s | %-16s | %-16s | %-16s |\n", "Merk", "Harga", "Warna",
                            "Ukuran", "Tipe", "Jenis Kain");
                    System.out.println(
                            "+------------------+------------------+------------------+------------------+------------------+------------------+");

                    for (TasParent tas : semuaTas) {
                        if (tas instanceof TasKain) {
                            TasKain tasKain = (TasKain) tas;
                            System.out.format("| %-16s | %-16.2f | %-16s | %-16s | %-16s | %-16s |\n", tasKain.merk,
                                    tasKain.harga, tasKain.warna,
                                    tasKain.ukuran, tasKain.tipe, tasKain.jenisKain);
                        }
                    }
                    System.out.println(
                            "+------------------+------------------+------------------+------------------+------------------+------------------+");
                    // Tampilkan tabel tas kulit
                    System.out.println("\nTas Kulit:");
                    System.out.println(
                            "+------------------+------------------+------------------+------------------+------------------+------------------+");
                    System.out.format("| %-16s | %-16s | %-16s | %-16s | %-16s | %-16s |\n", "Merk", "Harga", "Warna",
                            "Ukuran", "Tipe", "Jenis Kulit");
                    System.out.println(
                            "+------------------+------------------+------------------+------------------+------------------+------------------+");

                    for (TasParent tas : semuaTas) {
                        if (tas instanceof TasKulit) {
                            TasKulit tasKulit = (TasKulit) tas;
                            System.out.format("| %-16s | %-16.2f | %-16s | %-16s | %-16s | %-16s |\n", tasKulit.merk,
                                    tasKulit.harga, tasKulit.warna,
                                    tasKulit.ukuran, tasKulit.tipe, tasKulit.jenisKulit);
                        }
                    }
                    System.out.println(
                            "+------------------+------------------+------------------+------------------+------------------+------------------+");

                    break;
                case 3:
                    // Hapus Data Tas
                    System.out.print("Masukkan merk tas yang ingin dihapus: ");
                    String merkHapus = scanner.nextLine();
                    TasJDBC.hapusTas(merkHapus);
                    break;

                case 4:
                    // Edit Data Tas
                    System.out.print("Masukkan merk tas yang ingin diedit: ");
                    String merkEdit = scanner.nextLine();

                    // Mencari tas yang sesuai dengan merk
                    TasParent tasEdit = null;
                    for (TasParent tas : semuaTas) {
                        if (tas.merk.equalsIgnoreCase(merkEdit)) {
                            tasEdit = tas;
                            break;
                        }
                    }

                    if (tasEdit != null) {
                        System.out.println("Data lama tas:");
                        tasEdit.infoTas();

                        // Input data baru
                        System.out.print("Masukkan merk baru: ");
                        String merkBaruEdit = scanner.nextLine();
                        System.out.print("Masukkan harga baru: ");
                        double hargaBaruEdit = scanner.nextDouble(); // Perbaikan pada nama variabel
                        System.out.print("Masukkan warna baru: ");
                        String warnaBaruEdit = scanner.next(); // Perbaikan pada nama variabel
                        System.out.print("Masukkan ukuran baru: ");
                        String ukuranBaruEdit = scanner.next(); // Perbaikan pada nama variabel
                        System.out.print("Masukkan tipe baru: ");
                        String tipeBaruEdit = scanner.next(); // Perbaikan pada nama variabel
                        scanner.nextLine();

                        if (tasEdit instanceof TasKain) {
                            System.out.print("Masukkan jenis kain baru: ");
                            String jenisKainBaruEdit = scanner.next(); // Perbaikan pada nama variabel
                            tasEdit = new TasKain(merkBaruEdit, hargaBaruEdit, warnaBaruEdit, ukuranBaruEdit,
                                    tipeBaruEdit,
                                    jenisKainBaruEdit);
                        } else if (tasEdit instanceof TasKulit) {
                            System.out.print("Masukkan jenis kulit baru: ");
                            String jenisKulitBaruEdit = scanner.next(); // Perbaikan pada nama variabel
                            tasEdit = new TasKulit(merkBaruEdit, hargaBaruEdit, warnaBaruEdit, ukuranBaruEdit,
                                    tipeBaruEdit,
                                    jenisKulitBaruEdit);
                        } else {
                            tasEdit = new TasParent(merkBaruEdit, hargaBaruEdit, warnaBaruEdit, ukuranBaruEdit,
                                    tipeBaruEdit);
                        }

                        // Update data di database
                        TasJDBC.updateTas(merkEdit, tasEdit);

                        System.out.println("Data tas berhasil diupdate.");
                    } else {
                        System.out.println("Tas dengan merk " + merkEdit + " tidak ditemukan.");
                    }
                    break;

                case 5:
                    // Pesan Tas
                    System.out.print("Masukkan merk tas yang ingin dibeli: ");
                    String merkTasPesan = scanner.nextLine();
                    System.out.print("Masukkan nominal uang: ");
                    double nominalUang = scanner.nextDouble();

                    // Mencari tas yang sesuai dengan merk
                    TasParent tasPesan = null;
                    for (TasParent tas : semuaTas) {
                        if (tas.merk.equalsIgnoreCase(merkTasPesan)) {
                            tasPesan = tas;
                            break;
                        }
                    }

                    if (tasPesan != null) {
                        if (nominalUang >= tasPesan.harga) {
                            double kembalian = nominalUang - tasPesan.harga;
                            System.out.println("Pembelian berhasil! Kembalian Anda: " + kembalian + " Rupiah.");
                        } else {
                            System.out.println("Uang Anda tidak mencukupi untuk membeli tas ini.");
                        }
                    } else {
                        System.out.println("Tas dengan merk " + merkTasPesan + " tidak ditemukan.");
                    }
                    break;

                case 0:
                    // Keluar
                    System.out.println("Program selesai. Sampai jumpa!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
            }

        } while (pilihan != 0);

    }
};