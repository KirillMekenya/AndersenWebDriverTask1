package elements;

import lombok.Getter;
import org.openqa.selenium.By;
@Getter
public class RegistrationFormElements {

    private final By headerLocator = By.xpath("//h2");
    private final By nameInputLocator = By.name("name");
    private final By surnameInputLocator = By.xpath("//form[@id='register_form']/fieldset/p[2]/input");
    private final By phoneInputLocator = By.name("phone");
    private final By hobbyCheckboxLocator = By.xpath("//label[contains(text(),'Reading')]");
    private final By statusCheckboxLocator = By.xpath("//label[contains(text(),'Single')]");
    private final By usernameLocator = By.name("username");
    private final By emailLocator = By.name("email");
    private final By passwordLocator = By.name("password");
    private final By repeatPasswordLocator = By.name("c_password");
    private final By submitButtonLocator = By.xpath("//input[@value='submit']");
    private final By errorElementLocator = By.xpath("//label[@class='error_p']");
    private final By countrySelector = By.cssSelector("fieldset:nth-child(4) > select");
    private final By aboutYourselfLocator = By.xpath("//textarea[@rows='5']");
    private final By uploadPictureLocator = By.xpath("//input[@type='file']");
    private final By dateOfBirthSelector = By.cssSelector("fieldset:nth-child(4) > select");
    private final By monthOfBirthSelector = By.cssSelector(".time_feild:nth-child(2) > select");
    private final By dayOfBirthSelector = By.cssSelector(".time_feild:nth-child(3) > select");
    private final By yearOfBirthSelector = By.cssSelector(".time_feild:nth-child(4) > select");
    private final By invalidEmailLocator = By.cssSelector(".error_p:nth-child(3)");
    private final By missedHobbyFieldLocator = By.cssSelector(".error_p:nth-child(2)");


}
