package ru.test.web.hw;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(id = "id_l.L.registerLink")
    private WebElement registration;

    @FindBy(id = "id_l.L.login")
    private WebElement login;

    @FindBy(id = "id_l.L.password")
    private WebElement password;

    @FindBy(id = "id_l.L.loginButton")
    private WebElement loginButton;

    public void toRegisterPage() {
        registration.click();
    }

    public void login(@NotNull User user) {
        login.sendKeys(user.getLogin());
        password.sendKeys(user.getPassword());

        loginButton.click();
    }
}
