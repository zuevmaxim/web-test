package ru.test.web.hw;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage {

    @FindBy(id = "id_l.R.user_fullName")
    private WebElement fullName;

    @FindBy(id = "id_l.R.user_email")
    private WebElement email;

    @FindBy(id = "id_l.R.user_login")
    private WebElement login;

    @FindBy(id = "id_l.R.password")
    private WebElement password;

    @FindBy(id = "id_l.R.confirmPassword")
    private WebElement confirmPassword;

    @FindBy(id = "id_l.R.register")
    private WebElement register;

    public void register(@NotNull User user) {
        fullName.sendKeys(user.getName());
        email.sendKeys(user.getEmail());
        login.sendKeys(user.getLogin());
        password.sendKeys(user.getPassword());
        confirmPassword.sendKeys(user.getPassword());

        register.click();
    }
}
