public class AutoVigenereCracker {
    public static void main(String[] args) {
        String keypool = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        String plain = "YOUR PACKAGE WILL BE READY BY FRIDAY";
        String cipher = "ANKYODKYUREPFJBYOJDSPLREYIUNOFDOIUERFPLUYTS";

        System.out.println(getDecryption(plain, keypool, cipher));
    }

    static String getDecryption(String plaintxt, String keys, String ciphertxt) {
        ciphertxt = ciphertxt.toUpperCase();
        plaintxt = plaintxt.toUpperCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < plaintxt.length() - 1; i++) {
            int k = ((-plaintxt.charAt(i) + ciphertxt.charAt(i) % 27));
            while (k < 0) {
                k += 27;
            }
            while (k > 26) {
                k -= 27;
            }
            sb.append(keys.charAt(k));
            if (sb.charAt(i) == '[') {
                sb.setCharAt(i, ' ');
            }
            j = ++j % keys.length();
        }
        return sb.toString();
    }
}