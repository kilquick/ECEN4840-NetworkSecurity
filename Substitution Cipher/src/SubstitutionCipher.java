public class SubstitutionCipher {

    final static String key = "]kYV}(!7P$n5_0i R:?jOWtF/=-pe'AD&@r6%ZXs\"v*N"
            + "[#wSl9zq2^+g;LoB`aGh{3.HIu4fbK)mU8|dMET><,Qc\\C1yxJ";

    static String text = "It seems that the tyrant overlords are onto my scheme of having "
            + "my coffee and drink it too under the Bean Tea Misnomer Act of 2020.  "
            + "They have confiscated all my beans and left me with hefty fines. "
            + "God only knows what the unjust courts will do with my fate. Going forth all "
            + "communique will be encoded with this embedded key and general substitution cipher encryption.";

    public static void main(String[] args) {
        String enc = encode(text);
        System.out.println("Encoded: " + enc);
        System.out.println("\nDecoded: " + decode(enc));
    }

    static String encode(String s) {
        StringBuilder sb = new StringBuilder(s.length());

        for (char c : s.toCharArray())
            sb.append(key.charAt((int) c - 32));

        return sb.toString();
    }

    static String decode(String s) {
        StringBuilder sb = new StringBuilder(s.length());

        for (char c : s.toCharArray())
            sb.append((char) (key.indexOf((int) c) + 32));

        return sb.toString();
    }
}