package base;

import java.io.IOException;

public class AllureReport {

	public static void openAllureReport() 
	{
		String command = "cmd.exe /c allure serve allure-results";
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
