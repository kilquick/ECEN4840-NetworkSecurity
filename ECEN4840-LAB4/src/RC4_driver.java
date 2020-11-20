import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


/**
 * Main driver class to test RC4 -- A lot of the code involving char[] arrays are commented out due to scope of assignment
 *                                  I originally wrote most of the logic with char arrays before realizing the assignment
 *                                  explicitly stated to use byte[] arrays.
 * @author tyler
 */
public class RC4_driver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Scanner fill = new Scanner(System.in);      // string scanner
        char[] key = {'F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F'}; // Secret key
        char[] keystream = new char[1024];
        int length = 8;                         // key length
        String fileStream;
        boolean useFile;
        byte[] stringBytes = {0};

        System.out.println("How many iterations?");
        int counter = input.nextInt();

        System.out.println("Use local file initially?");
        String temp = fill.nextLine();
        useFile = temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes");

        if (useFile) {
            System.out.print("\nReading File...\n");
            fileStream = readFile("ECEN4840-LAB4/src/stream.css");   // input read file

        } else {
            input = new Scanner(System.in);
            System.out.print("Please enter plaintext byte: ");
            fileStream = input.nextLine();
        }

        input.close();          // Close scanner
        fill.close();

        do {
          //  char[] plainText = fileStream.toCharArray();                            // convert to char array
          //  System.out.print("\nFile stream read into character array -->\n");
            System.out.println("Converting file stream into byte array...");
            try {
                stringBytes = fileStream.getBytes("ASCII");     //Assignment requested byte output
            } catch (UnsupportedEncodingException e){}
            RC4 cipher = new RC4(key);
            cipher.KSA();
            cipher.PRNG(keystream, length);

            // Encryption and time calculation for both char and byte arrays from  the input stream
            long startTime = System.currentTimeMillis();
            System.out.print("\nStarting RC4 encryption...\n");
         //   char[] cipherText = encrypt(plainText, keystream, length);
         //   long midTime = System.currentTimeMillis();
            byte[] cipherBytes = encrypt(stringBytes, keystream, length);
            long endTime = System.currentTimeMillis();
         //   long charTime = midTime - startTime;
         //   long byteTime = endTime - midTime;
            long totalTime = endTime - startTime;
        //    System.out.print("Encryption complete. [2 Tasks : " + totalTime + "ms]\n\nEncrypted [charArray] stream : " +
        //            "[Task " + charTime + "ms] -->\n");
        //    System.out.print(new String(cipherText));
            System.out.print("Decryption complete. [1 Task(s) : " + totalTime + "ms]\n");
    //        System.out.print("\nEncrypted [byteArray] stream -->\n");
    //        System.out.print(new String(cipherBytes));

            // Decryption and time calculation for bot char and byte arrays from the input stream
            startTime = System.currentTimeMillis();
            System.out.print("\n\nStarting RC4 decryption...\n");
            //char[] decryptText = decrypt(cipherText, keystream, length);
            //midTime = System.currentTimeMillis();
            byte[] decryptBytes = decrypt(cipherBytes, keystream, length);
            endTime = System.currentTimeMillis();
            //charTime = midTime - startTime;
            //byteTime = endTime - midTime;
            totalTime = endTime - startTime;

            //StringBuilder cb = new StringBuilder(); //char builder
            StringBuilder bb = new StringBuilder(); //byte builder


            //System.out.print("Decryption complete. [2 Tasks : " + totalTime + "ms]\n\nDecrypted [charArray] stream : "
              //      + "[Task " + charTime + "ms] -->\n");
            //for (char c : decryptText) {
            //    cb.append(c);
                //  System.out.print(decryptText[i]);
          //  }
          //  fileStream = cb.toString();     // update runtime filestream
          //  System.out.println(fileStream);
            System.out.print("Decryption complete. [1 Task(s) : " + totalTime + "ms]\n\n");
            System.out.print("Decrypted [byteArray] stream -->\n");

            for (byte decryptByte : decryptBytes) {
                bb.append(decryptByte);
                //	System.out.print(decryptBytes[i]);
            }
            fileStream = bb.toString();     // update runtime filestream
          //  fileStream = new String(decryptBytes, StandardCharsets.UTF_8);
            System.out.println(fileStream);
            counter--;
        } while (counter != 0);
    }

    /**
     * Reads in given file.
     * @param filename Name of the given file.
     * @return Returns file as a string.
     * @throws FileNotFoundException Exception if file not found.
     */
    public static String readFile(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
            sb.append("\n");
        }
        sc.close();
        return sb.toString();
    }

    /**
     * Encrypted the given plain text using the keystream.
     * @param plainText The plain text to encrypt.
     * @param keystream Keystream used to encrypt.
     * @param length The length of the keystream
     * @return Returns a char[] of encrypted text.
     */
    public static char[] encrypt(char[] plainText, char[] keystream, int length) {
        char[] cipherText = new char[plainText.length];
        for (int i = 0; i < plainText.length; i++) {
            cipherText[i] = (char) (plainText[i] ^ keystream[i % length]);
        }
        return cipherText;
    }

    // Overloaded encrpyt method, same as previous encrypt method except for byte arrays
    public static byte[] encrypt(byte[] plainBytes, char[] keystream, int length) {
        byte[] cipherBytes = new byte[plainBytes.length];
        for (int i = 0; i < plainBytes.length; i++) {
            cipherBytes[i] = (byte) (plainBytes[i] ^ keystream[i % length]);
        }
        return cipherBytes;
    }

    /**
     *
     * @param cipherText The ciphertext to decrypt.
     * @param keystream The keystream to decrypt the text with.
     * @param length The length of the keystream
     * @return A char[] of the decrypted text.
     */
    public static char[] decrypt(char[] cipherText, char[] keystream, int length) {
        return encrypt(cipherText, keystream, length);
    }

    // Overloaded decrypt method, same as previous decrypt method except for byte arrays
    public static byte[] decrypt(byte[] cipherBytes, char[] keystream, int length) {
        return encrypt(cipherBytes, keystream, length);
    }
}
