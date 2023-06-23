package modelo;

import Libraries.Arrays.JSON.JSON;
import Libraries.Files.File;
import java.util.ArrayList;
import modelo.interfaces.iPublicista;

/**
 * @author javiersolanop
 */
public class PublicistaModelo extends PersonaModel implements iPublicista {
    
    // Properties:
    private static final String ATR_NOMBRE_COLECCION = "Publicistas";
    
    private String atrCuentInstagram;
    
    // Constriuctors:
    public PublicistaModelo()
    {
        super();
        this.atrCuentInstagram = "No tiene";
        atrColeccion = new File(ATR_PATH);
    }
    
    public PublicistaModelo(String prmNombre, Long prmNIdentificacion, String prmCorreo, 
                           long prmTelefono, Long prmNCuentaBan, String prmBanco, String prmCuentInstagram)
    {
        super(prmNombre, prmNIdentificacion, prmCorreo, prmTelefono, prmNCuentaBan, prmBanco);
        this.atrCuentInstagram = prmCuentInstagram;
        atrColeccion = new File(ATR_PATH);
    }
    
    private PublicistaModelo(Integer prmId, String prmNombre, Long prmNIdentificacion, String prmCorreo, 
                           Long prmTelefono, Long prmNCuentaBan, String prmBanco, String prmCuentInstagram)
    {
        super(prmId, prmNombre, prmNIdentificacion, prmCorreo, prmTelefono, prmNCuentaBan, prmBanco);
        this.atrCuentInstagram = prmCuentInstagram;
        atrColeccion = new File(ATR_PATH);
    } 
    
    // Methods 'setter' and 'getter':
    public String getCuentInstagram() {
        return atrCuentInstagram;
    }

    public void setCuentInstagram(String prmCuentInstagram) {
        this.atrCuentInstagram = prmCuentInstagram;
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
        objJson.add("cuentInstagram", atrCuentInstagram);

        if(atrJsons.add(objJson))
            varRespuesta = atrColeccion.exportTxt(ATR_NOMBRE_COLECCION, JSON.toStringArrayJSON(atrJsons));
        this.limpiarJsons();
        return varRespuesta;
    }

    @Override
    public boolean actualizar(int prmId, PublicistaModelo prmPublicista) {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        int varIndice = obtenerIndice(prmId, atrJsons);
        
        if(varIndice >= 0){
            
            try{
            
                JSON objJson = atrJsons.get(varIndice);
                objJson.update("nombre", prmPublicista.getNombre());
                objJson.update("nIdentificacion", prmPublicista.getNIdentificacion());
                objJson.update("correo", prmPublicista.getCorreo());
                objJson.update("telefono", prmPublicista.getTelefono());
                objJson.update("nCuentaBan", prmPublicista.getNCuentaBan());
                objJson.update("banco", prmPublicista.getBanco());
                objJson.update("cuentInstagram", prmPublicista.getCuentInstagram());
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
     *  Metodo para obtener el arreglo de los publicistas de la coleccion
     * 
     *  @return El arreglo
     */
    public static ArrayList<PublicistaModelo> obtenerModelos()
    {
        PublicistaModelo objPublicistaModelo = new PublicistaModelo();
        ArrayList<JSON> arrObjJson = objPublicistaModelo.obtenerColeccion(ATR_NOMBRE_COLECCION);
        ArrayList<PublicistaModelo> arrPublicistaModelos = new ArrayList<>();
        
        for(JSON objJson: arrObjJson){
            
            arrPublicistaModelos.add(new PublicistaModelo(
                    (Integer)objJson.get("id"),
                    (String)objJson.get("nombre"),
                    (Long)objJson.get("nIdentificacion"),
                    (String)objJson.get("correo"),
                    (Long)objJson.get("telefono"),
                    (Long)objJson.get("nCuentaBan"),  
                    (String)objJson.get("banco"),
                    (String)objJson.get("cuentInstagram")
            ));
        }
        return arrPublicistaModelos;
    }
}
