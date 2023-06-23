package modelo;

import Libraries.Arrays.JSON.JSON;
import Libraries.Files.File;
import java.util.ArrayList;
import modelo.interfaces.iCliente;

/**
 * @author javiersolanop
 */
public class ClienteModelo extends PersonaModel implements iCliente{
    
    // Properties:
    private static final String ATR_NOMBRE_COLECCION = "Clientes";
    
    private String atrFInicio;
    private String atrFFin;
    
    // Constructors:
    public ClienteModelo()
    {
        super();
        this.atrFInicio = "No tiene";
        this.atrFFin = "No tiene";
        atrColeccion = new File(ATR_PATH);
    }
    
    public ClienteModelo(String prmNombre, Long prmNIdentificacion, String prmCorreo, 
                         long prmTelefono, Long prmNCuentaBan, String prmBanco,
                         String prmFInicio, String prmFFin)
    {
        super(prmNombre, prmNIdentificacion, prmCorreo, prmTelefono, prmNCuentaBan, prmBanco);
        this.atrFInicio = prmFInicio;
        this.atrFFin = prmFFin;
        atrColeccion = new File(ATR_PATH);
    }
    
    private ClienteModelo(Integer prmId, String prmNombre, Long prmNIdentificacion, String prmCorreo, 
                         Long prmTelefono, Long prmNCuentaBan, String prmBanco,
                         String prmFInicio, String prmFFin)
    {
        super(prmId, prmNombre, prmNIdentificacion, prmCorreo, prmTelefono, prmNCuentaBan, prmBanco);
        this.atrFInicio = prmFInicio;
        this.atrFFin = prmFFin;
        atrColeccion = new File(ATR_PATH);
    }
  
    //  Methods 'setter' and 'getter':
    public String getFInicio() {
        return atrFInicio;
    }

    public void setFInicio(String prmFInicio) {
        this.atrFInicio = prmFInicio;
    }

    public String getFFin() {
        return atrFFin;
    }

    public void setFFin(String prmFFin) {
        this.atrFFin = prmFFin;
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
        objJson.add("fInicio", atrFInicio);
        objJson.add("fFin", atrFFin);

        if(atrJsons.add(objJson))
            varRespuesta = atrColeccion.exportTxt(ATR_NOMBRE_COLECCION, JSON.toStringArrayJSON(atrJsons));
        this.limpiarJsons();
        return varRespuesta;
    }

    @Override
    public boolean actualizar(int prmId, ClienteModelo prmCliente) {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        int varIndice = obtenerIndice(prmId, atrJsons);
        
        if(varIndice >= 0){
            
            try{
            
                JSON objJson = atrJsons.get(varIndice);
                objJson.update("nombre", prmCliente.getNombre());
                objJson.update("nIdentificacion", prmCliente.getNIdentificacion());
                objJson.update("correo", prmCliente.getCorreo());
                objJson.update("telefono", prmCliente.getTelefono());
                objJson.update("nCuentaBan", prmCliente.getNCuentaBan());
                objJson.update("banco", prmCliente.getBanco());
                objJson.update("fInicio", prmCliente.getFInicio());
                objJson.update("fFin", prmCliente.getFFin());
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
     *  Metodo para obtener el arreglo de los clientes de la coleccion
     * 
     *  @return El arreglo
     */
    public static ArrayList<ClienteModelo> obtenerModelos()
    {
        ClienteModelo objClienteModelo = new ClienteModelo();
        ArrayList<JSON> arrObjJson = objClienteModelo.obtenerColeccion(ATR_NOMBRE_COLECCION);
        ArrayList<ClienteModelo> arrClienteModelos = new ArrayList<>();
        
        for(JSON objJson: arrObjJson){
            
            arrClienteModelos.add(new ClienteModelo(
                    (Integer)objJson.get("id"),
                    (String)objJson.get("nombre"),
                    (Long)objJson.get("nIdentificacion"),
                    (String)objJson.get("correo"),
                    (Long)objJson.get("telefono"),
                    (Long)objJson.get("nCuentaBan"),  
                    (String)objJson.get("banco"),
                    (String)objJson.get("fInicio"),  
                    (String)objJson.get("fFin")                    
            ));
        }
        return arrClienteModelos;
    }
}
