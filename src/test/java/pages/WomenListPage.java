package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class WomenListPage extends BasePage {
    public WomenListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Women")
    WebElement womenLink;

    @FindBy(className = "navigation_page")
    WebElement navigationPageWomen;

    @FindBy(css = ".product_list .product-price")
    List<WebElement> productPrices;

    public void clickOnWomenLink() {
        womenLink.click();
    }

    public boolean isWomenPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(navigationPageWomen));
        boolean isDisplayed = false;
        try {
            isDisplayed = navigationPageWomen.isDisplayed();
        } catch (NoSuchElementException e) {
        }
        return isDisplayed;
    }


    public List<Double> getProductPrices() {
        return productPrices.stream()
                .map(el -> el.getText().replace("$", " "))
                .map(e -> e.trim())
                .map(Double::parseDouble)
                // .filter(num -> num < 0)
                .collect(Collectors.toList());
    }
}
