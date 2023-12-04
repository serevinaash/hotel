import java.util.Scanner;

class Admin {
    String key;
    String value;

    public Admin(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

class MenuHotel {
    public void menuHotel() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
			System.out.println("==========================");
			System.out.println("== WELCOME TO OUR HOTEL ==");
			System.out.println("==========================");
			System.out.println("=== HALAMAN MENU HOTEL ===");
            System.out.println("1. Kelola Kamar Hotel");
            System.out.println("2. Tambah Informasi Penyewa");
            System.out.println("3. Check In");
            System.out.println("4. Check Out");
            System.out.println("5. Cari Transaksi");
            System.out.println("6. Statistik Hotel");
            System.out.println("0. Keluar");
			System.out.println("==========================");
            System.out.print("Pilih menu (0-6): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    kelolakamar();
                    break;
                case 2:
                    tambahInfoPenyewa();
                    break;
                case 3:
                    checkin();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    caritransaksi();
                    break;
                case 6:
                    statistikhotel();
                    break;
                case 0:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        } while (choice != 0);
    }

    public void kelolakamar() {
        // Implementasi tambah kamar dan tampilkan kamar
        // Implementasi logika untuk menambah kamar dan tampilkan kamar
    }

    public void tambahInfoPenyewa() {
        // Implementasi tambah informasi penyewa
        // Implementasi logika untuk menambah informasi penyewa
    }

    public void checkin() {
        // Implementasi check in
        // TODO: Implementasi logika untuk checkin
    }

    public void checkout() {
        // Implementasi check out
        // TODO: Implementasi logika untuk checkout
    }

    public void caritransaksi() {
        // Implementasi cari transaksi
        // Implementasi logika untuk cari transaksi
    }

    public void statistikhotel() {
        // Implementasi check-in
        // Implementasi logika untuk statistikhotel
    }
}

public class HotelManagement {
    private static final int TABLE_SIZE = 100;
    private static Admin[] hashTable = new Admin[TABLE_SIZE];
    private static int userCount = 0;

    public static void main(String[] args) {
        HotelManagement hotel = new HotelManagement();
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("==========================");
        System.out.println("== WELCOME TO OUR HOTEL ==");
        System.out.println("==========================");
        System.out.println("====== HALAMAN LOGIN =====");
        System.out.println("1. Sign Up");
        System.out.println("2. Log In");
        System.out.println("3. Exit");
        System.out.println("==========================");

        do {
            System.out.print("Pilih Menu: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    SistemLogin.signUp(scanner, hashTable);
                    break;
                case 2:
                    SistemLogin.logIn(scanner, hashTable);
                    MenuHotel menuHotel = new MenuHotel();
                    menuHotel.menuHotel();
                    break;
                case 0:
                    System.out.println("TERIMAKASIH TELAH MENGGUNAKAN PROGRAM INI.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 3);
    }

    public static void setUserCount(int count) {
        userCount = count;
    }

    public static int getUserCount() {
        return userCount;
    }
}

class SistemLogin {
    private static final int TABLE_SIZE = 100;

    public static void signUp(Scanner scanner, Admin[] hashTable) {
        System.out.print("Enter Nama Lengkap: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        int hash = hashFunction(username);

        // Linear probing
        while (hashTable[hash] != null) {
            hash = (hash + 1) % TABLE_SIZE;
        }
        hashTable[hash] = new Admin(username, password);
        HotelManagement.setUserCount(HotelManagement.getUserCount() + 1);
        System.out.println("Registration successful!");
        System.out.println("Admin ID: " + hash);
        System.out.println("==========================");
    }

    public static void logIn(Scanner scanner, Admin[] hashTable) {
        System.out.print("Enter Admin ID: ");
        String adminIdInput = scanner.nextLine();
        int adminId = Integer.parseInt(adminIdInput);

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Linear probing to find the user in the hash table
        while (hashTable[adminId] != null) {
            if (hashTable[adminId].value.equals(password)) {
                System.out.println("Login successful!");
                System.out.println("==========================");
                return;
            }
            adminId = (adminId + 1) % TABLE_SIZE;
        }

        System.out.println("Incorrect Admin ID or password. Login failed.");
    }

    private static int hashFunction(String key) {
        return key.length() % TABLE_SIZE;
    }
}
