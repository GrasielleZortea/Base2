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

import common.Arquivo;

public class ReportarIssues {

	public ReportarIssues(WebDriver navegador) {
		nav = navegador;
	}

	private static WebDriver nav;
	By reportIssues = By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[3]");
	By confirma = By.className("button");
	By camposObrigmsg = By.xpath("/html/body/div[2]/table/tbody/tr[2]/td/p");
	By categoria = By.name("category_id");
	By catBase = By.xpath("/html/body/div[3]/form/table/tbody/tr[2]/td[2]/select/option[2]");
	By resumo = By.name("summary");
	By descricao = By.name("description");
	By passos = By.name("steps_to_reproduce");
	By upload = By.name("ufile[]");
	By visualizarIssues = By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[2]");
	By editar = By.xpath("//*[@id=\"buglist\"]/tbody/tr[4]/td[2]/a");
	By nota = By.name("bugnote_text");
	By irNota = By.xpath("/html/body/table[3]/tbody/tr[1]/td[1]/span/span[1]/a");
	By checkDel = By.name("bug_arr[]");
	By acoes = By.name("action");
	By deletar = By.xpath("//*[@id=\"buglist\"]/tbody/tr[5]/td/span[1]/select/option[5]");
	By excluirSucesso = By.xpath("//*[@id=\"buglist\"]/tbody/tr[1]/td/span[1]");
	By fecharIssues = By.xpath("//*[@id=\"buglist\"]/tbody/tr[5]/td/span[1]/select/option[4]");
	By filtro = By.name("source_query_id");
	By todosFiltro = By.xpath("//*[@id=\"filters_form_open\"]/table/tbody/tr[11]/td[3]/form[1]/select/option[4]");

	public void CamposObrigatorios() throws InterruptedException, AWTException, IOException {
		// Verificacao de Campos Obrigatorios

		nav.findElement(reportIssues).click();
		nav.findElement(confirma).click();
		Thread.sleep(3000);
		String autentifica = nav.findElement(camposObrigmsg).getText();
		assertEquals("A necessary field \"Summary\" was empty. Please recheck your inputs.", autentifica);
		capturaTela();
	}

	public void CriarIssues() throws InterruptedException, AWTException, IOException {
		nav.findElement(reportIssues).click();
		nav.findElement(categoria).click();
		nav.findElement(catBase).click();
		nav.findElement(resumo).sendKeys("Falta opcao no item Resolucao");
		nav.findElement(descricao).sendKeys("No item Resolucao do Mantis, nao existe a opcao Resolvido");
		nav.findElement(passos).sendKeys(
				"Criar uma Issues:" + "\n" + "Clicar no item Resolucao" + "\n" + "Procurar a opcao Resolvido");
		nav.findElement(upload).click();
		Arquivo evidencias = new Arquivo();
		evidencias.ArquivoTela("mantis.jpg");
		nav.findElement(confirma).click();
		Thread.sleep(3000);
		capturaTela();
	}

	public void EditarIssues() throws InterruptedException, AWTException, IOException {
		nav.findElement(visualizarIssues).click();
		nav.findElement(editar).click();
		Thread.sleep(3000);
		nav.findElement(nota).sendKeys("Criacao da Nota Adicional - Processo Seletivo");
		nav.findElement(confirma).click();
		Thread.sleep(3000);
		nav.findElement(irNota).click();
		capturaTela();
	}

	public void DeletarIssues() throws InterruptedException {

		nav.findElement(visualizarIssues).click();
		nav.findElement(filtro).click();
		nav.findElement(todosFiltro).click();
		Thread.sleep(1000);
		nav.findElement(checkDel).click();
		Thread.sleep(3000);
		nav.findElement(acoes).click();
		Thread.sleep(3000);
		nav.findElement(deletar).click();
		Thread.sleep(3000);
		nav.findElement(confirma).click();
		Thread.sleep(3000);
		nav.findElement(confirma).click();
		Thread.sleep(3000);
		String autentifica = nav.findElement(excluirSucesso).getText();
		assertEquals("Viewing Issues (0 - 0 / 0)", autentifica);
		Thread.sleep(3000);
	}

	public void FecharIssue() throws InterruptedException, AWTException, IOException {
		nav.findElement(visualizarIssues).click();
		nav.findElement(checkDel).click();
		nav.findElement(acoes).click();
		nav.findElement(fecharIssues).click();
		nav.findElement(confirma).click();
		nav.findElement(nota).sendKeys("Demanda testada e resolvida com sucesso");
		nav.findElement(confirma).click();
		Thread.sleep(1000);
		nav.findElement(filtro).click();
		nav.findElement(todosFiltro).click();
		Thread.sleep(1000);
		capturaTela();
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
