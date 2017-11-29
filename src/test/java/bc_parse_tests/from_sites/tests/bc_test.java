package bc_parse_tests.from_sites.tests;

/**
 * Created by Любовь on 28.11.2017.
 * */
 import org.openqa.selenium.chrome.ChromeDriver;
 import org.openqa.selenium.chrome.ChromeOptions;
 import org.testng.annotations.BeforeMethod;
 import org.testng.annotations.AfterMethod;
 import org.testng.annotations.Test;

 import java.io.*;
 import java.text.SimpleDateFormat;
 import java.util.concurrent.TimeUnit;
 import org.openqa.selenium.firefox.FirefoxDriver;
 import org.openqa.selenium.*;


 public class bc_test {
     ChromeDriver wd;
     ChromeOptions o;
  String qiwiWallet;
  String userEmail;
  String bcWallet;
  String targetWallet;
  String targetName;
  String chromeProfile;
  File outputData;
     @BeforeMethod
     public void setUp() throws Exception {
      BufferedReader reader = new BufferedReader (new FileReader(new File("C:/tmp/chromeProfile.csv")));
      String line = reader.readLine();
      while (line != null) {
       String [] split = line.split(";");
       chromeProfile = split[0];
       line = reader.readLine();
      }

      System.out.println("chromeProfile is : ");
      System.out.println(chromeProfile);
      ChromeOptions options = new ChromeOptions();
      options.addArguments("user-data-dir="+chromeProfile);
      options.addArguments("--start-maximized");
      wd = new ChromeDriver(options);

         wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
     }

 @Test
 public void bc_test() throws IOException, InterruptedException {
 wd.get("https://fastchange.cc/");
 wd.findElement(By.cssSelector("span.bank_name")).click();
 wd.findElement(By.xpath("//ul[@id='calculation_param']//span[.='Киви RUR']")).click();
 wd.findElement(By.xpath("//ul[@id='calculation_param']/li[2]/div/div[1]/ul/li[12]/span")).click();
 wd.findElement(By.name("give_money")).click();
 wd.findElement(By.name("give_money")).sendKeys("1000");
 BufferedReader reader = new BufferedReader (new FileReader(new File("src/test/resources/inputData.csv")));
 String line = reader.readLine();
 while (line != null) {
    String [] split = line.split(";");
    qiwiWallet = split[0];
    userEmail = split[1];
    bcWallet = split[2];
   line = reader.readLine();
 }

  // qiwiWallet = "79057810930";
   //userEmail = "a.bogdanov@geesoft.ru";
   //bcWallet = "0xca30e63200a0fe3182dc61fc5605efc414c1df97";
 sendTestData(qiwiWallet,userEmail, bcWallet);
  TimeUnit.SECONDS.sleep(60);
  wd.findElement(By.name("captcha")).click();
  wd.findElement(By.xpath("//ul[@id='application_param']/li[9]/input")).click();
  TimeUnit.SECONDS.sleep(5);
  targetWallet = wd.findElement(By.id("finish_kard")).getText();
  saveData(targetWallet);


 }

  private void saveData(String targetWallet) throws IOException {
   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
   targetName = "outputData_"+sdf.format( System.currentTimeMillis() )+".csv";

   Writer writer = new FileWriter("src/test/resources/"+targetName, true);
   writer.write(String.format("%s;%s\n",targetWallet, java.util.Calendar.getInstance().getTime()));
   writer.close();

  }
  private void sendTestData(String qiwiWallet, String userEmail, String bcWallet) {

   wd.findElement(By.id("wallet_one")).click();
   wd.findElement(By.id("wallet_one")).sendKeys(qiwiWallet);
   wd.findElement(By.id("e_mail")).click();
   wd.findElement(By.id("e_mail")).sendKeys(userEmail);
   wd.findElement(By.id("wallet_two")).click();
   wd.findElement(By.id("wallet_two")).sendKeys(bcWallet);
  }

  @AfterMethod
 public void tearDown() {
 wd.quit();
 }

 public static boolean isAlertPresent(FirefoxDriver wd) {
 try {
 wd.switchTo().alert();
 return true;
 } catch (NoAlertPresentException e) {
 return false;
 }
 }
 }
