class TasParent implements Tas {
    String merk;
    double harga;
    String warna;
    String ukuran;
    String tipe;

    // Constructor
    TasParent(String merk, double harga, String warna, String ukuran, String tipe) {
        this.merk = merk;
        this.harga = harga;
        this.warna = warna;
        this.ukuran = ukuran;
        this.tipe = tipe;
    }

    // Implementasi method interface
    public void infoTas() {
        System.out.println("Ini adalah tas merek " + merk + " dengan harga " + harga + " Rupiah.");
        System.out.println("Warna: " + warna);
        System.out.println("Ukuran: " + ukuran);
        System.out.println("Tipe: " + tipe);
    }
}