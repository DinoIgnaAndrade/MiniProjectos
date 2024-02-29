
package tienda.servicios;

import tienda.entidades.fabricante;
import tienda.persistencia.fabricanteDAO;

public class fabricanteServicios {
    private  fabricanteDAO dao;

    public fabricanteServicios() {
        this.dao = new fabricanteDAO();
    }
    
    public void crearFabricante (Integer codigo, String nombre) throws Exception{
        
        try {
            if (null == codigo){
            throw new Exception("Debe indicar codigo");
            }
            
        fabricante fabricanteExistente = dao.buscarFabricanteCodigo(codigo);
        if (fabricanteExistente != null) {
            throw new Exception("El producto con código " + codigo + " ya existe");
        }
            
            if (null == nombre || nombre.trim().isEmpty()){
                throw new Exception("Debe indicar nombre de fabricante");
            }
            
            fabricante fabricante = new fabricante(codigo, nombre);
            dao.guardarFabricante(fabricante);
            
        } catch (Exception e) {
            System.out.println("Error no se guardo el fabricante " + e.getMessage());
            throw e;
        }
    }
    
}
