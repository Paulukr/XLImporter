package mp2.util;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class TEstBinaryQuiz {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String s1 = "2 1C 32 7C 5A 12 8F F0";
		String s2 = "21 C3 27 C5 A1 28 FF 0";
		Scanner in = new Scanner(s1);
		byte[] sb1 = new byte[10];
		int j = 0;
		while (in.hasNextInt(16)) {
			int i = in.nextInt(16);

			char c = (char)i;
			System.out.println(i + " " + c);
			sb1[j++] = (byte)c;
		}
		String s1c = new String(sb1, "ISO-8859-1");
		String s1cU = new String(s1c.getBytes("ISO-8859-1"), "UTF-8");
		
		System.out.println(s1c);
		System.out.println(s1cU);


	}

}
