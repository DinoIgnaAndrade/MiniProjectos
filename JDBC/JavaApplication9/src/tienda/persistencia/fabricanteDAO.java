
package tienda.persistencia;
import tienda.entidades.fabricante;

public final class fabricanteDAO extends DAO {
    
    public void guardarFabricante(fabricante f) throws Exception{
        
        try {
            if (f == null) {
                throw new Exception("Debe indicar fabricante");
            }
            
            String sql = "insert into fabricante(codigo,nombre) " + 
                    "values (" + f.getCodigo() + " , '" + f.getNombre() +"');";
            
            insertarModificarEliminar(sql);
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificarFabricante(fabricante f) throws Exception{
        
        try {
            if (f == null) {
                throw new Exception("Debe indicar fabricante que desea modificar");
            }
            
            String sql = "update fabricante set " + 
                    "codigo = " + f.getCodigo() + " where nombre = '" + f.getNombre() + " ');";
            
            insertarModificarEliminar(sql);
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarFabricante(fabricante f) throws Exception{
        
        try {
            
            String sql = "delete from fabricante where codigo = " + f.getCodigo() + ";";
            
            insertarModificarEliminar(sql);
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public fabricante buscarFabricanteCodigo(int codigoFabricante) throws Exception{
        
        
        try {
            
            String sql = "select * from fabricante " + "where codigo = " + codigoFabricante;
            
            consultarBase(sql);
            
            fabricante fabricante = null;
            
            while (resultado.next()){
                fabricante  = new fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return fabricante;
            
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
}
