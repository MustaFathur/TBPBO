class TasKain extends TasParent {
    String jenisKain;

    // Constructor
    TasKain(String merk, double harga, String warna, String ukuran, String tipe, String jenisKain) {
        super(merk, harga, warna, ukuran, tipe);
        this.jenisKain = jenisKain;
    }

    // Override method infoTas
    @Override
    public void infoTas() {
        super.infoTas();
        System.out.println("Jenis Kain: " + jenisKain);
    }
}