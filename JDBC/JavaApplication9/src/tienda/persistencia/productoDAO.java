
package tienda.persistencia;
import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.producto;

public final class productoDAO extends DAO {
    
    public void guardarProducto(producto f) throws Exception{
        
        try {
            if (f == null) {
                throw new Exception("Debe indicar fabricante");
            }
            
            String sql = "insert into producto(codigo,nombre,precio,codigo_fabricante) " + 
                    "values (" + f.getCodigo() + " , '" + f.getNombre() + "' , " + f.getPrecio() +  " , " + f.getCodigoFabricante()  + " );" ;
            
            insertarModificarEliminar(sql);
            
        } catch (Exception e) {
            System.out.println("error al guardar" + e.getMessage());
        }
    }
    
    public void modificarProducto(producto f, Integer code) throws Exception{
        
        try {
            if (f == null) {
                throw new Exception("Debe indicar producto que desea modificar");
            }
            
            String sql = "update producto set " + 
                    " codigo = " + code + " where codigo = " + f.getCodigo() + ";";
            
            insertarModificarEliminar(sql);
            
        } catch (Exception e) {
            System.out.println("error al modificar" + e.getMessage());
        }
    }
    
    public void eliminarProducto(producto f) throws Exception{
        
        try {
            
            String sql = "delete from producto where codigo = " + f.getCodigo() + ";";
            
            insertarModificarEliminar(sql);
            
        } catch (Exception e) {
            System.out.println("Error al eliminar" + e.getMessage());
        }
    }
    
    public producto buscarProductoCodigo(Integer codigoProducto) throws Exception{
        
        
        try {
            
            String sql = "select * from producto " + "where codigo = " + codigoProducto;
            
            consultarBase(sql);
            
            producto producto = null;
            
            while (resultado.next()){
                producto  = new producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getInt(3));
                producto.setCodigoFabricante(resultado.getInt(4));
            }
            desconectarBase();
            return producto;
            
        } catch (Exception e) {
            desconectarBase();
            System.out.println("Error al buscar" + e.getMessage());
            throw e;
        }
    }
    
    public Collection <producto> listaDeProductos() throws Exception{
        try {
            String sql = "select * from producto;";
            
            consultarBase(sql);
            
            producto p = null;
            Collection <producto> lp = new ArrayList<>();
            
            while (resultado.next()){
                p = new producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getInt(3));
                p.setCodigoFabricante(resultado.getInt(4));
                lp.add(p);
            }
            desconectarBase();
            return lp;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception(" error lista de productos");
        }
    }
    
    public Collection <producto> listaPorPrecio(int p1,int p2) throws Exception{
        try {
            String sql = "select * from producto " + 
                    "where precio >= " + p1 + " and precio <= " + p2;
            
            consultarBase(sql);
            
            producto p = null;
            Collection <producto> lp = new ArrayList<>();
            
            while (resultado.next()){
                p = new producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getInt(3));
                p.setCodigoFabricante(resultado.getInt(4));
                lp.add(p);
            }
            desconectarBase();
            return lp;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception(" error lista de productos");
        }
    }
    
    public Collection <producto> listaPorNombre(String m) throws Exception{
        try {
            String sql = "select * from producto " + 
                    "where nombre like '%" + m + "%'";
            
            consultarBase(sql);
            
            producto p = null;
            Collection <producto> lp = new ArrayList<>();
            
            while (resultado.next()){
                p = new producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getInt(3));
                p.setCodigoFabricante(resultado.getInt(4));
                lp.add(p);
            }
            desconectarBase();
            return lp;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception(" error lista de productos");
        }
    }
}

