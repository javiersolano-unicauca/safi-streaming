package Libraries.Arrays.SuperArray;

/**
 *  @author javiersolanop
 *  @param K tipo de clave.
 *  @param V tipo de valor.
 */
public class Element<K, V> {

    // Properties:
    private int atrIndex;
    private K atrKey;
    private V atrValue;

    // Constructors:
    public Element(int prmIndex, K prmKey, V prmValue)
    {
        atrIndex = prmIndex;
        atrKey = prmKey; 
        atrValue = prmValue;
    }

    // Methods 'getter' and 'setter':
    public int getIndex(){ return atrIndex; }

    public K getKey(){ return atrKey; }

    public void setKey(K prmKey){ atrKey = prmKey; }

    public V getValue(){ return atrValue; }

    public void setValue(V prmValue){ atrValue = prmValue; }
}
