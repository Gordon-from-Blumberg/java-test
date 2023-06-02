import org.junit.jupiter.api.Test;

import static java.lang.Byte.toUnsignedInt;

public class BitwiseTest {
    static final byte ALL_BITS_BYTE = (byte) 0b11111111;
    static final int ALL_BITS_INT = 0b11111111111111111111111111111111;

    @Test
    void andTest() {
        System.out.println("- - - AND TEST - - -");
        byte n = 0b00011010;
        printByte("n", n);
        byte m = (byte) 0b11000010;
        printByte("m", m);
        printByte("n&n", n & n);
        printByte("n&0", n & 0);
        printByte("n&m", n & m);
        printByte("all byte bits", ALL_BITS_BYTE);
        printByte("n & all bits", n & ALL_BITS_BYTE);
    }

    @Test
    void orTest() {
        System.out.println("- - - OR TEST - - -");
        byte n = 0b00011010;
        printByte("n", n);
        byte m = (byte) 0b11000010;
        printByte("m", m);
        printByte("n|n", n | n);
        printByte("n|0", n | 0);
        printByte("n|m", n | m);
        printByte("all byte bits", ALL_BITS_BYTE);
        printByte("n | all bits", n | ALL_BITS_BYTE);
    }

    @Test
    void notTest() {
        System.out.println("- - - NOT TEST - - -");
        byte n = 0b00011010;
        printByte("n", n);
        printByte("~n", ~n);
        byte m = (byte) 0b11000010;
        printByte("m", m);
        printByte("~m", ~m);
        printByte("~0", ~0);
        printByte("~5", ~5);
        printByte("all byte bits", ALL_BITS_BYTE);
        printByte("~ all bits", ~ALL_BITS_BYTE);
    }

    @Test
    void xorTest() {
        System.out.println("- - - XOR TEST - - -");
        byte n = 0b00011010;
        printByte("n", n);
        byte m = (byte) 0b11000010;
        printByte("m", m);
        printByte("n^n", n ^ n);
        printByte("n^0", n ^ 0);
        printByte("n^m", n ^ m);
        printByte("n^m^m", (n ^ m) ^ m);
        printByte("all byte bits", ALL_BITS_BYTE);
        printByte("n ^ all bits", n ^ ALL_BITS_BYTE);
    }

    @Test
    void rsTest() {
        System.out.println("- - - RIGHT SHIFT TEST - - -");
        byte n = 0b00011010;
        printByte("n", n);
        printByte("n<<1", n<<1);
        printByte("n<<2", n<<2);
        printByte("n<<28", n<<28);
        printByte("(n<<28)>>28", (n<<28)>>28);
        byte m = (byte) 0b11000010;
        printByte("m", m);
        printByte("m<<1", m<<1);
        printByte("m<<2", m<<2);
        printByte("all byte bits", ALL_BITS_BYTE);
        printByte("all bits<<1", ALL_BITS_BYTE << 1);
        printByte("(all bits<<1)>>1", (ALL_BITS_BYTE << 1)>>1);
    }

    @Test
    void lsTest() {
        System.out.println("- - - LEFT SHIFT (>>) TEST - - -");
        byte n = 0b00011010;
        printByte("n", n);
        printByte("n>>1", n>>1);
        printByte("n>>2", n>>2);
        printByte("n>>4", n>>4);
        printByte("(n>>4)<<4", (n>>4)<<4);
        byte m = (byte) 0b11000010;
        printByte("m", m);
        printByte("m>>1", m>>1);
        printByte("m>>2", m>>2);
        printByte("m>>4", m>>4);
        printByte("all byte bits", ALL_BITS_BYTE);
        printByte("all bits>>3", ALL_BITS_BYTE >> 3);
        printByte("(all bits>>3)<<3", (ALL_BITS_BYTE >> 3) << 3);
        printInt("all int bits", ALL_BITS_INT);
        printInt("all int bits >> 4", ALL_BITS_INT >> 4);
        printInt("(all int bits >> 4) << 4", (ALL_BITS_INT >> 4) << 4);
    }

    @Test
    void llsTest() {
        System.out.println("- - - LEFT SHIFT (>>>) TEST - - -");
        byte n = 0b00011010;
        printByte("n", n);
        printByte("n>>>1", n>>>1);
        printByte("n>>>2", n>>>2);
        printByte("n>>>4", n>>>4);
        printByte("(n>>>4)<<4", (n>>>4)<<4);
        byte m = (byte) 0b11000010;
        printByte("m", m);
        printByte("m>>>1", m>>>1);
        printByte("m>>>2", m>>>2);
        printByte("m>>>4", m>>>4);
        printByte("all byte bits", ALL_BITS_BYTE);
        printByte("all bits>>>3", ALL_BITS_BYTE >>> 3);
        printInt("all bits>>>3", ALL_BITS_INT >>> 3);
        printByte("(all bits>>>3)<<3", (ALL_BITS_BYTE >>> 3) << 3);
        printInt("all int bits", ALL_BITS_INT);
        printInt("all int bits >>> 4", ALL_BITS_INT >>> 4);
        printInt("(all int bits >>> 4) << 4", (ALL_BITS_INT >>> 4) << 4);
    }

    @Test
    void castNumbersTest() {
        System.out.println("- - - CAST NUMBERS TEST - - -");
        byte maxByte = Byte.MAX_VALUE;
        printByte("maxByte", maxByte);
        byte minByte = Byte.MIN_VALUE;
        printByte("minByte", minByte);
        byte n = -1;
        printByte("n", n);
        printInt("(int)maxByte", (int) maxByte);
        printInt("(int)minByte", (int) minByte);
        printInt("(int)n", (int) n);

        int maxBytePlusOne = maxByte + 1;
        printInt("maxByte + 1", maxBytePlusOne);
        printByte("maxByte + 1 as byte", (byte) maxBytePlusOne);
        printInt("maxByte + 10", maxByte + 10);
        printByte("maxByte + 10 as byte", (byte) (maxByte + 10));
        printInt("minByte - 2", minByte - 2);
        printByte("minByte - 2 as byte", (byte) (minByte - 2));
    }

    void printByte(String varName, int val) {
        System.out.println(varName + " (value) = " + (byte) val);
        System.out.println(varName + " (bits)  = " + byteToString(val));
    }

    void printInt(String varName, int val) {
        System.out.println(varName + " (value) = " + val);
        System.out.println(varName + " (bits)  = " + Integer.toBinaryString(val));
    }

    String byteToString(int b) {
        return Integer.toBinaryString(toUnsignedInt((byte) b));
    }
}
