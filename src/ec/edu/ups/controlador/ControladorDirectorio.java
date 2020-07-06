/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author Usuario
 */
public class ControladorDirectorio {
    private String ruta;
    private File archivo;
    private File[] archivos;

    public ControladorDirectorio() {

    }

    public boolean validarRuta(String ruta) {
        archivo = new File(ruta);
        if (archivo.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean comprobarExistencia(String ruta, String nombre) {
        archivo = new File(ruta + File.separator + nombre);
        if (archivo.exists()) {
            return true;
        } else {
            return false;
        }

    }

    public List<String> listarArchivos(String ruta) {
        List<String> lista = new ArrayList<>();
        lista.clear();
        archivo = new File(ruta);
        archivos = archivo.listFiles();

        for (File elemento : archivos) {
            if (!elemento.isHidden()) {
                lista.add(elemento.getName());
            }

        }

        return lista;
    }

    public List<String> listarArchivosOcultos(String ruta) {
        List<String> lista = new ArrayList<>();
        lista.clear();
        archivo = new File(ruta);
        archivos = archivo.listFiles();

        for (File elemento : archivos) {
            if (elemento.isHidden() && elemento.isFile()) {
                lista.add(elemento.getName());
            }
        }

        for (File elemento : archivos) {
            if (elemento.isDirectory()) {
                File[] subdirectorios = elemento.listFiles();
                for (File subelemento : subdirectorios) {
                    if (subelemento.isHidden() && subelemento.isFile()) {
                        lista.add(subelemento.getName());
                    }

                }

            }
        }

        return lista;
    }

    public List<String> listarDirectoriosOcultos(String ruta) {
        List<String> lista = new ArrayList<>();
        lista.clear();
        archivo = new File(ruta);
        archivos = archivo.listFiles();

        for (File elemento : archivos) {
            if (elemento.isHidden() && elemento.isDirectory()) {
                lista.add(elemento.getName());
            }
        }

        return lista;
    }

    public String mostrarInfo(String nombre, String ruta) {

        archivo = new File(ruta);
        archivos = archivo.listFiles();
        String informacion = "Informacion";

        for (File elemento : archivos) {
            if (elemento.getName().equals(nombre)) {
                String path = "Path: ";
                path = path.concat(elemento.getAbsolutePath());
                informacion = informacion.concat("\n");
                informacion = informacion.concat(path);

                String tamaño = "Tamaño: ";
                long bytes = elemento.length();
                bytes = (bytes) / (1024);
                String cad = String.valueOf(bytes);
                cad = cad.concat(" Kb");
                tamaño = tamaño.concat(cad);
                informacion = informacion.concat("\n");
                informacion = informacion.concat(tamaño);

                //permisos de lectura y escritura
                String lectura = "Permisos de lectura: ";
                if (elemento.canRead()) {
                    lectura = lectura.concat("Abierto");
                } else {
                    lectura = lectura.concat("Cerrado");
                }

                informacion = informacion.concat("\n");
                informacion = informacion.concat(lectura);

                String escritura = "Permisos de escritura: ";
                if (elemento.canWrite()) {
                    escritura = escritura.concat("Abierto");
                } else {
                    escritura = escritura.concat("Cerrado");
                }

                informacion = informacion.concat("\n");
                informacion = informacion.concat(escritura);

                long lastModified = elemento.lastModified();

                String pattern = "yyyy-MM-dd hh:mm aa";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                Date lastModifiedDate = new Date(lastModified);

                String fecha = "Última modificación: ";
                fecha = fecha.concat(lastModifiedDate.toString());
                informacion = informacion.concat("\n");
                informacion = informacion.concat(fecha);
            }
        }

        return informacion;
    }

    public void crearDirectorio(String ruta, String nombre) {
        archivo = new File(ruta + File.separator + nombre);
        archivo.mkdir();
    }

    public void renombrarDirectorio(String ruta, String actual, 
            String renombre) {
        archivo = new File(ruta + File.separator + actual);

        File nuevo = new File(ruta + File.separator + renombre);
        archivo.renameTo(nuevo);
    }

    public void eliminarDirectorio(String ruta, String eliminar) 
            throws IOException {
        archivo = new File(ruta + File.separator + eliminar);
        if (archivo.isDirectory()) {
            archivos = archivo.listFiles();

            for (int i = 0; i < archivos.length; i++) {
                if (archivos[i].isDirectory()) {
                    eliminarDirectorios(archivos[i]);
                } else {
                    archivos[i].delete();
                }
            }
            archivo.delete();
        } else {
            
            archivo.delete();
            
        }

    }

    public void eliminarDirectorios(File path) {
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                eliminarDirectorios(files[i]);
            } else {
                files[i].delete();
            }
        }
        path.delete();

    }

    public List<String> buscarNombre(String ruta, String nombre) {
        archivo = new File(ruta + File.separator + nombre);
        archivos = archivo.listFiles();
        List<String> lista = new ArrayList<>();
        for (File archivo1 : archivos) {
            lista.add(archivo1.getName());
        }
        
        return lista;
    }
    
    public String devolverRuta(String ruta, String nombre){
        archivo = new File(ruta + File.separator + nombre);
        
        return archivo.getAbsolutePath();
    }
}
