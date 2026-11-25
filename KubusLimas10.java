import java.util.Scanner;

public class KubusLimas10 {
    static int luasPersegi(int s) {
        return s * s;
    }

    static int volumeKubus(int s) {
        return s * s * s;
    }

    static int luasPermukaanKubus(int s) {
        return 6 * luasPersegi(s);
    }

    static double volumeLimasPersegi(int s, int t) {
        return (double) luasPersegi(s) * t / 3.0;
    }

    static int bacaPositif(Scanner sc, String label) {
        int nilai;
        do {
            System.out.print("Masukkan " + label + " (bilangan bulat > 0): ");
            nilai = sc.nextInt();
            if (nilai <= 0) {
                System.out.println("Nilai harus lebih dari 0.");
            }
        } while (nilai <= 0);
        return nilai;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n===== MENU KUBUS & LIMAS =====");
            System.out.println("1. Luas Persegi");
            System.out.println("2. Volume Kubus");
            System.out.println("3. Luas Permukaan Kubus");
            System.out.println("4. Volume Limas Persegi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = sc.nextInt();

            switch (pilihan) {
                case 1: {
                    int s = bacaPositif(sc, "sisi");
                    System.out.println("Luas persegi = " + luasPersegi(s));
                    break;
                }
                case 2: {
                    int s = bacaPositif(sc, "sisi");
                    System.out.println("Volume kubus = " + volumeKubus(s));
                    break;
                }
                case 3: {
                    int s = bacaPositif(sc, "sisi");
                    System.out.println("Luas permukaan kubus = " + luasPermukaanKubus(s));
                    break;
                }
                case 4: {
                    int s = bacaPositif(sc, "sisi alas");
                    int t = bacaPositif(sc, "tinggi");
                    System.out.printf("Volume limas persegi = %.2f%n", volumeLimasPersegi(s, t));
                    break;
                }
                case 0:
                    System.out.println("Terima kasih, program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);

        sc.close();
    }
}
