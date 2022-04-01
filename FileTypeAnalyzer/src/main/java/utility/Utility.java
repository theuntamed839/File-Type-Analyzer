package utility;

public class Utility {
    public static byte[] getBytefromHex(String s) {
        byte[] ans = new byte[s.length() / 2];

        for (int i = 0; i < ans.length; i++) {
            int index = i * 2;

            // Using parseInt() method of Integer class
            int val = Integer.parseInt(s.substring(index, index + 2), 16);
            ans[i] = (byte) val;
        }
        return ans;
    }
}
