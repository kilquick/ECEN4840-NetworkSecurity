import java.util.Arrays;
import java.util.List;


public class OTPCipher {
    // Using substitution cipher with 27 characters per assignment.
    // Last character is a space.
    private final static List<Character> alphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                                                    'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                                                    'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ');

    public static void main(String[] args) {
        String encodedMessage = "ANKYODKYUREPFJBYOJDSPLREYIUNOFDOIUERFPLUYTS ";

    }
    private static String messageEncoder(String message, int key, boolean transform){
        StringBuffer sb = new StringBuffer();
        for (int ii = 0; ii<message.length(); ii++){
            int newCharacterNumber = 0;
            char character = message.charAt(ii);
            if(character == ' '){
                sb.append(' ');
            }
            else{
                int index = alphabet.indexOf(character);
                if(index == -1){
                    sb.append(character);
                }
                else{
                    if(transform){
                        newCharacterNumber = (index+key)%27;
                    }
                    else{
                        newCharacterNumber = (index-key)%27;
                    }
                    sb.append(switcher(newCharacterNumber));
                }
            }
        }
        return sb.toString();
    }

    private static void messageCracker(String cipherText){
        for(int key = 1; key<27; key++){
            StringBuffer sb = new StringBuffer();

            for(int ii = 0; ii<cipherText.length(); ii++){
                int newCharacterNumber = 0;
                char character = cipherText.charAt(ii);
                if(character == ' '){
                    sb.append(' ');
                }
                else{
                    int index = alphabet.indexOf(character);
                    if(index == -1){
                        sb.append(character);
                    }
                    else{
                        newCharacterNumber = (index-key)%27;
                        sb.append(switcher(newCharacterNumber));
                    }
                }
            }
            System.out.println(key + ". " + sb);
        }
    }

    private static char switcher(int charInt){
        if (charInt < 0){
            charInt = 27-Math.abs(charInt);
            return(switcher(charInt));
        }
        else if(charInt >= 0 && charInt < alphabet.size()){
            return alphabet.get(charInt);
        }
        else return' ';
    }
}