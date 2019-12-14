import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.test.web.hw.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WebTest {

    private FirefoxDriver driver;
    private WebDriverWait wait;

    private static final String LOGIN_URL = "http://localhost:8080/login";
    private static final int TIMEOUT = 3;

    @BeforeAll
    static void init() {
        WebDriverManager.firefoxdriver().setup();

        createUser();
        //createProject();
    }

    @BeforeEach
    void initDriver() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, TIMEOUT);
        driver.get(LOGIN_URL);
    }

    @AfterEach
    void close() {
        driver.close();
    }

    @Test
    void testNonEmpty() {
        final var issue = new Issue("Summary", "Description");
        loginAndCreateIssue(issue);
        final var issuePage = getIssuePage();
        assertEquals(issue.getSummary(), issuePage.getSummary());
        assertEquals(issue.getDescription(), issuePage.getDescription());
    }

    @Test
    void testEmptyDescription() {
        final var issue = new Issue("Summary", "");
        loginAndCreateIssue(issue);
        final var issuePage = getIssuePage();
        assertEquals(issue.getSummary(), issuePage.getSummary());
    }

    @Test
    void testEmptySummary() {
        final var issue = new Issue("", "Description.");
        login(getUser());
        createNewIssue();
        final var newIssuePage = PageFactory.initElements(driver, NewIssuePage.class);
        newIssuePage.createIssue(issue);

        assertTrue(newIssuePage.checkError(wait));
        assertEquals("Summary is required", newIssuePage.getError(wait));
    }

    @Test
    void testEmpty() {
        final var issue = new Issue("", "");
        login(getUser());
        createNewIssue();
        final var newIssuePage = PageFactory.initElements(driver, NewIssuePage.class);
        newIssuePage.createIssue(issue);

        assertTrue(newIssuePage.checkError(wait));
        assertEquals("Summary is required", newIssuePage.getError(wait));
    }

    @Test
    void testSpecialCharacters() {
        final var issue = new Issue(
                "~!@#$%^&*()_+{}|\":<>?, -=[]\\;',./`1234567890",
                "~!@#$%^&*()_+{}|\":<>?, -=[]\\;',./`1234567890"
        );
        loginAndCreateIssue(issue);
        final var issuePage = getIssuePage();
        assertEquals(issue.getSummary(), issuePage.getSummary());
        assertEquals(issue.getDescription(), issuePage.getDescription());
    }

    @Test
    void testMultiLine() {
        final var issue = new Issue(
                "This\n is\n single line\n summary.",
                "This\nis\nmultiline\ndescription."
        );
        loginAndCreateIssue(issue);
        final var issuePage = getIssuePage();
        assertEquals("This is single line summary.", issuePage.getSummary());
        assertEquals(issue.getDescription(), issuePage.getDescription());
    }

    @Test
    void testEnglishCharacters() {
        final var issue = new Issue(
                "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm",
                "MNBVCXZLKJHGFDSAPOIUYTREWQpoiuytrewqlkjhgfdsamnvcxz"
        );
        loginAndCreateIssue(issue);
        final var issuePage = getIssuePage();
        assertEquals(issue.getSummary(), issuePage.getSummary());
        assertEquals(issue.getDescription(), issuePage.getDescription());
    }

    @Test
    void testRussianCharacters() {
        final var issue = new Issue(
                "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэячсмитьбю",
                "ЪХЗЩШГНЕКУЦЙЭЖДЛОРПАВЫФЮБЬТИМСЧЯъхзщшгнекуцйэждлорпавыфюбьтимсчя"
        );
        loginAndCreateIssue(issue);
        final var issuePage = getIssuePage();
        assertEquals(issue.getSummary(), issuePage.getSummary());
        assertEquals(issue.getDescription(), issuePage.getDescription());
    }

    @Test
    void testLongSummary() {
        final var summary = "summary;".repeat(1000);
        final var issue = new Issue(summary, "Description");
        loginAndCreateIssue(issue);
        final var issuePage = getIssuePage();
        assertEquals(issue.getSummary(), issuePage.getSummary());
        assertEquals(issue.getDescription(), issuePage.getDescription());
    }

    @Test
    void testLongDescription() {
        final var description = "Description".repeat(500);
        final var issue = new Issue("Summary", description);
        loginAndCreateIssue(issue);
        final var issuePage = getIssuePage();
        assertEquals(issue.getSummary(), issuePage.getSummary());
        assertEquals(issue.getDescription(), issuePage.getDescription());
    }

    private void loginAndCreateIssue(Issue issue) {
        login(getUser());
        createNewIssue();
        createIssue(issue);
    }

    private void login(@NotNull User user) {
        final var loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(user);
    }

    private void createNewIssue() {
        final var dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        dashboardPage.createIssue(wait);
    }

    private void createIssue(@NotNull Issue issue) {
        final var newIssuePage = PageFactory.initElements(driver, NewIssuePage.class);
        newIssuePage.createIssue(issue);
    }

    private IssuePage getIssuePage() {
        final var dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        dashboardPage.toNewIssuePage(wait);
        return PageFactory.initElements(driver, IssuePage.class);
    }

    private static void createUser() {
        var driver = new FirefoxDriver();
        register(driver, getUser());
        driver.close();
    }

    private static void createProject() {
        var driver = new FirefoxDriver();
        createProject(driver);
        driver.close();
    }

    private static void register(@NotNull WebDriver driver, @NotNull User user) {
        driver.get(LOGIN_URL);

        final var loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.toRegisterPage();

        final var registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.register(user);
    }

    private static void createProject(@NotNull WebDriver driver) {
        driver.get(LOGIN_URL);

        final var loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(getRootUser());

        final var dashBoardPage = PageFactory.initElements(driver, DashboardPage.class);
        var wait = new WebDriverWait(driver, TIMEOUT);
        dashBoardPage.createIssueAndCreateProject(wait);

        final var createProjectPage = PageFactory.initElements(driver, CreateProjectPage.class);
        createProjectPage.createProject(wait, getProject());
    }

    @Contract(value = " -> new", pure = true)
    @NotNull
    private static User getUser() {
        return new User("User Userov", "user@mail.ru", "user", "user123");
    }

    @Contract(value = " -> new", pure = true)
    @NotNull
    private static User getRootUser() {
        return new User("Root", "", "root", "root123");
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    private static Project getProject() {
        return new Project("TestProject", "id", "root");
    }
}
