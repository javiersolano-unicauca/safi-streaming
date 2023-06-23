package modelo.interfaces;

import modelo.PublicistaModelo;

/**
 * @author javiersolanop
 */
public interface iPublicista {

    /**
     *  Metodo para guardar el publicista en su coleccion
     * 
     *  @return 'true' si se guarda. 'false' si no.
     */
    boolean guardar();

    /**
     *  Metodo para buscar un publicista especifico en su coleccion.
     *
     *  @param prmId Recibe el 'id' a buscar
     *  @param prmPublicista Recibe la nueva instancia del publicista
     *  @return 'true' si se actualiza. 'false' si no existe.
     */    
    boolean actualizar(int prmId, PublicistaModelo prmPublicista);

    /**
     *  Metodo para eliminar un publicista especifico en su coleccion.
     * 
     *  @param prmId Recibe el 'id' a buscar
     *  @return 'true' si se elimina. 'false' si no existe.
     */       
    boolean eliminar(int prmId);
}
