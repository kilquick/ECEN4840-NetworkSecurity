import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
/**
 * Main driver to test RC4
 */
public class RC4_driver {
    public static void main(String[] args) throws FileNotFoundException {
        char[] key = {'K', 'e', 'y'};            // Secret key
        char[] keystream = new char[1024];
        int length = 16;                         // 16-bit keystream length

        System.out.print("\nReading File...\n");
        String fileStream = readFile("ECEN4840-LAB4/src/stream.css");   // input read file
        char[] plainText = fileStream.toCharArray();                            // convert to char array
        System.out.print("\nFile stream read into character array -->\n");
        System.out.println("Converting file stream into byte array...");
        byte[] plainBytes = fileStream.getBytes(StandardCharsets.US_ASCII);     //Assignment requested byte output
        RC4 cipher = new RC4(key);
        cipher.KSA();
        cipher.PRNG(keystream, length);

        // Encryption and time calculation for both char and byte arrays from  the input stream
        long startTime = System.currentTimeMillis();
        System.out.print("\nStarting RC4 encryption...\n");
        char[] cipherText = encrypt(plainText, keystream, length);
        long midTime = System.currentTimeMillis();
        byte[] cipherBytes = encrypt(plainBytes, keystream, length);
        long endTime   = System.currentTimeMillis();
        long charTime = midTime - startTime;
        long byteTime = endTime - midTime;
        long totalTime = endTime - startTime;
        System.out.print("Encryption complete. [2 Tasks : " + totalTime + "ms]\n\nEncrypted [charArray] stream : " +
                "[Task " + charTime + "ms] -->\n");
        System.out.print(new String(cipherText));
        System.out.print("\n\nEncrypted [byteArray] stream : [Task " + byteTime + "ms] -->\n");
        System.out.print(new String(cipherBytes));

        // Decryption and time calculation for bot char and byte arrays from the input stream
		startTime = System.currentTimeMillis();
		System.out.print("\n\nStarting RC4 decryption...\n");
		char[] decryptText = decrypt(cipherText, keystream, length);
		midTime = System.currentTimeMillis();
        byte[] decryptBytes = decrypt(cipherBytes, keystream, length);
		endTime = System.currentTimeMillis();
		charTime = midTime - startTime;
		byteTime = endTime - midTime;
		totalTime = endTime - startTime;
        System.out.print("Decryption complete. [2 Tasks : " + totalTime + "ms]\n\nDecrypted [charArray] stream : "
                + "[Task " + charTime + "ms] -->\n");
        for (int i = 0; i < decryptText.length; i++){
            System.out.print(decryptText[i]);
        }
		System.out.print("\nDecrypted [byteArray] stream : [Task " + byteTime + "ms] -->\n");
		for (int i = 0; i < decryptBytes.length; i++) {
			System.out.print(decryptBytes[i]);
		}
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

    public static byte[] decrypt(byte[] cipherBytes, char[] keystream, int length) {
        return encrypt(cipherBytes, keystream, length);
    }
}
