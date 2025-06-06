package Persistencia;

import java.io.*;

public class Persona {
    // Nueva ruta base apuntando a C:/Users/Diego/Desktop/mis_personas/personas/
    private String rutaBase = "C:/Users/Diego/Desktop/mis_personas/personas/";

    private int id;
    private String nom;
    private int edad;
    private String archivo;

    public Persona(int id, String nom, int edad) {
        this.id = id;
        this.nom = nom;
        this.edad = edad;
        this.archivo = rutaBase + "persona" + id + ".txt";
    }

    // Constructor para cargar desde un archivo existente (se le pasa la ruta completa)
    public Persona(String rutaArchivo) throws IOException {
        this.archivo = rutaArchivo;
        File archivo = new File(this.archivo);
        if (!archivo.exists()) {
            throw new FileNotFoundException("El archivo no existe: " + this.archivo);
        } else {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            this.id = Integer.parseInt(br.readLine());
            this.nom = br.readLine();
            this.edad = Integer.parseInt(br.readLine());
            br.close();
        }
    }

    public void guardar() throws IOException {
        File carpeta = new File(rutaBase);
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crear carpeta si no existe
        }

        File archivo = new File(this.archivo);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.archivo));
        bw.write(this.id + "\n");
        bw.write(this.nom + "\n");
        bw.write(this.edad + "\n");
        bw.close();
    }

    public void actualizar() throws IOException {
        eliminar();
        guardar();
    }

    public void eliminar() {
        File f = new File(this.archivo);
        if (f.exists()) {
            f.delete();
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nom + ", Edad: " + edad;
    }

    public String getNom() {
        return this.nom;
    }
}
