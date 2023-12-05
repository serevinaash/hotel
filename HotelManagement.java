import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Admin {
    String key;
    String value;

    public Admin(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

class Room {
     ArrayList<CheckInInfo> checkIns = new ArrayList<>();
    int roomNumber;
    String roomType;
    boolean isOccupied;
    String[] facilities;
    double roomPrice; // Harga kamar
    double fine; // Denda

    // Atribut tambahan untuk informasi penyewa
    String guestName;
    String occupation;
    String phoneNumber;
    String nationalId;
    String homeAddress;
    String emailAddress;

    // Atribut untuk informasi check-in
    String transactionNumber;
    int durationInDays;
    java.util.Date checkInDateTime; // Menggunakan java.util.Date
    java.util.Date checkOutDateTime; // Menggunakan java.util.Date

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isOccupied = false;
        this.facilities = getFacilitiesByType(roomType);
        setRoomAttributes(roomType);
    }

    private void setRoomAttributes(String roomType) {
        switch (roomType.toLowerCase()) {
            case "presiden":
                roomPrice = 9000000;
                fine = roomPrice * 0.1;
                break;
            case "vip":
                roomPrice = 5000000;
                fine = roomPrice * 0.1;
                break;
            case "reguler":
                roomPrice = 1000000;
                fine = roomPrice * 0.1;
                break;
            default:
                roomPrice = 0;
                fine = 0;
        }
    }

    public void displayRoomInfo() {
        System.out.println("Nomor Kamar: " + roomNumber);
        System.out.println("Tipe Kamar: " + roomType);
        System.out.println("Status: " + (isOccupied ? "Terisi" : "Tersedia"));
        System.out.println("Harga: " + formatCurrency(roomPrice));
        System.out.println("Denda: " + formatCurrency(fine));
        System.out.println("Fasilitas Kamar:");
        for (String facility : facilities) {
            System.out.println("- " + facility);
        }
    }

    public void displayGuestInfo() {
        System.out.println("Informasi Penyewa:");
        System.out.println("Nama Tamu: " + guestName);
        System.out.println("Pekerjaan: " + occupation);
        System.out.println("Nomor Telepon: " + phoneNumber);
        System.out.println("NIK: " + nationalId);
        System.out.println("Alamat Asal: " + homeAddress);
        System.out.println("Alamat Email: " + emailAddress);
    }

        public void displayCheckinInfo() {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Informasi Check-in:");
        for (CheckInInfo checkInInfo : checkIns) {
            System.out.println("Nomor Transaksi: " + checkInInfo.transactionNumber);
            System.out.println("Durasi Menginap (dalam sehari): " + checkInInfo.durationInDays);
            System.out.println("Tanggal dan Waktu Check-in: " + dateFormat.format(checkInInfo.checkInDateTime));
            System.out.println("Tanggal Checkout: " + dateFormat.format(checkInInfo.checkOutDateTime));
        }
    }

    public void addCheckInInfo(CheckInInfo checkInInfo) {
        checkIns.add(checkInInfo);
    }

    private class CheckInInfo {
        String transactionNumber;
        int durationInDays;
        java.util.Date checkInDateTime; // Menggunakan java.util.Date
        java.util.Date checkOutDateTime; // Menggunakan java.util.Date

        public CheckInInfo(String transactionNumber, int durationInDays, java.util.Date checkInDateTime,
                           String guestName, String occupation, String phoneNumber,
                           String nationalId, String homeAddress, String emailAddress,
                           java.util.Date checkOutDateTime) {

            this.checkOutDateTime = checkOutDateTime;
        }
    }

    private String formatCurrency(double amount) {
        String currencySymbol = "Rp";
        return currencySymbol + amount;
    }

    private String[] getFacilitiesByType(String roomType) {
        if ("presiden".equalsIgnoreCase(roomType)) {
            return new String[]{"Private Jacuzzi", "Luxury Furniture", "Personal Butler"};
        } else if ("vip".equalsIgnoreCase(roomType)) {
            return new String[]{"Balcony", "Mini Bar", "King Size Bed"};
        } else if ("reguler".equalsIgnoreCase(roomType)) {
            return new String[]{"TV", "Bathroom", "Standard Furniture"};
        } else {
            return new String[0];
        }
    }
}

class MenuHotel {
    Room[] daftarKamar = new Room[1000];

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
        Scanner scanner = new Scanner(System.in);
        int pilih;
        do {
            System.out.println("=== HALAMAN MENU KELOLA KAMAR ===");
            System.out.println("1. Tambah Kamar Baru");
            System.out.println("2. Lihat Data Kamar");
            System.out.println("0. Keluar");
            System.out.println("==========================");
            System.out.print("Pilih menu (0-2): ");
            pilih = scanner.nextInt();
            scanner.nextLine();
            switch (pilih) {
                case 1:
                    tambahKamarBaru();
                    break;
                case 2:
                    lihatDataKamar();
                    break;
                case 0:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        } while (pilih != 0);
    }

    public void tambahKamarBaru() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan Nomor Kamar: ");
        int roomNumber = scanner.nextInt();

        if (daftarKamar[roomNumber] != null) {
            System.out.println("Nomor Kamar sudah terpakai. Pilih nomor kamar lain.");
            return;
        }

        scanner.nextLine();

        System.out.println("Pilih Tipe Kamar:");
        System.out.println("1. Presiden");
        System.out.println("2. VIP");
        System.out.println("3. Reguler");
        System.out.print("Masukkan pilihan (1-3): ");
        int roomTypeChoice = scanner.nextInt();
        scanner.nextLine();

        String roomType;
        switch (roomTypeChoice) {
            case 1:
                roomType = "Presiden";
                break;
            case 2:
                roomType = "VIP";
                break;
            case 3:
                roomType = "Reguler";
                break;
            default:
                System.out.println("Pilihan tidak valid. Menggunakan tipe kamar default.");
                roomType = "Reguler";
        }

        Room newRoom = new Room(roomNumber, roomType);
        newRoom.isOccupied = true;
        daftarKamar[roomNumber] = newRoom;

        System.out.println("Kamar berhasil ditambahkan. Berikut informasinya:");
        newRoom.displayRoomInfo();
        System.out.println("==========================");
    }

    public void lihatDataKamar() {
        System.out.println("Data kamar tersedia: ");
        for (Room room : daftarKamar) {
            if (room != null) {
                room.displayRoomInfo();
                System.out.println("==========================");
            }
        }
    }

    public void tambahInfoPenyewa() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan Nomor Kamar yang Ingin Ditambahkan Informasi Penyewa: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        if (daftarKamar[roomNumber] == null || !daftarKamar[roomNumber].isOccupied) {
            System.out.println("Kamar tidak terisi atau tidak tersedia.");
            return;
        }

        System.out.print("Nama Tamu: ");
        daftarKamar[roomNumber].guestName = scanner.nextLine();
        System.out.print("Pekerjaan: ");
        daftarKamar[roomNumber].occupation = scanner.nextLine();
        System.out.print("Nomor Telepon: ");
        daftarKamar[roomNumber].phoneNumber = scanner.nextLine();
        System.out.print("NIK: ");
        daftarKamar[roomNumber].nationalId = scanner.nextLine();
        System.out.print("Alamat Asal: ");
        daftarKamar[roomNumber].homeAddress = scanner.nextLine();
        System.out.print("Alamat Email: ");
        daftarKamar[roomNumber].emailAddress = scanner.nextLine();

        System.out.println("Informasi penyewa berhasil ditambahkan.");
        System.out.println("Berikut informasinya:");
        daftarKamar[roomNumber].displayRoomInfo();
        daftarKamar[roomNumber].displayGuestInfo();
        System.out.println("==========================");
    }

    public void checkin() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Masukkan Nomor Transaksi: ");
    int transactionNumber = 0;
    while (!scanner.hasNextInt()) {
        System.out.println("Masukkan nomor transaksi yang valid: ");
        scanner.next(); // Membersihkan buffer
    }
    transactionNumber = scanner.nextInt();

    System.out.print("Masukkan Nomor Kamar: ");
    int roomNumber = 0;
    while (!scanner.hasNextInt()) {
        System.out.println("Masukkan nomor kamar yang valid: ");
        scanner.next(); // Membersihkan buffer
    }
    roomNumber = scanner.nextInt();

    if (roomNumber >= 0 && roomNumber < daftarKamar.length) {
        Room room = daftarKamar[roomNumber];

        if (room != null && !room.isOccupied()) {
            room.setOccupied(true);
            // Memperoleh informasi pengguna lainnya di sini

            System.out.print("Metode Pembayaran: ");
            String paymentMethod = scanner.next();
            
            System.out.print("Masukkan Tanggal Check-in (dd/mm/yyyy): ");
            String checkInDateStr = scanner.next();
            // Validasi format tanggal di sini menggunakan SimpleDateFormat
            
            System.out.println("Check-in berhasil.");
        } else {
            System.out.println("Nomor Kamar tidak ditemukan atau sudah ditempati.");
        }
    } else {
        System.out.println("Nomor Kamar tidak valid.");
    }
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
    private static MenuHotel menuHotel = new MenuHotel();

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

            try {
                switch (choice) {
                    case 1:
                        SistemLogin.signUp(scanner, hashTable);
                        break;
                    case 2:
                        SistemLogin.logIn(scanner, hashTable);
                        menuHotel.menuHotel();
                        break;
                    case 0:
                        System.out.println("TERIMAKASIH TELAH MENGGUNAKAN PROGRAM INI.");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
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
        int maxAttempts = 3;  // Define the maximum number of login attempts
        int attempts = 0;

        do {
            System.out.print("Enter Admin ID: ");
            String adminIdInput = scanner.nextLine();

            // Mengubah input Admin ID menjadi integer
            int adminId = Integer.parseInt(adminIdInput);

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Linear probing untuk menemukan admin di hashTable
            while (hashTable[adminId] != null) {
                if (hashTable[adminId].value.equals(password)) {
                    System.out.println("Login successful!");
                    System.out.println("==========================");
                    return;  // Exit the method and proceed to the menu
                }
                adminId = (adminId + 1) % TABLE_SIZE;
            }

            attempts++;
            System.out.println("Incorrect Admin ID or password. Login attempt " + attempts + " of " + maxAttempts);
            System.out.println("==========================");

        } while (attempts < maxAttempts);

        System.out.println("Exceeded maximum login attempts. Exiting program.");
        System.exit(0);  // Terminate the program after exceeding maximum login attempts
    }

    private static int hashFunction(String key) {
        return key.length() % TABLE_SIZE;
}
}
