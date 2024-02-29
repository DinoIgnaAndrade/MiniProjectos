
package libreria.servicios;

import java.util.List;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.libroDAO;


public class libroServicios {
    private final libroDAO DAO;
    
    public libroServicios() {
        this.DAO = new libroDAO();
    }
    
    public Libro crearLibro(long isbn,String titulo,Integer anio,Integer ejemplares,
            Integer ejemplaresPrestados,Integer ejemplaresRestantes,Boolean alta,
            Autor autor,Editorial editorial){
        Libro ed = new Libro();
        
        try {
            ed.setIsbn(isbn);
            ed.setTitulo(titulo);
            ed.setAnio(anio);
            ed.setEjemplares(ejemplares);
            ed.setEjemplaresPrestados(ejemplaresPrestados);
            ed.setEjemplaresRestantes(ejemplaresRestantes);
            ed.setAlta(alta);
            ed.setAutor(autor);
            ed.setEditorial(editorial);
            DAO.guardar(ed);
            return ed;
        } catch (Exception e) {
            System.out.println("Error Libro Servicios"+e.getMessage());
            return null;
        }
    }
    
    public Libro buscarPorIsbn(Integer isbn){
        try {
            return DAO.buscarPorIsbn(isbn);
        } catch (Exception e) {
            System.out.println("Error Libro Servicios"+e.getMessage());
            return null;
        }
    }
    
    public Libro buscarTitulo(String name){
        try {
            return DAO.buscarTitulo(name);
        } catch (Exception e) {
            System.out.println("Error Libro Servicios"+e.getMessage());
            return null;
        }
    }
    
    public List<Libro> listaAutores(){
        try {
            return DAO.listarLibros();
        } catch (Exception e) {
            System.out.println("Error Libro Servicios"+e.getMessage());
            return null;
        }
    }
    
    public boolean eliminarPorIsbn(Integer Isbn){
        try {
            Libro libro = DAO.buscarPorIsbn(Isbn);
            DAO.eliminar(libro);
            return true;
        } catch (Exception e) {
            System.out.println("Error Libro Servicios"+e.getMessage());
            return false;
        }
    }
}
