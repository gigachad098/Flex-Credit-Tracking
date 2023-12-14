import java.util.Arrays;

public class Encrypt {

    public static String makePassword (String un, String pw) {
        if (un.equals(Username.getUsername()) && pw.equals(Password.getPassword())) {
            return "mongodb+srv://" + un + ":" + enc𝙤𝙤de(pw) + "@flextracker.altgap7.mongodb.net/?retryWrites=true&w=majority";
        }
        return "";
    }
    private static String enc𝝄𝝄de(String str) {
        int[] b = new int[7];
        int[] e = new int[7];
        for (int i = 12; i < 19; i++) {
            e[i-12] = i;
        }
        String a = "!";
        char c = (char) ('Α' + str.length());
        for (int i = 0; i < str.length(); i++) {
            char d = str.charAt(i);
            int ind = ((((int) (c))) % 7);
            b[ind] += (int) d * e[ind];
            e[ind] += (b[ind] ^ d) % (e[(ind+1) % 7]);
            c = d % (i+1) <= 1 ? (char) ((int) c + i) : (char) ((int) d - 3);
        }
        for (int i = 0; i < 7; i++) {
            b[i] += (int) (Math.PI * (b[i] - e[(i+3) % 7]));
        }
        int ind = Math.abs(c % 7);
        a += (char) (((char) ((b[ind] % 1000000) ^ 395248)) % 256);
        ind = Math.abs(a.charAt(1) * 2) % 7;
        a += (char) (((char) (b[ind] + 571)) % 256);
        ind = Math.abs(a.charAt(2) + a.charAt(1)) % 7;
        a += (char) (((char) ((Math.E * b[ind]) % e[ind])) % 256);
        ind = Math.abs(a.charAt(3) / 6) % 7;
        a += (char) (((char) (1.0 / b[ind] * Math.log(e[ind]))) % 256);
        ind = Math.abs(a.charAt(4) - a.charAt(3)) % 7;
        a += (char) (((char) (b[ind] ^ 'Ị')) % 256);
        ind = Math.abs(a.charAt(5) * a.charAt(4)) % 7;
        a += (char) (((char) (Math.toDegrees(b[ind]))) % 256);
        ind = Math.abs(a.charAt(6)) % 7;
        a += (char) (((char) (e[ind])) % 256);
        ind = Math.abs(b[ind] - a.charAt(7)) % 7;
        a += (char) (((char) (ind + b[ind] / 1000)) % 256);
        ind = Math.abs(a.charAt(8) / 3) % 7;
        a += (char) (((char) Math.pow(b[ind], 1.1)) % 256);
        ind = Math.abs(a.hashCode()) % 7;
        a += (char) (((char) (b[ind] + a.charAt(9) - 1987)) % 256);
        ind = Math.abs(ind + a.charAt(10)) % 7;
        a += (char) (((char) (b[ind] % (e[ind] * 202))) % 256);
        ind = Math.abs(Arrays.hashCode(b)) % 7;
        a += (char) (((char) (b[ind] / 64)) % 256);
        ind = Math.abs(a.charAt(11) / a.charAt(12)) % 7;
        a += (char) (((char) (b[ind] + e[(ind+6)%7])) % 256);
        ind = Math.abs(a.charAt(13) - a.charAt(3)) % 7;
        a += (char) (((char) (ind % 2 == 0 ? b[ind] / 616 : b[(ind + 2) % 7] - 199999)) % 256);
        a += "!";
        for (int i = 1; i < a.length() - 1; i++) {
            while (a.charAt(i) < 31 || (a.charAt(i) >= 127 && a.charAt(i) <= 159)) {
                a = a.substring(0, i) + ((char) (a.charAt(i) * 2 + 1)) % 256 + a.substring(i+1);
            }
        }
        return a;
    }

    // e1
    private static String enc𝝄𝙤de(String str) {
        return enc𝝄𝝄de(str) + enc𝝄𝝄de(str);
    }

    private static String enc𝙤𝙤de (String str) {
        return (enc𝝄𝝄de(enc𝙤𝝄de(str)));
    }

    private static String enc𝙤𝝄de (String str) {
        return str.charAt(0) % 2 == 0 ? (enc𝝄𝙤de(enc𝝄𝝄de(str))) : enc𝝄𝝄de(str);
    }
}
