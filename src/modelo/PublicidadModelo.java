package modelo;

import Libraries.Arrays.JSON.JSON;
import Libraries.Files.File;
import java.util.ArrayList;
import modelo.interfaces.iPublicidad;

/**
 * @author javiersolanop
 */
public class PublicidadModelo extends Modelo implements iPublicidad {
    
    // Properties:
    private static final String ATR_NOMBRE_COLECCION = "Publicidades";
    
    private Integer atrId;
    private String atrDescripcion;
    private Float atrCosto;
    
    // Constructors:
    public PublicidadModelo()
    {
        atrColeccion = new File(ATR_PATH);
    }
    
    public PublicidadModelo(String prmDescripcion, float prmCosto)
    {
        this.atrDescripcion = prmDescripcion;
        this.atrCosto = prmCosto;
        atrColeccion = new File(ATR_PATH);
    }

    private PublicidadModelo(Integer prmId, String prmDescripcion, Float prmCosto)
    {
        this.atrId = prmId;
        this.atrDescripcion = prmDescripcion;
        this.atrCosto = prmCosto;
        atrColeccion = new File(ATR_PATH);
    }    
    
    // Methods 'setter' and 'getter':
    public int getId() {
        return atrId;
    }

    public String getDescripcion() {
        return atrDescripcion;
    }

    public void setDescripcion(String prmDescripcion) {
        this.atrDescripcion = prmDescripcion;
    }

    public float getCosto() {
        return atrCosto;
    }

    public void setCosto(float prmCosto) {
        this.atrCosto = prmCosto;
    }
    
    // Methods:

    @Override
    public boolean guardar() {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        atrId = generarId(atrJsons);
        
        JSON objJson = new JSON();
        objJson.add("id", atrId);
        objJson.add("descripcion", atrDescripcion);
        objJson.add("costo", atrCosto);

        if(atrJsons.add(objJson))
            varRespuesta = atrColeccion.exportTxt(ATR_NOMBRE_COLECCION, JSON.toStringArrayJSON(atrJsons));
        this.limpiarJsons();
        return varRespuesta;
    }

    @Override
    public boolean actualizar(int prmId, PublicidadModelo prmPublicidad) {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        int varIndice = obtenerIndice(prmId, atrJsons);
        
        if(varIndice >= 0){
            
            try{
            
                JSON objJson = atrJsons.get(varIndice);
                objJson.update("descripcion", prmPublicidad.getDescripcion());
                objJson.update("costo", prmPublicidad.getCosto());
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
     *  Metodo para obtener el arreglo de las publicidades de la coleccion
     * 
     *  @return El arreglo
     */
    public static ArrayList<PublicidadModelo> obtenerModelos()
    {
        PublicidadModelo objPublicidadModelo = new PublicidadModelo();
        ArrayList<JSON> arrObjJson = objPublicidadModelo.obtenerColeccion(ATR_NOMBRE_COLECCION);
        ArrayList<PublicidadModelo> arrPublicidadModelos = new ArrayList<>();
        
        for(JSON objJson: arrObjJson){
            
            arrPublicidadModelos.add(new PublicidadModelo(
                    (Integer)objJson.get("id"),
                    (String)objJson.get("descripcion"),
                    (Float)objJson.get("precio")
            ));
        }
        return arrPublicidadModelos;
    }
}