package edu.aub.security.a5;

import lombok.Getter;

/**
 * An implementation of the A5/1 stream cipher, which will be used to encrypt and decrypt 64-bit keys for our JavaFX application.
 * <p>
 * {@see https://en.wikipedia.org/wiki/A5/1}.
 *
 * @author Abdallah M. Wahidi
 * Credits go to the initial authors.
 */
public class A51Cipher {

    /**
     * The key of the {@link A51Cipher}.
     */
    @Getter
    public String key = null;

    /**
     * The length of the Reg X.
     */
    private static final int REG_X_LENGTH = 19;
    /**
     * The length of the Reg Y.
     */
    private static final int REG_Y_LENGTH = 22;
    /**
     * The length of the Reg Y.
     */
    private static final int REG_Z_LENGTH = 23;

    /**
     * The reg X int array using it's length.
     */
    private final int[] regX = new int[REG_X_LENGTH];

    /**
     * The reg Y int array using it's length.
     */
    private final int[] regY = new int[REG_Y_LENGTH];

    /**
     * The reg Z int array using it's length.
     */
    private final int[] regZ = new int[REG_Z_LENGTH];

    /**
     * Encrypts the input plaintext using the A5/1 stream cipher.
     *
     * @param plaintext The plaintext that is being encrypted.
     * @return the ciphertext
     */
    public String encrypt(String plaintext) {
        StringBuilder text = new StringBuilder();
        int[] binary = A51Util.toBinary(plaintext);
        int[] stream = getKeystream(binary.length);
        for (int i = 0; i < binary.length; i++)
            text.append(binary[i] ^ stream[i]);
        return text.toString();
    }

    /**
     * Decrypts the output ciphertext using the A5/1 stream cipher.
     *
     * @param ciphertext The ciphertext to decrypt.
     * @return the plaintext
     */
    public String decrypt(String ciphertext) {
        StringBuilder s = new StringBuilder();
        int[] binary = new int[ciphertext.length()];
        int[] keystream = getKeystream(ciphertext.length());
        for (int i = 0; i < binary.length; i++) {
            binary[i] = Integer.parseInt(ciphertext.substring(i, i + 1));
            s.append(binary[i] ^ keystream[i]);
        }
        return A51Util.toStr(s.toString());
    }

    /**
     * Gets the majority using the x, y, and z parameters.
     *
     * @param x The x component.
     * @param y The y component.
     * @param z The z component.
     * @return the majority
     */
    private int getMajority(int x, int y, int z) {
        return x + y + z > 1 ? 1 : 0;
    }

    /**
     * Gets the keystream using the length of the input key.
     *
     * @param length The length of the key.
     * @return the keystream
     */
    protected int[] getKeystream(int length) {
        // 1. Instantiate registers and keystream
        int[] regX = this.regX.clone();
        int[] regY = this.regY.clone();
        int[] regZ = this.regZ.clone();
        int[] keystream = new int[length];
        // 2. Generate keystream bits
        for (int i = 0; i < length; i++) {
            // 2a. Calculate maj(x8, y10 ,z10)​
            int maj = this.getMajority(regX[8], regY[10], regZ[10]);

            // 2b. If necessary, step regX
            if (regX[8] == maj) {
                int newStart = regX[13] ^ regX[16] ^ regX[17] ^ regX[18];
                int[] temp = regX.clone();
                System.arraycopy(temp, 0, regX, 1, regX.length - 1);
                regX[0] = newStart;
            }

            // 2c. If necessary, step regY
            if (regY[10] == maj) {
                int newStart = regY[20] ^ regY[21];
                int[] temp = regY.clone();
                System.arraycopy(temp, 0, regY, 1, regY.length - 1);
                regY[0] = newStart;
            }

            // 2d. If necessary, step regZ
            if (regZ[10] == maj) {
                int newStart = regZ[7] ^ regZ[20] ^ regZ[21] ^ regZ[22];
                int[] temp = regZ.clone();
                System.arraycopy(temp, 0, regZ, 1, regZ.length - 1);
                regZ[0] = newStart;
            }
            keystream[i] = regX[18] ^ regY[21] ^ regZ[22];
        }
        return keystream;
    }
}