
package libreria.persistencia;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import libreria.entidades.Autor;


public class autorDAO extends DAO {

    public void guardar(Autor autor) {
        super.guardar(autor); 
    }

    public void eliminar(Autor autor) {
        super.eliminar(autor); 
    }

    public void editar(Autor autor) {
        super.editar(autor); 
    }
    
    public List<Autor> listaAutores() {
        conectar();
        TypedQuery<Autor> query = em.createQuery("SELECT a FROM Autor a", Autor.class);
        List<Autor> autores = query.getResultList();
        desconectar();
        return autores;
    }
    
    public Autor buscarPorId(int id) {
        conectar();
        Query query = em.createQuery("SELECT a FROM Autor a WHERE a.id = :id");
        query.setParameter("id", id);
        Autor autor = (Autor) query.getSingleResult();
        desconectar();
        return autor;
    }
    
//    public Autor buscarNombre(String name){
//        conectar();
//        Autor editorial = (Autor) em.createQuery("Select * from autor "
//                + "where NOMBRE like '" + name + "';").setParameter("nombre", name).getSingleResult();
//        return editorial;
//    }
    public Autor buscarNombre(String name) {
        conectar();
        Query query = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :name");
        query.setParameter("name", "%" + name + "%");
        Autor autor = (Autor) query.getSingleResult();
        desconectar();
        return autor;
    }
    
}
