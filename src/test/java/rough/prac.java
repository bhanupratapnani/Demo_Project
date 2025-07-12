package rough;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class prac {
	
		int i;
		Sheet sheet;
	@Test
	public void test() throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream(new File("./files/gisfy.xlsx"));
		Workbook workbook = WorkbookFactory.create(fis);   
		sheet = workbook.getSheetAt(0); 
		
		for(i=1; i<=10; i++) 
		{
			System.out.println(i);
			System.out.println(sheet.getRow(i).getCell(3).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(4).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(5).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(6).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(7).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(8).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(9).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(10).getStringCellValue());
			System.out.println("==============================================");
		}

	}
}
