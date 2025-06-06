package Persistencia;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Farmacia farmacia = new Farmacia("Farmacia Central");

        
        farmacia.agregarPersona(new Persona(1, "Jose", 30));
        farmacia.agregarPersona(new Persona(2, "Ana", 22));
        farmacia.agregarPersona(new Persona(3, "Luis", 25));
        farmacia.agregarPersona(new Persona(4, "Maria", 28));
        farmacia.agregarPersona(new Persona(5, "Carlos", 35));

        
        farmacia.agregarMedicamento(new Medicamento(1, "Ibuprofeno", "Analgésico", 5.99, 50));
        farmacia.agregarMedicamento(new Medicamento(2, "Paracetamol", "Analgésico", 3.50, 40));
        farmacia.agregarMedicamento(new Medicamento(3, "Amoxicilina", "Antibiótico", 7.25, 30));
        farmacia.agregarMedicamento(new Medicamento(4, "Loratadina", "Antialérgico", 4.20, 20));

        
        farmacia.cargarPersonas();
        farmacia.cargarMedicamentos();

        
        System.out.println("------ MEDICAMENTOS DISPONIBLES ------");
        farmacia.mostrarMedicamentos();
        System.out.println("\n------ PERSONAS EN COLA ------");
        farmacia.mostrarPersonas();

        
        System.out.println("\n====== PRIMERA VENTA ======");
        farmacia.venderMedFactura("Ibuprofeno", 3);
        System.out.println("\n====== SEGUNDA VENTA ======");
        farmacia.venderMedFactura("Paracetamol", 5);

        
        System.out.println("\n------ VENTAS TOTALES ------");
        farmacia.mostrarVentasTotales();
    }
}
