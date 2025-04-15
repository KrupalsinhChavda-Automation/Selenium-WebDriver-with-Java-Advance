package testcases;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.print.PrintOptions;



public class TestPrintPagePDF {

	public static void main(String[] args) throws IOException {
		
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_frames2");
		
		Pdf pdf = ((PrintsPage) driver).print(new PrintOptions());
		
		Files.write(Paths.get("./Selenium.pdf"), OutputType.BYTES.convertFromBase64Png(pdf.getContent()));
		

	}

}
