package ru.test.web.hw;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProjectPage {

    @FindBy(id = "id_l.C.EditProjectMain.projectName")
    private WebElement name;

    @FindBy(id = "id_l.C.EditProjectMain.shortName")
    private WebElement id;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[4]/div/div/input")
    private WebElement lead;

    @FindBy(id = "id_l.C.EditProjectMain.saveProject")
    private WebElement save;

    @FindBy(xpath = "/html/body/div[2]/ul/li[2]")
    private WebElement root;

    public void createProject(@NotNull WebDriverWait wait, @NotNull Project project) {
        name.sendKeys(project.getName());
        id.sendKeys(project.getId());

        lead.clear();
        lead.sendKeys(project.getLead());
        wait.until(x -> root.isDisplayed());
        root.click();

        save.click();
    }

}
