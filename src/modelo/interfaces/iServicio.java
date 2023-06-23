package modelo.interfaces;

import modelo.ServicioModelo;

/**
 * @author javiersolanop
 */
public interface iServicio {

    /**
     *  Metodo para guardar el servicio en su coleccion
     * 
     *  @return 'true' si se guarda. 'false' si no.
     */    
    boolean guardar();

    /**
     *  Metodo para buscar un servicio especifico en su coleccion.
     *
     *  @param prmId Recibe el 'id' a buscar
     *  @param prmServicio Recibe la nueva instancia del servicio
     *  @return 'true' si se actualiza. 'false' si no existe.
     */     
    boolean actualizar(int prmId, ServicioModelo prmServicio);

    /**
     *  Metodo para eliminar un servicio especifico en su coleccion.
     * 
     *  @param prmId Recibe el 'id' a buscar
     *  @return 'true' si se elimina. 'false' si no existe.
     */       
    boolean eliminar(int prmId);
}
