package tienda.servicios;

import java.util.Collection;
import tienda.entidades.producto;
import tienda.persistencia.productoDAO;

public class productoServicios {
    private  productoDAO dao;

    public productoServicios() {
        this.dao = new productoDAO();
    }
    
    public void crearProducto (double precio, Integer codigoFabricante, Integer codigo, String nombre) throws Exception{
        
        try {
            if (null == codigo){
            throw new Exception("Debe indicar codigo");
            }
            
        producto productoExistente = dao.buscarProductoCodigo(codigo);
        if (productoExistente != null) {
            throw new Exception("El producto con código " + codigo + " ya existe");
        }

            
            if (null == nombre || nombre.trim().isEmpty()){
                throw new Exception("Debe indicar nombre de fabricante");
            }
            
            producto producto = new producto(precio, codigoFabricante, codigo, nombre);
            dao.guardarProducto(producto);
            
        } catch (Exception e) {
            System.out.println("Error al crear " + e.getMessage());
            throw e;
        }
    }
    
    public void modificarCodigoProducto (Integer codigoV, Integer codigoN) throws Exception{
        
        try {
            
            if (null == codigoV){
                throw new Exception("Debe indicar codigo de producto");
            }
            
            producto productoExistente = dao.buscarProductoCodigo(codigoV);
            dao.modificarProducto(productoExistente, codigoN);
            
        } catch (Exception e) {
            System.out.println("Error al modificar " + e.getMessage());
            throw e;
        }
    }
    
    public void eliminarProducto (Integer codigo) throws Exception{
        try {
             if (null == codigo){
            throw new Exception("Debe indicar codigo");
            }
             
             producto pExiste = dao.buscarProductoCodigo(codigo);
             if (pExiste == null){
                 throw new Exception("El producto con código " + codigo + " no existe");
             }
        
        dao.eliminarProducto(pExiste);
             
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection <producto> listaDeProductos() throws Exception{
        try {
            return dao.listaDeProductos();
        } catch (Exception e) {
            System.out.println("Error al obtener la lista");
            throw e;
        }
    }
    
    public Collection <producto> listaPorPrecio(int p1,int p2) throws Exception{
        try {
            return dao.listaPorPrecio(p1, p2);
        } catch (Exception e) {
            System.out.println("Error al obtener la lista");
            throw e;
        }
    }
    
    public Collection <producto> listaPorNombre(String m) throws Exception{
        try {
            return dao.listaPorNombre(m);
        } catch (Exception e) {
            System.out.println("Error al obtener la lista");
            throw e;
        }
    }
    
    
}