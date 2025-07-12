package rough;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class prac8 {

    // Shared workbook and sheet for output
    private static final Workbook outputWorkbook = new XSSFWorkbook();
    private static final Sheet outputSheet = outputWorkbook.createSheet("SendLogs");
    private static final AtomicInteger rowNum = new AtomicInteger(1);
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // Read user credentials from Excel
        List<User> users = readUserCredentials("SIGINDATA.xlsx");
        if (users.isEmpty()) {
            System.err.println("No users found in credentials file");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(users.size());

        try {
            // Create header row
            createHeaderRow();

            // Run tests for each user
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                int runId = i + 1;
                executor.submit(() -> {
                    try {
                        runTest(runId, user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            executor.shutdown();
            if (!executor.awaitTermination(15, TimeUnit.MINUTES)) {
                System.err.println("Some tasks did not complete within the timeout period");
            }

            // Save the output Excel
            saveWorkbook();
            System.out.println("✅ All logs saved to CombinedMessageSendLog.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeWorkbook();
        }
    }

    private static List<User> readUserCredentials(String filePath) {
        List<User> users = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0); // Assuming first sheet contains credentials
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                String email = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();
                users.add(new User(email, password));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    private static void createHeaderRow() {
        synchronized (lock) {
            Row header = outputSheet.createRow(0);
            header.createCell(0).setCellValue("Run ID");
            header.createCell(1).setCellValue("User Email");
            header.createCell(2).setCellValue("Action Type");
            header.createCell(3).setCellValue("Content");
            header.createCell(4).setCellValue("Time Taken (ms)");
            header.createCell(5).setCellValue("Timestamp");
        }
    }

    public static void runTest(int runId, User user) throws InterruptedException, IOException {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://dev.iamstillalive.com/login/");

            // Login with user credentials
            long loginStart = System.currentTimeMillis();
            driver.findElement(By.id("email")).sendKeys(user.email);
            driver.findElement(By.id("password")).sendKeys(user.password);
            driver.findElement(By.xpath("//button[@class='btn btn-gradient text-center fs-6 w-25 rounded-5 px-3 font-inter']")).click();
            long loginEnd = System.currentTimeMillis();
            writeToExcel(runId, user.email, "Login", "Login Action", loginEnd - loginStart);

            driver.findElement(By.xpath("//img[@class='bird-img']")).click();

            String[] messages = {"Hello", "How are you?", "This is update", "File coming next", "Photo coming next"};
            String[] filePaths = {"lab1.pdf", "lab1.pdf", "lab3.pdf", "lab3.pdf", "lab3.pdf"};
            String[] photoPaths = {"A.jpg", "B.jpg", "C.png", "D.jpg", "E.png"};

            // Send messages
            for (String msg : messages) {
                sendMessage(driver, runId, user.email, msg);
                Thread.sleep(1000);
            }

            // Upload files
            for (String file : filePaths) {
                uploadFile(driver, runId, user.email, file);
                Thread.sleep(2000);
            }

            // Upload photos
            for (String photo : photoPaths) {
                uploadPhoto(driver, runId, user.email, photo);
                Thread.sleep(2000);
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void sendMessage(WebDriver driver, int runId, String email, String message) {
        long start = System.currentTimeMillis();
        WebElement messageBox = driver.findElement(By.id("replyBox"));
        messageBox.sendKeys(message);
        messageBox.sendKeys(Keys.ENTER);
        long end = System.currentTimeMillis();
        writeToExcel(runId, email, "Message", message, end - start);
    }

    private static void uploadFile(WebDriver driver, int runId, String email, String filePath) {
        long start = System.currentTimeMillis();
        WebElement fileUpload = driver.findElement(By.xpath("//input[@type='file']"));
        fileUpload.sendKeys(new File(filePath).getAbsolutePath());
        driver.findElement(By.xpath("//img[@class='img-fluid p-2']")).click();
        long end = System.currentTimeMillis();
        writeToExcel(runId, email, "File", filePath, end - start);
    }

    private static void uploadPhoto(WebDriver driver, int runId, String email, String photoPath) {
        long start = System.currentTimeMillis();
        WebElement photoUpload = driver.findElement(By.xpath("//input[@type='file']"));
        photoUpload.sendKeys(new File(photoPath).getAbsolutePath());
        driver.findElement(By.xpath("//img[@class='img-fluid p-2']")).click();
        long end = System.currentTimeMillis();
        writeToExcel(runId, email, "Photo", photoPath, end - start);
    }

    private static void writeToExcel(int runId, String email, String actionType, String content, long timeTaken) {
        synchronized (lock) {
            int rowIndex = rowNum.getAndIncrement();
            Row row = outputSheet.createRow(rowIndex);
            row.createCell(0).setCellValue("Run " + runId);
            row.createCell(1).setCellValue(email);
            row.createCell(2).setCellValue(actionType);
            row.createCell(3).setCellValue(content);
            row.createCell(4).setCellValue(timeTaken);
            row.createCell(5).setCellValue(getTimeStamp());
        }
    }

    private static String getTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private static void saveWorkbook() throws IOException {
        try (FileOutputStream out = new FileOutputStream("CombinedMessageSendLog.xlsx")) {
            outputWorkbook.write(out);
        }
    }

    private static void closeWorkbook() {
        try {
            outputWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class User {
        String email;
        String password;

        public User(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}