package modelo;

import Libraries.Arrays.JSON.JSON;
import Libraries.Files.File;
import java.util.ArrayList;
import modelo.interfaces.iVentaServicioModelo;

/**
 * @author javiersolanop
 */
public class VentaServicioModelo extends Modelo implements iVentaServicioModelo {
    
    // Properties:
    private static final String ATR_NOMBRE_COLECCION = "VentaServicios";    
    
    private Integer atrId;
    private String atrFechaAdq;
    
    // Constructors:
    public VentaServicioModelo()
    {
        atrFechaAdq = "No tiene";
        atrColeccion = new File(ATR_PATH);
    }
    
    public VentaServicioModelo(String prmFechaAdq)
    {
        this.atrFechaAdq = prmFechaAdq;
        atrColeccion = new File(ATR_PATH);
    }
    
    private VentaServicioModelo(Integer prmId, String prmFechaAdq)
    {
        this.atrId = prmId;
        this.atrFechaAdq = prmFechaAdq;
        atrColeccion = new File(ATR_PATH);
    }
    
    // Methods 'setter' and 'getter':
    public String getFechaAdq() {
        return atrFechaAdq;
    }

    public void setFechaAdq(String prmFechaAdq) {
        this.atrFechaAdq = prmFechaAdq;
    }
    
    // Methods:

    @Override
    public boolean guardar() {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        atrId = generarId(atrJsons);
        
        JSON objJson = new JSON();
        objJson.add("id", atrId);
        objJson.add("fechaAdq", atrFechaAdq);

        if(atrJsons.add(objJson))
            varRespuesta = atrColeccion.exportTxt(ATR_NOMBRE_COLECCION, JSON.toStringArrayJSON(atrJsons));
        this.limpiarJsons();
        return varRespuesta;
    }

    @Override
    public boolean actualizar(int prmId, VentaServicioModelo prmVentaServicio) {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        int varIndice = obtenerIndice(prmId, atrJsons);
        
        if(varIndice >= 0){
            
            try{
            
                JSON objJson = atrJsons.get(varIndice);
                objJson.update("fechaAdq", prmVentaServicio.getFechaAdq());
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
     *  Metodo para obtener el arreglo de la venta de los servicios de la coleccion
     * 
     *  @return El arreglo
     */
    public static ArrayList<VentaServicioModelo> obtenerModelos()
    {
        VentaServicioModelo objVentaServicioModelo = new VentaServicioModelo();
        ArrayList<JSON> arrObjJson = objVentaServicioModelo.obtenerColeccion(ATR_NOMBRE_COLECCION);
        ArrayList<VentaServicioModelo> arrVentaServicioModelos = new ArrayList<>();
        
        for(JSON objJson: arrObjJson){
            
            arrVentaServicioModelos.add(new VentaServicioModelo(
                    (Integer)objJson.get("id"),
                    (String)objJson.get("fechaAdq")
            ));
        }
        return arrVentaServicioModelos;
    }
}