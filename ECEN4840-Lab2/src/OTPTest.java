import java.util.*;

public class OTPTest {
    public static void main(String[] args){
        String text = "ANKYODKYUREPFJBYOJDSPLREYIUNOFDOIUERFPLUYTST";
        String key = RandomAlpha(text.length());

        String enc = OTPEncryption(text,key);
        System.out.println("CipherText : "+text);
        System.out.println("Decrypted : "+OTPDecryption(enc,key));
    }

    public static String RandomAlpha(int len){
        Random r = new Random();
        StringBuilder key = new StringBuilder();
        for(int x=0;x<len;x++)
            key.append((char) (r.nextInt(26) + ' ')); // Substitution cipher with 27 characters. Last is space.
        return key.toString();
    }

    public static String OTPEncryption(String text,String key){
        String alphaU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        String alphaL = "abcdefghijklmnopqrstuvwxyz ";

        int len = text.length();

        StringBuilder sb = new StringBuilder();
        for(int x=0;x<len;x++){
            char textChar = text.charAt(x);
            char keyget = key.charAt(x);
            if(Character.isUpperCase(textChar)){
                int index = alphaU.indexOf(textChar);
                int keydex = alphaU.indexOf(Character.toUpperCase(keyget));

                int total = (index + keydex) % 27;

                sb.append(alphaU.charAt(total));
            }
            else if(Character.isLowerCase(textChar)){
                int index = alphaL.indexOf(textChar);
                int keydex = alphaU.indexOf(Character.toLowerCase(keyget));

                int total = (index + keydex) % 27;

                sb.append(alphaL.charAt(total));
            }
            else{
                sb.append(textChar);
            }
        }

        return sb.toString();
    }
    public static String OTPDecryption(String text,String key){
        String alphaU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        String alphaL = "abcdefghijklmnopqrstuvwxyz ";

        int len = text.length();

        StringBuilder sb = new StringBuilder();
        for(int x=0;x<len;x++){
            char getChar = text.charAt(x);
            char keyget = key.charAt(x);
            if(Character.isUpperCase(getChar)){
                int index = alphaU.indexOf(getChar);
                int keydex = alphaU.indexOf(Character.toUpperCase(keyget));

                int total = (index - keydex) % 27;
                total = (total<0)? total + 27 : total;

                sb.append(alphaU.charAt(total));
            }
            else if(Character.isLowerCase(getChar)){
                int index = alphaL.indexOf(getChar);
                int keydex = alphaU.indexOf(Character.toLowerCase(keyget));

                int total = (index - keydex) % 27;
                if (total < 0)
                    total = total + 27;
                else total = total;

                sb.append(alphaL.charAt(total));
            }
            else{
                sb.append(getChar);
            }
        }

        return sb.toString();
    }

}