import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class TasJDBC {
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/tas_fatur";
    static final String USERNAME = "root";
    static final String PASSWORD = "";

    // Method untuk membuat koneksi ke database
    static Connection buatKoneksi() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // Method untuk menambahkan tas ke database
    static void tambahTas(TasParent tas) {
        try (Connection connection = buatKoneksi();
                Statement statement = connection.createStatement()) {

            String query = "INSERT INTO tas (merk, harga, warna, ukuran, tipe, jenis_kain, jenis_kulit) " +
                    "VALUES ('" + tas.merk + "', " + tas.harga + ", '" + tas.warna + "', '" +
                    tas.ukuran + "', '" + tas.tipe + "', '"
                    + ((tas instanceof TasKain) ? ((TasKain) tas).jenisKain : "") +
                    "', '" + ((tas instanceof TasKulit) ? ((TasKulit) tas).jenisKulit : "") + "')";
            statement.executeUpdate(query);

            System.out.println("Data tas berhasil ditambahkan ke database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateTas(String merk, TasParent tasBaru) {
        try (Connection connection = buatKoneksi();
                Statement statement = connection.createStatement()) {

            String query = "UPDATE tas SET merk = '" + tasBaru.merk + "', harga = " + tasBaru.harga +
                    ", warna = '" + tasBaru.warna + "', ukuran = '" + tasBaru.ukuran +
                    "', tipe = '" + tasBaru.tipe + "', jenis_kain = '" +
                    ((tasBaru instanceof TasKain) ? ((TasKain) tasBaru).jenisKain : "") +
                    "', jenis_kulit = '" + ((tasBaru instanceof TasKulit) ? ((TasKulit) tasBaru).jenisKulit : "") +
                    "' WHERE merk = '" + merk + "'";
            statement.executeUpdate(query);

            System.out.println("Data tas berhasil diupdate di database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method untuk menampilkan semua tas dari database
    static List<TasParent> tampilkanSemuaTas() {
        List<TasParent> tasList = new ArrayList<>();

        try (Connection connection = buatKoneksi();
                Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM tas";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String merk = resultSet.getString("merk");
                double harga = resultSet.getDouble("harga");
                String warna = resultSet.getString("warna");
                String ukuran = resultSet.getString("ukuran");
                String tipe = resultSet.getString("tipe");
                String jenisKain = resultSet.getString("jenis_kain");
                String jenisKulit = resultSet.getString("jenis_kulit");
                
                if (jenisKain != null && !jenisKain.isEmpty()) {
                    tasList.add(new TasKain(merk, harga, warna, ukuran, tipe, jenisKain));
                } else if (jenisKulit != null && !jenisKulit.isEmpty()) {
                    tasList.add(new TasKulit(merk, harga, warna, ukuran, tipe, jenisKulit));
                } else {
                    tasList.add(new TasParent(merk, harga, warna, ukuran, tipe));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasList;
    }

    // Method untuk menghapus tas dari database
    static void hapusTas(String merk) {
        try (Connection connection = buatKoneksi();
                Statement statement = connection.createStatement()) {

            String query = "DELETE FROM tas WHERE merk = '" + merk + "'";
            statement.executeUpdate(query);

            System.out.println("Data tas berhasil dihapus dari database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}