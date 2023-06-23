package modelo.interfaces;

import modelo.EmpresaModelo;

/**
 * @author javiersolanop
 */
public interface iEmpresa {
    
    /**
     *  Metodo para guardar la empresa en su coleccion
     * 
     *  @return 'true' si se guarda. 'false' si no.
     */
    boolean guardar();
    
    /**
     *  Metodo para buscar una empresa especifica en su coleccion.
     *
     *  @param prmId Recibe el 'id' a buscar
     *  @param prmEmpresa Recibe la nueva instancia de la empresa
     *  @return 'true' si se actualiza. 'false' si no existe.
     */
    boolean actualizar(int prmId, EmpresaModelo prmEmpresa);

    /**
     *  Metodo para eliminar una empresa especifica en su coleccion.
     * 
     *  @param prmId Recibe el 'id' a buscar
     *  @return 'true' si se elimina. 'false' si no existe.
     */    
    boolean eliminar(int prmId);
}
