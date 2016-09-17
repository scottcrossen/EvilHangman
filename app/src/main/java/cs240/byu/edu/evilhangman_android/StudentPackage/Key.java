package cs240.byu.edu.evilhangman_android.StudentPackage;

/**
 * Created by slxn42 on 9/16/16.
 */
public class Key {
    private int hash_code=0;
    private String key=new String();
    public Key(int size){
        while(size-- > 0) key+="-";
    }
    public Key(String word, char c){
        for(int iter=0; iter<word.length(); iter++)
            if (word.charAt(iter) == c) {
                key += c;
                hash_code+=2^iter;
            }
            else
                key+="-";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key keyO = (Key)o;
        return key == keyO.key;
    }
    @Override
    public int hashCode() {
        return hash_code;
    }
    @Override
    public String toString() {
        return key;
    }
    public void catenate(Key keyO){
        for(int iter=0; iter<keyO.key.length(); iter++){
            if(keyO.key.charAt(iter) != '-' && key.charAt(iter)=='-')
                key=key.substring(0,iter)+keyO.key.charAt(iter)+key.substring(iter+1);
        }
    }
    public int count(){
        int output=0;
        for(int iter=0; iter<key.length(); iter++){
            if(key.charAt(iter) != '-' ) output++;
        }
        return output;
    }
}
