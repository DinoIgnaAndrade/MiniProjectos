
package libreria;


import java.util.Scanner;
import libreria.servicios.autorServicios;
import libreria.servicios.editorialServicios;
import libreria.servicios.libroServicios;


public class main {

    public static void main(String[] args) {
        Scanner input =  new Scanner(System.in);
        editorialServicios editorial = new editorialServicios();
        autorServicios autor = new autorServicios();
        libroServicios libro = new libroServicios();
        
        //Creacion de Tablas y Entidades
//        try {
//            editorial.crearEditorial("PitosChicos", Boolean.FALSE);
//            autor.crearAutor("Berga Gorda", Boolean.FALSE);
//            
//            libro.crearLibro(0, "CamaPuta", 2004, 2, 3,
//                    4, Boolean.FALSE, autor.buscarNombre("Berga Gorda"), editorial.buscarNombre("PitosChicos"));
//            
//            editorial.crearEditorial("ConchaSeca", Boolean.FALSE);
//            autor.crearAutor("Trolo Resentido", Boolean.FALSE);
//            
//            libro.crearLibro(0, "Cabaret", 2003, 1, 1,
//                    1, Boolean.FALSE, autor.buscarNombre("Berga Gorda"), editorial.buscarNombre("PitosChicos"));
//            
//        } catch (Exception e) {
//            throw e;
//        }
        
        //Menu
        try {
            
            boolean x = true;
            
            do {                
                System.out.println("Elija una opción\n" +
                        "1: Búsqueda de autor por nombre\n" +
                        "2: Busqueda por Isbn");
                String opcionStr = input.next();
                int opcion = Integer.parseInt(opcionStr);

                switch (opcion) {
                    case 1:
                        System.out.println("Escriba el nombre");
                        String name = input.next();
                        System.out.println(autor.buscarNombre(name).toString());
                        break;
                    case 2:
                        System.out.println("Escriba el ISBN");
                        int isbn = input.nextInt();
                        System.out.println(libro.buscarPorIsbn(isbn).toString());
                        break;
                    case 3:
                        // Otras opciones
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
                }

                System.out.println("¿Desea realizar otra operación? (S/N)");
                input.nextLine(); // Limpiar el búfer del Scanner
                String respuesta = input.nextLine();
                x = respuesta.equalsIgnoreCase("S"); 

            } while (x);
    
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }
        
    }
}
