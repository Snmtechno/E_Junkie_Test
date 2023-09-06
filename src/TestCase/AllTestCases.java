package TestCase;

import Utility.BaseDriver;
import Utility.Func;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class AllTestCases extends BaseDriver {

    @Test(priority=1)
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

    }

    @Test(priority=2)
    public void CardPaymentConfirmationTest() {

        driver.get("https://www.e-junkie.com/wiki/demo/paypal");

        WebElement addToCard= driver.findElement(By.xpath("//a[text()='Add to Cart']"));
        addToCard.click();

        driver.switchTo().frame(0);

        WebElement payButton= wait.until(ExpectedConditions.elementToBeClickable
                (driver.findElement(By.xpath("//button[@class='Payment-Button CC']"))));
        payButton.click();

        WebElement eMail= driver.findElement(By.cssSelector("input[placeholder='Email']"));
        eMail.sendKeys("enestester12@gmail.com");

        WebElement confirmMail= driver.findElement(By.cssSelector("input[placeholder='Confirm Email']"));
        confirmMail.sendKeys("enestester12@gmail.com");

        WebElement nameOnCard= driver.findElement(By.cssSelector("input[placeholder='Name On Card']"));
        nameOnCard.sendKeys("Ennes");

        WebElement phone= driver.findElement(By.xpath("//p[@class='Billing-Phone Inline']//input"));
        phone.sendKeys("01223334444");

        WebElement company=driver.findElement(By.xpath("//p[@class='Billing-Company']//input"));
        company.sendKeys("Techno Study");

        WebElement address= driver.findElement(By.xpath("//p[@class='Billing-Address1']//input"));
        address.sendKeys("Beykoz/İstanbul");

        WebElement address2=driver.findElement(By.xpath("//p[@class='Billing-Address2']//input"));
        address2.sendKeys("Samandira/İstanbul");

        WebElement city=driver.findElement(By.xpath("//p[@class='Billing-City Inline MarginRight']//input"));
        city.sendKeys("İstanbul");

        WebElement country=driver.findElement(By.xpath("//select[@class='Countries-List']"));

        Select selector=new Select(country);
        selector.selectByVisibleText("Turkey");

        WebElement state=driver.findElement(By.xpath("//p[@class='Billing-State Inline MarginRight']/input"));
        state.sendKeys("Beykoz");

        WebElement postalCode=driver.findElement(By.xpath("//p[@class='Billing-PostCode Inline']/input"));
        postalCode.sendKeys("35900");

        WebElement cardNumber=driver.findElement(By.xpath("//p[@class='Card-Number']/input"));
        cardNumber.clear();
        cardNumber.sendKeys("4242 4242 4242 4242");

        WebElement cardExpiry=driver.findElement(By.xpath("//p[@class='Card-Expiry Inline MarginRight']/input"));
        cardExpiry.sendKeys("1223");

        WebElement cvv=driver.findElement(By.xpath("//p[@class='Card-CVV Inline']/input"));
        cvv.sendKeys("000");

        WebElement payButton2=driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        payButton2.click();

        WebElement messageConfirm=wait.until
                (ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//span[@class='green_text_margin']")));

        Assert.assertTrue(messageConfirm.getText().contains("is confirmed. Thank you!"));

    }
}