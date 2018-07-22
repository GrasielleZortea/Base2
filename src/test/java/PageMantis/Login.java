package PageMantis;

import static org.junit.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

	public Login(WebDriver navegador) {
		nav = navegador;
	}

	private static WebDriver nav;
	By nome = By.name("username");
	By senha = By.name("password");
	By entrar = By.className("button");
	By sucesso = By.xpath("/html/body/table[1]/tbody/tr/td[1]/span[1]");
	By falha = By.xpath("/html/body/div[2]/font");
	By logout = By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[9]");

	public void LoginComSucesso() throws InterruptedException, AWTException, IOException {
		// Verificacao de Login valido
		nav.findElement(nome).clear();
		nav.findElement(nome).sendKeys("grasielle.zortea");
		nav.findElement(senha).clear();
		nav.findElement(senha).sendKeys("gra3101");
		nav.findElement(entrar).click();
		Thread.sleep(3000);
		capturaTela();
		String autentifica = nav.findElement(sucesso).getText();
		assertEquals("grasielle.zortea", autentifica);
	}

	public void LoginSenhaInvalida() throws InterruptedException, AWTException, IOException {
		// Verificacao de login com senha invalida
		nav.findElement(nome).clear();
		nav.findElement(nome).sendKeys("grasielle.zortea");
		nav.findElement(senha).clear();
		nav.findElement(senha).sendKeys("123456789");
		nav.findElement(entrar).click();
		capturaTela();
		Thread.sleep(3000);
		String autentifica = nav.findElement(falha).getText();
		assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.",
				autentifica);
	}

	public void LoginCamposNaoPreenchidos() throws InterruptedException, AWTException, IOException {
		// Verificacao de login com campos nao preenchidos
		nav.findElement(nome).clear();
		nav.findElement(senha).clear();
		nav.findElement(entrar).click();
		capturaTela();
		String autentifica = nav.findElement(falha).getText();
		assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.",
				autentifica);
	}

	public void Logout() {
		nav.findElement(logout).click();
	}

	public void capturaTela() throws InterruptedException, AWTException, IOException {

		Robot robot = new Robot();
		// Captura a tela dentro do retangulo
		BufferedImage imagem = robot.createScreenCapture(new java.awt.Rectangle(1600, 875));
		// Salva a imagem dentro da pasta Evidencias do Resource. O nome e a hora da
		// imagem.
		ImageIO.write(imagem, "jpg",
				new File("src\\main\\resources\\evidencias\\" + System.currentTimeMillis() + ".jpg"));
	}

}
