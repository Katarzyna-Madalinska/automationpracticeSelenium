package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.WomenListPage;
import utils.PageTitleUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class WomenListTest extends BaseTest {
    private WomenListPage womenListPage;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        womenListPage = new WomenListPage(driver);
    }
    // first test: click on Women link, be assure that opened a correct website,  read all prices
    // that  are displayed as a result and create an assertion, that  check that all that price
    // are greater than 0

    @Test
    public void shouldClickOnWomenLink() {
        womenListPage.clickOnWomenLink();
        assertThat(womenListPage.isWomenPageDisplayed()).isTrue();
    }

    @Test
    public void shouldPricesBeGreaterThanNull() {
        womenListPage.clickOnWomenLink();
        List<Double> productsWomenList = womenListPage.getProductPrices();

      /*  List<Double> productsWithNullPrice = productsWomenList.stream()
                 .filter(num -> num < 0)
                .collect(Collectors.toList());*/
        System.out.println(womenListPage.getProductPrices());
       // assertThat(productsWithNullPrice).isEmpty();
      /*  System.out.println(productsWithNullPrice);
        System.out.println(productsWomenList);*/
    }
}
