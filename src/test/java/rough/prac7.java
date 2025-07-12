package rough;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class prac7 {  // Class names should start with uppercase letter

    // Shared workbook and sheet
    private static final Workbook workbook = new XSSFWorkbook();
    private static final Sheet sheet = workbook.createSheet("SendLogs");
    private static final AtomicInteger rowNum = new AtomicInteger(1); // Thread-safe row counter
    private static final Object lock = new Object(); // Lock for sync writing

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            // Header row (only once)
            createHeaderRow();

            // Run tasks
            for (int i = 1; i <= 10; i++) {
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
            // Wait for all threads to complete with a reasonable timeout
            if (!executor.awaitTermination(5, TimeUnit.MINUTES)) {
                System.err.println("Some tasks did not complete within the timeout period");
            }

            // Save the combined Excel
            saveWorkbook();
            System.out.println("✅ All logs saved to CombinedMessageSendLog.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeWorkbook();
        }
    }

    private static void createHeaderRow() {
        synchronized (lock) {
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Run ID");
            header.createCell(1).setCellValue("Action Type");
            header.createCell(2).setCellValue("Content");
            header.createCell(3).setCellValue("Time Taken (ms)");
            header.createCell(4).setCellValue("Timestamp");
        }
    }

    public static void runTest(int runId) throws InterruptedException, IOException {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://dev.iamstillalive.com/login/");

            // Login
            driver.findElement(By.id("email")).sendKeys("edmundo.johnson@smail.com");
            driver.findElement(By.id("password")).sendKeys("Pass@123");
            driver.findElement(By.xpath("//button[@class='btn btn-gradient text-center fs-6 w-25 rounded-5 px-3 font-inter']")).click();
            driver.findElement(By.xpath("//img[@class='bird-img']")).click();

            String[] messages = {"Hello", "How are you?", "This is update", "File coming next", "Photo coming next"};
            String[] filePaths = {"lab1.pdf", "lab1.pdf", "lab3.pdf", "lab3.pdf", "lab3.pdf"};
            String[] photoPaths = {"A.jpg", "B.jpg", "C.png", "D.jpg", "E.png"};

            // Send messages
            for (String msg : messages) {
                sendMessage(driver, runId, msg);
                Thread.sleep(1000);
            }

            // Upload files
            for (String file : filePaths) {
                uploadFile(driver, runId, file);
                Thread.sleep(2000);
            }

            // Upload photos
            for (String photo : photoPaths) {
                uploadPhoto(driver, runId, photo);
                Thread.sleep(2000);
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void sendMessage(WebDriver driver, int runId, String message) {
        long start = System.currentTimeMillis();
        WebElement messageBox = driver.findElement(By.id("replyBox"));
        messageBox.sendKeys(message);
        messageBox.sendKeys(Keys.ENTER);
        long end = System.currentTimeMillis();
        writeToExcel(runId, "Message", message, end - start);
    }

    private static void uploadFile(WebDriver driver, int runId, String filePath) {
        long start = System.currentTimeMillis();
        WebElement fileUpload = driver.findElement(By.xpath("//input[@type='file']"));
        fileUpload.sendKeys(new File(filePath).getAbsolutePath());
        driver.findElement(By.xpath("//img[@class='img-fluid p-2']")).click();
        long end = System.currentTimeMillis();
        writeToExcel(runId, "File", filePath, end - start);
    }

    private static void uploadPhoto(WebDriver driver, int runId, String photoPath) {
        long start = System.currentTimeMillis();
        WebElement photoUpload = driver.findElement(By.xpath("//input[@type='file']"));
        photoUpload.sendKeys(new File(photoPath).getAbsolutePath());
        driver.findElement(By.xpath("//img[@class='img-fluid p-2']")).click();
        long end = System.currentTimeMillis();
        writeToExcel(runId, "Photo", photoPath, end - start);
    }

    private static void writeToExcel(int runId, String actionType, String content, long timeTaken) {
        synchronized (lock) {
            int rowIndex = rowNum.getAndIncrement();
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue("Run " + runId);
            row.createCell(1).setCellValue(actionType);
            row.createCell(2).setCellValue(content);
            row.createCell(3).setCellValue(timeTaken);
            row.createCell(4).setCellValue(getTimeStamp());
        }
    }

    private static String getTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private static void saveWorkbook() throws IOException {
        try (FileOutputStream out = new FileOutputStream("CombinedMessageSendLog.xlsx")) {
            workbook.write(out);
        }
    }

    private static void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}