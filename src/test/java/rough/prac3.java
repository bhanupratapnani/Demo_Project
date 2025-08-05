package rough;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class prac3 {

	@Test
	    public static void test() {
		
	        Faker faker = new Faker();

	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Accounts");

	        // Create header
	        Row header = sheet.createRow(0);
	        header.createCell(0).setCellValue("Name");
	        header.createCell(1).setCellValue("Email");
	        header.createCell(2).setCellValue("Username");
	        header.createCell(3).setCellValue("Password");
	        header.createCell(4).setCellValue("Phone");
	        header.createCell(5).setCellValue("Job");
	        header.createCell(6).setCellValue("Address");
	        

	        int numAccounts = 100; // you can change the number of accounts

	        for (int i = 1; i <= numAccounts; i++) {
	            Row row = sheet.createRow(i);
	            row.createCell(0).setCellValue(faker.name().fullName());
	            row.createCell(1).setCellValue(faker.internet().emailAddress());
	            row.createCell(2).setCellValue(faker.name().username());
	            row.createCell(3).setCellValue(faker.internet().password(8, 12));
	            row.createCell(4).setCellValue(faker.phoneNumber().cellPhone());
	            row.createCell(5).setCellValue(faker.job().position());
	            row.createCell(6).setCellValue(faker.address().streetAddress());
	        }

	        // Save Excel
	        try (FileOutputStream fos = new FileOutputStream("FakeAccounts.xlsx")) {
	            workbook.write(fos);
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}



