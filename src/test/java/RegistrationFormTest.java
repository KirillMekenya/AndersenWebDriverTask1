import elements.RegistrationFormElements;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationFormTest {

    private RegistrationFormElements registrationFormElements;

    WebElement header;
    WebElement nameInputField;
    WebElement surnameInputField;
    WebElement phoneInputField;
    WebElement hobbyCheckbox;
    WebElement usernameInputField;
    WebElement emailInputField;
    WebElement submitButton;
    WebElement passwordInputField;
    WebElement repeatPasswordInputField;
    WebElement dropDownListBoxCountry;
    Select selectCountry;
    WebElement statusRadioBtn;
    WebElement uploadPicture;
    WebElement aboutInputField;
    WebDriver driver;

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

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void initElements() {
        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        driver.navigate().refresh();
        registrationFormElements = new RegistrationFormElements();
        header = driver.findElement(registrationFormElements.getHeaderLocator());
        nameInputField = driver.findElement(registrationFormElements.getNameInputLocator());
        surnameInputField = driver.findElement(registrationFormElements.getSurnameInputLocator());
        phoneInputField = driver.findElement(registrationFormElements.getPhoneInputLocator());
        hobbyCheckbox = driver.findElement(registrationFormElements.getHobbyCheckboxLocator());
        usernameInputField = driver.findElement(registrationFormElements.getUsernameLocator());
        emailInputField = driver.findElement(registrationFormElements.getEmailLocator());
        submitButton = driver.findElement(registrationFormElements.getSubmitButtonLocator());
        passwordInputField = driver.findElement(registrationFormElements.getPasswordLocator());
        repeatPasswordInputField = driver.findElement(registrationFormElements.getRepeatPasswordLocator());
        dropDownListBoxCountry = driver.findElement(registrationFormElements.getCountrySelector());
        selectCountry = new Select(dropDownListBoxCountry);
        statusRadioBtn = driver.findElement(registrationFormElements.getStatusCheckboxLocator());
        uploadPicture = driver.findElement(registrationFormElements.getUploadPictureLocator());
        aboutInputField = driver.findElement(registrationFormElements.getAboutYourselfLocator());
    }

    @AfterAll
    public void finish() {
        driver.close();
    }

    @Test
    @Order(1)
    @DisplayName("Проверка открытия формы регистрации")
    public void openingRegistrationPage() {
        WebElement header = driver.findElement(registrationFormElements.getHeaderLocator());
        assertEquals(EXPECTED_HEADER, header.getText(), "Страница регистрации не открыта или имеет некорректный заголовок");
    }

    @Test
    @DisplayName("Проверка регистрации только с обязательными параметрамий")
    public void successRequiredFieldsRegistration() {
        nameInputField.click();
        nameInputField.sendKeys(NAME);
        surnameInputField.click();
        surnameInputField.sendKeys(SURNAME);
        phoneInputField.click();
        phoneInputField.sendKeys(PHONE_NUMBER);
        hobbyCheckbox.click();
        usernameInputField.click();
        usernameInputField.sendKeys(USERNAME);
        emailInputField.click();
        emailInputField.sendKeys(VALID_EMAIL);
        passwordInputField.click();
        passwordInputField.sendKeys(PASSWORD);
        repeatPasswordInputField.click();
        repeatPasswordInputField.sendKeys(PASSWORD);
        submitButton.click();

        assertEquals(0, driver.findElements(registrationFormElements.getErrorElementLocator()).size(),
                "Регистрация завершилась неуспешно");
    }

    @Test
    @DisplayName("Проверка регистрации с невалидной почтой")
    public void invalidEmailRegistration() {
        nameInputField.click();
        nameInputField.sendKeys(NAME);
        surnameInputField.click();
        surnameInputField.sendKeys(SURNAME);
        phoneInputField.click();
        phoneInputField.sendKeys(PHONE_NUMBER);
        hobbyCheckbox.click();
        usernameInputField.click();
        usernameInputField.sendKeys(USERNAME);
        emailInputField.click();
        emailInputField.sendKeys(INVALID_EMAIL);
        passwordInputField.click();
        passwordInputField.sendKeys(PASSWORD);
        repeatPasswordInputField.click();
        repeatPasswordInputField.sendKeys(PASSWORD);
        submitButton.click();

        assertEquals(EXPECTED_EMAIL_IVALID_MESSAGE, driver.findElement(registrationFormElements.getInvalidEmailLocator()).getText(),
                "Отсутствует валидация на некорректный email некорректный текст ошибки");
    }

    @Test
    @DisplayName("Проверка регистрации с незаполненным required полем Хобби")
    public void missedHobbyRequiredCheckbox() {
        nameInputField.click();
        nameInputField.sendKeys(NAME);
        surnameInputField.click();
        surnameInputField.sendKeys(SURNAME);
        phoneInputField.click();
        phoneInputField.sendKeys(PHONE_NUMBER);
        usernameInputField.click();
        usernameInputField.sendKeys(USERNAME);
        emailInputField.click();
        emailInputField.sendKeys(VALID_EMAIL);
        passwordInputField.click();
        passwordInputField.sendKeys(PASSWORD);
        repeatPasswordInputField.click();
        repeatPasswordInputField.sendKeys(PASSWORD);
        submitButton.click();

        assertEquals(MISSING_REQUIRED_FIELD_MESSAGE, driver.findElement(registrationFormElements.getMissedHobbyFieldLocator()).getText(),
                "Отсутствует валидация на заполнения обязательного поля Хобби");
    }

    @Test
    @DisplayName("Проверка регистрации с несовпадающими паролями")
    public void passwordMismatch() {
        nameInputField.click();
        nameInputField.sendKeys(NAME);
        surnameInputField.click();
        surnameInputField.sendKeys(SURNAME);
        phoneInputField.click();
        phoneInputField.sendKeys(PHONE_NUMBER);
        hobbyCheckbox.click();
        usernameInputField.click();
        usernameInputField.sendKeys(USERNAME);
        emailInputField.click();
        emailInputField.sendKeys(VALID_EMAIL);
        passwordInputField.click();
        passwordInputField.sendKeys(PASSWORD);
        repeatPasswordInputField.click();
        repeatPasswordInputField.sendKeys(REPEATED_PASSWORD);
        submitButton.click();

        //Будет патать т.к отсутствует валидация на проверку совпадения паролей
        assertNotEquals(0, driver.findElements(registrationFormElements.getErrorElementLocator()).size(),
                "Отсутствует валидация на совпадение паролей!");
    }

    @Test
    @DisplayName("Проверка регистрации со всеми полями")
    public void allFieldsRegistration() {
        nameInputField.click();
        nameInputField.sendKeys(NAME);
        surnameInputField.click();
        surnameInputField.sendKeys(SURNAME);
        phoneInputField.click();
        phoneInputField.sendKeys(PHONE_NUMBER);
        hobbyCheckbox.click();
        selectCountry.getWrappedElement().click();
        selectCountry.selectByVisibleText(SELECTED_COUNTRY);
        usernameInputField.click();
        usernameInputField.sendKeys(USERNAME);
        emailInputField.click();
        emailInputField.sendKeys(VALID_EMAIL);
        statusRadioBtn.click();
        uploadPicture.sendKeys("/Users/user/IdeaProjects/selenium_hometask/src/main/resources/images.jpeg");
        aboutInputField.sendKeys("It's me");
        passwordInputField.click();
        passwordInputField.sendKeys(PASSWORD);
        repeatPasswordInputField.click();
        repeatPasswordInputField.sendKeys(REPEATED_PASSWORD);
        submitButton.click();

        assertFalse(driver.findElements(registrationFormElements.getErrorElementLocator()).size() > 0,
                "Регистрация завершилась неуспешно");
    }
}
