package common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class Arquivo {
private Robot robot;
private File f;

public void ArquivoTela(String fileRefPath) throws AWTException, InterruptedException {

		robot = new Robot();
		robot.setAutoDelay(1000);
		File f = new File(fileRefPath);
		StringSelection strAnx = new StringSelection(f.getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strAnx, null);
		Thread.sleep(2000);
		robot.setAutoDelay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.setAutoDelay(2000);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.setAutoDelay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
	}
}