package Teste;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PageMantis.Login;
import PageMantis.ReportarIssues;;

public class MantisChrome {
	private WebDriver nav;

	private static Login login;
	private static ReportarIssues reportarIssues;

	@Before
	public void setUpBeforeClass() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drive\\chromedriver.exe");
		nav = new ChromeDriver();
		nav.manage().window().maximize();
		nav.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		nav.get("http://mantis-prova.base2.com.br/login_page.php");
		login = new Login(nav);
		reportarIssues = new ReportarIssues(nav);
	}

	@After
	public void tearDown() throws Exception {
		nav.quit();
	}

	@Test
	public void testLogin() throws Exception {

		login.LoginSenhaInvalida();
		login.LoginCamposNaoPreenchidos();
		login.LoginComSucesso();
		login.Logout();
	}

	@Test
	public void testIssues() throws Exception {
		login.LoginComSucesso();
		reportarIssues.CamposObrigatorios();
		reportarIssues.CriarIssues();
		reportarIssues.EditarIssues();
		reportarIssues.FecharIssue();
		reportarIssues.DeletarIssues();
		login.Logout();
	}

}
