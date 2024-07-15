import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    private String brand;
    private int ram;
    private int hdd;
    private String os;
    private String color;
    private double price;

    public Laptop(String brand, int ram, int hdd, String os, String color, double price) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 16, 512, "Windows", "Black", 1200.0));
        laptops.add(new Laptop("Apple", 8, 256, "MacOS", "Silver", 1500.0));
        laptops.add(new Laptop("Lenovo", 32, 1024, "Windows", "Gray", 1800.0));

        Map<String, Object> filters = new HashMap<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Выберите критерии для фильтрации:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.println("5 - Цена");
            System.out.println("0 - Завершить выбор");

            int choice;
            while (true) {
                choice = scanner.nextInt();
                if (choice == 0) {
                    break;
                }
                switch (choice) {
                    case 1:
                        System.out.println("Минимальный объем ОЗУ?");
                        filters.put("ram", scanner.nextInt());
                        break;
                    case 2:
                        System.out.println("Минимальный объем ЖД?");
                        filters.put("hdd", scanner.nextInt());
                        break;
                    case 3:
                        System.out.println("Операционная система?");
                        filters.put("os", scanner.next());
                        break;
                    case 4:
                        System.out.println("Цвет?");
                        filters.put("color", scanner.next());
                        break;
                    case 5:
                        System.out.println("Максимальная цена?");
                        filters.put("price", scanner.nextDouble());
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            }
        }

        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("ram", 0) instanceof Integer
                        && laptop.getRam() >= (int) filters.getOrDefault("ram", 0))
                .filter(laptop -> filters.getOrDefault("hdd", 0) instanceof Integer
                        && laptop.getHdd() >= (int) filters.getOrDefault("hdd", 0))
                .filter(laptop -> filters.getOrDefault("os", "").equals("")
                        || laptop.getOs().equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("")
                        || laptop.getColor().equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .filter(laptop -> filters.getOrDefault("price", Double.MAX_VALUE) instanceof Double
                        && laptop.getPrice() <= (double) filters.getOrDefault("price", Double.MAX_VALUE))
                .collect(Collectors.toSet());

        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}