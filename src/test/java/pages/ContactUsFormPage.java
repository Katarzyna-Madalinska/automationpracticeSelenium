package pages;

import model.Message;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.BoxDisplayedUtils;

public class ContactUsFormPage extends BasePage {

    @FindBy(id = "submitMessage")
    WebElement sendButton;

    @FindBy(className = "alert-danger")
    WebElement redAlertBox;

    @FindBy(className = "alert-success")
    WebElement greenAlertBox;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "id_contact")
    WebElement subjectSelect;

    @FindBy(id = "id_order")
    WebElement orderReferenceInput;

    @FindBy(id = "message")
    WebElement messageTextArea;


    public ContactUsFormPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSendButton() {
        sendButton.click();
    }

    public boolean isGreenAlertBoxDisplayed() {
        return isAlertBoxDisplayed(greenAlertBox);
    }


    public boolean isRedAlertBoxDisplayed() {
        return isAlertBoxDisplayed(redAlertBox);
    }

   private boolean isAlertBoxDisplayed(WebElement box) {
    //    TODO nie jest dostepna metoda: isElementVisible z klasy BoxDisplayedUtils
        BoxDisplayedUtils.isElementVisible(box);

   /*     wait.until(ExpectedConditions.visibilityOf(box));
        boolean isDisplayed = false;
        try {
            isDisplayed = box.isDisplayed();
        } catch (NoSuchElementException e) {
        }
        return isDisplayed;*/
    }


    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void sendContactUsForm(Message message) {
        Select subject = new Select(subjectSelect);
        subject.selectByVisibleText(message.getSubject().getValue());
        emailInput.sendKeys(message.getEmail());
        orderReferenceInput.sendKeys(message.getOrderReference());
        messageTextArea.sendKeys(message.getMessage());
        sendButton.click();
    }
}
