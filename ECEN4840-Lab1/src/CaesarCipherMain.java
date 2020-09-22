import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CaesarCipherMain {
    private final static List<Character> alphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                                                                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                                                                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
    private static boolean encode = false;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userChoice;
        char userChar;
        boolean ask;
        int key = 0;

        do {
            System.out.print("Type 'E' or 'D' to choose encryption mode or decryption mode: ");
            userChoice = input.nextLine();
            userChoice = userChoice.toUpperCase();
            userChar = userChoice.charAt(0);
            if (userChar == 'E' || userChar == 'D') {
                ask = false;
                if (userChar == 'E') {
                    System.out.print("\nPlease input a key: ");
                    userChoice = input.nextLine();
                    userChoice = userChoice.toUpperCase();
                    userChar = userChoice.charAt(0);
                    key = Character.getNumericValue(userChar) - 9;
                    encode = true;
                } else {
                    encode = false;
                }
            } else {
                System.out.println("\nYou've entered a wrong input.\nPlease try again.\n");
                ask = true;
            }
        } while (ask);

        System.out.print("\nPlease input a message for the Cesar Cipher: ");
        String message = input.nextLine();
        message = message.toUpperCase();


        String encodedMessage = messageEncoder(message, key, true);

        if (encode) {
            System.out.println("\nYour encoded new message is: " + encodedMessage);
        } else {
            messageCracker(encodedMessage);
        }
    }

    private static String messageEncoder(String message, int key, boolean transform){
        StringBuilder sb = new StringBuilder();
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
                        newCharacterNumber = (index+key)%26;
                    }
                    else{
                        newCharacterNumber = (index-key)%26;
                    }
                    sb.append(switcher(newCharacterNumber));
                }
            }
        }
        return sb.toString();
    }

    private static void messageCracker(String cipherText){
        for(int key = 1; key<26; key++){
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
                        newCharacterNumber = (index-key)%26;
                        sb.append(switcher(newCharacterNumber));
                    }
                }
            }
            System.out.println(key + ". " + sb);
        }
    }

    private static char switcher(int charInt){
        if (charInt < 0){
            charInt = 26-Math.abs(charInt);
            return(switcher(charInt));
        }
        else if(charInt >= 0 && charInt < alphabet.size()){
            return alphabet.get(charInt);
        }
        else return' ';
    }
}