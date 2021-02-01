package tests;

import model.Address;
import model.PersonalInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SignInPage;
import pages.TopMenuPage;
import utils.PageTitleUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInTest extends BaseTest {
    private TopMenuPage topMenuPage;
    private SignInPage signInPage;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        topMenuPage = new TopMenuPage(driver);
        signInPage = new SignInPage(driver);
    }
//  creating an account with invalid and valid data and checked that is it possible
//  to register an account

    @Test
    public void shouldNotAllowedToCreateAnAccountWithExistingEmail() {
        topMenuPage.clickOnSignInLink();
        signInPage.enterEmailSignIn("test@test.pl");
        signInPage.clickOnCreateAnAccountButton();
        assertThat(signInPage.isAlertDangerBoxCreateAccountDisplayed()).isTrue();
    }

    @Test
    public void shouldNotAllowedToCreateAnAccountWithValidEmail() {
        topMenuPage.clickOnSignInLink();
        signInPage.enterEmailSignIn("test.pla");
        signInPage.clickOnCreateAnAccountButton();
        signInPage.clickOnRegisterButton();
        assertThat(signInPage.isAlertDangerBoxRegisterAccountDisplayed()).isTrue();
        System.out.println(signInPage.alertDangerText());
    }


    @Test
    public void shouldAllowedToCreateAnAccountWithNewEmail() {
        topMenuPage.clickOnSignInLink();
        signInPage.enterEmailSignIn("test@testala.pl");
        signInPage.clickOnCreateAnAccountButton();
        signInPage.clickOnRegisterButton();
        assertThat(signInPage.isSignInPageDisplayed()).isTrue();
        System.out.println(signInPage.alertDangerText());
    }

    @Test
    public void shouldNotAllowedToCreateAnaccountWithEmptyEmailAddress() {
        topMenuPage.clickOnSignInLink();
        signInPage.clickOnCreateAnAccountButton();

        assertThat(signInPage.isAlertDangerBoxCreateAccountDisplayed()).isTrue();
        System.out.println(signInPage.alertDangerText());
    }

    @Test
    public void shouldAllowedToSignInWithValidData() {
        topMenuPage.clickOnSignInLink();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        signInPage.enterEmailSignIn("test@testala.pll");
        signInPage.clickOnCreateAnAccountButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        signInPage.clickOnRadioGenderMrs();
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setFirstName("Kasia");
        personalInformation.setLastName("Test");
        personalInformation.setPassword("123456");
        signInPage.sendPersonalFullInformation(personalInformation);
        signInPage.clickOnNewsletter();
        assertThat(signInPage.isSignInPageDisplayed()).isTrue();

        Address address = new Address();
        address.setCompany("My company");
        address.setAddress("Al. Jerozolimskie 100 ");
        address.setCity("Warsaw");
        address.setPostCode("12345");
        address.setMobilePhone("123456789");
        address.setAdditionalInformation("building behind the post office, third floor");
        signInPage.sendFullAddressInformation(address);
        signInPage.clickOnRegisterButton();
        assertThat(signInPage.isRegisterInfoAccountParagraphDisplayed()).isTrue();
    }
}

