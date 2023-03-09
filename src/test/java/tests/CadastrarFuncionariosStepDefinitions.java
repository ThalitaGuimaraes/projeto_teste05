package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Faker;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class CadastrarFuncionariosStepDefinitions {

	WebDriver driver;
	Faker faker = new Faker(new Locale("pt-BR"));
	
	@Dado("Acessar a página de cadastro de funcionários")
    public void passo1() {
    	
		System.setProperty("webdriver.chrome.driver", "c:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://testesoftware1-001-site1.etempurl.com/exercicios/tarefa03");
    }

	@E("Informar o nome do funcionário")
    public void passo2() {
    	
		String nome = faker.name().fullName();
		driver.findElement(By.xpath("//*[@id=\"Nome\"]")).sendKeys(nome);
    }
    
	@E("Informar o cpf do funcionário")
    public void passo3() {
    	
		String cpf = faker.number().digits(11);
		driver.findElement(By.xpath("//*[@id=\"Cpf\"]")).sendKeys(cpf);
    }
    
	@E("Informar a matricula do funcionário")
    public void passo4() {
    	
		String matricula = faker.number().digits(6);
		driver.findElement(By.xpath("//*[@id=\"Matricula\"]")).sendKeys(matricula);
    }
    
	@E("Informar a data de admissão do funcionário")
    public void passo5() {
    	
		String data = new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday());
		driver.findElement(By.xpath("//*[@id=\"DataAdmissao\"]")).sendKeys(data);
    }
    
    @E("Selecionar a empresa {string}")
    public void passo6(String empresa) {
    	
    	new Select(driver.findElement(By.xpath("//*[@id=\"Empresa\"]"))).selectByVisibleText(empresa);
    }
    
    @E("Selecionar o setor {string}")
    public void passo7(String setor) {
    	
    	new Select(driver.findElement(By.xpath("//*[@id=\"Setor\"]"))).selectByVisibleText(setor);
    }
    
    @Quando("Solicitar a realização do cadastro")
    public void passo8() {
    	
    	driver.findElement(By.xpath("//*[@id=\"btnCadastro\"]")).click();
    }
    
    @Então("Sistema realiza o cadastro com sucesso")
    public void passo9() {
    	
    	String mensagem = driver.findElement(By.xpath("//*[@id=\"mensagem\"]")).getText();
    	assertEquals(mensagem,"Funcionário cadastrado com sucesso.");
    	
        try {
        	
        	String numero = faker.number().digits(4);
			
			File arquivo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(arquivo, new File("Evidência - Cadastrar Funcionário_" + numero +".png"));	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		driver.close();
	}
}
