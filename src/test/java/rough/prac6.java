package rough;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class prac6 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10); // Pool size 10

        for (int i = 1; i <= 2; i++) {
            int runId = i;
            executor.submit(() -> {
                try {
                    runTest(runId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }

    public static void runTest(int runId) throws InterruptedException, IOException {
    	
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dev.iamstillalive.com/login/");

        driver.findElement(By.id("email")).sendKeys("edmundo.johnson@smail.com");
        driver.findElement(By.id("password")).sendKeys("Pass@123");
        driver.findElement(By.xpath("//button[@class='btn btn-gradient text-center fs-6 w-25 rounded-5 px-3 font-inter']")).click();
        driver.findElement(By.xpath("//img[@class='bird-img']")).click();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("SendLogs");

        Row header = sheet.createRow(0);
        header.createCell(1).setCellValue("Action Type");
        header.createCell(2).setCellValue("Content");
        header.createCell(3).setCellValue("Time Taken (ms)");
        header.createCell(4).setCellValue("Timestamp");

        int rowNum = 1;

        String[] messages = {"Hello", "How are you?", "This is update", "File coming next", "Photo coming next"};
        String[] filePaths = {"lab1.pdf", "lab1.pdf", "lab3.pdf", "lab3.pdf", "lab3.pdf"};
        String[] photoPaths = {"A.jpg", "B.jpg", "C.png", "D.jpg", "E.png"};

        for (String msg : messages) {
            long start = System.currentTimeMillis();
            WebElement messageBox = driver.findElement(By.id("replyBox"));
            messageBox.sendKeys(msg);
            messageBox.sendKeys(Keys.ENTER);
            long end = System.currentTimeMillis();

            Row row = sheet.createRow(rowNum++);
            row.createCell(1).setCellValue("Message");
            row.createCell(2).setCellValue(msg);
            row.createCell(3).setCellValue(end - start);
            row.createCell(4).setCellValue(getTimeStamp());

            Thread.sleep(1000);
        }

        for (String file : filePaths) {
            long start = System.currentTimeMillis();
            WebElement fileUpload = driver.findElement(By.xpath("//input[@type='file']"));
            fileUpload.sendKeys(new File(file).getAbsolutePath());
            driver.findElement(By.xpath("//img[@class='img-fluid p-2']")).click();
            long end = System.currentTimeMillis();

            Row row = sheet.createRow(rowNum++);
            row.createCell(1).setCellValue("File");
            row.createCell(2).setCellValue(file);
            row.createCell(3).setCellValue(end - start);
            row.createCell(4).setCellValue(getTimeStamp());

            Thread.sleep(2000);
        }

        for (String photo : photoPaths) {
            long start = System.currentTimeMillis();
            WebElement photoUpload = driver.findElement(By.xpath("//input[@type='file']"));
            photoUpload.sendKeys(new File(photo).getAbsolutePath());
            driver.findElement(By.xpath("//img[@class='img-fluid p-2']")).click();
            long end = System.currentTimeMillis();

            Row row = sheet.createRow(rowNum++);
            row.createCell(1).setCellValue("Photo");
            row.createCell(2).setCellValue(photo);
            row.createCell(3).setCellValue(end - start);
            row.createCell(4).setCellValue(getTimeStamp());

            Thread.sleep(2000);
        }

        String fileName = "MessageSendLog_Run" + runId + "_" + System.currentTimeMillis() + ".xlsx";
        FileOutputStream out = new FileOutputStream(fileName);
        workbook.write(out);
        out.close();
        workbook.close();

        driver.quit();
    }

    private static String getTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}


