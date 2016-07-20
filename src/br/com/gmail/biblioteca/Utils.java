package br.com.gmail.biblioteca;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.gmail.selenium.Selenium;

/**
 * Classe com as bibliotecas para as classes de p�gina e classes de testes
 * @author btineli
 */
public abstract class Utils {	
	
	private static final WebDriver driver;
	private static final WebDriverWait wait;
	
	static{

		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 10);
	
	}
	
	/*
	 * M�todo para aguardar at� o locator estar visivel
	 */
	public static void isVisible(By locator) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		System.out.println(">Elemento visualizado: " + locator.toString());
	}
	
	
	public static void isVisible(String id) {
		
		isVisible(By.id(id));
	
	}
	
	/*
	 * M�todo para aguardar at� o locator ser localizado
	 */
	public static void isLocated(By locator) {
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		System.out.println(">Elemento localizado: " + locator.toString());
	
	}
	
	public static void isLocated(String id) {
		
		isLocated(By.id(id));
	
	}
	
	/*
	 * M�todo para aguardar at� o elemento estar clicavel
	 */
	public static void isClickable(By locator) {
	
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		
		System.out.println(">>> Elemento clic�vel: " + locator.toString());
	
	}
	
	public static void isClickable(String id) {
	
		isClickable(By.id(id));
	
	}
	
	/*
	 * M�todo para clicar em um elemento na tela via JS
	 */
	public static void clickJS(By locator) {
	
		isLocated(locator);
		
		WebElement element = driver.findElement(locator);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", element);
		
		System.out.println(">JS - Click: " + locator.toString());
	
	}
	
	/*
	 * M�todo para preencher um elemento na tela pelo ID via JS
	 */
	public static void jsSendKeysById(By locator1, String locator2, String text) {
	
		isLocated(locator1);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.getElementById('" + locator2 + "')[0].value = '" + text + "';");
		
		System.out.println(">>> JS - SendKeys by id: " + locator2);
	
	}
	
	/*
	 * M�todo para preencher um elemento na tela pelo NAME via JS
	 */
	public static void jsSendKeysByName(By locator1, String locator2, String text) {
	
		isLocated(locator1);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.getElementsByName('" + locator2 + "')[0].value = '" + text + "'");
			
		System.out.println(">>> JS - SendKeys by name: " + locator2);
	
	}
	
	/*
	 * M�todo para aguardar um quantia de milisegundos. 1000 milisegundos = 1 segundo
	 */
	public static void wait(final int iTimeInMillis) {
	
		try {
			System.out.println(">>> Waiting...");
			Thread.sleep(iTimeInMillis);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	
	}
	
	/*
	 * M�todo para alternar para um frame do mesmo n�vel ou um n�vel abaixo
	 */
	public static void switchFrame(String frame) {
	
		By frameElement = By.name(frame);
		isLocated(frameElement);
		driver.switchTo().frame(frame);
		
		System.out.println(">>> Frame " + frame + " localizado");
	
	}
	
	/*
	 * M�todo para capturar e salvar em arquivo o printscreen da tela
	 */
	public static void takeScreenshot(String fileName){

		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	    try {
			FileUtils.copyFile(scrFile, new File(new File("../").getAbsolutePath() + "\\Evid�ncias\\" + fileName.toString() + "_" + timeStamp + ".png"),true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	/*
	 * M�todo para deslocar a barra de rolagem
	 */
	public static void setScrollBar(By locator) {

		WebElement element = driver.findElement(locator);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);

	}

}
