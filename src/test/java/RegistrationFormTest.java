import elements.RegistrationFormPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationFormTest {

    private RegistrationFormPage registrationFormPage;

    private WebDriver driver;

    public final static String EXPECTED_HEADER = "Registration Form";
    public final static String EXPECTED_EMAIL_IVALID_MESSAGE = "Please enter a valid email address.";
    public final static String NAME = "Kirill";
    public final static String SURNAME = "Mekenya>";
    public final static String PHONE_NUMBER = "+4827989879";
    public final static String USERNAME = "qa_user";
    public final static String INVALID_EMAIL = "qwerty";
    public final static String VALID_EMAIL = "qwerty@mail.ru";
    public final static String PASSWORD = "qwerty";
    public final static String SELECTED_COUNTRY = "India";
    public final static String REPEATED_PASSWORD = "123";
    public final static String MISSING_REQUIRED_FIELD_MESSAGE = "This field is required.";
    public final static String PATH_TO_FILE = "/Users/user/IdeaProjects/selenium_hometask/src/main/resources/images.jpeg";

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        registrationFormPage = new RegistrationFormPage(driver);
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void initElements() {
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        driver.navigate().refresh();
    }

    @AfterAll
    public void finish() {

        driver.close();
    }

    @Test
    @Order(1)
    @DisplayName("Проверка открытия формы регистрации")
    public void openingRegistrationPage() {
        assertEquals(EXPECTED_HEADER, registrationFormPage.getHeader().getText(),
                "Страница регистрации не открыта или имеет некорректный заголовок");
    }

    @Test
    @DisplayName("Проверка регистрации только с обязательными параметрамий")
    public void successRequiredFieldsRegistration() {
        registrationFormPage.inputName(NAME)
                            .inputSurname(SURNAME)
                            .inputPhoneNumber(PHONE_NUMBER)
                            .selectReadingHobbyCheckbox()
                            .inputUserName(USERNAME)
                            .inputEmail(VALID_EMAIL)
                            .inputPassword(PASSWORD)
                            .inputRepeatPassword(PASSWORD)
                            .clickSubmitButton();

        assertFalse(registrationFormPage.isErrorDisplayed(),
                "Регистрация завершилась неуспешно");
    }

    @Test
    @DisplayName("Проверка регистрации с невалидной почтой")
    public void invalidEmailRegistration() {
        registrationFormPage.inputName(NAME)
                            .inputSurname(SURNAME)
                            .inputPhoneNumber(PHONE_NUMBER)
                            .selectReadingHobbyCheckbox()
                            .inputUserName(USERNAME)
                            .inputEmail(INVALID_EMAIL)
                            .inputPassword(PASSWORD)
                            .inputRepeatPassword(PASSWORD)
                            .clickSubmitButton();

        assertEquals(EXPECTED_EMAIL_IVALID_MESSAGE, registrationFormPage.getInvalidEmailElement().getText(),
                "Отсутствует валидация на некорректный email некорректный текст ошибки");
    }

    @Test
    @DisplayName("Проверка регистрации с незаполненным required полем Хобби")
    public void missedHobbyRequiredCheckbox() {
        registrationFormPage.inputName(NAME)
                            .inputSurname(SURNAME)
                            .inputPhoneNumber(PHONE_NUMBER)
                            .inputUserName(USERNAME)
                            .inputEmail(VALID_EMAIL)
                            .inputPassword(PASSWORD)
                            .inputRepeatPassword(PASSWORD)
                            .clickSubmitButton();

        assertEquals(MISSING_REQUIRED_FIELD_MESSAGE, registrationFormPage.getMissedHobbyFieldElement().getText(),
                "Отсутствует валидация на заполнения обязательного поля Хобби");
    }

    @Test
    @DisplayName("Проверка регистрации с несовпадающими паролями")
    public void passwordMismatch() {
        registrationFormPage.inputName(NAME)
                            .inputSurname(SURNAME)
                            .inputPhoneNumber(PHONE_NUMBER)
                            .selectReadingHobbyCheckbox()
                            .inputUserName(USERNAME)
                            .inputEmail(VALID_EMAIL)
                            .inputPassword(PASSWORD)
                            .inputRepeatPassword(REPEATED_PASSWORD)
                            .clickSubmitButton();

        //Будет патать т.к отсутствует валидация на проверку совпадения паролей
        assertTrue(registrationFormPage.isErrorDisplayed(),
                "Отсутствует валидация на совпадение паролей!");
    }

    @Test
    @DisplayName("Проверка регистрации со всеми полями")
    public void allFieldsRegistration() {
        registrationFormPage.inputName(NAME)
                            .inputSurname(SURNAME)
                            .inputPhoneNumber(PHONE_NUMBER)
                            .selectReadingHobbyCheckbox()
                            .inputUserName(USERNAME)
                            .inputEmail(VALID_EMAIL)
                            .inputPassword(PASSWORD)
                            .inputRepeatPassword(PASSWORD)
                            .selectCountry(SELECTED_COUNTRY)
                            .selectSingleStatus()
                            .uploadPicture(PATH_TO_FILE)
                            .inputAboutMe("It's me")
                            .clickSubmitButton();

        assertFalse(registrationFormPage.isErrorDisplayed(),
                "Регистрация завершилась неуспешно");
    }
}
