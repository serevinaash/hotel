import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.NumberFormat;


class Admin {
    String key;
    String value;

    public Admin(String key, String value) {
        this.key = key;
        this.value = value;
    }
}


class Penyewa {
    String guestName;
    String occupation;
    String phoneNumber;
    String nationalId;
    String homeAddress;
    String emailAddress;
    int roomNumber;

    public Penyewa(String guestName, String occupation, String phoneNumber, String nationalId, String homeAddress, String emailAddress, int roomNumber) {
        this.guestName = guestName;
        this.occupation = occupation;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.homeAddress = homeAddress;
        this.emailAddress = emailAddress;
        this.roomNumber = roomNumber;
    }

    public void displayPenyewaInfo() {
        System.out.println("Informasi Penyewa:");
        System.out.println("Nama Tamu: " + guestName);
        System.out.println("Pekerjaan: " + occupation);
        System.out.println("Nomor Telepon: " + phoneNumber);
        System.out.println("NIK: " + nationalId);
        System.out.println("Alamat Asal: " + homeAddress);
        System.out.println("Alamat Email: " + emailAddress);
    }
}


class Room {
    Penyewa penyewa;
    int roomNumber;
    String roomType;
    boolean isOccupied;
    String[] facilities;
    double roomPrice; // Harga kamar
    double fine; // Denda
    CheckIn checkIn;

    String guestName;
    String occupation;
    String phoneNumber;
    String nationalId;
    String homeAddress;
    String emailAddress;

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
                roomPrice = 3000000;
                break;
            case "vip":
                roomPrice = 2000000;
                break;
            case "reguler":
                roomPrice = 1000000;
                break;
            default:
                roomPrice = 0;
        }
    }

    public void displayRoomInfo() {
        System.out.println("Nomor Kamar: " + roomNumber);
        System.out.println("Tipe Kamar: " + roomType);
        System.out.println("Status: " + (isOccupied ? "Terisi" : "Tersedia"));

        DecimalFormat currencyFormatter = new DecimalFormat("Rp#,##0.00");
        String formattedPrice = currencyFormatter.format(roomPrice);

        System.out.println("Harga: " + formattedPrice);

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

    public void assignCheckIn(CheckIn checkIn) {
        this.checkIn = checkIn;
    }

    public void assignPenyewa(Penyewa newPenyewa) {
        this.penyewa = newPenyewa;
        isOccupied = true;
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

class CheckIn {
    private static int nextTransactionNumber = 1;
    private int transactionNumber;
    private int roomNumber;
    private String paymentMethod;
    private int durationInDays;
    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;

    public CheckIn(int roomNumber, String paymentMethod, int durationInDays) {
        this.transactionNumber = nextTransactionNumber++;
        this.roomNumber = roomNumber;
        this.paymentMethod = paymentMethod;
        this.durationInDays = durationInDays;
        this.checkInDateTime = LocalDateTime.now();
        this.checkOutDateTime = this.checkInDateTime.plusDays(durationInDays);
    }

    public LocalDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public LocalDateTime getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getDurationInDays() {
        return durationInDays;
    }
}


class MenuHotel {
    Room[] daftarKamar = new Room[1000];
    Penyewa[] daftarPenyewa = new Penyewa[1000];

    private static final NumberFormat currencyFormatter = new DecimalFormat("#,##0.00");

    public static String formatCurrency(double amount) {
        String currencySymbol = "Rp";
        return currencySymbol + amount;
    }

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
        String guestName = scanner.nextLine();
        System.out.print("Pekerjaan: ");
        String occupation = scanner.nextLine();
        System.out.print("Nomor Telepon: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("NIK: ");
        String nationalId = scanner.nextLine();
        System.out.print("Alamat Asal: ");
        String homeAddress = scanner.nextLine();
        System.out.print("Alamat Email: ");
        String emailAddress = scanner.nextLine();

        Penyewa penyewa = new Penyewa(guestName, occupation, phoneNumber, nationalId, homeAddress, emailAddress, roomNumber);
        daftarKamar[roomNumber].penyewa = penyewa;
        daftarKamar[roomNumber].isOccupied = true;
        daftarPenyewa[roomNumber] = penyewa;

        System.out.println("Informasi penyewa berhasil ditambahkan.");
        System.out.println("Berikut informasinya: ");
        penyewa.displayPenyewaInfo();
        System.out.println("==========================");
    }


public void checkin() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== HALAMAN CHECK-IN ===");
    System.out.print("Masukkan Nomor Kamar: ");
    int roomNumber = scanner.nextInt();
    scanner.nextLine();

    if (daftarKamar[roomNumber] == null) {
        System.out.println("Kamar tidak tersedia. Silakan tambahkan kamar terlebih dahulu.");
        return;
    }

    System.out.print("Metode Pembayaran: ");
    String paymentMethod = scanner.nextLine();

    System.out.print("Durasi Menginap (dalam hari): ");
    int durationInDays = scanner.nextInt();
    scanner.nextLine();


    CheckIn checkIn = new CheckIn(roomNumber, paymentMethod, durationInDays);

    daftarKamar[roomNumber].assignCheckIn(checkIn);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    System.out.println("Check-In Berhasil!");
    System.out.println("Nomor Transaksi: " + checkIn.getTransactionNumber());
    System.out.println("Nomor Kamar: " + checkIn.getRoomNumber());
    System.out.println("Metode Pembayaran: " + checkIn.getPaymentMethod());
    System.out.println("Durasi Menginap: " + checkIn.getDurationInDays() + " hari");
    DateTimeFormatter checkInFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    System.out.println("Tanggal dan Waktu Check In: " + checkIn.getCheckInDateTime().format(checkInFormatter));
    System.out.println("Tanggal Check-Out: " + checkIn.getCheckOutDateTime().format(formatter));
    System.out.println("==========================");
}



private Room findRoomByTransactionNumber(int transactionNumber) {
    for (Room room : daftarKamar) {
        if (room != null && room.checkIn != null && room.checkIn.getTransactionNumber() == transactionNumber) {
            return room;
        }
    }
    return null;
}


public void checkout() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== HALAMAN CHECKOUT ===");
    System.out.print("Masukkan Nomor Transaksi: ");
    int transactionNumber = scanner.nextInt();
    scanner.nextLine();

    Room room = findRoomByTransactionNumber(transactionNumber);
    if (room == null || !room.isOccupied) {
        System.out.println("Kamar tidak terisi atau tidak tersedia.");
        return;
    }

    Penyewa penyewa = room.penyewa;
    CheckIn checkIn = room.checkIn;

    System.out.println("=== Struk Checkout ===");
    System.out.println("Nomor Transaksi: " + checkIn.getTransactionNumber());
    System.out.println("Nomor Kamar: " + checkIn.getRoomNumber());
    System.out.println("Nama Tamu: " + penyewa.guestName);
    DateTimeFormatter checkInFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    System.out.println("Tanggal dan Waktu Check In: " + checkIn.getCheckInDateTime().format(checkInFormatter));
    System.out.print("Masukkan Jam Checkout (HH:mm): ");
    String checkoutTimeInput = scanner.nextLine();
    DateTimeFormatter checkoutFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    System.out.println("Tanggal Check-Out: " + checkIn.getCheckOutDateTime().format(checkoutFormatter));
    int checkoutHour = Integer.parseInt(checkoutTimeInput.substring(0, 2));
    int checkoutMinute = Integer.parseInt(checkoutTimeInput.substring(3));

    if (checkoutHour > 12 || (checkoutHour == 12 && checkoutMinute > 0)) {
        System.out.println("Anda checkout setelah jam 12:00 PM. Dikenakan denda.");

        // Menghitung selisih waktu checkout dengan jam 12:00 PM
        int hoursOverdue = 0;
        if (checkoutHour > 12) {
            hoursOverdue = checkoutHour - 12 ;
            if (checkoutMinute > 0) {
                hoursOverdue++;
            }
        }

        double fineRate = 0;
        switch (room.roomType.toLowerCase()) {
            case "presiden":
                fineRate = 500000;  // Change this to the appropriate fine rate for "presiden" room
                break;
            case "vip":
                fineRate = 300000;  // Change this to the appropriate fine rate for "vip" room
                break;
            case "reguler":
                fineRate = 100000;  // Change this to the appropriate fine rate for "reguler" room
                break;
            default:
                System.out.println("Tipe kamar tidak valid.");
                return;
        }

        // Calculate the fine based on the fine rate and the number of hours overdue
        double finePerHour = fineRate;
        double totalFine = Math.max(0, hoursOverdue * finePerHour);
        System.out.println("Total Harga Denda: " + formatCurrencyInHotel(totalFine));
        room.fine = totalFine;

        // Calculate the total bill including the fine
        double totalBiaya = Math.max(0, room.roomPrice * checkIn.getDurationInDays() + room.fine);
        System.out.println("Total Tagihan: " + currencyFormatter.format(totalBiaya));
    }
}

// Fungsi untuk format mata uang
private String formatCurrencyInHotel(double amount) {
    DecimalFormat currencyFormatter = new DecimalFormat("#,##0.00");
    return currencyFormatter.format(amount);
}


   public void caritransaksi() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("==========================");
    System.out.println("       CARI TRANSAKSI      ");
    System.out.println("==========================");

    System.out.print("Masukkan Nomor Transaksi: ");
    int transactionNumber = scanner.nextInt();
    scanner.nextLine();

    Room room = findRoomByTransactionNumber(transactionNumber);

    if (room != null && room.isOccupied && room.checkIn != null) {
        System.out.println("=== Informasi Transaksi ===");
        System.out.println("Nomor Transaksi: " + room.checkIn.getTransactionNumber());
        System.out.println("Nomor Kamar: " + room.roomNumber);
        System.out.println("Nama Tamu: " + room.penyewa.guestName);
        System.out.println("Tipe Kamar: " + room.roomType);
        System.out.println("Durasi Menginap: " + room.checkIn.getDurationInDays() + " hari");

        DateTimeFormatter checkInFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Tanggal dan Waktu Check In: " + room.checkIn.getCheckInDateTime().format(checkInFormatter));
        System.out.println("Tanggal Check-Out: " + room.checkIn.getCheckOutDateTime().format(checkInFormatter));

        DecimalFormat currencyFormatter = new DecimalFormat("Rp#,##0.00");
        String formattedRoomPrice = currencyFormatter.format(room.roomPrice);
        System.out.println("Harga Kamar per Hari: " + formattedRoomPrice);

        if (room.fine > 0) {
            String formattedFine = currencyFormatter.format(room.fine);
            System.out.println("Denda: " + formattedFine);
        }

        double totalBill = room.roomPrice * room.checkIn.getDurationInDays() + room.fine;
        String formattedTotalBill = currencyFormatter.format(totalBill);
        System.out.println("Total Tagihan: " + formattedTotalBill);

        System.out.println("==========================");
    } else {
        System.out.println("Maaf, transaksi tidak ditemukan atau kamar tidak terisi.");
        System.out.println("==========================");
    }
}


public void statistikhotel() {
    int nomorKamarTerbesar = 0;

    for (Room room : daftarKamar) {
        if (room != null && room.roomNumber > nomorKamarTerbesar) {
            nomorKamarTerbesar = room.roomNumber;
        }
    }

    int totalKamar = nomorKamarTerbesar;
    int kamarTersedia = 0;
    int kamarTerisi = 0;
    int kamarTamuMenginap = 0;

    for (Room room : daftarKamar) {
        if (room != null) {
            if (!room.isOccupied) {
                kamarTersedia++;
            } else {
                kamarTerisi++;
                if (room.checkIn != null) {
                    kamarTamuMenginap++;
                }
            }
        }
    }

    // Perbarui kamar tersedia berdasarkan jumlah check-in
    kamarTersedia = totalKamar - kamarTamuMenginap;

    System.out.println("==========================");
    System.out.println("      STATISTIK HOTEL      ");
    System.out.println("==========================");
    System.out.println("Jumlah Total Kamar: " + totalKamar);
    System.out.println("Jumlah Kamar Tersedia: " + kamarTersedia);
    System.out.println("Jumlah Kamar Terisi: " + kamarTerisi);
    System.out.println("Jumlah Kamar Tamu yang Menginap: " + kamarTamuMenginap);
    System.out.println("==========================");
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
                    case 3:
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