class TasKulit extends TasParent {
    String jenisKulit;

    // Constructor
    TasKulit(String merk, double harga, String warna, String ukuran, String tipe, String jenisKulit) {
        super(merk, harga, warna, ukuran, tipe);
        this.jenisKulit = jenisKulit;
    }

    // Override method infoTas
    @Override
    public void infoTas() {
        super.infoTas();
        System.out.println("Jenis Kulit: " + jenisKulit);
    }
}