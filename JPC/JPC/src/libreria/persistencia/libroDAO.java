
package libreria.persistencia;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import libreria.entidades.Libro;


public class libroDAO extends DAO {

    public void guardar(Libro libro) {
        super.guardar(libro); 
    }

    public void eliminar(Libro libro) {
        super.eliminar(libro); 
    }

    public void editar(Libro libro) {
        super.editar(libro); 
    }
    
    public List <Libro> listarLibros(){
        conectar();
        TypedQuery<Libro> query = em.createQuery("SELECT a FROM Autor a", Libro.class);
        List<Libro> libros = query.getResultList();
        desconectar();
        return libros;
    }
    
    public Libro buscarPorIsbn (int isbn){
        conectar();
        Query query = em.createQuery("SELECT a FROM Libro a WHERE a.isbn = :isbn");
        query.setParameter("isbn", isbn);
        Libro libro = (Libro) query.getSingleResult();
        desconectar();
        return libro;
    }
    
    public Libro buscarTitulo(String titulo){
        conectar();
        Query query = em.createQuery("SELECT a FROM Libro a WHERE a.titulo LIKE :titulo");
        query.setParameter("titulo", "%" + titulo + "%");
        Libro libro = (Libro) query.getSingleResult();
        desconectar();
        return libro;
    }
    
}
