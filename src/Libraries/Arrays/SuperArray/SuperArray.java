package Libraries.Arrays.SuperArray;

import java.util.ArrayList;

/**
*  @author javiersolanop
*  @param K recibe el tipo de clave
*  @param O recibe el tipo de objeto que almacena
*/
public class SuperArray<K, O> {

    // Properties:
    private ArrayList<Element<K, O>> atrObjects;

    // Constructors:
    public SuperArray(){ atrObjects = new ArrayList<>(); }
    
    /**
     *  Metodo para validar si el arreglo esta vacio.
     * 
     *  @return 'true' si esta vacio. 'false' si no.
     */
    public boolean isEmpty(){ return atrObjects.isEmpty(); }

    /**
     *  Metodo para obtener el tamanio del arreglo.
     * 
     *  @return El tamanio.
     */
    public int size(){ return atrObjects.size(); }

    /**
     *  Metodo para validar si existe un elemento especifico en el arreglo.
     * 
     *  @param prmKey La clave del elemento.
     *  @return 'true' si existe. 'false' si no.
     */
    public boolean validateExistence(K prmKey)
    {
        for(Element<K, O> obj: atrObjects){
            if(obj.getKey().equals(prmKey))
                return true;
        }
        return false;
    }

    /**
     *  Metodo para adicionar un elemento al arreglo.
     * 
     *  @param prmKey recibe la clave del elemento en el arreglo.
     *  @param prmObject recibe el objeto del elemento.
     *  @return 'true' si se adiciona. 'false' si ya existe.
     */
    public boolean add(K prmKey, O prmObject)
    {
        if(!validateExistence(prmKey)){
            if(atrObjects.add(new Element<>(atrObjects.size(), prmKey, prmObject)))
                return true;
        }
        return false;
    }

    /**
     *  Metodo para obtener el objeto de un elemento especifico del arreglo.
     * 
     *  @param prmKey recibe la clave del elemento.
     *  @return El objeto, si existe el elemento. De lo contrario null.
     */
    public O get(K prmKey)
    {
        for(Element<K, O> obj: atrObjects){
            if(obj.getKey().equals(prmKey))
                return obj.getValue();
        }
        return null;
    }

    /**
     *  Metodo para obtener todos los elementos del arreglo.
     *  @return El arreglo.
     */
    public ArrayList<Element<K, O>> getAll(){ return atrObjects; }

    /**
     *  Metodo para obtener los objetos de cada elemento del arreglo.
     * 
     *  @return El arreglo de objetos.
     */
    public ArrayList<O> getAllObjects()
    {
        ArrayList<O> arr = new ArrayList<>();

        for(Element<K, O> obj: atrObjects)
            arr.add(obj.getValue());
        return arr;
    }

    /**
     *  Metodo para actualizar el objeto de un elemento especifico del arreglo.
     * 
     *  @param prmKey recibe la clave del elemento.
     *  @param prmObject recibe el nuevo objeto.
     *  @return 'true' si se actualiza. 'false' si no existe.
     */
    public boolean update(K prmKey, O prmObject)
    {
        for(Element<K, O> obj: atrObjects){

            if(obj.getKey().equals(prmKey)){

                obj.setValue(prmObject);
                atrObjects.set(obj.getIndex(), obj);
                return true;
            }
        }
        return false;
    }

    /**
     *  Metodo para remover un elemento especifico del arreglo.
     * 
     *  @param prmKey recibe la clave del elemento.
     *  @return 'true' si lo remueve. 'false' si no existe.
     */
    public boolean remove(K prmKey)
    {
        for(Element<K, O> obj: atrObjects){
            if(obj.getKey().equals(prmKey))
               return atrObjects.remove(obj);
        }
        return false;
    }
}
