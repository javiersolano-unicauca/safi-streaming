package modelo.interfaces;

import modelo.VentaServicioModelo;

/**
 * @author javiersolanop
 */
public interface iVentaServicioModelo {

    /**
     *  Metodo para guardar la venta de un servicio en su coleccion
     * 
     *  @return 'true' si se guarda. 'false' si no.
     */
    boolean guardar();

    /**
     *  Metodo para buscar la venta de un servicio especifico en su coleccion.
     *
     *  @param prmId Recibe el 'id' a buscar
     *  @param prmVentaServicio Recibe la nueva instancia de la venta
     *  @return 'true' si se actualiza. 'false' si no existe.
     */    
    boolean actualizar(int prmId, VentaServicioModelo prmVentaServicio);

    /**
     *  Metodo para eliminar la venta de un servicio especifico en su coleccion.
     * 
     *  @param prmId Recibe el 'id' a buscar
     *  @return 'true' si se elimina. 'false' si no existe.
     */     
    boolean eliminar(int prmId);     
}
