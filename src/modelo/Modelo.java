package modelo;

import Libraries.Arrays.JSON.JSON;
import Libraries.Files.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author javiersolanop
 */
public abstract class Modelo {
    
    // Properties:
    protected static final String ATR_PATH = "C:\\Users\\ROOT\\Documents\\NetBeansProjects\\SafiStreaming\\src\\modelo\\Colecciones"; 
    protected File atrColeccion;
    protected ArrayList<JSON> atrJsons;
    
    /**
     *  Metodo para obtener una coleccion especifica de un archivo en formato txt
     * 
     *  @param prmNombreColeccion Recibe el nombre del archivo
     *  @return El arreglo con los objetos JSON de la coleccion o uno vacio en el caso de que no tenga
     */
    protected ArrayList<JSON> obtenerColeccion(String prmNombreColeccion)
    {
        String[] varColeccion = atrColeccion.importTxt(prmNombreColeccion);
        
        if((varColeccion != null) && (!varColeccion[0].isEmpty()))
            return JSON.parseStringArrayJSON(varColeccion);
        return new ArrayList<>();
    }
    
    /**
     *  Metodo para liberar memoria de la propiedad atrJsons
     */
    protected void limpiarJsons()
    {
        atrJsons = null;
    }
    
    /**
     *  Metodo para generar un 'id' con base en los existentes del arreglo de objetos JSON
     * 
     *  @param prmJsons Recibe el arreglo de objetos JSON
     *  @return El id generado
     */
    protected Integer generarId(ArrayList<JSON> prmJsons)
    {
        return (!prmJsons.isEmpty()) ? (Integer)prmJsons.get(prmJsons.size() - 1).get("id") + 1 : 1;
    }
    
    /**
     *  Metodo para validar si existe un objeto especifico en un arreglo de objetos JSON.
     *  
     *  @param prmId Recibe el 'id' a buscar
     *  @param prmJsons Recibe el arreglo de objetos JSON
     *  @return 'true' si existe. 'false' si no.
     */
    protected boolean validarExistencia(Integer prmId, ArrayList<JSON> prmJsons)
    {
        for(JSON objJson: prmJsons){
            
            if(Objects.equals((Integer)objJson.get("id"), prmId))
                return true;
        }
        return false;
    }
    
    /**
     *  Metodo para obtener el indice de la posicion que ocupa un objeto especifico
     *  en un arreglo de objetos JSON
     * 
     *  @param prmId Recibe el 'id' a buscar
     *  @param prmJsons Recibe el arreglo de objetos JSON 
     *  @return El indice del objeto o -1 en el caso de que no exista
     */
    protected Integer obtenerIndice(Integer prmId, ArrayList<JSON> prmJsons)
    {
        for(int i = 0; i < prmJsons.size(); i++){
            
            if(Objects.equals((Integer)prmJsons.get(i).get("id"), prmId))
                return i;
        }
        return -1;
    }
}
