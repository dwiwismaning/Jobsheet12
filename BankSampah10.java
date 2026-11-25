import java.util.Locale;
import java.util.Scanner;

public class BankSampah10 {
    static final double HARGA_PER_KG = 3000;
    static final Scanner sc = new Scanner(System.in).useLocale(Locale.US); 

    static String rupiah(double nilai) {
        return "Rp" + String.format(Locale.forLanguageTag("id-ID"), "%,.0f", nilai);
    }

    static double[] inputSetoran(int n) {
        double[] setoran = new double[n];
        for (int i = 0; i < n; i++) {
            do {
                System.out.print("Berat setoran nasabah ke-" + (i + 1) + " (kg): ");
                setoran[i] = sc.nextDouble();
                if (setoran[i] < 0) {
                    System.out.println("Berat tidak boleh negatif.");
                }
            } while (setoran[i] < 0);
        }
        return setoran;
    }

    static void tampilSetoran(double[] setoran) {
        System.out.println("\n===== DATA SETORAN =====");
        for (int i = 0; i < setoran.length; i++) {
            System.out.printf("Nasabah ke-%d : %.2f kg%n", (i + 1), setoran[i]);
        }
    }

    static double totalSetoran(double[] setoran) {
        double total = 0;
        for (double berat : setoran) {
            total += berat;
        }
        return total;
    }

    static double hitungSaldo(double beratKg, double hargaPerKg) {
        return beratKg * hargaPerKg;
    }

    static int nasabahTerbanyak(double[] setoran) {
        int idxMax = 0;
        for (int i = 1; i < setoran.length; i++) {
            if (setoran[i] > setoran[idxMax]) {
                idxMax = i;
            }
        }
        return idxMax;
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); 

        int n;
        do {
            System.out.print("Masukkan jumlah nasabah (N): ");
            n = sc.nextInt();
            if (n <= 0) {
                System.out.println("Jumlah nasabah harus lebih dari 0.");
            }
        } while (n <= 0);

        double[] setoran = inputSetoran(n);

        tampilSetoran(setoran);

        System.out.println("\n===== SALDO NASABAH (" + rupiah(HARGA_PER_KG) + " per kg) =====");
        for (int i = 0; i < setoran.length; i++) {
            System.out.printf("Nasabah ke-%d : %s%n", (i + 1), rupiah(hitungSaldo(setoran[i], HARGA_PER_KG)));
        }

        System.out.printf("%nTotal berat setoran seluruh nasabah : %.2f kg%n", totalSetoran(setoran));

        int idx = nasabahTerbanyak(setoran);
        System.out.printf("Nasabah dengan setoran terbanyak     : nasabah ke-%d (%.2f kg)%n", (idx + 1), setoran[idx]);
    }
}
