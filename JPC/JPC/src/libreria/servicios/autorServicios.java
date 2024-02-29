
package libreria.servicios;

import java.util.List;
import libreria.entidades.Autor;
import libreria.persistencia.autorDAO;


public class autorServicios {
    private final autorDAO DAO;
    
    public autorServicios() {
        this.DAO = new autorDAO();
    }
    
    public Autor crearAutor(String nombre, Boolean alta){
        Autor ed = new Autor();
        
        try {
            ed.setNombre(nombre);
            ed.setAlta(alta);
            DAO.guardar(ed);
            return ed;
        } catch (Exception e) {
            System.out.println("Error Autor Servicios"+e.getMessage());
            return null;
        }
    }
    
    public void darAlta(Integer x){
        Autor ed = new Autor();
        try {
            DAO.buscarPorId(x);
        } catch (Exception e) {
            
        }
    }
    
    public Autor buscarPorId(Integer id){
        try {
            return DAO.buscarPorId(id);
        } catch (Exception e) {
            System.out.println("Error Autor Servicios"+e.getMessage());
            return null;
        }
    }
    
    public Autor buscarNombre(String name){
        try {
            return DAO.buscarNombre(name);
        } catch (Exception e) {
            System.out.println("Error Autor Servicios"+e.getMessage());
            return null;
        }
    }
    
    public List<Autor> listaAutores(){
        try {
            return DAO.listaAutores();
        } catch (Exception e) {
            System.out.println("Error Autor Servicios"+e.getMessage());
            return null;
        }
    }
    
    public boolean eliminarPorID(Integer id){
        try {
            Autor autor = DAO.buscarPorId(id);
            DAO.eliminar(autor);
            return true;
        } catch (Exception e) {
            System.out.println("Error Autor Servicios"+e.getMessage());
            return false;
        }
    }
}

