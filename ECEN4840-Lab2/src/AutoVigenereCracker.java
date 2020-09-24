public class AutoVigenereCracker {
    public static void main(String[] args) {
        String keypool = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        String plaintxt1 = "YOUR PACKAGE WILL BE READY BY FRIDAY";
        String plaintxt2 = "LOST SCARLET WITH THE KNIFE IN THE LIBRARY";
        String plaintxt3 = "MEET ME ON SUNDAY AT SIX AM";
        String plaintxt4 = "MR MUSTARD WITH THE CANDLESTICK IN THE HALL";
        String cipher = "ANKYODKYUREPFJBYOJDSPLREYIUNOFDOIUERFPLUYTS";

        // Padded for display output cleanliness
        System.out.println(plaintxt1 + "        ----->  " + getDecryption(plaintxt1, keypool, cipher));
        System.out.println(plaintxt2 + "  ----->  " + getDecryption(plaintxt2, keypool, cipher));
        System.out.println(plaintxt3 + "                 ----->  " + getDecryption(plaintxt3, keypool, cipher));
        System.out.println(plaintxt4 + " ----->  " + getDecryption(plaintxt4, keypool, cipher));;
    }

    static String getDecryption(String plaintxt, String keys, String ciphertxt) {
        ciphertxt = ciphertxt.toUpperCase();
        plaintxt = plaintxt.toUpperCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < plaintxt.length() - 1; i++) {
            int k = (((-plaintxt.charAt(i) + ciphertxt.charAt(i)) % 27));
            while (k < 0) {
                k += 27;
            }
            while (k > 26) {
                k -= 27;
            }
            sb.append(keys.charAt(k));
            if (sb.charAt(i) == '[') {       //  ASCII character shift mapped to space
                sb.setCharAt(i, ' ');           //if exists, replace with space
            }
            j = ++j % keys.length();
        }
        return sb.toString();
    }
}