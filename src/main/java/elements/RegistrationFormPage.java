package elements;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

@Getter
public class RegistrationFormPage {

    private final WebDriver driver;

    @FindBy(xpath = "//h2")
    private WebElement header;

    @FindBy(name = "name")
    private WebElement nameInputField;

    @FindBy(xpath = "//form[@id='register_form']/fieldset/p[2]/input")
    private WebElement surnameInputField;

    @FindBy(name = "phone")
    private WebElement phoneInputField;

    @FindBy(xpath = "//label[contains(text(),'Reading')]")
    private WebElement hobbyCheckbox;

    @FindBy(xpath = "//label[text()=' Single']")
    private WebElement statusRadioBtn;

    @FindBy(name = "username")
    private WebElement usernameInputField;

    @FindBy(name = "email")
    private WebElement emailInputField;

    @FindBy(name = "password")
    private WebElement passwordInputField;

    @FindBy(name = "c_password")
    private WebElement repeatPasswordInputField;

    @FindBy(xpath = "//input[@value='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//label[@class='error_p']")
    private WebElement errorElement;

    @FindBy(css = "fieldset:nth-child(4) > select")
    private WebElement dropDownListBoxCountry;

    @FindBy(xpath = "//textarea[@rows='5']")
    private WebElement aboutInputField;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement uploadPicture;

    @FindBy(css = "fieldset:nth-child(4) > select")
    private WebElement dateOfBirthSelector;

    @FindBy(css = ".time_feild:nth-child(2) > select")
    private WebElement monthOfBirthSelector;

    @FindBy(css = ".time_feild:nth-child(3) > select")
    private WebElement dayOfBirthSelector;

    @FindBy(css = ".time_feild:nth-child(4) > select")
    private WebElement yearOfBirthSelector;

    @FindBy(css = ".error_p:nth-child(3)")
    private WebElement invalidEmailElement;

    @FindBy(css = ".error_p:nth-child(2)")
    private WebElement missedHobbyFieldElement;

    public RegistrationFormPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввести имя в поле Name")
    public RegistrationFormPage inputName(String name) {
        nameInputField.click();
        nameInputField.sendKeys(name);
        return this;
    }

    @Step("Выбрать страну {country} в выпадающем списке")
    public RegistrationFormPage selectCountry(String country) {
        Select select = new Select(dropDownListBoxCountry);
        select.getWrappedElement().click();
        select.selectByVisibleText(country);
        return this;
    }

    @Step("Отметить чекбокс Single")
    public RegistrationFormPage selectSingleStatus() {
        statusRadioBtn.click();
        return this;
    }

    @Step("Прикрепить файл {path}")
    public RegistrationFormPage uploadPicture(String path) {
        uploadPicture.sendKeys(path);
        return this;
    }

    @Step("Ввести информацию {info} в поле About me")
    public RegistrationFormPage inputAboutMe(String info) {
        aboutInputField.click();
        aboutInputField.sendKeys(info);
        return this;
    }

    @Step("Ввести имя пользователя {username} в поле user name")
    public RegistrationFormPage inputUserName(String username) {
        usernameInputField.click();
        usernameInputField.sendKeys(username);
        return this;
    }

    @Step("Ввести фамилию {surname} в поле Surname")
    public RegistrationFormPage inputSurname(String surname) {
        surnameInputField.click();
        surnameInputField.sendKeys(surname);
        return this;
    }

    @Step("Ввести номер телефона {phoneNumber} в поле Phone number")
    public RegistrationFormPage inputPhoneNumber(String phoneNumber) {
        phoneInputField.click();
        phoneInputField.sendKeys(phoneNumber);
        return this;
    }

    @Step("Ввести имейл {email} в поле Email")
    public RegistrationFormPage inputEmail(String email) {
        emailInputField.click();
        emailInputField.sendKeys(email);
        return this;
    }

    @Step("Ввести пароль {password} в поле Password")
    public RegistrationFormPage inputPassword(String password) {
        passwordInputField.click();
        passwordInputField.sendKeys(password);
        return this;
    }

    @Step("Ввести пароль {password} в поле Repeat password")
    public RegistrationFormPage inputRepeatPassword(String password) {
        repeatPasswordInputField.click();
        repeatPasswordInputField.sendKeys(password);
        return this;
    }

    @Step("Выбрать радиобаттон Reading в хобби")
    public RegistrationFormPage selectReadingHobbyCheckbox () {
        hobbyCheckbox.click();
        return this;
    }

    @Step("Нажать кнопку Submit")
    public void clickSubmitButton() {
        submitButton.click();
    }

    public boolean isErrorDisplayed() {
        try {
            errorElement.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
