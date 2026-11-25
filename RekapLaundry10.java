import java.util.Locale;
import java.util.Scanner;

public class RekapLaundry10 {
    static String rupiah(int nilai) {
        return "Rp" + String.format(Locale.forLanguageTag("id-ID"), "%,d", nilai);
    }

    static int bacaInt(Scanner sc, String pesan, int min) {
        while (true) {
            System.out.print(pesan);
            String teks = sc.nextLine().trim();
            try {
                int nilai = Integer.parseInt(teks);
                if (nilai >= min) {
                    return nilai;
                }
                System.out.println("  Nilai minimal " + min + ".");
            } catch (NumberFormatException e) {
                System.out.println("  Masukkan bilangan bulat.");
            }
        }
    }

    static String[] buatNamaHari(int jumlahHari) {
        String[] hari = new String[jumlahHari];
        for (int j = 0; j < jumlahHari; j++) {
            hari[j] = "Hari ke-" + (j + 1);
        }
        return hari;
    }

    static void inputLayanan(Scanner sc, String[] layanan, int[] harga) {
        for (int i = 0; i < layanan.length; i++) {
            System.out.print("Nama layanan ke-" + (i + 1) + ": ");
            layanan[i] = sc.nextLine().trim();
            harga[i] = bacaInt(sc, "Harga per kg layanan ke-" + (i + 1) + " (Rp): ", 1);
        }
    }

    static int[][] inputData(Scanner sc, String[] layanan, String[] hari) {
        int[][] data = new int[layanan.length][hari.length];
        for (int i = 0; i < layanan.length; i++) {
            System.out.println("\nLayanan: " + layanan[i]);
            for (int j = 0; j < hari.length; j++) {
                data[i][j] = bacaInt(sc, "  Berat cucian " + hari[j] + " (kg): ", 0);
            }
        }
        return data;
    }

    static void tampilTabel(String[] layanan, int[] harga, String[] hari, int[][] data) {
        System.out.println("\n===== REKAP BERAT CUCIAN (kg) =====");
        System.out.printf("%-16s %-10s", "Layanan", "Harga/kg");
        for (String h : hari) {
            System.out.printf("%11s", h);
        }
        System.out.println();

        for (int i = 0; i < data.length; i++) {
            System.out.printf("%-16s %-10s", layanan[i], rupiah(harga[i]));
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf("%11d", data[i][j]);
            }
            System.out.println();
        }
    }

    static int totalPerLayanan(int[][] data, int idxLayanan) {
        int total = 0;
        for (int j = 0; j < data[idxLayanan].length; j++) {
            total += data[idxLayanan][j];
        }
        return total;
    }

    static int totalPerHari(int[][] data, int idxHari) {
        int total = 0;
        for (int i = 0; i < data.length; i++) {
            total += data[i][idxHari];
        }
        return total;
    }

    static void tampilLayananTertinggi(String[] layanan, int[][] data) {
        int idxMax = 0;
        for (int i = 1; i < data.length; i++) {
            if (totalPerLayanan(data, i) > totalPerLayanan(data, idxMax)) {
                idxMax = i;
            }
        }
        System.out.println("\nLayanan dengan total berat cucian tertinggi: "
                + layanan[idxMax] + " (" + totalPerLayanan(data, idxMax) + " kg)");
    }

    static int hariTersibuk(int[][] data) {
        int idxMax = 0;
        for (int j = 1; j < data[0].length; j++) {
            if (totalPerHari(data, j) > totalPerHari(data, idxMax)) {
                idxMax = j;
            }
        }
        return idxMax;
    }

    static void tampilPendapatan(String[] layanan, int[] harga, int[][] data) {
        System.out.println("\n===== PENDAPATAN PER LAYANAN =====");
        int totalPendapatan = 0;
        for (int i = 0; i < data.length; i++) {
            int pendapatan = totalPerLayanan(data, i) * harga[i];
            totalPendapatan += pendapatan;
            System.out.printf("%-16s : %d kg x %s = %s%n",
                    layanan[i], totalPerLayanan(data, i), rupiah(harga[i]), rupiah(pendapatan));
        }
        System.out.println("Total pendapatan seluruh layanan: " + rupiah(totalPendapatan));
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("id-ID"));
        Scanner sc = new Scanner(System.in);

        int jumlahLayanan = bacaInt(sc, "Masukkan jumlah layanan: ", 1);
        int jumlahHari = bacaInt(sc, "Masukkan jumlah hari: ", 1);

        String[] layanan = new String[jumlahLayanan];
        int[] harga = new int[jumlahLayanan];
        String[] hari = buatNamaHari(jumlahHari);

        inputLayanan(sc, layanan, harga);
        int[][] data = inputData(sc, layanan, hari);

        tampilTabel(layanan, harga, hari, data);

        System.out.println();
        for (int i = 0; i < layanan.length; i++) {
            System.out.println("Total " + layanan[i] + " selama " + jumlahHari + " hari: "
                    + totalPerLayanan(data, i) + " kg");
        }

        tampilLayananTertinggi(layanan, data);

        int idxHari = hariTersibuk(data);
        System.out.println("Hari tersibuk: " + hari[idxHari]
                + " (" + totalPerHari(data, idxHari) + " kg)");

        tampilPendapatan(layanan, harga, data);
        sc.close();
    }
}
