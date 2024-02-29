
package tienda;

import java.util.List;
import java.util.Scanner;
import tienda.entidades.producto;
import tienda.servicios.fabricanteServicios;
import tienda.servicios.productoServicios;


public class main {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        fabricanteServicios us = new fabricanteServicios();
        productoServicios ps = new productoServicios();
        
        
        boolean x = true;
        do{
            System.out.println("Elige una opcion/n" +
                "1: Lista de productos" + 
                "2: lista entre precios" +
                "3: lista por nombre" +
                "4: crear producto" +
                "5: crear fabricante" +
                "6: modificar producto");
            int op = input.nextInt();
            try {
                switch(op){
                    case 1:
                          List<producto> listaDeProductos = (List<producto>) ps.listaDeProductos();
                          for (producto p : listaDeProductos) {
                            System.out.println("Código: " + p.getCodigo());
                            System.out.println("Nombre: " + p.getNombre());
                            System.out.println("Precio: " + p.getPrecio());
                            System.out.println("Código de Fabricante: " + p.getCodigoFabricante());
                            System.out.println("--------------------------");
                        }
                          continue;
                    case 2:
                        System.out.println("Indique el intervalo de precios");
                        int p1 = input.nextInt();
                        int p2 = input.nextInt();
                        List <producto> listaPorPrecio = (List<producto>) ps.listaPorPrecio(p1, p2);
                        for (producto p : listaPorPrecio) {
                            System.out.println("--------------------------");
                            System.out.println("Código: " + p.getCodigo());
                            System.out.println("Nombre: " + p.getNombre());
                            System.out.println("Precio: " + p.getPrecio());
                            System.out.println("Código de Fabricante: " + p.getCodigoFabricante());
                            System.out.println("--------------------------");
                        }
                        continue;
                    case 3:
                        System.out.println("Indique el nombre");
                        String m = input.next();
                        List <producto> listaPorNombre = (List<producto>) ps.listaPorNombre(m);
                        for (producto p : listaPorNombre) {
                            System.out.println("--------------------------");
                            System.out.println("Código: " + p.getCodigo());
                            System.out.println("Nombre: " + p.getNombre());
                            System.out.println("Precio: " + p.getPrecio());
                            System.out.println("Código de Fabricante: " + p.getCodigoFabricante());
                            System.out.println("--------------------------");
                        }
                    case 4:
                        System.out.println("Ingrese un producto");
                        System.out.println("Precio");
                        double p = input.nextDouble();
                        System.out.println("codigo de fabricante");
                        int cf = input.nextInt();
                        System.out.println("Codigo");
                        int c = input.nextInt();
                        System.out.println("Nombre");
                        String n = input.next();
                        
                        ps.crearProducto(p, cf, c, n);
                        
                    case 5:
                        System.out.println("Ingrese un fabricante");
                        System.out.println("Codigo");
                        int co = input.nextInt();
                        System.out.println("Nombre");
                        String nom = input.next();
                        
                        us.crearFabricante(co, nom);
                        
                    case 6:
                        System.out.println("Ingrese el codigo del Producto");
                        int cv = input.nextInt();
                        System.out.println("Ingrese el nuevo");
                        int cn = input.nextInt();
                        
                        ps.modificarCodigoProducto(cv, cn);
                        
                    case 8:
                        x = false;
                        continue;
                }
                   
//            us.crearFabricante(35, "socio");
//            ps.crearProducto(100, 1, 34, "VergaDeGoma");
//            ps.eliminarProducto(34);     
            } catch (Exception e) {
            throw e;
            }
        }while(x);
        
    }
    
}
