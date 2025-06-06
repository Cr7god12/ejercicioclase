package Persistencia;

import java.io.*;

public class Facturas {
    // Ruta base apuntando a C:/Users/Diego/Desktop/mis_personas/facturas/
    private static String rutaBase = "C:/Users/Diego/Desktop/mis_personas/facturas/";

    private String nombreCliente;
    private String nombreMedicamento;
    private int cantidad;
    private double total;

    public Facturas(String nombreCliente, String nombreMedicamento, int cantidad, double total) {
        this.nombreCliente = nombreCliente;
        this.nombreMedicamento = nombreMedicamento;
        this.cantidad = cantidad;
        this.total = total;
    }

    public void guardar() throws IOException {
        File carpeta = new File(rutaBase);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        // Nombre de archivo: factura_<cliente>_<medicamento>.txt
        String nombreArchivo = rutaBase + "factura_" +
                               nombreCliente.replaceAll("\\s+", "") + "_" +
                               nombreMedicamento.replaceAll("\\s+", "") + ".txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));
        bw.write(nombreCliente + "\n");
        bw.write(nombreMedicamento + "\n");
        bw.write(cantidad + "\n");
        bw.write(total + "\n");
        bw.close();
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }
}
