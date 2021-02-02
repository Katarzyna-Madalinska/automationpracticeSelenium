package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SearchPage;
import utils.PageTitleUtils;


import static org.assertj.core.api.Assertions.assertThat;

public class SearchBlouseTest extends BaseTest {
    private SearchPage searchPage;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        searchPage = new SearchPage(driver);
    }

    //  test: click on input Search,  put thw word: Blouse and checked the result ( be assure that
    // one blouse is displayed. I create an assertion, that result of search have a correct product
    // name and prise is not empty

    @Test
    public void shouldSearchFindOneBlouse() {
        searchPage.clickOnSearchInput();
        searchPage.enterSearchInput("Blouse");
        searchPage.clickOnSearchButton();

        assertThat(searchPage.isSearchBlouseDisplayed()).isTrue();
        assertThat(searchPage.isSearchBlouseDisplayed()).isTrue();
        assertThat(searchPage.resultSearchProductNameBlouse()).isEqualTo("Blouse");
        assertThat(searchPage.resultTextProductPriceBlouse()).isNotNull();

    }
}
