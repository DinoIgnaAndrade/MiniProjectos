
package libreria.servicios;

import java.util.List;
import libreria.entidades.Editorial;
import libreria.persistencia.editorialDAO;

public class editorialServicios {
    
    private final editorialDAO DAO;
    
    public editorialServicios() {
        this.DAO = new editorialDAO();
    }
    
    public Editorial crearEditorial(String nombre, Boolean alta){
        Editorial ed = new Editorial();
        
        try {
            ed.setNombre(nombre);
            ed.setAlta(alta);
            DAO.guardar(ed);
            return ed;
        } catch (Exception e) {
            System.out.println("Error Editorial Servicios"+e.getMessage());
            return null;
        }
    }
    
    public Editorial buscarPorId(Integer id){
        try {
            return DAO.buscarPorId(id);
        } catch (Exception e) {
            System.out.println("Error Editorial Servicios"+e.getMessage());
            return null;
        }
    }
    
    public Editorial buscarNombre(String name){
        try {
            return DAO.buscarNombre(name);
        } catch (Exception e) {
            System.out.println("Error Editorial Servicios"+e.getMessage());
            return null;
        }
    }
    
    public List<Editorial> listaEditoriales(){
        try {
            return DAO.listarEditoriales();
        } catch (Exception e) {
            System.out.println("Error Editorial Servicios"+e.getMessage());
            return null;
        }
    }
    
    public boolean eliminarPorID(Integer id){
        try {
            Editorial editorial = DAO.buscarPorId(id);
            DAO.eliminar(editorial);
            return true;
        } catch (Exception e) {
            System.out.println("Error Editorial Servicios"+e.getMessage());
            return false;
        }
    }
}
