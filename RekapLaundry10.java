import java.util.Locale;
import java.util.Scanner;

public class RekapLaundry10 {
    static String rupiah(int nilai) {
        return "Rp" + String.format(Locale.forLanguageTag("id-ID"), "%,d", nilai);
    }

    static int[][] inputData(Scanner sc, String[] layanan, String[] hari) {
        int[][] data = new int[layanan.length][hari.length];
        for (int i = 0; i < layanan.length; i++) {
            System.out.println("\nLayanan: " + layanan[i]);
            for (int j = 0; j < hari.length; j++) {
                System.out.print("  Berat cucian hari " + hari[j] + " (kg): ");
                data[i][j] = sc.nextInt();
            }
        }
        return data;
    }

    static void tampilTabel(String[] layanan, int[] harga, String[] hari, int[][] data) {
        System.out.println("\n===== REKAP BERAT CUCIAN (kg) =====");
        System.out.printf("%-14s %-10s", "Layanan", "Harga/kg");
        for (String h : hari) {
            System.out.printf("%8s", h);
        }
        System.out.println();

        for (int i = 0; i < data.length; i++) {
            System.out.printf("%-14s %-10s", layanan[i], rupiah(harga[i]));
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf("%8d", data[i][j]);
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
            System.out.printf("%-14s : %d kg x %s = %s%n",
                    layanan[i], totalPerLayanan(data, i), rupiah(harga[i]), rupiah(pendapatan));
        }
        System.out.println("Total pendapatan seluruh layanan: " + rupiah(totalPendapatan));
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("id-ID"));
        Scanner sc = new Scanner(System.in);

        String[] layanan = {"Cuci Kering", "Cuci Setrika", "Setrika Saja", "Express"};
        int[] harga = {6000, 8000, 5000, 12000};
        String[] hari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};

        int[][] data = inputData(sc, layanan, hari);

        tampilTabel(layanan, harga, hari, data);

        System.out.println();
        for (int i = 0; i < layanan.length; i++) {
            System.out.println("Total " + layanan[i] + " selama " + hari.length + " hari: "
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
