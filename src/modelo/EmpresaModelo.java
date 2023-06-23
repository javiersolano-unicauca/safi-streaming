package modelo;

import Libraries.Arrays.JSON.JSON;
import Libraries.Files.File;
import java.util.ArrayList;
import modelo.interfaces.iEmpresa;

/**
 * @author javiersolanop
 */
public class EmpresaModelo extends Modelo implements iEmpresa {
    
    // Properties:
    private static final String ATR_NOMBRE_COLECCION = "Empresas";
    
    private Integer atrId;
    private String atrNombre;
    private String atrPais;
    private String atrDescripcion;
    
    // Constructors:
    public EmpresaModelo()
    {
        this.atrNombre = "No tiene";
        this.atrPais = "No tiene";
        this.atrDescripcion = "No tiene";
        this.atrColeccion = new File(ATR_PATH);
    }

    public EmpresaModelo(String prmNombre, String prmPais, String prmDescripcion) 
    {
        this.atrNombre = prmNombre;
        this.atrPais = prmPais;
        this.atrDescripcion = prmDescripcion;
        this.atrColeccion = new File(ATR_PATH);
    }
    
    private EmpresaModelo(Integer prmId, String prmNombre, String prmPais, String prmDescripcion)
    {
        this.atrId = prmId;
        this.atrNombre = prmNombre;
        this.atrPais = prmPais;
        this.atrDescripcion = prmDescripcion;
        this.atrColeccion = new File(ATR_PATH);
    }
    
    // Methods 'setter' and 'getter':
    public int getId() {
        return atrId;
    }

    public String getNombre() {
        return atrNombre;
    }

    public void setNombre(String prmNombre) {
        this.atrNombre = prmNombre;
    }

    public String getPais() {
        return atrPais;
    }

    public void setPais(String prmPais) {
        this.atrPais = prmPais;
    }

    public String getDescripcion() {
        return atrDescripcion;
    }

    public void setDescripcion(String prmDescripcion) {
        this.atrDescripcion = prmDescripcion;
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
        objJson.add("pais", atrPais);
        objJson.add("descripcion", atrDescripcion);

        if(atrJsons.add(objJson))
            varRespuesta = atrColeccion.exportTxt(ATR_NOMBRE_COLECCION, JSON.toStringArrayJSON(atrJsons));
        this.limpiarJsons();
        return varRespuesta;
    }

    @Override
    public boolean actualizar(int prmId, EmpresaModelo prmEmpresa) {
        
        boolean varRespuesta = false;
        atrJsons = this.obtenerColeccion(ATR_NOMBRE_COLECCION);
        Integer varIndice = obtenerIndice(prmId, atrJsons);
        
        if(varIndice >= 0){
            
            try{
            
                JSON objJson = atrJsons.get(varIndice);
                objJson.update("nombre", prmEmpresa.getNombre());
                objJson.update("pais", prmEmpresa.getPais());
                objJson.update("descripcion", prmEmpresa.getDescripcion());
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
     *  Metodo para obtener el arreglo de las empresas de la coleccion
     * 
     * @return El arreglo
     */
    public static ArrayList<EmpresaModelo> obtenerModelos()
    {
        EmpresaModelo objEmpresaModelo = new EmpresaModelo();
        ArrayList<JSON> arrObjJson = objEmpresaModelo.obtenerColeccion(ATR_NOMBRE_COLECCION);
        ArrayList<EmpresaModelo> arrEmpresaModelos = new ArrayList<>();
        
        for(JSON objJson: arrObjJson){
            
            arrEmpresaModelos.add(new EmpresaModelo(
                    (Integer)objJson.get("id"),
                    (String)objJson.get("nombre"),
                    (String)objJson.get("pais"),
                    (String)objJson.get("descripcion")
            ));
        }
        return arrEmpresaModelos;
    }
}