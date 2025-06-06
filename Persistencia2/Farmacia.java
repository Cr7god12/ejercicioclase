package Persistencia;

import java.io.*;
import java.util.ArrayList;

public class Farmacia {

    private String nombre;
    // Nuevas rutas apuntando a mis_personas/medicamentos/ y mis_personas/personas/
    private String rutaMed = "C:/Users/Diego/Desktop/mis_personas/medicamentos/";
    private String rutaPer = "C:/Users/Diego/Desktop/mis_personas/personas/";
    // Ruta para facturas
    private String rutaFac = "C:/Users/Diego/Desktop/mis_personas/facturas/";

    private ArrayList<Medicamento> medicamentos = new ArrayList<>();
    private ArrayList<Persona> personas = new ArrayList<>();

    public Farmacia(String nombre) {
        this.nombre = nombre;
    }

    // Carga todos los .txt de medicamentos/ en la lista
    public void cargarMedicamentos() throws IOException {
        File carpeta = new File(rutaMed);
        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    medicamentos.add(new Medicamento(archivo.getAbsolutePath()));
                }
            }
        } else {
            System.out.println("Carpeta medicamentos vacía o no existe.");
        }
    }

    // Carga todos los .txt de personas/ en la lista
    public void cargarPersonas() throws IOException {
        File carpeta = new File(rutaPer);
        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    personas.add(new Persona(archivo.getAbsolutePath()));
                }
            }
        } else {
            System.out.println("Carpeta personas vacía o no existe.");
        }
    }

    // Agrega a la lista y guarda en disco
    public void agregarPersona(Persona p) throws IOException {
        this.personas.add(p);
        p.guardar();
    }

    // Agrega a la lista y guarda en disco
    public void agregarMedicamento(Medicamento m) throws IOException {
        this.medicamentos.add(m);
        m.guardar();
    }

    public void mostrarMedicamentos() {
        for (Medicamento m : medicamentos) {
            System.out.println(m);
        }
    }

    public void mostrarPersonas() {
        for (Persona p : personas) {
            System.out.println(p);
        }
    }

    /**
     * Vende un medicamento a la primera persona en la cola.
     * - Quita a la persona (la elimina de disco)
     * - Descarga stock del medicamento
     * - Genera Factura y la guarda
     */
    public void venderMedFactura(String nombre, int cantidad) throws IOException {
        if (personas.isEmpty()) {
            System.out.println("No hay personas en espera.");
            return;
        }

        // Tomar la primera persona
        Persona cliente = personas.remove(0);
        cliente.eliminar();

        for (Medicamento med : medicamentos) {
            if (med.getNombre().equalsIgnoreCase(nombre)) {
                if (med.getStock() >= cantidad) {
                    double total = med.getPrecio() * cantidad;
                    med.setStock(med.getStock() - cantidad);
                    med.actualizar();

                    // Crear y guardar factura
                    Facturas factura = new Facturas(cliente.getNom(), nombre, cantidad, total);
                    factura.guardar();

                    System.out.println("FACTURA GENERADA:");
                    System.out.println("Cliente: " + cliente.getNom());
                    System.out.println("Medicamento: " + nombre);
                    System.out.println("Cantidad: " + cantidad);
                    System.out.println("Total: " + total);
                    return;
                } else {
                    System.out.println("Stock insuficiente para “" + nombre + "”. Disponible: " + med.getStock());
                    return;
                }
            }
        }

        System.out.println("Medicamento “" + nombre + "” no encontrado.");
    }

    /**
     * Recorre todas las facturas en disco y suma cantidad y total vendido.
     */
    public void mostrarVentasTotales() throws IOException {
        File carpeta = new File(rutaFac);
        File[] archivos = carpeta.listFiles();
        int totalCant = 0;
        double totalGanancia = 0.0;

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    BufferedReader br = new BufferedReader(new FileReader(archivo));
                    br.readLine(); // nombreCliente
                    br.readLine(); // nombreMedicamento
                    int cant = Integer.parseInt(br.readLine());
                    double tot = Double.parseDouble(br.readLine());
                    totalCant += cant;
                    totalGanancia += tot;
                    br.close();
                }
            }
        }

        System.out.println("----- RESUMEN DE VENTAS -----");
        System.out.println("TOTAL MEDICAMENTOS VENDIDOS: " + totalCant);
        System.out.println("GANANCIA TOTAL: Bs. " + totalGanancia);
    }
}
