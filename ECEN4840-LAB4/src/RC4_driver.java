import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Main driver to test RC4
 */
public class RC4_driver {
    public static void main(String[] args) throws FileNotFoundException {
        char[] key = {'S', 'e', 'c', 'r', 'e', 't'};            // Secret key
        char[] keystream = new char[1024];
        int length = 16;                                           // 16-bit keystream length
        long startTime;

        System.out.print("\nReading File...\n");
        String fileStream = readFile("ECEN4840-LAB4/src/stream.css");   // input read file
        char[] plainText = fileStream.toCharArray();                            // convert to char array
        RC4 cipher = new RC4(key);
        cipher.KSA();
        cipher.PRNG(keystream, length);

        startTime = System.currentTimeMillis();
        System.out.print("\nStarting RC4 encryption...\n");
        char[] cipherText = encrypt(plainText, keystream, length);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.print("Encryption complete. [Task " + totalTime + "ms]\n\nEncrypted file stream:\n");

        System.out.print(new String(cipherText));

		startTime = System.currentTimeMillis();
		System.out.print("\n\nStarting RC4 decryption...\n");
		char[] decryptText = decrypt(cipherText, keystream, length);
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.print("Decryption complete. [Task " + totalTime + "ms]\n\nDecrypted file stream:\n");

		for (int i = 0; i < decryptText.length; i++) {
			System.out.print(decryptText[i]);
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

}
