package ru.test.web.hw;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    @FindBy(xpath = "/html/body/div[1]/div[1]/div/a[2]/span")
    private WebElement issues;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div/a[5]/span")
    private WebElement createIssue;

    @FindBy(xpath = "/html/body/div[4]/table/tbody/tr/td[2]/ul/li/a")
    private WebElement createProject;

    @FindBy(xpath = "/html/body/div[4]/table/tbody/tr/td[2]/ul/li/a")
    private WebElement newIssue;

    public void createIssue(@NotNull WebDriverWait wait) {
        issues.click();

        wait.until(x -> createIssue.isDisplayed());
        createIssue.click();
    }

    public void createIssueAndCreateProject(@NotNull WebDriverWait wait) {
        createIssue.click();

        wait.until(x -> createProject.isDisplayed());
        createProject.click();
    }

    public void toNewIssuePage(@NotNull WebDriverWait wait) {
        wait.until(x -> newIssue.isDisplayed());
        newIssue.click();
    }

}
