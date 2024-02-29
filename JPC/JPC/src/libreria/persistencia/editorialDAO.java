
package libreria.persistencia;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;


public class editorialDAO extends DAO {

    public void guardar(Editorial editorial) {
        super.guardar(editorial); 
    }

    public void eliminar(Editorial editorial) {
        super.eliminar(editorial); 
    }

    public void editar(Editorial editorial) {
        super.editar(editorial); 
    }
    
    public List <Editorial> listarEditoriales(){
        conectar();
        TypedQuery<Editorial> query = em.createQuery("SELECT a FROM Editorial a", Editorial.class);
        List<Editorial> editorial = query.getResultList();
        desconectar();
        return editorial;
    }
    
    public Editorial buscarPorId (int id){
        conectar();
        Query query = em.createQuery("SELECT a FROM Editorial a WHERE a.id = :id");
        query.setParameter("id", id);
        Editorial editorial = (Editorial) query.getSingleResult();
        desconectar();
        return editorial;
    }
    
//    public Editorial buscarNombre(String name){
//        conectar();
//        Editorial editorial = (Editorial) em.createQuery("Select * from editorial "
//                + "where NOMBRE like '" + name + "';").setParameter("nombre", name).getSingleResult();
//        return editorial;
//    }
    
    public Editorial buscarNombre(String name) {
        conectar();
        Query query = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :name");
        query.setParameter("name", "%" + name + "%");
        Editorial editorial = (Editorial) query.getSingleResult();
        desconectar();
        return editorial;
    }
    
}
