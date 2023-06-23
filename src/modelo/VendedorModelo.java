package modelo;

import Libraries.Arrays.JSON.JSON;
import Libraries.Files.File;
import java.util.ArrayList;
import modelo.interfaces.iVendedor;

/**
 * @author javiersolanop
 */
public class VendedorModelo extends PersonaModel implements iVendedor {
    
    // Properties:
    private static final String ATR_NOMBRE_COLECCION = "Vendedores";
    
    // Constructors:
    public VendedorModelo()
    {
        super();
        atrColeccion = new File(ATR_PATH);
    }
    
    public VendedorModelo(String prmNombre, Long prmNIdentificacion, String prmCorreo, 
                          long prmTelefono, Long prmNCuentaBan, String prmBanco)
    {
        super(prmNombre, prmNIdentificacion, prmCorreo, prmTelefono, prmNCuentaBan, prmBanco);
        atrColeccion = new File(ATR_PATH);        
    }
    
    private VendedorModelo(Integer prmId, String prmNombre, Long prmNIdentificacion, String prmCorreo, 
                          Long prmTelefono, Long prmNCuentaBan, String prmBanco)
    {
        super(prmId, prmNombre, prmNIdentificacion, prmCorreo, prmTelefono, prmNCuentaBan, prmBanco);
        atrColeccion = new File(ATR_PATH);        
    }
    
    // Methods:

    @Override
    public boolean guardar() {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        atrId = generarId(atrJsons);
        
        JSON objJson = new JSON();
        objJson.add("id", atrId);
        objJson.add("nombre", atrNombre);
        objJson.add("nIdentificacion", atrNIdentificacion);
        objJson.add("correo", atrCorreo);
        objJson.add("telefono", atrTelefono);
        objJson.add("nCuentaBan", atrNCuentaBan);
        objJson.add("banco", atrBanco);

        if(atrJsons.add(objJson))
            varRespuesta = atrColeccion.exportTxt(ATR_NOMBRE_COLECCION, JSON.toStringArrayJSON(atrJsons));
        this.limpiarJsons();
        return varRespuesta;
    }

    @Override
    public boolean actualizar(int prmId, VendedorModelo prmVendedor) {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        int varIndice = obtenerIndice(prmId, atrJsons);
        
        if(varIndice >= 0){
            
            try{
            
                JSON objJson = atrJsons.get(varIndice);
                objJson.update("nombre", prmVendedor.getNombre());
                objJson.update("nIdentificacion", prmVendedor.getNIdentificacion());
                objJson.update("correo", prmVendedor.getCorreo());
                objJson.update("telefono", prmVendedor.getTelefono());
                objJson.update("nCuentaBan", prmVendedor.getNCuentaBan());
                objJson.update("banco", prmVendedor.getBanco());
                atrJsons.set(varIndice, objJson);

                varRespuesta = atrColeccion.exportTxt(ATR_NOMBRE_COLECCION, JSON.toStringArrayJSON(atrJsons));

            }catch(IndexOutOfBoundsException e){}
        }
        this.limpiarJsons();
        return varRespuesta;
    }

    @Override
    public boolean eliminar(int prmId) {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        int varIndice = obtenerIndice(prmId, atrJsons);
        
        if(varIndice >= 0){
        
            try{
                atrJsons.remove(varIndice);
                varRespuesta = atrColeccion.exportTxt(ATR_NOMBRE_COLECCION, JSON.toStringArrayJSON(atrJsons));
            }catch(IndexOutOfBoundsException e){}
        }
        this.limpiarJsons();
        return varRespuesta;
    }
    
    /**
     *  Metodo para obtener el arreglo de los vendedores de la coleccion
     * 
     *  @return El arreglo
     */
    public static ArrayList<VendedorModelo> obtenerModelos()
    {
        VendedorModelo objVendedorModelo = new VendedorModelo();
        ArrayList<JSON> arrObjJson = objVendedorModelo.obtenerColeccion(ATR_NOMBRE_COLECCION);
        ArrayList<VendedorModelo> arrVendedorModelos = new ArrayList<>();
        
        for(JSON objJson: arrObjJson){
            
            arrVendedorModelos.add(new VendedorModelo(
                    (Integer)objJson.get("id"),
                    (String)objJson.get("nombre"),
                    (Long)objJson.get("nIdentificacion"),
                    (String)objJson.get("correo"),
                    (Long)objJson.get("telefono"),
                    (Long)objJson.get("nCuentaBan"),  
                    (String)objJson.get("banco")
            ));
        }
        return arrVendedorModelos;
    }
}
