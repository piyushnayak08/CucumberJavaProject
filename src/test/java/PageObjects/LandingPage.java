package PageObjects;

import Utilities.Helper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class LandingPage {

    WebDriver driver;
    Actions action;
    Helper helper;

    public static final String pageUrl = "https://testscriptdemo.com/";

    @FindBy(xpath = "//div[@data-id='5f470bd']//li//*[@class='product-wishlist']//div//div//a")
    WebElement featuredProductsWishlist;

    @FindBy(xpath = "(//div[@data-id='5f470bd']//li//*[@data-title='Browse wishlist'])[1]")
    WebElement browseWishlist;

    @FindBy(xpath = "(//a[text()='Select options'])[1]")
    WebElement blackPantSelectOption;


    public LandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        helper = new Helper();
    }

    //Page Actions
    @BeforeEach
    public void accessPage() {
        String driverPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", driverPath + "/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(pageUrl);
    }

    public void addToWishlist() throws InterruptedException {
//        helper.performMouseHover(blackPantSelectOption, driver);
//        Thread.sleep(4000);
//        action = new Actions(driver);
//        action.moveToElement(blackPantSelectOption).build().perform();
        List<WebElement> wishlists = driver.findElements(By.xpath("//div[@data-id='5f470bd']//li//*[@class='product-wishlist']//div//div//a"));
        for (int i = 1; i < wishlists.size(); i++) {
            wishlists.get(i).click();
            Thread.sleep(2000);
        }

    }

    public void viewWishlist() throws InterruptedException {
        driver.findElement(By.xpath("(//div[@data-id='5f470bd']//li//*[@data-title='Browse wishlist'])[1]")).click();
        Thread.sleep(2000);
    }

    public void validateSelectedItemsWishlist() {
        Assert.assertTrue("Wishlist selected count is proper", driver.findElements(By.xpath("//table[@class='shop_table cart wishlist_table wishlist_view traditional responsive   ']//tbody//tr")).size() == 4);

    }

    public void findMinimumPrice() throws InterruptedException {
        int min = 0;
        String version = null;
        String amountValue = null;
        String eachAmount = null;
        String finalAmnt = null;

        Thread.sleep(4000);
        List<WebElement> Rows = driver.findElements(By.xpath("//table[@class='shop_table cart wishlist_table wishlist_view traditional responsive   ']//tbody//tr"));

        for (int i = 1; i <= Rows.size(); i++) {

            WebElement columnValue = driver.findElement(By.xpath("(//table[@class='shop_table cart wishlist_table wishlist_view traditional responsive   ']//tbody//tr//td[4])["+i+"]"));
            if (columnValue.getText().contains("-")) {
                String[] amount= driver.findElement(By.xpath("(//table[@class='shop_table cart wishlist_table wishlist_view traditional responsive   ']//tbody//tr//td[4])["+i+"]")).getText().split("-");
                amountValue = amount[0];
                eachAmount = amountValue.replaceAll("[^0-9.]","");
            }
            else {
               eachAmount = columnValue.getText().replaceAll("[^0-9.]","");
                String correctstring[] = eachAmount.split("\\.");

                finalAmnt = correctstring[0];
            }

            WebElement versionElem = driver.findElement(By.xpath("//table[@class='shop_table cart wishlist_table wishlist_view traditional responsive   ']//tbody//tr//td[1]"));


            if (Integer.parseInt(finalAmnt) < min) {

                version = finalAmnt;
                min = Integer.parseInt(finalAmnt);

            }
            System.out.println("Lowest price ="+finalAmnt);
            driver.findElement(By.xpath("(//table[@class='shop_table cart wishlist_table wishlist_view traditional responsive   ']//tbody//tr//td[6])["+i+"]")).click();
            Thread.sleep(4000);
            break;
        }
    }

}
