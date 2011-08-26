import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Muscoll {

	public static void main(String[] args) {
		try {
			Runtime runTime = Runtime.getRuntime();
			Process process = runTime.exec("java -jar Launcher.jar");
			InputStream inputStream = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream);
			InputStream errorStream = process.getErrorStream();
			InputStreamReader esr = new InputStreamReader(errorStream);

			int n1;
			char[] c1 = new char[1024];
			StringBuffer standardOutput = new StringBuffer();
			while ((n1 = isr.read(c1)) > 0) {
				standardOutput.append(c1, 0, n1);
			}
            System.out.println(standardOutput.toString());

			int n2;
			char[] c2 = new char[1024];
			StringBuffer standardError = new StringBuffer();
			while ((n2 = esr.read(c2)) > 0) {
				standardError.append(c2, 0, n2);
			}
//            System.out.println("Standard Error: " + standardError.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
