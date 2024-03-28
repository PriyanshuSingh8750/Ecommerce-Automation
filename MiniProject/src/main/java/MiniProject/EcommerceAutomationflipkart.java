package MiniProject;

import java.io.IOException;
import java.time.Duration;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EcommerceAutomationflipkart {

    // Declaring WebDriver and WebDriverWait as class variables
    public static WebDriver driver;
    public static WebDriverWait wait;

    // Method to initialize Edge browser
    public void edge() {
        driver = new EdgeDriver();
        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Method to initialize Chrome browser
    public void chrome() {
        driver = new ChromeDriver();
        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Method to perform various operations on the Flipkart website
    public void work() throws InterruptedException, IOException {

        // Clicking on an element to close a pop-up
        //driver.findElement(By.xpath("//*[@class='_30XB9F']")).click();

        // Finding the search box element and searching for data fetched from an Excel sheet
        WebElement element = driver.findElement(By.name("q"));
        String Data = Excel.getExcelData(); // Assuming a method to fetch data from an Excel file
        element.sendKeys(Data);
        element.sendKeys(Keys.ENTER);

        // Validating the title of the page to ensure the correct page is loaded
        String str = driver.getTitle();
        if (str.equals("Mobiles Under 15000- Buy Products Online at Best Price in India - All Categories | Flipkart.com")) {
            System.out.println("pass");
        } else {
            System.out.print("fail");
        }

        // Using JavaScriptExecutor to perform certain actions on the page

        // Dragging the price bar to set the maximum price
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Select s = new Select(driver.findElement(By.xpath("(//select[@class='_2YxCDZ'])[2]")));
        s.selectByValue("10000");
        Thread.sleep(3000);

        // Selecting specific options like 'Operating System Version Name' and others
        WebElement el = driver.findElement(By.xpath("//*[text()='Operating System Version Name']"));
        
        // Scrolling into view
        js.executeScript("arguments[0].scrollIntoView;", el);
        js.executeScript("arguments[0].click();", el);
        Thread.sleep(2000);
        WebElement el1 = driver.findElement(By.xpath("//*[text()='11 MORE']"));
        js.executeScript("arguments[0].scrollIntoView;", el1);
        js.executeScript("arguments[0].click();", el1);
        Thread.sleep(3000);
        WebElement el3 = driver.findElement(By.xpath("(//label[@class='_2iDkf8 t0pPfW'])[36]"));
        js.executeScript("arguments[0].click();", el3);
        Thread.sleep(3000);

        // Clicking on "Newest First" to sort products
        WebElement el2 = driver.findElement(By.xpath("//*[text()='Newest First']"));
        js.executeScript("arguments[0].click();", el2);
        Thread.sleep(2000);

        // Displaying the names and prices of the first five mobiles
        List<WebElement> list = driver.findElements(By.xpath("//*[@class='_4rR01T']"));
        List<WebElement> price = driver.findElements(By.xpath("//*[@class='_30jeq3 _1_WHN1']"));
        int count = 0;
        Thread.sleep(3000);
        for (WebElement ele : list) {
            System.out.println(ele.getText());

            count++;
            if (count == 5) {
                break;
            }
        }
        Thread.sleep(3000);
        int count1 = 0;
        for (WebElement elem : price) {
            System.out.println(elem.getText());
            count1++;
            if (count1 == 5) {
                break;
            }
        }

        // Validating whether the first mobile's price is less than 30000
        String str1 = driver.findElement(By.xpath("(//*[@class='_30jeq3 _1_WHN1'])[1]")).getText();
        String[] s1 = str1.split("₹");
        String[] s2 = s1[1].split(",");
        String sp = s2[0] + s2[1];
        int p = Integer.parseInt(sp);
        if (p < 30000) {
            System.out.print("pass");
        } else {
            System.out.print("fail");
        }
    }

    // Closing the browser
    public void close() {
        driver.quit();
    }

    // Main method to initiate the automation process
    @SuppressWarnings("resource")
    public static void main(String[] args) throws InterruptedException, IOException {

        // Taking user input to select the browser
        System.out.println("Enter Browser you want to select:");
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        EcommerceAutomationflipkart e = new EcommerceAutomationflipkart();

        // Initiating the selected browser
        if (string.equalsIgnoreCase("chrome")) {
            e.chrome();
        } else if (string.equalsIgnoreCase("edge")) {
            e.edge();
        } else {
            System.out.print("chrome/edge");
        }

        // Performing operations on Flipkart website
        e.work();

        // Closing the browser
        e.close();
    }

}
