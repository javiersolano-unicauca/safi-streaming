package Libraries.Arrays.JSON;

import java.util.ArrayList;

import Libraries.Arrays.ChainOfCharacter.ChainOfCharacter;
import Libraries.Arrays.SuperArray.Element;
import Libraries.Arrays.SuperArray.SuperArray;
import Libraries.Consoles.Console;

/**
*  @author javiersolanop
*/
public class JSON  {
    
    // Properties:
    private SuperArray<String, Object> atrJSON;

    // Constructors:
    public JSON(){ atrJSON = new SuperArray<>(); }

    // Methods of object:
    
     /**
     *  Metodo para validar los tipos de caracteres de un valor de tipo 'String'
     * 
     * @param prmValue Recibe el valor
     * @return 'true' si los tipos de caracteres son permitidos. 'false' si no.
     */
    private boolean validateStringValue(String prmValue)
    {
        for(int i = 0; i < prmValue.length(); i++){

            if(((prmValue.charAt(i) < 32) || (prmValue.charAt(i) > 126)) && (prmValue.charAt(i) == 0))
                return false;
        }
        return true;
    }

    /**
     *  Metodo para validar el tipo de dato del valor recibido.
     * 
     * @param prmValue Recibe el valor
     * @return 'true' si el tipo de dato es valido. 'false' si no.
     */
    private boolean validateTypeValue(Object prmValue)
    {
        for(EnumJSON objTypeData: EnumJSON.values()){

            if(objTypeData.getTypeData().equals(prmValue.getClass().getSimpleName())){

                if(prmValue instanceof String)
                    return validateStringValue((String)prmValue);
                return true;
            }
        }
        return false;
    }

    /**
     *  Metodo para adicionar una clave y su valor.
     *  El tipo de dato del valor debe ser compatible con los
     *  definidos en el 'EnumJSON':
     *  @see EnumJSON
     * 
     *  @param prmKey Recibe la clave
     *  @param prmValue Recibe el valor
     *  @return 'true' si se adiciona. 'false' si el tipo de dato
     *  del valor no es valido o si ya existe esa clave.
     */
    public boolean add(String prmKey, Object prmValue)
    {
        if(validateTypeValue(prmValue))
            return atrJSON.add(prmKey, prmValue);
        return false;
    }

    /**
     *  Metodo para obtener el valor de una clave especifica.
     * 
     *  @param prmKey Recibe la clave
     *  @return El valor, si existe la clave. De lo contrario null
     */
    public Object get(String prmKey)
    {
        return atrJSON.get(prmKey);
    }

    /**
     *  Metodo para actualizar una clave especifica.
     * 
     * @param prmKey Recibe la clave
     * @param prmValue Recibe el nuevo valor
     * @return 'true' si se actualiza. 'false' si no existe esa clave.
     */
    public boolean update(String prmKey, Object prmValue)
    {
       return atrJSON.update(prmKey, prmValue);
    }

    /**
     *  Metodo para remover una clave especifica.
     * 
     * @param prmKey Recibe la clave
     * @return  true' si la remueve. 'false' si no existe.
     */
    public boolean remove(String prmKey)
    {
        return atrJSON.remove(prmKey);

    }

    /**
     *  Metodo para validar si el objeto esta vacio.
     * 
     * @return 'true' si esta vacio. 'false' si no
     */
    public boolean isEmpty(){ return atrJSON.isEmpty(); }

    /**
     *  Metodo para obtener el tamanio del objeto
     * 
     * @return El tamanio
     */
    public int size(){ return atrJSON.size(); }

    @Override
    public String toString()
    {
        ArrayList<Element<String, Object>> arrJSON = atrJSON.getAll();
        int varCount = 1, varSize = atrJSON.size();
        String varJSON = "{";

        for(Element<String, Object> objJSON: arrJSON){
            
            varJSON += "\""+objJSON.getKey()+"\":";
            varJSON += (objJSON.getValue() instanceof String) ? "\""+objJSON.getValue()+"\"" : objJSON.getValue();

            if(varCount < varSize)
                varJSON += ",";
            varCount++;
        }

        varJSON += "}";
        return varJSON;
    }

    /**
     *  Metodo para convertir el objeto a String con formato 'JSON'
     *  @return El String de 'JSON' con formato.
     */
    public String toFormattedString()
    {
        ArrayList<Element<String, Object>> arrJSON = atrJSON.getAll();
        int varCount = 1, varSize = atrJSON.size();
        String varJSON = "{\n";

        for(Element<String, Object> objJSON: arrJSON){
            
            varJSON += "  \""+objJSON.getKey()+"\":";
            varJSON += (objJSON.getValue() instanceof String) ? "\""+objJSON.getValue()+"\"" : objJSON.getValue();

            if(varCount < varSize)
                varJSON += ",";
            varJSON += "\n";
            varCount++;
        }

        varJSON += "}";
        return varJSON;
    }

    // Methods of class:

    /**
     *  Metodo para validar el tipo de dato de un valor que se encuentra en un String
     * 
     *  @param prmValue Recibe el valor
     *  @return El tipo de dato.
     */
    private static Object validateTypeData(String prmValue)
    {
        if(ChainOfCharacter.isNumberInt(prmValue))
            return ChainOfCharacter.parseNumberInt(prmValue);

        else if(ChainOfCharacter.isNumberReal(prmValue))
            return ChainOfCharacter.parseNumberReal(prmValue);

        else if(ChainOfCharacter.isBoolean(prmValue))
            return Boolean.parseBoolean(prmValue);

        return prmValue;
    }

    /**
     *  Metodo para parsear un String de 'JSON' a objeto
     * 
     *  @param prmJSON Recibe el String de 'JSON'
     *  @return El objeto
     */
    public static JSON parseStringJSON(String prmJSON)
    {
        JSON objJSON = new JSON();
        String varJSON = prmJSON.replace("{", "")
                                .replace("}", "")
                                .replace("\"", "");

        String[] arrJSON = varJSON.split(","),
                 arrRow;

        for(String row: arrJSON){
            arrRow = row.split(":");
            objJSON.add(arrRow[0], validateTypeData(arrRow[1]));
        }
        return objJSON;
    }

    /**
     *  Metodo para parsear un arreglo de String de 'JSON' a de objetos.
     * 
     *  @param prmJSON Recibe el arreglo de String de 'JSON'
     *  @return El arreglo de objetos 
     */
    public static ArrayList<JSON> parseStringArrayJSON(String[] prmJSON)
    {
        String[] arrJSON = prmJSON.clone();
        ArrayList<JSON> arrObjJsons = new ArrayList<>();

        arrJSON[0] = arrJSON[0].replace("[", "");
        arrJSON[arrJSON.length - 1] = arrJSON[arrJSON.length - 1].replace("]", "");

        for(String objJson: arrJSON)
            arrObjJsons.add(JSON.parseStringJSON(objJson.replace("},", "}")));
        return arrObjJsons;
    }

    /**
     *  Metodo para convertir el arreglo de objetos a de String de 'JSON'
     * 
     *  @param prmJsons Recibe el arreglo de objetos
     *  @return  El arreglo de String de 'JSON'
     */
    public static String[] toStringArrayJSON(ArrayList<JSON> prmJsons)
    {   
        int varSize = prmJsons.size();
        String[] arrJson = new String[varSize];

        arrJson[0] = (varSize > 1) ? "["+prmJsons.get(0).toString()+"," : "["+prmJsons.get(0).toString();

        for(int i = 1; i < varSize; i++){

            arrJson[i] = prmJsons.get(i).toString();

            if(i < (varSize - 1))
                arrJson[i] += ",";
        }

        arrJson[varSize - 1] += "]";
        return arrJson;
    }
}
