package ru.test.web.hw;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewIssuePage {

    @FindBy(id = "id_l.I.ni.ei.eit.summary")
    private WebElement summary;

    @FindBy(id = "id_l.I.ni.ei.eit.description")
    private WebElement description;

    @FindBy(id = "id_l.I.ni.ei.submitButton_74_0")
    private WebElement createIssue;

    @FindBy(xpath = "/html/body/div[5]/table/tbody/tr/td[2]/ul/li[2]")
    private WebElement emptySummaryError;

    public void createIssue(@NotNull Issue issue) {
        summary.clear();
        summary.sendKeys(issue.getSummary());
        description.clear();
        description.sendKeys(issue.getDescription());
        createIssue.click();
    }

    public boolean checkError(@NotNull WebDriverWait wait) {
        wait.until(x -> emptySummaryError.isDisplayed());
        return emptySummaryError.isDisplayed();
    }

    public String getError(@NotNull WebDriverWait wait) {
        wait.until(x -> emptySummaryError.isDisplayed());
        return emptySummaryError.getText();
    }
}
