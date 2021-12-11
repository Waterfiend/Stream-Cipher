package edu.aub.security.a5;

/**
 * A class that stores several utility methods for our A5/1 Cipher.
 *
 * @author Abdallah M. Wahidi
 */
public class A51Util {

    /**
     * Used to convert a string to binary values. Used by encrypt()
     */
    public static int[] toBinary(String text) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String temp = Integer.toBinaryString(text.charAt(i));
            for (int j = temp.length(); j < 8; j++)
                temp = "0" + temp;
            s.append(temp);
        }
        String binaryStr = s.toString();
        int[] binary = new int[binaryStr.length()];
        for (int i = 0; i < binary.length; i++)
            binary[i] = Integer.parseInt(binaryStr.substring(i, i + 1));
        return binary;
    }

    /**
     * Used to convert a binary string to text equivalent Used by decrypt()
     */
    public static String toStr(String binary) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i <= binary.length() - 8; i += 8)
            s.append((char) Integer.parseInt(binary.substring(i, i + 8), 2));
        return s.toString();
    }
}
