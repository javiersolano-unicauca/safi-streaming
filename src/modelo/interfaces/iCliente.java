package modelo.interfaces;

import modelo.ClienteModelo;

/**
 * @author javiersolanop
 */
public interface iCliente {
    
    /**
     *  Metodo para guardar el cliente en su coleccion
     * 
     *  @return 'true' si se guarda. 'false' si no.
     */
    boolean guardar();
    
    /**
     *  Metodo para buscar un cliente especifico en su coleccion.
     *
     *  @param prmId Recibe el 'id' a buscar
     *  @param prmCliente Recibe la nueva instancia del cliente
     *  @return 'true' si se actualiza. 'false' si no existe.
     */
    boolean actualizar(int prmId, ClienteModelo prmCliente);
    
    /**
     *  Metodo para eliminar un cliente especifico en su coleccion.
     * 
     *  @param prmId Recibe el 'id' a buscar
     *  @return 'true' si se elimina. 'false' si no existe.
     */
    boolean eliminar(int prmId);    
}
