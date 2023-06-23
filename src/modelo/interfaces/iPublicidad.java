package modelo.interfaces;

import modelo.PublicidadModelo;

/**
 * @author javiersolanop
 */
public interface iPublicidad {

    /**
     *  Metodo para guardar una publicidad en su coleccion
     * 
     *  @return 'true' si se guarda. 'false' si no.
     */
    boolean guardar();

    /**
     *  Metodo para buscar una publicidad especifica en su coleccion.
     *
     *  @param prmId Recibe el 'id' a buscar
     *  @param prmPublicidad Recibe la nueva instancia de la publicidad
     *  @return 'true' si se actualiza. 'false' si no existe.
     */    
    boolean actualizar(int prmId, PublicidadModelo prmPublicidad);

    /**
     *  Metodo para eliminar una publicidad especifica en su coleccion.
     * 
     *  @param prmId Recibe el 'id' a buscar
     *  @return 'true' si se elimina. 'false' si no existe.
     */      
    boolean eliminar(int prmId);
}
