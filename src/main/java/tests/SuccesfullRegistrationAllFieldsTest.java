package tests;

import elements.RegistrationFormElements;
import exceptions.ValueDoesNotMatch;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SuccesfullRegistrationAllFieldsTest {
    public static void main(String[] args) throws ValueDoesNotMatch, InterruptedException {
        String expectedHeader = "Registration Form";


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");

        RegistrationFormElements registrationFormElements = new RegistrationFormElements();

        WebElement header = driver.findElement(registrationFormElements.getHeaderLocator());
        WebElement nameInputField = driver.findElement(registrationFormElements.getNameInputLocator());
        WebElement surnameInputField = driver.findElement(registrationFormElements.getSurnameInputLocator());
        WebElement phoneInputField = driver.findElement(registrationFormElements.getPhoneInputLocator());
        WebElement hobbyCheckbox = driver.findElement(registrationFormElements.getHobbyCheckboxLocator());
        WebElement usernameInputField = driver.findElement(registrationFormElements.getUsernameLocator());
        WebElement emailInputField = driver.findElement(registrationFormElements.getEmailLocator());
        WebElement submitButton = driver.findElement(registrationFormElements.getSubmitButtonLocator());
        WebElement passwordInputField = driver.findElement(registrationFormElements.getPasswordLocator());
        WebElement repeatPasswordInputField = driver.findElement(registrationFormElements.getRepeatPasswordLocator());
        WebElement dropDownListBoxCountry = driver.findElement(registrationFormElements.getCountrySelector());
        WebElement statusRadioBtn = driver.findElement(registrationFormElements.getStatusCheckboxLocator());
        WebElement uploadPicture = driver.findElement(registrationFormElements.getUploadPictureLocator());
        WebElement aboutInputField = driver.findElement(registrationFormElements.getAboutYourselfLocator());


        try {
            if (!header.getText().equals(expectedHeader)) {
                throw new ValueDoesNotMatch("Заголовок " + header.getText() +
                        " не соотвествует названию " + expectedHeader);
            }

            nameInputField.click();
            nameInputField.sendKeys("Kirill");
            surnameInputField.click();
            surnameInputField.sendKeys("Mekenya");
            phoneInputField.click();
            phoneInputField.sendKeys("+4827989879");
            hobbyCheckbox.click();
            Select selectCountry = new Select(dropDownListBoxCountry);
            selectCountry.getWrappedElement().click();
            selectCountry.selectByVisibleText("India");
            usernameInputField.click();
            usernameInputField.sendKeys("qa_user");
            statusRadioBtn.click();
            uploadPicture.sendKeys("/Users/user/IdeaProjects/selenium_hometask/src/main/resources/images.jpeg");
            aboutInputField.sendKeys("It's me");
            emailInputField.click();
            emailInputField.sendKeys("qauser@mail.ru");
            passwordInputField.click();
            passwordInputField.sendKeys("1234");
            repeatPasswordInputField.click();
            repeatPasswordInputField.sendKeys("1234");

            submitButton.click();
            if (driver.findElements(registrationFormElements.getErrorElementLocator()).size() > 0) {
                throw new ValueDoesNotMatch("Регистрация завершилась неуспешно");
            }
        } finally {
            driver.close();
        }
    }
}
