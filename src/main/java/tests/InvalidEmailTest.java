package tests;

import elements.RegistrationFormElements;
import exceptions.ValueDoesNotMatch;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InvalidEmailTest {
    public static void main(String[] args) throws ValueDoesNotMatch {
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
            usernameInputField.click();
            usernameInputField.sendKeys("qa_user");
            emailInputField.click();
            emailInputField.sendKeys("qwerty");
            passwordInputField.click();
            passwordInputField.sendKeys("1234");
            repeatPasswordInputField.click();
            repeatPasswordInputField.sendKeys("1234");
            submitButton.click();
            if(!driver.findElement(registrationFormElements.getInvalidEmailLocator()).getText().equals("Please enter a valid email address.")) {
                throw new ValueDoesNotMatch("Отсутствует валидация на некорректный email. Текст валидации не совпадает с Please enter a valid email address.");
            }
        } finally {
            driver.close();
        }
    }
}
