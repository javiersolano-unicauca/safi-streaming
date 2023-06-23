package modelo;

import Libraries.Arrays.JSON.JSON;
import Libraries.Files.File;
import java.util.ArrayList;
import modelo.Enums.EnumTipoServicio;
import modelo.interfaces.iServicio;

/**
 * @author javiersolanop
 */
public class ServicioModelo extends Modelo implements iServicio  {
    
    // Properties:
    private static final String ATR_NOMBRE_COLECCION = "Servicios";

    private Integer atrId;
    private String atrNomPlataforma;
    private Float atrPrecio;
    
    // Constructors:
    public ServicioModelo()
    {
        this.atrNomPlataforma = "No tiene";
        this.atrPrecio = (float)0;
        this.atrColeccion = new File(ATR_PATH);
    }
    
    public ServicioModelo(EnumTipoServicio prmNomPlataforma, float prmPrecio)
    {
        this.atrNomPlataforma = prmNomPlataforma.name();
        this.atrPrecio = prmPrecio;
        this.atrColeccion = new File(ATR_PATH);
    }
    
    private ServicioModelo(Integer prmId, String prmNomPlataforma, Float prmPrecio)
    {
        this.atrId = prmId;
        this.atrNomPlataforma = prmNomPlataforma;
        this.atrPrecio = prmPrecio;
        this.atrColeccion = new File(ATR_PATH);
    }
    
    // Methods 'setter' and 'getter':
    public int getId()
    {
        return atrId;
    }
    
    public String getNomPlataforma() {
        return atrNomPlataforma;
    }

    public void setNomPlataforma(EnumTipoServicio prmNomPlataforma) {
        this.atrNomPlataforma = prmNomPlataforma.name();
    }

    public float getPrecio() {
        return atrPrecio;
    }

    public void setPrecio(float prmPrecio) {
        this.atrPrecio = prmPrecio;
    }
    
    // Methods:
    
    @Override
    public boolean guardar() {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        atrId = generarId(atrJsons);
        
        JSON objJson = new JSON();
        objJson.add("id", atrId);
        objJson.add("nomPlataforma", atrNomPlataforma);
        objJson.add("precio", atrPrecio);

        if(atrJsons.add(objJson))
            varRespuesta = atrColeccion.exportTxt(ATR_NOMBRE_COLECCION, JSON.toStringArrayJSON(atrJsons));
        this.limpiarJsons();
        return varRespuesta;
    }

    @Override
    public boolean actualizar(int prmId, ServicioModelo prmServicio) {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        int varIndice = obtenerIndice(prmId, atrJsons);
        
        if(varIndice >= 0){
            
            try{
            
                JSON objJson = atrJsons.get(varIndice);
                objJson.update("nomPlataforma", prmServicio.getNomPlataforma());
                objJson.update("precio", prmServicio.getPrecio());
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
     *  Metodo para obtener el arreglo de los servicios de la coleccion
     * 
     *  @return El arreglo
     */
    public static ArrayList<ServicioModelo> obtenerModelos()
    {
        ServicioModelo objEmpresaModelo = new ServicioModelo();
        ArrayList<JSON> arrObjJson = objEmpresaModelo.obtenerColeccion(ATR_NOMBRE_COLECCION);
        ArrayList<ServicioModelo> arrServicioModelos = new ArrayList<>();
        
        for(JSON objJson: arrObjJson){
            
            arrServicioModelos.add(new ServicioModelo(
                    (Integer)objJson.get("id"),
                    (String)objJson.get("nomPlataforma"),
                    (Float)objJson.get("precio")
            ));
        }
        return arrServicioModelos;
    }
}
