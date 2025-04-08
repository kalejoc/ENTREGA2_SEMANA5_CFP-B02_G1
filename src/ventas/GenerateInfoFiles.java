package ventas; // Declaración del paquete llamado 'ventas'

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {
    // Arreglo bidimensional que contiene información de los vendedores (tipo de documento, ID, nombre, apellido)
    private static final String[][] SALESMEN = {
            {"CC", "001", "JUAN", "ALEGRIAS"},
            {"CC", "002", "KEVIN", "ALEJO"},
            {"CC", "003", "PAOLA", "ALVAREZ"},
            {"CC", "004", "DANIELA", "ALZATE"},
            {"CC", "005", "DAYRA", "ARAGON"}
    };

    // Arreglo bidimensional que contiene información de los productos (ID, nombre, precio)
    private static final String[][] PRODUCTS = {
            {"P001", "Mouse", "30000"},
            {"P002", "Teclado", "60000"},
            {"P003", "Audifonos", "45000"},
            {"P004", "Cargador de Portatil", "70000"},
            {"P005", "Laptop", "2500000"},
            {"P006", "Pantalla", "800000"},
            {"P007", "PC de Torre", "2000000"},
            {"P008", "PacMouse", "25000"}
    };

    // Objeto Random para generar números aleatorios
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        try {
            // Crear archivo de productos con todos los productos definidos
            createProductsFile(PRODUCTS.length);
            // Crear archivo de información de vendedores
            createSalesManInfoFile(SALESMEN.length);
            
            // Para cada vendedor, crear un archivo de ventas con entre 3 y 7 registros aleatorios
            for (String[] salesman : SALESMEN) {
                String name = salesman[2] + " " + salesman[3]; // nombre completo
                long id = Long.parseLong(salesman[1]); // ID numérico
                createSalesMenFile(3 + RANDOM.nextInt(5), name, id);
            }
            System.out.println("Archivos generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error generando archivos: " + e.getMessage());
        }
    }

    // Método que crea el archivo productos.csv con la información de los productos
    public static void createProductsFile(int productsCount) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("productos.csv"))) {
            for (int i = 0; i < productsCount; i++) {
                String[] prod = PRODUCTS[i];
                writer.write(prod[0] + ";" + prod[1] + ";" + prod[2] + "\n"); // ID;Nombre;Precio
            }
        }
    }

    // Método que crea el archivo vendedores.csv con la información de los vendedores
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vendedores.csv"))) {
            for (int i = 0; i < salesmanCount; i++) {
                String[] s = SALESMEN[i];
                writer.write(s[0] + ";" + s[1] + ";" + s[2] + ";" + s[3] + "\n"); // TipoDoc;ID;Nombre;Apellido
            }
        }
    }

    // Método que crea un archivo por vendedor con registros de ventas aleatorias
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        String fileName = "ventas_" + id + ".csv"; // Nombre del archivo por ID del vendedor
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("CC;" + id + ";"); // Encabezado con tipo de documento e ID
            for (int i = 0; i < randomSalesCount; i++) {
                int prodIndex = RANDOM.nextInt(PRODUCTS.length); // Índice aleatorio para seleccionar un producto
                String productId = PRODUCTS[prodIndex][0]; // ID del producto
                int cantidad = 1 + RANDOM.nextInt(5); // Cantidad aleatoria entre 1 y 5
                writer.write(productId + ";" + cantidad + ";"); // Registro: ProductoID;Cantidad;
            }
        }
    }
}
