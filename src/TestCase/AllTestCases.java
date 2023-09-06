package TestCase;

import Utility.BaseDriver;
import Utility.Func;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class AllTestCases extends BaseDriver {

    @Test
    public void E_BookPaymentProcessTest() {
        driver.get("https://www.e-junkie.com/wiki/demo");

        WebElement addToCartButton = driver.findElement(By.xpath("//a[text()='Add to Cart']"));
        addToCartButton.click();

        driver.switchTo().frame(0);
        WebElement debitCardOption = driver.findElement(By.xpath("//span[text()='Visa, AMEX, MasterCard, Maestro, Discover']"));
        debitCardOption.click();

        Func.Wait(2);
        WebElement payButton = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        payButton.click();

        List<String> errorMessages = Arrays.asList("Invalid Email", "Invalid Billing Name", "Invalid Billing Address 1", "Invalid Billing City", "Invalid Billing PostCode");

        for (String error : errorMessages) {
            WebElement errorMessage = driver.findElement(By.xpath("//div[@id='SnackBar']/span"));
            Assert.assertTrue(error + " mesajı görüntülenmiyor!", errorMessage.isDisplayed());
        }
        WaitAndClose();
    }

    @Test
    public void CardPaymentConfirmationTest() {





    }
}