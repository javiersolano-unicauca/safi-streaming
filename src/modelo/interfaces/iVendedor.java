package modelo.interfaces;

import modelo.VendedorModelo;

/**
 * @author javiersolanop
 */
public interface iVendedor {

    /**
     *  Metodo para guardar el vendedor en su coleccion
     * 
     *  @return 'true' si se guarda. 'false' si no.
     */     
    boolean guardar();

    /**
     *  Metodo para buscar un vendedor especifico en su coleccion.
     *
     *  @param prmId Recibe el 'id' a buscar
     *  @param prmVendedor Recibe la nueva instancia del vendedor
     *  @return 'true' si se actualiza. 'false' si no existe.
     */    
    boolean actualizar(int prmId, VendedorModelo prmVendedor);
 
    /**
     *  Metodo para eliminar un vendedor especifico en su coleccion.
     * 
     *  @param prmId Recibe el 'id' a buscar
     *  @return 'true' si se elimina. 'false' si no existe.
     */ 
    boolean eliminar(int prmId); 
}
