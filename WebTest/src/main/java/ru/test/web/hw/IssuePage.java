package ru.test.web.hw;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IssuePage {

    @FindBy(id = "id_l.I.ic.icr.it.issSum")
    private WebElement summary;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div")
    private WebElement description;

    public String getSummary() {
        return summary.getText();
    }

    public String getDescription() {
        return description.getText();
    }
}
